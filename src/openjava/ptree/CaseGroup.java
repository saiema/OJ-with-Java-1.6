/*
 * CaseGroupjava 1.0
 *
 *
 * Jun 20, 1997 by mich
 * Sep 29, 1997 by mich
 *
 * @see openjava.ptree.ParseTree
 * @version 1.0 last updated:  Sep 29, 1997
 * @author  Michiaki Tatsubori
 */
package openjava.ptree;

import openjava.ptree.util.ParseTreeVisitor;

/**
 *
 */
public class CaseGroup extends NonLeaf {

	public CaseGroup(ExpressionList cll, StatementList bsl) {
		super();
		set(cll, bsl);
	}

	CaseGroup() {
		super();
	}

	public ExpressionList getLabels() {
		return (ExpressionList) elementAt(0);
	}

	public StatementList getStatements() {
		return (StatementList) elementAt(1);
	}

	public void accept(ParseTreeVisitor v) throws ParseTreeException {
		v.visit(this);
	}

	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case NODE : {
				CaseGroup res = (CaseGroup) makeCopy_keepOriginalID();
				ExpressionList labelsCopy = (ExpressionList) (getLabels()==null?null:getLabels().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				StatementList stListCopy = (StatementList) (getStatements()==null?null:getStatements().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				res.set(labelsCopy, stListCopy);
				res.copyAdditionalInfo(this);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
