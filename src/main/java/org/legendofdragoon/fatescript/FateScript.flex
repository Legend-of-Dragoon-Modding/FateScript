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

%%
<YYINITIAL> {
  {EOL}                       { return EOL; }
  {WHITE_SPACE}               { return WHITE_SPACE; }

  // Keyword ops
  "yield"                     { return YIELD; }
  "return"                    { return RETURN; }
  "rewind"                    { return REWIND; }

  // Loop ops
  "while"                     { return WHILE; }

  // Wait ops
  "wait"                      { return WAIT; }
  "wait_cmp"                  { return WAIT_CMP; }

  // Bitwise ops
  "and"                       { return AND; }
  "or"                        { return OR; }
  "xor"                       { return XOR; }
  "andor"                     { return ANDOR; }
  "not"                       { return NOT; }
  "shl"                       { return SHL; }
  "shr"                       { return SHR; }

  // Math ops
  "add"                       { return ADD; }
  "sub"                       { return SUB; }
  "sub_rev"                   { return SUB_REV; }
  "incr"                      { return INCR; }
  "decr"                      { return DECR; }
  "neg"                       { return NEG; }
  "abs"                       { return ABS; }
  "mul"                       { return MUL; }
  "div"                       { return DIV; }
  "div_rev"                   { return DIV_REV; }
  "mod"                       { return MOD; }
  "mod_rev"                   { return MOD_REV; }
  "mul_12"                    { return MUL_12; }
  "div_12"                    { return DIV_12; }
  "div_12_rev"                { return DIV_12_REV; }
  "sqrt"                      { return SQRT; }
  "rand"                      { return RAND; }
  "sin_12"                    { return SIN_12; }
  "cos_12"                    { return COS_12; }
  "atan2_12"                  { return ATAN2_12; }

  // Jump ops
  "jmp"                       { return JMP; }
  "jmp_table"                 { return JMP_TABLE; }

  // Gosub ops
  "gosub"                     { return GOSUB; }
  "gosub_table"               { return GOSUB_TABLE; }

  // Script ops
  "consume"                   { return CONSUME; }
  "deallocate"                { return DEALLOCATE; }
  "deallocate_other"          { return DEALLOCATE_OTHER; }
  "fork"                      { return FORK; }
  "fork_reenter"              { return FORK_REENTER; }

  // Other ops
  "call"                      { return CALL; }
  "mov"                       { return MOV; }
  "memcpy"                    { return MEMCPY; }

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
  "str"                       { pushState(STRING_EXPECTED); return STR; }

  ","                         { return COMMA; }
  "::"                        { pushState(METHOD_EXPECTED); return DOUBLECOLON; }
  ":"                         { return COLON; }
  "["                         { return LBRACKET; }
  "]"                         { return RBRACKET; }

  {COMMENT}                   { return COMMENT; }
  {CMP}                       { return CMP; }
  {BINOP}                     { return BINOP; }
  {ID}                        { return ID; }
  {DEC}                       { return DEC; }
  {HEX}                       { return HEX; }
}

<STRING_EXPECTED> {
  {STRING}                    { popState(); return STRING; }
}

<METHOD_EXPECTED> {
  {ID}                        { popState(); return METHOD; }
}

[^] { return BAD_CHARACTER; }
