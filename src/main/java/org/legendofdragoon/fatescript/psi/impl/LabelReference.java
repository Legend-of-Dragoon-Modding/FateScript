package org.legendofdragoon.fatescript.psi.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.legendofdragoon.fatescript.FateScriptIcons;
import org.legendofdragoon.fatescript.FateScriptUtil;
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
    final Project project = this.myElement.getProject();
    final List<FateScriptLabelRef> labelRefs = FateScriptUtil.findLabelReferences(project, this.label);
    final List<ResolveResult> results = new ArrayList<>();
    for (final FateScriptLabelRef labelRef : labelRefs) {
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
    final Project project = this.myElement.getProject();
    final List<FateScriptLabelRef> labelRefs = FateScriptUtil.findLabelReferences(project);
    final List<LookupElement> variants = new ArrayList<>();
    for (final FateScriptLabelRef labelRef : labelRefs) {
      if (labelRef.getText() != null && !labelRef.getText().isEmpty()) {
        variants.add(LookupElementBuilder
            .create(labelRef).withIcon(FateScriptIcons.FILE)
            .withTypeText(labelRef.getContainingFile().getName())
        );
      }
    }
    return variants.toArray();
  }

}
