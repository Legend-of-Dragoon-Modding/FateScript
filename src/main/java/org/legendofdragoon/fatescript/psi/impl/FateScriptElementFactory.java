package org.legendofdragoon.fatescript.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import org.legendofdragoon.fatescript.FateScriptFileType;
import org.legendofdragoon.fatescript.psi.FateScriptFile;
import org.legendofdragoon.fatescript.psi.FateScriptLabelHeader;

public class FateScriptElementFactory {
  public static PsiElement createLabel(final Project project, final String name) {
    final FateScriptFile file = createFile(project, name + ':');
    return file.getFirstChild().getFirstChild().getFirstChild();
  }

  public static FateScriptFile createFile(final Project project, final String text) {
    return (FateScriptFile) PsiFileFactory.getInstance(project).
        createFileFromText("dummy.fate", FateScriptFileType.INSTANCE, text);
  }
}