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

WHITE_SPACE=[\ \t\f]+
EOL=\n+
COMMENT=;.*
CMP=<=|<|>=|>|==|\!=|&|\!&
SCOPE=:{2}
LABELSIGNIFIER=:
PARAMSEPARATOR=,
DEC=[0-9]{1,10}
ID=[a-zA-Z_][a-zA-Z_0-9]*
HEX=0x[a-fA-F\d]{1,8}
PARAMSTORAGE=stor[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?]
PARAMOTHEROTHERSTORAGE=stor[ \t\n\x0B\f\r]*?\[\s*?stor\s*?\[\s*?stor\s*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?,[ \t\n\x0B\f\r]*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?,[ \t\n\x0B\f\r]*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?]
PARAMOTHERSTORAGEOFFSET=stor[ \t\n\x0B\f\r]*?\[\s*?stor\s*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?,[ \t\n\x0B\f\r]*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?\+[ \t\n\x0B\f\r]*?stor[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?]
PARAMGAMEVAR1=var[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?]
PARAMGAMEVAR2=var[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?\+[ \t\n\x0B\f\r]*?stor[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?]
PARAMGAMEVARARRAY1=var[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?\[\s*?stor\s*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?]
PARAMGAMEVARARRAY2=var[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?\+[ \t\n\x0B\f\r]*?stor[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?\[\s*?stor\s*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?]
PARAMINLINE1=inl[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10}|:[a-zA-Z_0-9]+)[ \t\n\x0B\f\r]*?]
PARAMINLINE2=inl[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10}|:[a-zA-Z_0-9]+)[ \t\n\x0B\f\r]*?\[\s*?stor\s*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?]
PARAMINLINE3=inl[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10}|:[a-zA-Z_0-9]+)[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10}|:[a-zA-Z_0-9]+)[ \t\n\x0B\f\r]*?\[\s*?stor\s*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?]
PARAMGAMEVARARRAY3=var[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?]
PARAMGAMEVARARRAY4=var[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?[ \t\n\x0B\f\r]*?\+[ \t\n\x0B\f\r]*?stor[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?]
PARAMINLINE6=inl[ \t\n\x0B\f\r]*?\[\s*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10}|:[a-zA-Z_0-9]+)[ \t\n\x0B\f\r]*?\+[ \t\n\x0B\f\r]*?inl[ \t\n\x0B\f\r]*?\[(0x[a-fA-F\d]{1,8}|[0-9]{1,10}|:[a-zA-Z_0-9]+)[ \t\n\x0B\f\r]*?\+[ \t\n\x0B\f\r]*?(0x[a-fA-F\d]{1,8}|[0-9]{1,10})[ \t\n\x0B\f\r]*?][ \t\n\x0B\f\r]*?]
DATASTRING=str\[.*?]

%%
<YYINITIAL> {
  {WHITE_SPACE}                   { return WHITE_SPACE; }
  {EOL}                           { return EOL; }
  {COMMENT}                       { return COMMENT; }
  {CMP}                           { return CMP; }
  {SCOPE}                         { return SCOPE; }
  {LABELSIGNIFIER}                { return LABELSIGNIFIER; }
  {PARAMSEPARATOR}                { return PARAMSEPARATOR; }
  {DEC}                           { return DEC; }
  {ID}                            { return ID; }
  {HEX}                           { return HEX; }
  {PARAMSTORAGE}                  { return PARAMSTORAGE; }
  {PARAMOTHEROTHERSTORAGE}        { return PARAMOTHEROTHERSTORAGE; }
  {PARAMOTHERSTORAGEOFFSET}       { return PARAMOTHERSTORAGEOFFSET; }
  {PARAMGAMEVAR1}                 { return PARAMGAMEVAR1; }
  {PARAMGAMEVAR2}                 { return PARAMGAMEVAR2; }
  {PARAMGAMEVARARRAY1}            { return PARAMGAMEVARARRAY1; }
  {PARAMGAMEVARARRAY2}            { return PARAMGAMEVARARRAY2; }
  {PARAMINLINE1}                  { return PARAMINLINE1; }
  {PARAMINLINE2}                  { return PARAMINLINE2; }
  {PARAMINLINE3}                  { return PARAMINLINE3; }
  {PARAMGAMEVARARRAY3}            { return PARAMGAMEVARARRAY3; }
  {PARAMGAMEVARARRAY4}            { return PARAMGAMEVARARRAY4; }
  {PARAMINLINE6}                  { return PARAMINLINE6; }
  {DATASTRING}                    { return DATASTRING; }
}

[^] { return BAD_CHARACTER; }
