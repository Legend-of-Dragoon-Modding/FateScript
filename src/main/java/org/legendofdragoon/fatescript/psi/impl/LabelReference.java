package org.legendofdragoon.fatescript.psi.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.diagnostic.PluginException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.legendofdragoon.fatescript.FateScriptIcons;
import org.legendofdragoon.fatescript.FateScriptUtil;
import org.legendofdragoon.fatescript.psi.FateScriptFile;
import org.legendofdragoon.fatescript.psi.FateScriptLabelHeader;
import org.legendofdragoon.fatescript.psi.FateScriptLabelRef;

import java.util.ArrayList;
import java.util.List;

public class LabelReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
  private final String label;

  public LabelReference(@NotNull final PsiElement element, final TextRange textRange) {
    super(element, textRange);
    this.label = element.getText().substring(1);
  }

  @Override
  public ResolveResult @NotNull [] multiResolve(final boolean incompleteCode) {
    final FateScriptFile file = (FateScriptFile) this.myElement.getContainingFile();
    final List<FateScriptLabelHeader> labelRefs = FateScriptUtil.findLabelReferences(file, this.label);
    final List<ResolveResult> results = new ArrayList<>();
    for (final FateScriptLabelHeader labelRef : labelRefs) {
      results.add(new PsiElementResolveResult(labelRef));
    }
    return results.toArray(new ResolveResult[results.size()]);
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    final ResolveResult[] resolveResults = this.multiResolve(false);
    return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
  }

  @Override
  public Object @NotNull [] getVariants() {
    final FateScriptFile file = (FateScriptFile) this.myElement.getContainingFile();
    final List<FateScriptLabelHeader> labelHeaders = FateScriptUtil.findLabelReferences(file);
    final List<LookupElement> variants = new ArrayList<>();
    for (final FateScriptLabelHeader labelHeader : labelHeaders) {
      if (labelHeader.getText() != null && !labelHeader.getText().isEmpty()) {
        variants.add(LookupElementBuilder
            .create(labelHeader).withIcon(FateScriptIcons.FILE)
            .withTypeText(labelHeader.getContainingFile().getName())
        );
      }
    }
    return variants.toArray();
  }

  @Override
  public PsiElement handleElementRename(@NotNull final String newElementName) throws IncorrectOperationException {
    final PsiElement e = this.getElement();
    if (e instanceof FateScriptLabelRef) {
      return ((FateScriptLabelRef)e).getLabel().replace(FateScriptElementFactory.createLabel(e.getProject(), newElementName));
    }
    throw new UnsupportedOperationException(e.toString());
  }
}
