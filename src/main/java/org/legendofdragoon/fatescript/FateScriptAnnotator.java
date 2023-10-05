package org.legendofdragoon.fatescript;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.legendofdragoon.fatescript.psi.*;
import org.legendofdragoon.fatescript.psi.impl.LabelReference;

public class FateScriptAnnotator implements Annotator {
  @Override
  public void annotate(@NotNull final PsiElement element, @NotNull final AnnotationHolder holder) {
    element.accept(new FateScriptVisitor() {
      @Override
      public void visitKeywordOps(@NotNull final FateScriptKeywordOps o) {
        super.visitKeywordOps(o);
        setHighlighting(holder, FateScriptSyntaxHighlighter.KEYWORD_OPS);
      }

      @Override
      public void visitScriptOps(@NotNull final FateScriptScriptOps o) {
        super.visitScriptOps(o);
        setHighlighting(holder, FateScriptSyntaxHighlighter.SCRIPT_OPS);
      }

      @Override
      public void visitJumpOps(@NotNull final FateScriptJumpOps o) {
        super.visitJumpOps(o);
        setHighlighting(holder, FateScriptSyntaxHighlighter.JUMP_OPS);
      }

      @Override
      public void visitGosubOps(@NotNull final FateScriptGosubOps o) {
        super.visitGosubOps(o);
        setHighlighting(holder, FateScriptSyntaxHighlighter.GOSUB_OPS);
      }

      @Override
      public void visitLabelRef(@NotNull final FateScriptLabelRef o) {
        super.visitLabelRef(o);
        final LabelReference ref = (LabelReference) o.getReference();
        final FateScriptLabelHeader declaration = (FateScriptLabelHeader) ref.resolve();
        if (declaration == null) {
          holder.newAnnotation(HighlightSeverity.ERROR, "Cannot resolve symbol " + o.getLabel().getText()).range(element.getLastChild()).textAttributes(FateScriptSyntaxHighlighter.MISSING_DECLARATION).create();
        }
      }
    });
  }

  private static void setHighlighting(@NotNull final AnnotationHolder holder, @NotNull final TextAttributesKey key) {
    holder.newSilentAnnotation(HighlightSeverity.INFORMATION).textAttributes(key).create();
  }
}
