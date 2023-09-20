package org.legendofdragoon.fatescript;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.legendofdragoon.fatescript.psi.FateScriptTypes.*;

%%

%{
  public FateScriptLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class FateScriptLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\n+
WHITE_SPACE=\s+
COMMENT=;.*
CMP=<=|<|>=|>|==|\!=|&|\!&
SCOPE=::
LABELSIGNIFIER=:
SEPARATOR=,
BRACKETOPEN=\[
BRACKETCLOSE=]
BINOP=[+\-*/]
ID=[a-zA-Z_][a-zA-Z_0-9]*
DEC=[0-9]{1,10}
HEX=0x[a-fA-F\d]{1,8}
DATASTRING=str\[.*?]

%%
<YYINITIAL> {
  {EOL}                  { return EOL; }
  {WHITE_SPACE}          { return WHITE_SPACE; }
  {COMMENT}              { return COMMENT; }
  {CMP}                  { return CMP; }
  {SCOPE}                { return SCOPE; }
  {LABELSIGNIFIER}       { return LABELSIGNIFIER; }
  {SEPARATOR}            { return SEPARATOR; }
  {BRACKETOPEN}          { return BRACKETOPEN; }
  {BRACKETCLOSE}         { return BRACKETCLOSE; }
  {BINOP}                { return BINOP; }
  {ID}                   { return ID; }
  {DEC}                  { return DEC; }
  {HEX}                  { return HEX; }
  {DATASTRING}           { return DATASTRING; }

}

[^] { return BAD_CHARACTER; }
