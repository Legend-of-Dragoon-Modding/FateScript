package org.legendofdragoon.fatescript.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.legendofdragoon.fatescript.psi.FateScriptLabelHeader;
import org.legendofdragoon.fatescript.psi.FateScriptLabelHeader;
import org.legendofdragoon.fatescript.psi.FateScriptLabelRef;
import org.legendofdragoon.fatescript.psi.FateScriptTypes;

import java.util.Objects;

public final class FateScriptPsiImplUtil {
  private FateScriptPsiImplUtil() {
  }

  public static @NotNull String getName(final FateScriptLabelHeader element) {
    return Objects.requireNonNull(element.getNameIdentifier()).getText();
  }

  public static PsiElement setName(final FateScriptLabelHeader element, final String newName) {
    Objects.requireNonNull(element.getNameIdentifier()).replace(FateScriptElementFactory.createLabel(element.getProject(), newName));
    return element;
  }

  public static PsiElement getNameIdentifier(final FateScriptLabelHeader element) {
    final ASTNode labelNode = element.getNode().findChildByType(FateScriptTypes.LABEL);
    return labelNode != null ? labelNode.getPsi() : null;
  }

  public static @NotNull PsiReference getReference(final FateScriptLabelRef element) {
    return new LabelReference(element, new TextRange(1, element.getTextLength()));
  }
}