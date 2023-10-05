package org.legendofdragoon.fatescript;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.legendofdragoon.fatescript.psi.FateScriptTypes;

import java.util.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class FateScriptSyntaxHighlighter extends SyntaxHighlighterBase {
  private static final List<IElementType> bitwiseOps = Arrays.asList(
      FateScriptTypes.AND,
      FateScriptTypes.OR,
      FateScriptTypes.XOR,
      FateScriptTypes.ANDOR,
      FateScriptTypes.NOT,
      FateScriptTypes.SHL,
      FateScriptTypes.SHR
  );
  private static final List<IElementType> mathOps = Arrays.asList(
      FateScriptTypes.ADD,
      FateScriptTypes.SUB,
      FateScriptTypes.SUB_REV,
      FateScriptTypes.INCR,
      FateScriptTypes.DECR,
      FateScriptTypes.NEG,
      FateScriptTypes.ABS,
      FateScriptTypes.MUL,
      FateScriptTypes.DIV,
      FateScriptTypes.DIV_REV,
      FateScriptTypes.MOD,
      FateScriptTypes.MOD_REV,
      FateScriptTypes.MUL_12,
      FateScriptTypes.DIV_12,
      FateScriptTypes.DIV_12_REV,
      FateScriptTypes.SQRT,
      FateScriptTypes.RAND,
      FateScriptTypes.SIN_12,
      FateScriptTypes.COS_12,
      FateScriptTypes.ATAN2_12
  );
  private static final List<IElementType> scriptOps = Arrays.asList(
      FateScriptTypes.CONSUME,
      FateScriptTypes.DEALLOCATE,
      FateScriptTypes.DEALLOCATE_OTHER,
      FateScriptTypes.FORK,
      FateScriptTypes.FORK_REENTER
  );
  private static final List<IElementType> subscriptableParams = Arrays.asList(
      FateScriptTypes.INL,
      FateScriptTypes.VAR,
      FateScriptTypes.STOR,
      FateScriptTypes.STR
  );
  private static final List<IElementType> comparators = Arrays.asList(
      FateScriptTypes.LTE,
      FateScriptTypes.LT,
      FateScriptTypes.GTE,
      FateScriptTypes.GT,
      FateScriptTypes.EQ,
      FateScriptTypes.NEQ,
      FateScriptTypes.CAND,
      FateScriptTypes.NAND
  );

  public static final TextAttributesKey NUMBER =
      createTextAttributesKey("FATESCRIPT_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
  public static final TextAttributesKey LABEL =
      createTextAttributesKey("FATESCRIPT_LABEL", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
  public static final TextAttributesKey DATATYPE =
      createTextAttributesKey("FATESCRIPT_DATATYPE", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
  public static final TextAttributesKey KEYWORD_OPS =
      createTextAttributesKey("FATESCRIPT_KEYWORD_OPS", DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey LOOP_OPS =
      createTextAttributesKey("FATESCRIPT_LOOP_OPS", DefaultLanguageHighlighterColors.IDENTIFIER);
  public static final TextAttributesKey WAIT_OPS =
      createTextAttributesKey("FATESCRIPT_WAIT_OPS", KEYWORD_OPS);
  public static final TextAttributesKey BITWISE_OPS =
      createTextAttributesKey("FATESCRIPT_BITWISE_OPS", DefaultLanguageHighlighterColors.IDENTIFIER);
  public static final TextAttributesKey MATH_OPS =
      createTextAttributesKey("FATESCRIPT_MATH_OPS", DefaultLanguageHighlighterColors.IDENTIFIER);
  public static final TextAttributesKey JUMP_OPS =
      createTextAttributesKey("FATESCRIPT_JUMP_OPS", DefaultLanguageHighlighterColors.FUNCTION_CALL);
  public static final TextAttributesKey GOSUB_OPS =
      createTextAttributesKey("FATESCRIPT_GOSUB_OPS", JUMP_OPS);
  public static final TextAttributesKey SCRIPT_OPS =
      createTextAttributesKey("FATESCRIPT_SCRIPT_OPS", KEYWORD_OPS);
  public static final TextAttributesKey OTHER_OPS =
      createTextAttributesKey("FATESCRIPT_OTHER_OPS", DefaultLanguageHighlighterColors.IDENTIFIER);
  public static final TextAttributesKey USELESS_OPS =
      createTextAttributesKey("FATESCRIPT_USELESS_OPS", DefaultLanguageHighlighterColors.IDENTIFIER);
  public static final TextAttributesKey SUBSCRIPTABLE_PARAMS =
      createTextAttributesKey("FATESCRIPT_SUBSCRIPTABLE_PARAMS", DefaultLanguageHighlighterColors.IDENTIFIER);
  public static final TextAttributesKey CLASS =
      createTextAttributesKey("FATESCRIPT_CLASS", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey METHOD =
      createTextAttributesKey("FATESCRIPT_METHOD", DefaultLanguageHighlighterColors.STATIC_METHOD);
  public static final TextAttributesKey COMPARATORS =
      createTextAttributesKey("FATESCRIPT_COMPARATORS", DefaultLanguageHighlighterColors.OPERATION_SIGN);
  public static final TextAttributesKey OPERATORS =
      createTextAttributesKey("FATESCRIPT_OPERATORS", DefaultLanguageHighlighterColors.OPERATION_SIGN);
  public static final TextAttributesKey COMMA =
      createTextAttributesKey("FATESCRIPT_COMMA", DefaultLanguageHighlighterColors.COMMA);
  public static final TextAttributesKey COLON =
      createTextAttributesKey("FATESCRIPT_COLON", DefaultLanguageHighlighterColors.OPERATION_SIGN);
  public static final TextAttributesKey BRACKETS =
      createTextAttributesKey("FATESCRIPT_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
  public static final TextAttributesKey LODSTRING =
      createTextAttributesKey("FATESCRIPT_LODSTRING", DefaultLanguageHighlighterColors.STRING);
  public static final TextAttributesKey COMMENT =
      createTextAttributesKey("FATESCRIPT_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
  public static final TextAttributesKey UNDEFINED_SYMBOL =
      createTextAttributesKey("FATESCRIPT_UNDEFINED_SYMBOL", DefaultLanguageHighlighterColors.HIGHLIGHTED_REFERENCE);
  public static final TextAttributesKey BAD_CHARACTER =
      createTextAttributesKey("FATESCRIPT_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


  private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
  private static final TextAttributesKey[] LABEL_KEYS = new TextAttributesKey[]{LABEL};
  private static final TextAttributesKey[] DATATYPE_KEYS = new TextAttributesKey[]{DATATYPE};
  private static final TextAttributesKey[] KEYWORD_OPS_KEYS = new TextAttributesKey[]{KEYWORD_OPS};
  private static final TextAttributesKey[] LOOP_OPS_KEYS = new TextAttributesKey[]{LOOP_OPS};
  private static final TextAttributesKey[] WAIT_OPS_KEYS = new TextAttributesKey[]{WAIT_OPS};
  private static final TextAttributesKey[] BITWISE_OPS_KEYS = new TextAttributesKey[]{BITWISE_OPS};
  private static final TextAttributesKey[] MATH_OPS_KEYS = new TextAttributesKey[]{MATH_OPS};
  private static final TextAttributesKey[] SCRIPT_OPS_KEYS = new TextAttributesKey[]{SCRIPT_OPS};
  private static final TextAttributesKey[] JUMP_OPS_KEYS = new TextAttributesKey[]{JUMP_OPS};
  private static final TextAttributesKey[] GOSUB_OPS_KEYS = new TextAttributesKey[]{GOSUB_OPS};
  private static final TextAttributesKey[] OTHER_OPS_KEYS = new TextAttributesKey[]{OTHER_OPS};
  private static final TextAttributesKey[] USELESS_OPS_KEYS = new TextAttributesKey[]{USELESS_OPS};
  private static final TextAttributesKey[] SUBSCRIPTABLE_PARAMS_KEYS = new TextAttributesKey[]{SUBSCRIPTABLE_PARAMS};
  private static final TextAttributesKey[] CLASS_KEYS = new TextAttributesKey[]{CLASS};
  private static final TextAttributesKey[] METHOD_KEYS = new TextAttributesKey[]{METHOD};
  private static final TextAttributesKey[] COMPARATOR_KEYS = new TextAttributesKey[]{COMPARATORS};
  private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATORS};
  private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA};
  private static final TextAttributesKey[] COLON_KEYS = new TextAttributesKey[]{COLON};
  private static final TextAttributesKey[] BRACKETS_KEYS = new TextAttributesKey[]{BRACKETS};
  private static final TextAttributesKey[] LODSTRING_KEYS = new TextAttributesKey[]{LODSTRING};
  private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
  private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
  private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

  @Override
  public @NotNull Lexer getHighlightingLexer() {
    return new FateScriptLexerAdapter();
  }

  @Override
  public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
    if (tokenType.equals(FateScriptTypes.DEC) || tokenType.equals(FateScriptTypes.HEX)) {
      return NUMBER_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.LABEL)) {
      return LABEL_KEYS;
    }
    if (
        tokenType.equals(FateScriptTypes.ENTRYPOINT) ||
            tokenType.equals(FateScriptTypes.REL) ||
            tokenType.equals(FateScriptTypes.DATA)
    ) {
      return DATATYPE_KEYS;
    }
    if (
        tokenType.equals(FateScriptTypes.YIELD) ||
            tokenType.equals(FateScriptTypes.RETURN) ||
            tokenType.equals(FateScriptTypes.REWIND)
    ) {
      return KEYWORD_OPS_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.WHILE)) {
      return LOOP_OPS_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.WAIT) || tokenType.equals(FateScriptTypes.WAIT_CMP)) {
      return WAIT_OPS_KEYS;
    }
    if (bitwiseOps.contains(tokenType)) {
      return BITWISE_OPS_KEYS;
    }
    if (mathOps.contains(tokenType)) {
      return MATH_OPS_KEYS;
    }
    if (scriptOps.contains(tokenType)) {
      return SCRIPT_OPS_KEYS;
    }
    if (
        tokenType.equals(FateScriptTypes.JMP) ||
            tokenType.equals(FateScriptTypes.JMP_CMP) ||
            tokenType.equals(FateScriptTypes.JMP_TABLE)
    ) {
      return JUMP_OPS_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.GOSUB) || tokenType.equals(FateScriptTypes.GOSUB_TABLE)) {
      return GOSUB_OPS_KEYS;
    }
    if (
        tokenType.equals(FateScriptTypes.CALL) ||
            tokenType.equals(FateScriptTypes.MOV) ||
            tokenType.equals(FateScriptTypes.MEMCPY)
    ) {
      return OTHER_OPS_KEYS;
    }
    if (
        tokenType.equals(FateScriptTypes.SWAP_BROKEN) ||
            tokenType.equals(FateScriptTypes.DEPTH) ||
            tokenType.equals(FateScriptTypes.NOOP)
    ) {
      return USELESS_OPS_KEYS;
    }
    if (subscriptableParams.contains(tokenType)) {
      return SUBSCRIPTABLE_PARAMS_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.METHOD)) {
      return METHOD_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.CLASSNAME)) {
      return CLASS_KEYS;
    }
    if (comparators.contains(tokenType)) {
      return COMPARATOR_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.PLUS)) {
      return OPERATOR_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.COMMA)) {
      return COMMA_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.COLON)) {
      return COLON_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.LBRACKET) || tokenType.equals(FateScriptTypes.RBRACKET)) {
      return BRACKETS_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.LODSTRING)) {
      return LODSTRING_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.COMMENT)) {
      return COMMENT_KEYS;
    }
    if (tokenType.equals(TokenType.BAD_CHARACTER)) {
      return BAD_CHAR_KEYS;
    }
    return EMPTY_KEYS;
  }
}