// This is a generated file. Not intended for manual editing.
package org.legendofdragoon.fatescript.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.legendofdragoon.fatescript.psi.impl.*;

public interface FateScriptTypes {

  IElementType CMP = new FateScriptElementType("CMP");
  IElementType CODE = new FateScriptElementType("CODE");
  IElementType DATA = new FateScriptElementType("DATA");
  IElementType ENTRYPOINT = new FateScriptElementType("ENTRYPOINT");
  IElementType INLINE = new FateScriptElementType("INLINE");
  IElementType LABEL = new FateScriptElementType("LABEL");
  IElementType LABEL_REF = new FateScriptElementType("LABEL_REF");
  IElementType METHOD_REF = new FateScriptElementType("METHOD_REF");
  IElementType NUMBER = new FateScriptElementType("NUMBER");
  IElementType OP = new FateScriptElementType("OP");
  IElementType PARAM = new FateScriptElementType("PARAM");
  IElementType PARAMS = new FateScriptElementType("PARAMS");
  IElementType REL = new FateScriptElementType("REL");
  IElementType STORAGE = new FateScriptElementType("STORAGE");
  IElementType STR = new FateScriptElementType("STR");
  IElementType VAR = new FateScriptElementType("VAR");

  IElementType COMMENT = new FateScriptTokenType("comment");
  IElementType DEC = new FateScriptTokenType("dec");
  IElementType EOL = new FateScriptTokenType("eol");
  IElementType HEX = new FateScriptTokenType("hex");
  IElementType ID = new FateScriptTokenType("id");
  IElementType PARAMGAMEVAR1 = new FateScriptTokenType("paramgamevar1");
  IElementType PARAMGAMEVAR2 = new FateScriptTokenType("paramgamevar2");
  IElementType PARAMGAMEVARARRAY1 = new FateScriptTokenType("paramgamevararray1");
  IElementType PARAMGAMEVARARRAY2 = new FateScriptTokenType("paramgamevararray2");
  IElementType PARAMGAMEVARARRAY3 = new FateScriptTokenType("paramgamevararray3");
  IElementType PARAMGAMEVARARRAY4 = new FateScriptTokenType("paramgamevararray4");
  IElementType PARAMINLINE1 = new FateScriptTokenType("paraminline1");
  IElementType PARAMINLINE2 = new FateScriptTokenType("paraminline2");
  IElementType PARAMINLINE3 = new FateScriptTokenType("paraminline3");
  IElementType PARAMINLINE6 = new FateScriptTokenType("paraminline6");
  IElementType PARAMOTHEROTHERSTORAGE = new FateScriptTokenType("paramotherotherstorage");
  IElementType PARAMOTHERSTORAGEOFFSET = new FateScriptTokenType("paramotherstorageoffset");
  IElementType PARAMSTORAGE = new FateScriptTokenType("paramstorage");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == CMP) {
        return new FateScriptCmpImpl(node);
      }
      else if (type == CODE) {
        return new FateScriptCodeImpl(node);
      }
      else if (type == DATA) {
        return new FateScriptDataImpl(node);
      }
      else if (type == ENTRYPOINT) {
        return new FateScriptEntrypointImpl(node);
      }
      else if (type == INLINE) {
        return new FateScriptInlineImpl(node);
      }
      else if (type == LABEL) {
        return new FateScriptLabelImpl(node);
      }
      else if (type == LABEL_REF) {
        return new FateScriptLabelRefImpl(node);
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
      else if (type == REL) {
        return new FateScriptRelImpl(node);
      }
      else if (type == STORAGE) {
        return new FateScriptStorageImpl(node);
      }
      else if (type == STR) {
        return new FateScriptStrImpl(node);
      }
      else if (type == VAR) {
        return new FateScriptVarImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
