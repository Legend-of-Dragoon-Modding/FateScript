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

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class FateScriptSyntaxHighlighter extends SyntaxHighlighterBase {
  public static final TextAttributesKey NUMBER =
      createTextAttributesKey("FATESCRIPT_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
  public static final TextAttributesKey LABEL =
      createTextAttributesKey("FATESCRIPT_LABEL", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
  public static final TextAttributesKey DATATYPE =
      createTextAttributesKey("FATESCRIPT_DATATYPE", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey METHOD =
      createTextAttributesKey("FATESCRIPT_METHOD", DefaultLanguageHighlighterColors.STATIC_METHOD);
  public static final TextAttributesKey KEYWORD_OPS =
      createTextAttributesKey("FATESCRIPT_KEYWORD_OPS", DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey LODSTRING =
      createTextAttributesKey("FATESCRIPT_LODSTRING", DefaultLanguageHighlighterColors.STRING);
  public static final TextAttributesKey COMMENT =
      createTextAttributesKey("FATESCRIPT_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
  public static final TextAttributesKey BAD_CHARACTER =
      createTextAttributesKey("FATESCRIPT_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


  private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
  private static final TextAttributesKey[] LABEL_KEYS = new TextAttributesKey[]{LABEL};
  private static final TextAttributesKey[] DATATYPE_KEYS = new TextAttributesKey[]{DATATYPE};
  private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
  private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
  private static final TextAttributesKey[] METHOD_KEYS = new TextAttributesKey[]{METHOD};
  private static final TextAttributesKey[] KEYWORD_OPS_KEYS = new TextAttributesKey[]{KEYWORD_OPS};
  private static final TextAttributesKey[] LODSTRING_KEYS = new TextAttributesKey[]{LODSTRING};
  private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

  @Override
  public @NotNull Lexer getHighlightingLexer() {
    return new FateScriptLexerAdapter();
  }

  @Override
  public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
    if (tokenType.equals(FateScriptTypes.COMMENT)) {
      return COMMENT_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.DEC) || tokenType.equals(FateScriptTypes.HEX)) {
      return NUMBER_KEYS;
    }
    if (
        tokenType.equals(FateScriptTypes.ENTRYPOINT) ||
            tokenType.equals(FateScriptTypes.REL) ||
            tokenType.equals(FateScriptTypes.DATA)
    ) {
      return DATATYPE_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.KEYWORD_OPS)) {
      return KEYWORD_OPS_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.METHOD)) {
      return METHOD_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.LODSTRING)) {
      return LODSTRING_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.LABEL)) {
      return LABEL_KEYS;
    }
    if (tokenType.equals(TokenType.BAD_CHARACTER)) {
      return BAD_CHAR_KEYS;
    }
    return EMPTY_KEYS;
  }

}