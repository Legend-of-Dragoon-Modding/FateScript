package org.legendofdragoon.fatescript;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FateScriptFileType extends LanguageFileType {
  public static final FateScriptFileType INSTANCE = new FateScriptFileType();

  protected FateScriptFileType() {
    super(FateScriptLanguage.INSTANCE);
  }

  @Override
  public @NonNls @NotNull String getName() {
    return "FateScript";
  }

  @Override
  public @NotNull String getDescription() {
    return "Legend of Dragoon script file";
  }

  @Override
  public @NotNull String getDefaultExtension() {
    return "fate";
  }

  @Override
  public Icon getIcon() {
    return FateScriptIcons.FILE;
  }
}
