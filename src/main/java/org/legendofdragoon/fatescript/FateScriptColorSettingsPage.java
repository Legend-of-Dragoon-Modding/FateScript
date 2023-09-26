package org.legendofdragoon.fatescript;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.swing.*;
import java.util.Map;

public class FateScriptColorSettingsPage implements ColorSettingsPage {

  private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
          new AttributesDescriptor("Number", FateScriptSyntaxHighlighter.NUMBER),
          new AttributesDescriptor("String", FateScriptSyntaxHighlighter.LODSTRING),
          new AttributesDescriptor("Comment", FateScriptSyntaxHighlighter.COMMENT),
          new AttributesDescriptor("Ops//Keyword", FateScriptSyntaxHighlighter.KEYWORD_OPS),
          new AttributesDescriptor("Bad value", FateScriptSyntaxHighlighter.BAD_CHARACTER)
  };

  @Nullable
  @Override
  public Icon getIcon() {
    return FateScriptIcons.FILE;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new FateScriptSyntaxHighlighter();
  }

  @NotNull
  @Override
  public String getDemoText() {
    return """
            ; SUBROUTINE
            LABEL_763:
            call Bttl_800c::scriptGetBentSlot, stor[28], stor[10], 0x0 ; bentIndex, charOrBentSlot, mode
            mov stor[8], var[128 + stor[10]][5] ; source, dest
            mov 0x5, var[128 + stor[10]][0] ; source, dest
            call Bttl_800c::scriptSetBentStat, stor[30], stor[8], SPELL_ID ; bentIndex, value, statIndex
            call Bttl_800f::scriptSetTempSpellStats, stor[30] ; bentIndex
            mov stor[8], var[45][119] ; source, dest
            call Bttl_800c::scriptGetBentStat, stor[30], SPELL_TARGET_TYPE, stor[9] ; bentIndex, statIndex, value
            jmp_cmp &, 0x80, stor[9], inl[:LABEL_764] ; operand, left, right, addr
            call Bttl_800f::scriptCheckPhysicalHit, stor[30], stor[28], stor[9] ; attackerIndex, defenderIndex, hit
            jmp inl[:LABEL_765] ; addr
            LABEL_764:
            yield
            call Bttl_800f::scriptCheckSpellOrStatusHit, stor[30], stor[28], stor[9] ; attackerIndex, defenderIndex, hit
            LABEL_765:
            jmp_cmp ==, 0, stor[9], inl[:LABEL_766] ; operand, right, addr
            gosub inl[:LABEL_767] ; addr
            mov stor[9], var[45][119] ; source, dest
            jmp_cmp ==, 0, stor[8], inl[:LABEL_769] ; operand, right, addr
            call Bttl_800c::scriptGetBentStat, stor[30], STATUS, stor[9] ; bentIndex, statIndex, value
            jmp_cmp !&, 0x8, stor[9], inl[:LABEL_769] ; operand, left, right, addr
            shr 0x1, stor[8] ; right, left
            LABEL_769:
            mov var[45][119], stor[9] ; source, dest
            LABEL_128:
            rel :PTR_1864_0
            data str[<speed=0><colour=5>Hellena<colour=0> is not this way.]
            PTR_1864_3:
            data 0x18
            data 0x1
            return""";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }

  @Override
  public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
    return DESCRIPTORS;
  }

  @Override
  public ColorDescriptor @NotNull [] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "FateScript";
  }

}