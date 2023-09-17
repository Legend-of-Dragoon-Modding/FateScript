package org.legendofdragoon.fatescript.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import org.legendofdragoon.fatescript.FateScriptFileType;
import org.legendofdragoon.fatescript.FateScriptLanguage;

public class FateScriptFile extends PsiFileBase {
  public FateScriptFile(@NotNull final FileViewProvider viewProvider) {
    super(viewProvider, FateScriptLanguage.INSTANCE);
  }

  @Override
  public @NotNull FileType getFileType() {
    return FateScriptFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "FateScript File";
  }
}
