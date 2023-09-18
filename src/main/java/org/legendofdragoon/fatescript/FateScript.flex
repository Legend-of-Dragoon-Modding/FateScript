// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.legendofdragoon.fatescript;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.legendofdragoon.fatescript.psi.FateScriptTypes;

%%

%class FateScriptLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{
  return;
%eof}

EOL="\r"|"\n"|"\r\n"
LINE_WS=[\ \t\f]
WHITE_SPACE=({LINE_WS})+
LINE_COMMENT=;.*

ALPHA=[:letter:]
DIGIT=[:digit:]

ID={ALPHA} | {DIGIT} | "_"
HEX={DIGIT} | [aAbBcCdDeEfF]
NUMBER={DIGIT}+ | "0x" {HEX}+

%%

<YYINITIAL> {
  {LINE_COMMENT}                                  { yybegin(YYINITIAL); return FateScriptTypes.COMMENT; }
  {HEX}                                           { yybegin(YYINITIAL); return FateScriptTypes.HEX; }
}

[^]                                                         { return TokenType.BAD_CHARACTER; }
