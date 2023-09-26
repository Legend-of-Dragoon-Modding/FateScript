package org.legendofdragoon.fatescript;

import com.intellij.lexer.FlexLexer;
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

EOL=\n+
WHITE_SPACE=\s+
COMMENT=;.*
ID=[a-zA-Z_][a-zA-Z_0-9]*
DEC=[0-9]{1,10}
HEX=0x[a-fA-F\d]{1,8}
LODSTRING=.*

%state PARAM_EXPECTED
%state LABEL_TERMINATOR
%state LABEL_REF_EXPECTED
%state CLASS_EXPECTED
%state METHOD_EXPECTED
%state STATE_INIT_LODSTRING
%state STATE_LODSTRING
%state STATE_LODSTRING_TAG

%%
<YYINITIAL, PARAM_EXPECTED, STATE_INIT_LODSTRING> {
  {EOL}                       { popState(); return EOL; }
  {WHITE_SPACE}               { return WHITE_SPACE; }

  // Keyword ops
  "yield"                     { return YIELD; }
  "return"                    { return RETURN; }
  "rewind"                    { return REWIND; }

  // Loop ops
  "while"                     { pushState(PARAM_EXPECTED); return WHILE; }

  // Wait ops
  "wait"                      { pushState(PARAM_EXPECTED); return WAIT; }
  "wait_cmp"                  { pushState(PARAM_EXPECTED); return WAIT_CMP; }

  // Bitwise ops
  "and"                       { pushState(PARAM_EXPECTED); return AND; }
  "or"                        { pushState(PARAM_EXPECTED); return OR; }
  "xor"                       { pushState(PARAM_EXPECTED); return XOR; }
  "andor"                     { pushState(PARAM_EXPECTED); return ANDOR; }
  "not"                       { pushState(PARAM_EXPECTED); return NOT; }
  "shl"                       { pushState(PARAM_EXPECTED); return SHL; }
  "shr"                       { pushState(PARAM_EXPECTED); return SHR; }

  // Math ops
  "add"                       { pushState(PARAM_EXPECTED); return ADD; }
  "sub"                       { pushState(PARAM_EXPECTED); return SUB; }
  "sub_rev"                   { pushState(PARAM_EXPECTED); return SUB_REV; }
  "incr"                      { pushState(PARAM_EXPECTED); return INCR; }
  "decr"                      { pushState(PARAM_EXPECTED); return DECR; }
  "neg"                       { pushState(PARAM_EXPECTED); return NEG; }
  "abs"                       { pushState(PARAM_EXPECTED); return ABS; }
  "mul"                       { pushState(PARAM_EXPECTED); return MUL; }
  "div"                       { pushState(PARAM_EXPECTED); return DIV; }
  "div_rev"                   { pushState(PARAM_EXPECTED); return DIV_REV; }
  "mod"                       { pushState(PARAM_EXPECTED); return MOD; }
  "mod_rev"                   { pushState(PARAM_EXPECTED); return MOD_REV; }
  "mul_12"                    { pushState(PARAM_EXPECTED); return MUL_12; }
  "div_12"                    { pushState(PARAM_EXPECTED); return DIV_12; }
  "div_12_rev"                { pushState(PARAM_EXPECTED); return DIV_12_REV; }
  "sqrt"                      { pushState(PARAM_EXPECTED); return SQRT; }
  "rand"                      { pushState(PARAM_EXPECTED); return RAND; }
  "sin_12"                    { pushState(PARAM_EXPECTED); return SIN_12; }
  "cos_12"                    { pushState(PARAM_EXPECTED); return COS_12; }
  "atan2_12"                  { pushState(PARAM_EXPECTED); return ATAN2_12; }

  // Jump ops
  "jmp"                       { pushState(PARAM_EXPECTED); return JMP; }
  "jmp_cmp"                   { pushState(PARAM_EXPECTED); return JMP_CMP; }
  "jmp_table"                 { pushState(PARAM_EXPECTED); return JMP_TABLE; }

  // Gosub ops
  "gosub"                     { pushState(PARAM_EXPECTED); return GOSUB; }
  "gosub_table"               { pushState(PARAM_EXPECTED); return GOSUB_TABLE; }

  // Script ops
  "consume"                   { return CONSUME; }
  "deallocate"                { return DEALLOCATE; }
  "deallocate_other"          { return DEALLOCATE_OTHER; }
  "fork"                      { return FORK; }
  "fork_reenter"              { return FORK_REENTER; }

  // Other ops
  "call"                      { pushState(CLASS_EXPECTED); return CALL; }
  "mov"                       { pushState(PARAM_EXPECTED); return MOV; }
  "memcpy"                    { pushState(PARAM_EXPECTED); return MEMCPY; }

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

  ","                         { return COMMA; }
  ":"                         { pushState(LABEL_REF_EXPECTED); return COLON; }
  "["                         { if (zzLexicalState == STATE_INIT_LODSTRING) { popState(); pushState(STATE_LODSTRING); } return LBRACKET; }
  "]"                         { return RBRACKET; }
  "+"                         { return PLUS; }
  "<="                        { return LTE; }
  "<"                         { return LT;}
  ">="                        { return GTE; }
  ">"                         { return GT; }
  "=="                        { return EQ; }
  "!="                        { return NEQ; }
  "&"                         { return AND; }
  "!&"                        { return NAND; }

  {COMMENT}                   { return COMMENT; }
  {ID}                        { if (zzLexicalState == YYINITIAL) { pushState(LABEL_TERMINATOR); return LABEL; } return ID; }
  {DEC}                       { return DEC; }
  {HEX}                       { return HEX; }
}

<CLASS_EXPECTED, METHOD_EXPECTED> {
  "::"                        { return DOUBLECOLON; }
  {WHITE_SPACE}               { return WHITE_SPACE; }
  {ID}
    {
      if (zzLexicalState - CLASS_EXPECTED == 0) {
        popState();
        pushState(METHOD_EXPECTED);
        return CLASSNAME;
      } else if (zzLexicalState - METHOD_EXPECTED == 0) {
        popState();
        pushState(PARAM_EXPECTED);
        return METHOD;
      }
    }
}

<LABEL_TERMINATOR> {
  {WHITE_SPACE}               { return WHITE_SPACE; }
  ":"                         { popState(); return COLON; }
}

<LABEL_REF_EXPECTED> {
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
