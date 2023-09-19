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
          createTextAttributesKey("FATESCRIPT_NUMBER", DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey OP =
          createTextAttributesKey("FATESCRIPT_OP", DefaultLanguageHighlighterColors.FUNCTION_CALL);

  public static final TextAttributesKey DATASTRING =
          createTextAttributesKey("FATESCRIPT_DATASTRING", DefaultLanguageHighlighterColors.STRING);
  public static final TextAttributesKey COMMENT =
          createTextAttributesKey("FATESCRIPT_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
  public static final TextAttributesKey BAD_CHARACTER =
          createTextAttributesKey("FATESCRIPT_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


  private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
  private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
  private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
  private static final TextAttributesKey[] OP_KEYS = new TextAttributesKey[]{OP};
  private static final TextAttributesKey[] DATASTRING_KEYS = new TextAttributesKey[]{DATASTRING};
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
    if (tokenType.equals(FateScriptTypes.NUMBER)) {
      return NUMBER_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.OP)) {
      return OP_KEYS;
    }
    if (tokenType.equals(FateScriptTypes.DATASTRING)) {
      return DATASTRING_KEYS;
    }
    if (tokenType.equals(TokenType.BAD_CHARACTER)) {
      return BAD_CHAR_KEYS;
    }
    return EMPTY_KEYS;
  }

}