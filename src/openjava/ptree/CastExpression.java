/*
 * CastExpression.java 1.0
 *
 *
 * Jun 20, 1997 by mich
 * Sep 29, 1997 by bv
 * Oct 10, 1997 by mich
 *
 * @see openjava.ptree.ParseTree
 * @version 1.0 last updated:  Oct 10, 1997
 * @author  Michiaki Tatsubori
 */
package openjava.ptree;

import openjava.mop.Environment;
import openjava.mop.OJClass;
import openjava.ptree.util.ParseTreeVisitor;

/**
 * The <code>CastExpression</code> class represents
 * a cast expression of parse tree.
 * <br>
 * If the operator in the expression of the right operand has week unity,
 * this automatically produces the code in which the right operand
 * is enclosed by parenthesises.
 * <br>
 * In the case the caster is <code>int</code> and
 * the right operand to be casted is <code>p + q</code>,
 * this produces the code :
 * <br><blockquote><pre>
 *     (int) (p + q)
 * </pre></blockquote><br>
 *
 * @see openjava.ptree.NonLeaf
 * @see openjava.ptree.Expression
 * @see openjava.ptree.TypeName
 */
public class CastExpression extends NonLeaf implements Expression {
	/**
	 * Allocates a new object.
	 *
	 * @param  ts  the type specifier to cast in this expression.
	 * @param  expr  the expression to be casted in this expression.
	 */
	public CastExpression(TypeName ts, Expression expr) {
		super();
		set((ParseTree) ts, (ParseTree) expr);
	}

	public CastExpression(OJClass type, Expression expr) {
		this(TypeName.forOJClass(type), expr);
	}

	CastExpression() {
		super();
	}

	/**
	 * Gets the type specifier to cast in this expression.
	 *
	 * @return  the type specifier.
	 */
	public TypeName getTypeSpecifier() {
		return (TypeName) elementAt(0);
	}

	/**
	 * Sets the type specifier to cast in this expression.
	 *
	 * @param  tspec  the type specifier.
	 */
	public void setTypeSpecifier(TypeName tspec) {
		setElementAt(tspec, 0);
	}

	/**
	 * Gets the expression of the operand to be casted in this expression.
	 *
	 * @return  the expression.
	 */
	public Expression getExpression() {
		return (Expression) elementAt(1);
	}

	/**
	 * Sets the expression of the operand to be casted in this expression.
	 *
	 * @param  expr  the expression.
	 */
	public void setExpression(Expression expr) {
		setElementAt(expr, 1);
	}

	public void accept(ParseTreeVisitor v) throws ParseTreeException {
		v.visit(this);
	}

	public OJClass getType(Environment env) throws Exception {
		String qname = env.toQualifiedName(getTypeSpecifier().toString());
		return env.lookupClass(qname);
	}

	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case NODE : {
				CastExpression res = (CastExpression) makeCopy_keepOriginalID();
				Expression exprCopy = (Expression) (getExpression()==null?null:getExpression().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				TypeName tnameCopy = (TypeName) (getTypeSpecifier()==null?null:getTypeSpecifier().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				res.setExpression(exprCopy);
				res.setTypeSpecifier(tnameCopy);
				res.copyAdditionalInfo(this);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
