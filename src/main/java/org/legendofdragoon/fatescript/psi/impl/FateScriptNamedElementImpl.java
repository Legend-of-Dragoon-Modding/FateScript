package org.legendofdragoon.fatescript.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.legendofdragoon.fatescript.psi.FateScriptNamedElement;

public abstract class FateScriptNamedElementImpl extends ASTWrapperPsiElement implements FateScriptNamedElement {
  public FateScriptNamedElementImpl(@NotNull ASTNode node) {
    super(node);
  }
}
