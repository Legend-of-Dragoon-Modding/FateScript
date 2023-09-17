package org.legendofdragoon.fatescript;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.legendofdragoon.fatescript.psi.FateScriptFile;
import org.legendofdragoon.fatescript.psi.FateScriptParser;
import org.legendofdragoon.fatescript.psi.FateScriptTokenSets;
import org.legendofdragoon.fatescript.psi.FateScriptTypes;

public class FateScriptParserDefinition implements ParserDefinition {
  public static final IFileElementType FILE = new IFileElementType(FateScriptLanguage.INSTANCE);

  @Override
  public @NotNull Lexer createLexer(final Project project) {
    return new FateScriptLexerAdapter();
  }

  @Override
  public @NotNull PsiParser createParser(final Project project) {
    return new FateScriptParser();
  }

  @Override
  public @NotNull IFileElementType getFileNodeType() {
    return FILE;
  }

  @Override
  public @NotNull TokenSet getCommentTokens() {
    return FateScriptTokenSets.COMMENTS;
  }

  @Override
  public @NotNull TokenSet getStringLiteralElements() {
    return TokenSet.EMPTY;
  }

  @Override
  public @NotNull PsiElement createElement(final ASTNode node) {
    return FateScriptTypes.Factory.createElement(node);
  }

  @Override
  public @NotNull PsiFile createFile(@NotNull final FileViewProvider viewProvider) {
    return new FateScriptFile(viewProvider);
  }
}
