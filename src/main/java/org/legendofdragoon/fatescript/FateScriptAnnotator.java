package org.legendofdragoon.fatescript;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.legendofdragoon.fatescript.psi.FateScriptKeywordOps;
import org.legendofdragoon.fatescript.psi.FateScriptVisitor;

public class FateScriptAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull final AnnotationHolder holder) {
        element.accept(new FateScriptVisitor() {
            @Override
            public void visitKeywordOps(@NotNull FateScriptKeywordOps o) {
                super.visitKeywordOps(o);
                setHighlighting(o, holder, FateScriptSyntaxHighlighter.KEYWORD_OPS);
            }
        });
    }

    private static void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                        @NotNull TextAttributesKey key) {
        holder.newAnnotation(HighlightSeverity.INFORMATION, "").textAttributes(key).create();
    }
}
