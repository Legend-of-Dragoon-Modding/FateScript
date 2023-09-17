package org.legendofdragoon.fatescript;

import com.intellij.lang.Language;

public class FateScriptLanguage extends Language {
  public static final FateScriptLanguage INSTANCE = new FateScriptLanguage();

  protected FateScriptLanguage() {
    super("fatescript");
  }
}
