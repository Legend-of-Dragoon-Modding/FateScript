// This is a generated file. Not intended for manual editing.
package org.legendofdragoon.fatescript.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FateScriptParam extends PsiElement {

  @Nullable
  FateScriptCmp getCmp();

  @Nullable
  FateScriptInline getInline();

  @Nullable
  FateScriptMethodRef getMethodRef();

  @Nullable
  FateScriptNumber getNumber();

  @Nullable
  FateScriptStorage getStorage();

  @Nullable
  FateScriptVar getVar();

  @Nullable
  PsiElement getId();

}
