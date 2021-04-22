// Generated from org/vaccs/eevis/parser/C.g4 by ANTLR 4.7.2
package org.vaccs.eevis.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CParser}.
 */
public interface CListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(CParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(CParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#typeDecl}.
	 * @param ctx the parse tree
	 */
	void enterTypeDecl(CParser.TypeDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#typeDecl}.
	 * @param ctx the parse tree
	 */
	void exitTypeDecl(CParser.TypeDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#signName}.
	 * @param ctx the parse tree
	 */
	void enterSignName(CParser.SignNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#signName}.
	 * @param ctx the parse tree
	 */
	void exitSignName(CParser.SignNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(CParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(CParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(CParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(CParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(CParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(CParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(CParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(CParser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(CParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(CParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(CParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(CParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#typeCast}.
	 * @param ctx the parse tree
	 */
	void enterTypeCast(CParser.TypeCastContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#typeCast}.
	 * @param ctx the parse tree
	 */
	void exitTypeCast(CParser.TypeCastContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(CParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(CParser.UnaryExpressionContext ctx);
}