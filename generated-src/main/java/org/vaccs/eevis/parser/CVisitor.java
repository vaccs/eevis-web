// Generated from org/vaccs/eevis/parser/C.g4 by ANTLR 4.7.2
package org.vaccs.eevis.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(CParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#typeDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDecl(CParser.TypeDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#signName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignName(CParser.SignNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(CParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(CParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(CParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(CParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#castExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(CParser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(CParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(CParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#typeCast}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeCast(CParser.TypeCastContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(CParser.UnaryExpressionContext ctx);
}