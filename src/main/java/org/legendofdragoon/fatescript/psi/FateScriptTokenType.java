package org.legendofdragoon.fatescript.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.legendofdragoon.fatescript.FateScriptLanguage;

public class FateScriptTokenType extends IElementType {
  public FateScriptTokenType(@NonNls @NotNull final String debugName) {
    super(debugName, FateScriptLanguage.INSTANCE);
  }

  @Override
  public String toString() {
    return "FateScriptTokenType." + super.toString();
  }
}
