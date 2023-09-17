// This is a generated file. Not intended for manual editing.
package org.legendofdragoon.fatescript.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.legendofdragoon.fatescript.psi.impl.*;

public interface FateScriptTypes {

  IElementType LINE = new FateScriptElementType("LINE");

  IElementType COMMENT = new FateScriptTokenType("comment");
  IElementType HEX = new FateScriptTokenType("HEX");
  IElementType ID = new FateScriptTokenType("id");
  IElementType STRING = new FateScriptTokenType("string");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == LINE) {
        return new FateScriptLineImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
