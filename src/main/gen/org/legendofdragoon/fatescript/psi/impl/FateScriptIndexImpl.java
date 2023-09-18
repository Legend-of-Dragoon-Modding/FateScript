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

public class FateScriptIndexImpl extends ASTWrapperPsiElement implements FateScriptIndex {

  public FateScriptIndexImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FateScriptVisitor visitor) {
    visitor.visitIndex(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FateScriptVisitor) accept((FateScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public FateScriptJumpTable getJumpTable() {
    return findChildByClass(FateScriptJumpTable.class);
  }

  @Override
  @Nullable
  public FateScriptStorage getStorage() {
    return findChildByClass(FateScriptStorage.class);
  }

}