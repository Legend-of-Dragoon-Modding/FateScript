package org.legendofdragoon.fatescript;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;import it.unimi.dsi.fastutil.ints.IntArrayList;

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
CMP=<=|<|>=|>|==|\!=|&|\!&
BINOP=[+\-*/]
ID=[a-zA-Z_][a-zA-Z_0-9]*
DEC=[0-9]{1,10}
HEX=0x[a-fA-F\d]{1,8}
STRING=\[.*]

%state STRING_EXPECTED
%state METHOD_EXPECTED
%state LABEL_REF_EXPECTED
%state LABEL_EXPECTED
%state PARAM_EXPECTED

%%
<YYINITIAL, PARAM_EXPECTED> {
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
  "call"                      { pushState(PARAM_EXPECTED); return CALL; }
  "mov"                       { pushState(PARAM_EXPECTED); return MOV; }
  "memcpy"                    { pushState(PARAM_EXPECTED); return MEMCPY; }

  // Useless ops
  "swap_broken"               { return SWAP_BROKEN; }
  "depth"                     { return DEPTH; }
  "noop"                      { return NOOP; }

  // Datatypes
  "entrypoint"                { pushState(LABEL_REF_EXPECTED); return ENTRYPOINT; }
  "data"                      { return DATA; }
  "rel"                       { return REL; }

  // Subscriptable params
  "inl"                       { return INL; }
  "var"                       { return VAR; }
  "stor"                      { return STOR; }
  "str"                       { pushState(STRING_EXPECTED); return STR; }

  ","                         { return COMMA; }
  "::"                        { pushState(METHOD_EXPECTED); return DOUBLECOLON; }
  ":"                         { return COLON; }
  "["                         { return LBRACKET; }
  "]"                         { return RBRACKET; }

  {COMMENT}                   { return COMMENT; }
  {CMP}                       { return CMP; }
  {BINOP}                     { return BINOP; }
  {ID}                        { if (zzLexicalState == 0) { return LABEL; } return ID; }
  {DEC}                       { return DEC; }
  {HEX}                       { return HEX; }
}

<STRING_EXPECTED> {
  {STRING}                    { popState(); return STRING; }
}

<METHOD_EXPECTED> {
  {ID}                        { popState(); return METHOD; }
}

<LABEL_REF_EXPECTED> {
  {WHITE_SPACE}               { return WHITE_SPACE; }
  ":"                         { return COLON; }
  {ID}                        { popState(); return LABEL; }
}

[^] { return BAD_CHARACTER; }
