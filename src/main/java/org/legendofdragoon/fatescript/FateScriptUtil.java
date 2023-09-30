package org.legendofdragoon.fatescript;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.legendofdragoon.fatescript.psi.FateScriptFile;
import org.legendofdragoon.fatescript.psi.FateScriptLabelRef;
import org.legendofdragoon.fatescript.psi.FateScriptLabelRef;

import java.util.*;

public class FateScriptUtil {

  /**
   * Searches the entire project for FateScript language files with instances of the FateScript property with the given label.
   *
   * @param project current project
   * @param label     to check
   * @return matching properties
   */
  public static List<FateScriptLabelRef> findLabelReferences(final Project project, final String label) {
    final List<FateScriptLabelRef> result = new ArrayList<>();
    final Collection<VirtualFile> virtualFiles =
        FileTypeIndex.getFiles(FateScriptFileType.INSTANCE, GlobalSearchScope.allScope(project));
    for (final VirtualFile virtualFile : virtualFiles) {
      final FateScriptFile fateScriptFile = (FateScriptFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (fateScriptFile != null) {
        final FateScriptLabelRef[] labelRefs = PsiTreeUtil.getChildrenOfType(fateScriptFile, FateScriptLabelRef.class);
        if (labelRefs != null) {
          for (final FateScriptLabelRef labelRef : labelRefs) {
            if (label.equals(labelRef.getText())) {
              result.add(labelRef);
            }
          }
        }
      }
    }
    return result;
  }

  public static List<FateScriptLabelRef> findLabelReferences(final Project project) {
    final List<FateScriptLabelRef> result = new ArrayList<>();
    final Collection<VirtualFile> virtualFiles =
        FileTypeIndex.getFiles(FateScriptFileType.INSTANCE, GlobalSearchScope.allScope(project));
    for (final VirtualFile virtualFile : virtualFiles) {
      final FateScriptFile fateScripFile = (FateScriptFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (fateScripFile != null) {
        final FateScriptLabelRef[] labelRefs = PsiTreeUtil.getChildrenOfType(fateScripFile, FateScriptLabelRef.class);
        if (labelRefs != null) {
          Collections.addAll(result, labelRefs);
        }
      }
    }
    return result;
  }
}