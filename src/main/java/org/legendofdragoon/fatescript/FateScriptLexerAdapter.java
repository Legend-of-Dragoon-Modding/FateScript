package org.legendofdragoon.fatescript;

import com.intellij.lexer.FlexAdapter;

public class FateScriptLexerAdapter extends FlexAdapter {
  public FateScriptLexerAdapter() {
    super(new FateScriptLexer(null));
  }
}