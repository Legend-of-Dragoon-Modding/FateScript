package org.legendofdragoon.fatescript;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.legendofdragoon.fatescript.psi.FateScriptFile;
import org.legendofdragoon.fatescript.psi.FateScriptLabelHeader;
import org.legendofdragoon.fatescript.psi.FateScriptLabelRef;
import org.legendofdragoon.fatescript.psi.FateScriptLabelRef;

import java.util.*;
import java.util.stream.Collectors;

public class FateScriptUtil {

  /**
   * Searches the current file for a FateScript label ref with the given label.
   *
   * @param file current file
   * @param label     to check
   * @return matching properties
   */
  public static List<FateScriptLabelHeader> findLabelReferences(final FateScriptFile file, final String label) {
    final List<FateScriptLabelHeader> result = new ArrayList<>();
    if (file != null) {
      for (PsiElement child = file.getFirstChild(); child != null; child = child.getNextSibling()) {
        final Collection<FateScriptLabelHeader> labelHeaders = PsiTreeUtil.collectElementsOfType(child, FateScriptLabelHeader.class);
        if (!labelHeaders.isEmpty()) {
          for (final FateScriptLabelHeader labelHeader : labelHeaders) {
            if (label.equals(labelHeader.getLabel().getText())) {
              result.add(labelHeader);
            }
          }
        }
      }
    }
    return result;
  }

  public static List<FateScriptLabelHeader> findLabelReferences(final FateScriptFile file) {
    final List<FateScriptLabelHeader> result = new ArrayList<>();
    if (file != null) {
      for (PsiElement child = file.getFirstChild(); child != null; child = child.getNextSibling()) {
        final Collection<FateScriptLabelHeader> labelHeaders = PsiTreeUtil.collectElementsOfType(child, FateScriptLabelHeader.class);
        if (!labelHeaders.isEmpty()) {
          Collections.addAll(result, labelHeaders.toArray(new FateScriptLabelHeader[labelHeaders.size()]));
        }
      }
    }
    return result;
  }
}