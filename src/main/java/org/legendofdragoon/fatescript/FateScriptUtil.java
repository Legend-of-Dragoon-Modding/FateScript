package org.legendofdragoon.fatescript;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.legendofdragoon.fatescript.psi.FateScriptFile;
import org.legendofdragoon.fatescript.psi.FateScriptLabelHeader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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