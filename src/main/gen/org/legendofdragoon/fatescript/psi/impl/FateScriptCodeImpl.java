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

public class FateScriptCodeImpl extends ASTWrapperPsiElement implements FateScriptCode {

  public FateScriptCodeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FateScriptVisitor visitor) {
    visitor.visitCode(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FateScriptVisitor) accept((FateScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public FateScriptLineNumber getLineNumber() {
    return findChildByClass(FateScriptLineNumber.class);
  }

  @Override
  @NotNull
  public FateScriptOp getOp() {
    return findNotNullChildByClass(FateScriptOp.class);
  }

}