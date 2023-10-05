package org.legendofdragoon.fatescript;

import com.intellij.psi.tree.IElementType;
import it.unimi.dsi.fastutil.ints.IntArrayList;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.legendofdragoon.fatescript.psi.FateScriptTypes.*;

%%

%{
  public FateScriptLexer() {
      this((java.io.Reader)null);
  }

  private final IntArrayList myStateStack = new IntArrayList();

  private void pushState(int newState) {
    myStateStack.add(yystate());
    yybegin(newState);
  }

  private void popState() {
    if (myStateStack.isEmpty()) {
      return;
    }

    int state = myStateStack.removeInt(myStateStack.size() - 1);
    yybegin(state);
  }
%}

%public
%class FateScriptLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\n
WHITE_SPACE=[ \t]+
COMMENT=;.*
ID=[a-zA-Z_][a-zA-Z_0-9]*
DEC=[0-9]{1,10}
HEX=0x[a-fA-F\d]{1,8}
LODSTRING=.*

%state STATE_KEYWORD_OP
%state STATE_PARAM
%state STATE_LABEL_TERMINATOR
%state STATE_LABEL_REF
%state STATE_CLASS
%state STATE_METHOD
%state STATE_INIT_LODSTRING
%state STATE_LODSTRING
%state STATE_LODSTRING_TAG

%%
<YYINITIAL, STATE_PARAM, STATE_INIT_LODSTRING> {
  {EOL}                       { popState(); return EOL; }
  {WHITE_SPACE}               { return WHITE_SPACE; }

  // Keyword ops
  "yield"                     { pushState(STATE_KEYWORD_OP); return YIELD; }
  "return"                    { pushState(STATE_KEYWORD_OP); return RETURN; }
  "rewind"                    { pushState(STATE_KEYWORD_OP); return REWIND; }

  // Loop ops
  "while"                     { pushState(STATE_PARAM); return WHILE; }

  // Wait ops
  "wait"                      { pushState(STATE_PARAM); return WAIT; }
  "wait_cmp"                  { pushState(STATE_PARAM); return WAIT_CMP; }

  // Bitwise ops
  "and"                       { pushState(STATE_PARAM); return AND; }
  "or"                        { pushState(STATE_PARAM); return OR; }
  "xor"                       { pushState(STATE_PARAM); return XOR; }
  "andor"                     { pushState(STATE_PARAM); return ANDOR; }
  "not"                       { pushState(STATE_PARAM); return NOT; }
  "shl"                       { pushState(STATE_PARAM); return SHL; }
  "shr"                       { pushState(STATE_PARAM); return SHR; }

  // Math ops
  "add"                       { pushState(STATE_PARAM); return ADD; }
  "sub"                       { pushState(STATE_PARAM); return SUB; }
  "sub_rev"                   { pushState(STATE_PARAM); return SUB_REV; }
  "incr"                      { pushState(STATE_PARAM); return INCR; }
  "decr"                      { pushState(STATE_PARAM); return DECR; }
  "neg"                       { pushState(STATE_PARAM); return NEG; }
  "abs"                       { pushState(STATE_PARAM); return ABS; }
  "mul"                       { pushState(STATE_PARAM); return MUL; }
  "div"                       { pushState(STATE_PARAM); return DIV; }
  "div_rev"                   { pushState(STATE_PARAM); return DIV_REV; }
  "mod"                       { pushState(STATE_PARAM); return MOD; }
  "mod_rev"                   { pushState(STATE_PARAM); return MOD_REV; }
  "mul_12"                    { pushState(STATE_PARAM); return MUL_12; }
  "div_12"                    { pushState(STATE_PARAM); return DIV_12; }
  "div_12_rev"                { pushState(STATE_PARAM); return DIV_12_REV; }
  "sqrt"                      { pushState(STATE_PARAM); return SQRT; }
  "rand"                      { pushState(STATE_PARAM); return RAND; }
  "sin_12"                    { pushState(STATE_PARAM); return SIN_12; }
  "cos_12"                    { pushState(STATE_PARAM); return COS_12; }
  "atan2_12"                  { pushState(STATE_PARAM); return ATAN2_12; }

  // Jump ops
  "jmp"                       { pushState(STATE_PARAM); return JMP; }
  "jmp_cmp"                   { pushState(STATE_PARAM); return JMP_CMP; }
  "jmp_table"                 { pushState(STATE_PARAM); return JMP_TABLE; }

  // Gosub ops
  "gosub"                     { pushState(STATE_PARAM); return GOSUB; }
  "gosub_table"               { pushState(STATE_PARAM); return GOSUB_TABLE; }

  // Script ops
  "consume"                   { return CONSUME; }
  "deallocate"                { return DEALLOCATE; }
  "deallocate_other"          { return DEALLOCATE_OTHER; }
  "fork"                      { return FORK; }
  "fork_reenter"              { return FORK_REENTER; }

  // Other ops
  "call"                      { pushState(STATE_CLASS); return CALL; }
  "mov"                       { pushState(STATE_PARAM); return MOV; }
  "memcpy"                    { pushState(STATE_PARAM); return MEMCPY; }

  // Useless ops
  "swap_broken"               { return SWAP_BROKEN; }
  "depth"                     { return DEPTH; }
  "noop"                      { return NOOP; }

  // Datatypes
  "entrypoint"                { return ENTRYPOINT; }
  "data"                      { return DATA; }
  "rel"                       { return REL; }

  // Subscriptable params
  "inl"                       { return INL; }
  "var"                       { return VAR; }
  "stor"                      { return STOR; }
  "str"                       { pushState(STATE_INIT_LODSTRING); return STR; }

  // Separators and operators
  ","                         { return COMMA; }
  ":"                         { pushState(STATE_LABEL_REF); return COLON; }
  "["                         { if (zzLexicalState == STATE_INIT_LODSTRING) { popState(); pushState(STATE_LODSTRING); } return LBRACKET; }
  "]"                         { return RBRACKET; }
  "+"                         { return PLUS; }

  // Comparators
  "<="                        { return LTE; }
  "<"                         { return LT;}
  ">="                        { return GTE; }
  ">"                         { return GT; }
  "=="                        { return EQ; }
  "!="                        { return NEQ; }
  "&"                         { return CAND; }
  "!&"                        { return NAND; }

  {COMMENT}                   { return COMMENT; }
  {ID}                        { if (zzLexicalState == YYINITIAL) { pushState(STATE_LABEL_TERMINATOR); return LABEL; } return ID; }
  {DEC}                       { return DEC; }
  {HEX}                       { return HEX; }
}

