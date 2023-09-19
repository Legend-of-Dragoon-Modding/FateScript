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

public class FateScriptVarImpl extends ASTWrapperPsiElement implements FateScriptVar {

  public FateScriptVarImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FateScriptVisitor visitor) {
    visitor.visitVar(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FateScriptVisitor) accept((FateScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getParamgamevar1() {
    return findChildByType(PARAMGAMEVAR1);
  }

  @Override
  @Nullable
  public PsiElement getParamgamevar2() {
    return findChildByType(PARAMGAMEVAR2);
  }

  @Override
  @Nullable
  public PsiElement getParamgamevararray1() {
    return findChildByType(PARAMGAMEVARARRAY1);
  }

  @Override
  @Nullable
  public PsiElement getParamgamevararray2() {
    return findChildByType(PARAMGAMEVARARRAY2);
  }

  @Override
  @Nullable
  public PsiElement getParamgamevararray3() {
    return findChildByType(PARAMGAMEVARARRAY3);
  }

  @Override
  @Nullable
  public PsiElement getParamgamevararray4() {
    return findChildByType(PARAMGAMEVARARRAY4);
  }

}
