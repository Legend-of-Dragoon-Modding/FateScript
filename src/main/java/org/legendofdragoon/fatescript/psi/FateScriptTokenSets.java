package org.legendofdragoon.fatescript.psi;

import com.intellij.psi.tree.TokenSet;

public class FateScriptTokenSets {
  public static final TokenSet IDENTIFIERS = TokenSet.create(FateScriptTypes.KEY);
  public static final TokenSet COMMENTS = TokenSet.create(FateScriptTypes.COMMENT);
}
