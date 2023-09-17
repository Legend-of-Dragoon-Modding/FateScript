package org.legendofdragoon.fatescript.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.legendofdragoon.fatescript.FateScriptLanguage;

public class FateScriptElementType extends IElementType {
  public FateScriptElementType(@NonNls @NotNull final String debugName) {
    super(debugName, FateScriptLanguage.INSTANCE);
  }
}
