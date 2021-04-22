/**
 *
 */
package org.vaccs.eevis.ast;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import org.vaccs.eevis.parser.CParser.AssignmentContext;
import org.vaccs.eevis.parser.CParser.CastExpressionContext;
import org.vaccs.eevis.parser.CParser.DeclarationContext;
import org.vaccs.eevis.parser.CParser.ExpressionContext;
import org.vaccs.eevis.parser.CParser.MultiplicativeExpressionContext;
import org.vaccs.eevis.parser.CParser.NumberContext;
import org.vaccs.eevis.parser.CParser.PrimaryExpressionContext;
import org.vaccs.eevis.parser.CParser.ProgramContext;
import org.vaccs.eevis.parser.CParser.SignNameContext;
import org.vaccs.eevis.parser.CParser.TypeCastContext;
import org.vaccs.eevis.parser.CParser.TypeDeclContext;
import org.vaccs.eevis.parser.CParser.TypeNameContext;
import org.vaccs.eevis.parser.CParser.UnaryExpressionContext;
import org.vaccs.eevis.parser.CParser.VarDeclContext;
import org.vaccs.eevis.parser.CParser;
import org.vaccs.eevis.parser.CVisitor;

/**
 * @author carr
 *
 */
public class ASTGenerator implements CVisitor<ASTNode> {

	ASTNodeFactory factory = null;
	/**
	 *
	 */
	public ASTGenerator() {
		factory = new ASTNodeFactory();
	}

