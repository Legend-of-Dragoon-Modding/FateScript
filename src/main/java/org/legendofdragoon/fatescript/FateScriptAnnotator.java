package org.legendofdragoon.fatescript;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.legendofdragoon.fatescript.psi.*;

public class FateScriptAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull final AnnotationHolder holder) {
        element.accept(new FateScriptVisitor() {
            @Override
            public void visitKeywordOps(@NotNull FateScriptKeywordOps o) {
                super.visitKeywordOps(o);
                setHighlighting(o, holder, FateScriptSyntaxHighlighter.KEYWORD_OPS);
            }

            @Override
            public void visitLabel(@NotNull FateScriptLabel o) {
                super.visitLabel(o);
                setHighlighting(o, holder, FateScriptSyntaxHighlighter.LABEL);
            }

            @Override
            public void visitLabelRefLabel(@NotNull FateScriptLabelRefLabel o) {
                super.visitLabelRefLabel(o);
                setHighlighting(o, holder, FateScriptSyntaxHighlighter.LABEL);
            }
        });
    }

    private static void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                        @NotNull TextAttributesKey key) {
        holder.newAnnotation(HighlightSeverity.INFORMATION, "").textAttributes(key).create();
    }
}
