// This is a generated file. Not intended for manual editing.
package org.legendofdragoon.fatescript.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.legendofdragoon.fatescript.psi.impl.*;

public interface FateScriptTypes {

  IElementType CODE = new FateScriptElementType("CODE");
  IElementType INDEX = new FateScriptElementType("INDEX");
  IElementType INLINE = new FateScriptElementType("INLINE");
  IElementType INLINE_INDEX = new FateScriptElementType("INLINE_INDEX");
  IElementType JUMP = new FateScriptElementType("JUMP");
  IElementType JUMP_TABLE = new FateScriptElementType("JUMP_TABLE");
  IElementType LABEL = new FateScriptElementType("LABEL");
  IElementType LINE_NUMBER = new FateScriptElementType("LINE_NUMBER");
  IElementType METHOD_REF = new FateScriptElementType("METHOD_REF");
  IElementType NUMBER = new FateScriptElementType("NUMBER");
  IElementType OP = new FateScriptElementType("OP");
  IElementType PARAM = new FateScriptElementType("PARAM");
  IElementType PARAMS = new FateScriptElementType("PARAMS");
  IElementType STORAGE = new FateScriptElementType("STORAGE");
  IElementType VAR = new FateScriptElementType("VAR");

  IElementType COMMENT = new FateScriptTokenType("comment");
  IElementType DEC = new FateScriptTokenType("dec");
  IElementType EOL = new FateScriptTokenType("eol");
  IElementType FULLHEX = new FateScriptTokenType("fullhex");
  IElementType HEX = new FateScriptTokenType("hex");
  IElementType ID = new FateScriptTokenType("id");
  IElementType STRING = new FateScriptTokenType("string");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == CODE) {
        return new FateScriptCodeImpl(node);
      }
      else if (type == INDEX) {
        return new FateScriptIndexImpl(node);
      }
      else if (type == INLINE) {
        return new FateScriptInlineImpl(node);
      }
      else if (type == INLINE_INDEX) {
        return new FateScriptInlineIndexImpl(node);
      }
      else if (type == JUMP) {
        return new FateScriptJumpImpl(node);
      }
      else if (type == JUMP_TABLE) {
        return new FateScriptJumpTableImpl(node);
      }
      else if (type == LABEL) {
        return new FateScriptLabelImpl(node);
      }
      else if (type == LINE_NUMBER) {
        return new FateScriptLineNumberImpl(node);
      }
      else if (type == METHOD_REF) {
        return new FateScriptMethodRefImpl(node);
      }
      else if (type == NUMBER) {
        return new FateScriptNumberImpl(node);
      }
      else if (type == OP) {
        return new FateScriptOpImpl(node);
      }
      else if (type == PARAM) {
        return new FateScriptParamImpl(node);
      }
      else if (type == PARAMS) {
        return new FateScriptParamsImpl(node);
      }
      else if (type == STORAGE) {
        return new FateScriptStorageImpl(node);
      }
      else if (type == VAR) {
        return new FateScriptVarImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