	@Override
	public ASTNode visit(ParseTree arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visitChildren(RuleNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visitProgram(ProgramContext ctx) {
		ASTNode n = factory.makeASTNode("Program")
				.addLineNumber(ctx.start.getLine());

    for (ParseTree t : ctx.varDecl()) {
      n.putChild(t.accept(this));
    }

		n.putChild(ctx.assignment().accept(this));

    return n;
	}

	@Override
	public ASTNode visitVarDecl(VarDeclContext ctx) {
		ASTNode n = factory.makeASTNode("VariableDeclaration")
						.addChild(ctx.typeDecl().accept(this))
						.addLineNumber(ctx.start.getLine());

		for (ParseTree t : ctx.declaration())
			n.putChild(t.accept(this));

		return n;
	}

	@Override
	public ASTNode visitTypeDecl(TypeDeclContext ctx) {
		ASTNode n = factory.makeASTNode("TypeSpecification")
				.addLineNumber(ctx.start.getLine());

		return (ctx.signName() == null) ?
				n.addChild(factory.makeASTNode("Signed"))
					.addChild(ctx.typeName().accept(this)) :
				n.addChild(ctx.signName().accept(this)).addChild(ctx.typeName().accept(this));

	}

	@Override
	public ASTNode visitSignName(SignNameContext ctx) {
		ASTNode n = null;

		if (ctx.SIGNED() != null)
			n = factory.makeASTNode("Signed")
					.addLineNumber(ctx.start.getLine());
		else
			n = factory.makeASTNode("Unsigned")
					.addLineNumber(ctx.start.getLine());

		return n;
	}

	@Override
	public ASTNode visitTypeName(TypeNameContext ctx) {
		ASTNode n = null;

		if (ctx.CHAR() != null)
			n = factory.makeASTNode("CByte")
					.addLineNumber(ctx.start.getLine());
		else if (ctx.SHORT() != null)
			n = factory.makeASTNode("CShort")
					.addLineNumber(ctx.start.getLine());
		else if (ctx.INT() != null)
			n = factory.makeASTNode("CInt")
					.addLineNumber(ctx.start.getLine());
		else
			n = factory.makeASTNode("CLong")
					.addLineNumber(ctx.start.getLine());

		return n;
	}

	@Override
	public ASTNode visitDeclaration(DeclarationContext ctx) {
		ASTNode n = null;

		if (ctx.ASSIGN() == null)
			n = factory.makeASTNode("IdDecl")
			.addLabel(ctx.ID().getText())
			.addLineNumber(ctx.start.getLine());
		else {
			Constant c = (Constant)ctx.number().accept(this);

			if (ctx.MINUS() != null)
				c.negateConstant();

			n = factory.makeASTNode("InitializeDecl")
					.addChild(factory.makeASTNode("IdDecl")
								.addLabel(ctx.ID().getText())
								.addLineNumber(ctx.start.getLine()))
					.addChild(c)
					.addLineNumber(ctx.start.getLine());
		}

		return n;
	}

	@Override
	public ASTNode visitAssignment(AssignmentContext ctx) {
		return factory.makeASTNode("Assignment")
					.addChild(factory.makeASTNode("IdDef")
								.addLabel(ctx.ID().getText())
								.addLineNumber(ctx.start.getLine()))
					.addChild(ctx.expression().accept(this))
					.addLineNumber(ctx.start.getLine());
	}

	@Override
	public ASTNode visitExpression(ExpressionContext ctx) {
		ASTNode n = null;

		if (ctx.PLUS() != null)
			n = factory.makeASTNode("Add")
					.addChild(ctx.expression().accept(this))
					.addChild(ctx.multiplicativeExpression().accept(this))
					.addLineNumber(ctx.start.getLine());
		else if (ctx.MINUS() != null)
			n = factory.makeASTNode("Subtract")
					.addChild(ctx.expression().accept(this))
					.addChild(ctx.multiplicativeExpression().accept(this))
					.addLineNumber(ctx.start.getLine());
		else
			n = ctx.multiplicativeExpression().accept(this);

		return n;
	}

	@Override
	public ASTNode visitMultiplicativeExpression(MultiplicativeExpressionContext ctx) {
		ASTNode n = null;

		if (ctx.TIMES() != null)
			n = factory.makeASTNode("Multiply")
					.addChild(ctx.multiplicativeExpression().accept(this))
					.addChild(ctx.castExpression().accept(this))
					.addLineNumber(ctx.start.getLine());
		else if (ctx.DIVIDE() != null)
			n = factory.makeASTNode("Divide")
					.addChild(ctx.multiplicativeExpression().accept(this))
					.addChild(ctx.castExpression().accept(this))
					.addLineNumber(ctx.start.getLine());
		else
			n = ctx.castExpression().accept(this);

		return n;
	}

	@Override
	public ASTNode visitCastExpression(CastExpressionContext ctx) {
		ASTNode n = null;

		if (ctx.typeCast() != null)
			n = factory.makeASTNode("TypeCast")
					.addChild(ctx.typeCast().accept(this))
					.addChild(ctx.expression().accept(this))
					.addLineNumber(ctx.start.getLine());
		else
			n = ctx.unaryExpression().accept(this);

		return n;
	}

	@Override
	public ASTNode visitPrimaryExpression(PrimaryExpressionContext ctx) {
		ASTNode n = null;

		if (ctx.ID() != null)
			n = factory.makeASTNode("IdRef")
					.addLabel(ctx.ID().getText())
					.addLineNumber(ctx.start.getLine());
		else if (ctx.number() != null)
			n = ctx.number().accept(this);
		else {
			n = ctx.expression().accept(this);
			n.setParens();
		}

		return n;
	}

	@Override
	public ASTNode visitNumber(NumberContext ctx) {
		ASTNode n = null;
		if (ctx.NUM() != null)
				n = factory.makeASTNode("Constant")
						.addLabel(ctx.NUM().getText())
						.addLineNumber(ctx.start.getLine());
		else
			n = factory.makeASTNode("Constant")
					.addLabel(Long.toString(Long.parseLong(ctx.HEXNUM().getText().substring(2),16)))
					.addLineNumber(ctx.start.getLine());
		return n;
	}

	@Override
	public ASTNode visitTypeCast(TypeCastContext ctx) {
		return ctx.typeDecl().accept(this);
	}

	@Override
	public ASTNode visitUnaryExpression(UnaryExpressionContext ctx) {
		ASTNode n = null;

		if (ctx.MINUS() != null) {
			ASTNode c = ctx.unaryExpression().accept(this);
			// if this unary minus is applied to a constant, just negate the constant
			if (c instanceof Constant) {
				((Constant) c).negateConstant();
				n = c;
			} else
				n = factory.makeASTNode("UnaryMinus")
					.addChild(c)
					.addLineNumber(ctx.start.getLine());
		}
		else
			n = ctx.primaryExpression().accept(this);


		return n;
	}
}
