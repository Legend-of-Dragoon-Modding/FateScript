package org.legendofdragoon.fatescript;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.legendofdragoon.fatescript.psi.FateScriptLabelHeader;
import org.legendofdragoon.fatescript.psi.FateScriptTokenSets;

public class FateScriptFindUsagesProvider implements FindUsagesProvider {

  @Nullable
  @Override
  public WordsScanner getWordsScanner() {
    return new DefaultWordsScanner(new FateScriptLexerAdapter(),
        FateScriptTokenSets.IDENTIFIERS,
        FateScriptTokenSets.COMMENTS,
        TokenSet.EMPTY);
  }

  @Override
  public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
    return psiElement instanceof PsiNamedElement;
  }

  @Nullable
  @Override
  public String getHelpId(@NotNull PsiElement psiElement) {
    return null;
  }

  @NotNull
  @Override
  public String getType(@NotNull PsiElement element) {
    if (element instanceof FateScriptLabelHeader) {
      return "simple property";
    }
    return "";
  }

  @NotNull
  @Override
  public String getDescriptiveName(@NotNull final PsiElement element) {
    if (element instanceof FateScriptLabelHeader) {
      return ((FateScriptLabelHeader) element).getName();
    }
    return "";
  }

  @NotNull
  @Override
  public String getNodeText(@NotNull final PsiElement element, final boolean useFullName) {
    if (element instanceof FateScriptLabelHeader) {
      return ((FateScriptLabelHeader) element).getName();
    }
    return "";
  }

}
