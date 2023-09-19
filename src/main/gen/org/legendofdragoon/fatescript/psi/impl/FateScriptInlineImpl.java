// This is a generated file. Not intended for manual editing.
package org.legendofdragoon.fatescript.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.legendofdragoon.fatescript.psi.FateScriptTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.legendofdragoon.fatescript.psi.*;

public class FateScriptInlineImpl extends ASTWrapperPsiElement implements FateScriptInline {

  public FateScriptInlineImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FateScriptVisitor visitor) {
    visitor.visitInline(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FateScriptVisitor) accept((FateScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getParaminline1() {
    return findChildByType(PARAMINLINE1);
  }

  @Override
  @Nullable
  public PsiElement getParaminline2() {
    return findChildByType(PARAMINLINE2);
  }

  @Override
  @Nullable
  public PsiElement getParaminline3() {
    return findChildByType(PARAMINLINE3);
  }

  @Override
  @Nullable
  public PsiElement getParaminline6() {
    return findChildByType(PARAMINLINE6);
  }

}
