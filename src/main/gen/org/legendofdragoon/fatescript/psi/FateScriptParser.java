// This is a generated file. Not intended for manual editing.
package org.legendofdragoon.fatescript.psi;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.legendofdragoon.fatescript.psi.FateScriptTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class FateScriptParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  /* ********************************************************** */
  // lineNumber? op !":"
  public static boolean code(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "code")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CODE, "<code>");
    r = code_0(b, l + 1);
    r = r && op(b, l + 1);
    r = r && code_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // lineNumber?
  private static boolean code_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "code_0")) return false;
    lineNumber(b, l + 1);
    return true;
  }

  // !":"
  private static boolean code_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "code_2")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, ":");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "[" (dec | storage "," dec | storage | jumpTable) ("+" dec)? "]"
  public static boolean index(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "index")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INDEX, "<index>");
    r = consumeToken(b, "[");
    r = r && index_1(b, l + 1);
    r = r && index_2(b, l + 1);
    r = r && consumeToken(b, "]");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // dec | storage "," dec | storage | jumpTable
  private static boolean index_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "index_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEC);
    if (!r) r = index_1_1(b, l + 1);
    if (!r) r = storage(b, l + 1);
    if (!r) r = jumpTable(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // storage "," dec
  private static boolean index_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "index_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = storage(b, l + 1);
    r = r && consumeToken(b, ",");
    r = r && consumeToken(b, DEC);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("+" dec)?
  private static boolean index_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "index_2")) return false;
    index_2_0(b, l + 1);
    return true;
  }

  // "+" dec
  private static boolean index_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "index_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "+");
    r = r && consumeToken(b, DEC);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "inl" inlineIndex
  public static boolean inline(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inline")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INLINE, "<inline>");
    r = consumeToken(b, "inl");
    r = r && inlineIndex(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "[" (inlineIndex | jumpTable | jump) "]"
  public static boolean inlineIndex(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inlineIndex")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INLINE_INDEX, "<inline index>");
    r = consumeToken(b, "[");
    r = r && inlineIndex_1(b, l + 1);
    r = r && consumeToken(b, "]");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // inlineIndex | jumpTable | jump
  private static boolean inlineIndex_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inlineIndex_1")) return false;
    boolean r;
    r = inlineIndex(b, l + 1);
    if (!r) r = jumpTable(b, l + 1);
    if (!r) r = jump(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ":" id
  public static boolean jump(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jump")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JUMP, "<jump>");
    r = consumeToken(b, ":");
    r = r && consumeToken(b, ID);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // jump (jumpTable | index)
  public static boolean jumpTable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jumpTable")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JUMP_TABLE, "<jump table>");
    r = jump(b, l + 1);
    r = r && jumpTable_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // jumpTable | index
  private static boolean jumpTable_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jumpTable_1")) return false;
    boolean r;
    r = jumpTable(b, l + 1);
    if (!r) r = index(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // id ":"
  public static boolean label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "label")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    r = r && consumeToken(b, ":");
    exit_section_(b, m, LABEL, r);
    return r;
  }

  /* ********************************************************** */
  // !<<eof>> (code | label)? eol+
  static boolean line(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "line")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = line_0(b, l + 1);
    r = r && line_1(b, l + 1);
    p = r; // pin = 2
    r = r && line_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !<<eof>>
  private static boolean line_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "line_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !eof(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (code | label)?
  private static boolean line_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "line_1")) return false;
    line_1_0(b, l + 1);
    return true;
  }

  // code | label
  private static boolean line_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "line_1_0")) return false;
    boolean r;
    r = code(b, l + 1);
    if (!r) r = label(b, l + 1);
    return r;
  }

  // eol+
  private static boolean line_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "line_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EOL);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "line_2", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // dec | hex
  public static boolean lineNumber(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lineNumber")) return false;
    if (!nextTokenIs(b, "<line number>", DEC, HEX)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LINE_NUMBER, "<line number>");
    r = consumeToken(b, DEC);
    if (!r) r = consumeToken(b, HEX);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // id "::" id
  public static boolean methodRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodRef")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    r = r && consumeToken(b, "::");
    r = r && consumeToken(b, ID);
    exit_section_(b, m, METHOD_REF, r);
    return r;
  }

  /* ********************************************************** */
  // dec | fullhex
  public static boolean number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "number")) return false;
    if (!nextTokenIs(b, "<number>", DEC, FULLHEX)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMBER, "<number>");
    r = consumeToken(b, DEC);
    if (!r) r = consumeToken(b, FULLHEX);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // id params?
  public static boolean op(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "op")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    r = r && op_1(b, l + 1);
    exit_section_(b, m, OP, r);
    return r;
  }

  // params?
  private static boolean op_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "op_1")) return false;
    params(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // methodRef | number | inline | jump | var | storage
  public static boolean param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAM, "<param>");
    r = methodRef(b, l + 1);
    if (!r) r = number(b, l + 1);
    if (!r) r = inline(b, l + 1);
    if (!r) r = jump(b, l + 1);
    if (!r) r = var(b, l + 1);
    if (!r) r = storage(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // param (("," param)+)?
  public static boolean params(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMS, "<params>");
    r = param(b, l + 1);
    r = r && params_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (("," param)+)?
  private static boolean params_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1")) return false;
    params_1_0(b, l + 1);
    return true;
  }

  // ("," param)+
  private static boolean params_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = params_1_0_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!params_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "params_1_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // "," param
  private static boolean params_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ",");
    r = r && param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // line *
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    while (true) {
      int c = current_position_(b);
      if (!line(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "root", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // "stor" index
  public static boolean storage(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "storage")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STORAGE, "<storage>");
    r = consumeToken(b, "stor");
    r = r && index(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "var" index+
  public static boolean var(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR, "<var>");
    r = consumeToken(b, "var");
    r = r && var_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // index+
  private static boolean var_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = index(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!index(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "var_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

}