<STATE_KEYWORD_OP> {
  {EOL}                       { popState(); return EOL; }
  {WHITE_SPACE}               { return WHITE_SPACE; }
}

<STATE_CLASS, STATE_METHOD> {
  "::"                        { return DOUBLECOLON; }
  {WHITE_SPACE}               { return WHITE_SPACE; }
  {ID}
    {
                  if (zzLexicalState - STATE_CLASS == 0) {
                    popState();
                    pushState(STATE_METHOD);
                    return CLASSNAME;
                  } else if (zzLexicalState - STATE_METHOD == 0) {
                    popState();
                    pushState(STATE_PARAM);
                    return METHOD;
                  }
                }
}

<STATE_LABEL_TERMINATOR> {
  {WHITE_SPACE}               { return WHITE_SPACE; }
  ":"                         { popState(); return COLON; }
}

<STATE_LABEL_REF> {
  {WHITE_SPACE}               { return WHITE_SPACE; }
  {ID}                        { popState(); return LABEL; }
}

<STATE_LODSTRING> {
  "<"                         { pushState(STATE_LODSTRING_TAG); return LT; }
  {LODSTRING}                 { yypushback(1); popState(); return LODSTRING; }
}

<STATE_LODSTRING_TAG> {
  ">"                         { popState(); return GT; }
  "="                         { return EQ; }
  "sauto"                     { return SAUTO; }
  "sbat"                      { return SBAT; }
  "element"                   { return ELEM; }
  "multibox"                  { return MULTIBOX; }
  "line"                      { return LINE; }
  "speed"                     { return SPEED; }
  "colour"                    { return COLOUR; }
  "var"                       { return VAR; }
  {DEC}                       { return DEC; }
}

[^] { return BAD_CHARACTER; }
