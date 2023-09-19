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

public class FateScriptParamImpl extends ASTWrapperPsiElement implements FateScriptParam {

  public FateScriptParamImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FateScriptVisitor visitor) {
    visitor.visitParam(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FateScriptVisitor) accept((FateScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public FateScriptCmp getCmp() {
    return findChildByClass(FateScriptCmp.class);
  }

  @Override
  @Nullable
  public FateScriptInline getInline() {
    return findChildByClass(FateScriptInline.class);
  }

  @Override
  @Nullable
  public FateScriptMethodRef getMethodRef() {
    return findChildByClass(FateScriptMethodRef.class);
  }

  @Override
  @Nullable
  public FateScriptNumber getNumber() {
    return findChildByClass(FateScriptNumber.class);
  }

  @Override
  @Nullable
  public FateScriptStorage getStorage() {
    return findChildByClass(FateScriptStorage.class);
  }

  @Override
  @Nullable
  public FateScriptVar getVar() {
    return findChildByClass(FateScriptVar.class);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

}
