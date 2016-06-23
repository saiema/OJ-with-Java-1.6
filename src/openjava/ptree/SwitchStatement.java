/*
 * SwitchStatement.java 1.0
 *
 * Jun 20, 1997 mich
 * Sep 29, 1997 bv
 * Oct 11, 1997 mich
 *
 * @see openjava.ptree.ParseTree
 * @version 1.0 last updated:  Oct 11, 1997
 * @author  Michiaki Tatsubori
 */
package openjava.ptree;

import openjava.ptree.util.ParseTreeVisitor;

/**
 * The <code>SwitchStatement</code> class represents switch statement node
 * of parse tree.
 * <br>
 * The specification around <code>CaseGroupList</code> may be
 * modified in the later version of OpenJava.
 *
 * @see openjava.ptree.ParseTree
 * @see openjava.ptree.NonLeaf
 * @see openjava.ptree.Statement
 * @see openjava.ptree.CaseGroupList
 */
public class SwitchStatement extends NonLeaf implements Statement {

	/**
	 * Allocates a new object.
	 *
	 * @param  expr  the condition of this switch statement.
	 * @param  cglist  the list of the group of case and statements.
	 */
	public SwitchStatement(Expression expr, CaseGroupList cglist) {
		super();
		if (cglist == null)
			cglist = new CaseGroupList();
		set((ParseTree) expr, cglist);
	}

	SwitchStatement() {
	}

	/**
	 * Gets the expression of the condition to switch.
	 *
	 * @return  the expression of the condition.
	 */
	public Expression getExpression() {
		return (Expression) elementAt(0);
	}

	/**
	 * Sets the expression of the condition to switch.
	 *
	 * @param  expr  the expression of the condition to set.
	 */
	public void setExpression(Expression expr) {
		setElementAt(expr, 0);
	}

	/**
	 * Gets the case group list.
	 *
	 * @return  the case group list.
	 */
	public CaseGroupList getCaseGroupList() {
		return (CaseGroupList) elementAt(1);
	}

	/**
	 * Sets the case group list.
	 *
	 * @param  cglist  the case group list.
	 */
	public void setCaseGroupList(CaseGroupList cglist) {
		setElementAt(cglist, 1);
	}

	public void accept(ParseTreeVisitor v) throws ParseTreeException {
		v.visit(this);
	}

	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case STATEMENT:
			case NODE : {
				SwitchStatement res = (SwitchStatement) makeCopy_keepOriginalID();
				CaseGroupList cgroupCopy = (CaseGroupList) (getCaseGroupList()==null?null:getCaseGroupList().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				Expression exprCopy = (Expression) (getExpression()==null?null:getExpression().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				res.setCaseGroupList(cgroupCopy);
				res.setExpression(exprCopy);
				res.copyAdditionalInfo(this);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
