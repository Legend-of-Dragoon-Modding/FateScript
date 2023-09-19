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
  // "<=" | "<" | "==" | "!=" | ">" | ">=" | "&" | "!&"
  public static boolean cmp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cmp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CMP, "<cmp>");
    r = consumeToken(b, "<=");
    if (!r) r = consumeToken(b, "<");
    if (!r) r = consumeToken(b, "==");
    if (!r) r = consumeToken(b, "!=");
    if (!r) r = consumeToken(b, ">");
    if (!r) r = consumeToken(b, ">=");
    if (!r) r = consumeToken(b, "&");
    if (!r) r = consumeToken(b, "!&");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // op !":"
  public static boolean code(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "code")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = op(b, l + 1);
    r = r && code_1(b, l + 1);
    exit_section_(b, m, CODE, r);
    return r;
  }

  // !":"
  private static boolean code_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "code_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, ":");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "data" (number | str)
  public static boolean data(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA, "<data>");
    r = consumeToken(b, "data");
    r = r && data_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // number | str
  private static boolean data_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_1")) return false;
    boolean r;
    r = number(b, l + 1);
    if (!r) r = str(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // "entrypoint" labelRef
  public static boolean entrypoint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entrypoint")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENTRYPOINT, "<entrypoint>");
    r = consumeToken(b, "entrypoint");
    r = r && labelRef(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // paraminline1 | paraminline2 | paraminline3 | paraminline6
  public static boolean inline(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inline")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INLINE, "<inline>");
    r = consumeToken(b, PARAMINLINE1);
    if (!r) r = consumeToken(b, PARAMINLINE2);
    if (!r) r = consumeToken(b, PARAMINLINE3);
    if (!r) r = consumeToken(b, PARAMINLINE6);
    exit_section_(b, l, m, r, false, null);
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
  // ":" id
  public static boolean labelRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "labelRef")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL_REF, "<label ref>");
    r = consumeToken(b, ":");
    r = r && consumeToken(b, ID);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !<<eof>> (data | rel | entrypoint | code | label)? eol+
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

  // (data | rel | entrypoint | code | label)?
  private static boolean line_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "line_1")) return false;
    line_1_0(b, l + 1);
    return true;
  }

  // data | rel | entrypoint | code | label
  private static boolean line_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "line_1_0")) return false;
    boolean r;
    r = data(b, l + 1);
    if (!r) r = rel(b, l + 1);
    if (!r) r = entrypoint(b, l + 1);
    if (!r) r = code(b, l + 1);
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
  // dec | hex
  public static boolean number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "number")) return false;
    if (!nextTokenIs(b, "<number>", DEC, HEX)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMBER, "<number>");
    r = consumeToken(b, DEC);
    if (!r) r = consumeToken(b, HEX);
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
  // methodRef | number | inline | var | storage | cmp | id
  public static boolean param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAM, "<param>");
    r = methodRef(b, l + 1);
    if (!r) r = number(b, l + 1);
    if (!r) r = inline(b, l + 1);
    if (!r) r = var(b, l + 1);
    if (!r) r = storage(b, l + 1);
    if (!r) r = cmp(b, l + 1);
    if (!r) r = consumeToken(b, ID);
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
  // "rel" labelRef
  public static boolean rel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rel")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REL, "<rel>");
    r = consumeToken(b, "rel");
    r = r && labelRef(b, l + 1);
    exit_section_(b, l, m, r, false, null);
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
  // paramstorage | paramotherotherstorage | paramotherstorageoffset
  public static boolean storage(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "storage")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STORAGE, "<storage>");
    r = consumeToken(b, PARAMSTORAGE);
    if (!r) r = consumeToken(b, PARAMOTHEROTHERSTORAGE);
    if (!r) r = consumeToken(b, PARAMOTHERSTORAGEOFFSET);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "str[" "]"
  public static boolean str(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "str")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STR, "<str>");
    r = consumeToken(b, "str[");
    r = r && consumeToken(b, "]");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // paramgamevar1 | paramgamevar2 | paramgamevararray1 | paramgamevararray2 | paramgamevararray3 | paramgamevararray4
  public static boolean var(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR, "<var>");
    r = consumeToken(b, PARAMGAMEVAR1);
    if (!r) r = consumeToken(b, PARAMGAMEVAR2);
    if (!r) r = consumeToken(b, PARAMGAMEVARARRAY1);
    if (!r) r = consumeToken(b, PARAMGAMEVARARRAY2);
    if (!r) r = consumeToken(b, PARAMGAMEVARARRAY3);
    if (!r) r = consumeToken(b, PARAMGAMEVARARRAY4);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
