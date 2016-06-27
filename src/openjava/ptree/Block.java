/*
 * Block.java 1.0
 *
 * Jun 11, 1997 by mich
 * Aug 29, 1997 by mich
 *
 * @see openjava.ptree.ParseTree
 * @version last updated: Aug 29, 1997
 * @author  Michiaki Tatsubori
 */
package openjava.ptree;

import openjava.ptree.util.ParseTreeVisitor;

/**
 * The Block class represents a node of parse tree
 * of block statement like :
 * <br><blockquote><pre>
 *     {
 *         int i = 0;
 *         i = f( i );
 *     }
 * </pre></blockquote><br>
 *
 * @see openjava.ptree.NonLeaf
 * @see openjava.ptree.Statement
 */
public class Block extends NonLeaf implements Statement {
	
	private String afterComment = "";
	
	/**
	 * Allocates a new object.
	 *
	 * @param  stmts  statement list.
	 */
	public Block(StatementList stmts) {
		super();
		if (stmts == null)
			stmts = new StatementList();
		set(stmts);
	}

	/**
	 * Allocates a new object with an empty statement list.
	 *
	 */
	public Block() {
		this(new StatementList());
	}

	/**
	 * Gets the statement list of this block.
	 *
	 * @return  the statement list.
	 */
	public StatementList getStatements() {
		return (StatementList) elementAt(0);
	}

	/**
	 * Sets the statement list of this block.
	 *
	 * @param  stmts  the statement list to set.
	 */
	public void setStatements(StatementList stmts) {
		setElementAt(stmts, 0);
	}

	public void accept(ParseTreeVisitor v) throws ParseTreeException {
		v.visit(this);
	}
	
	public void setAfterComment(String comment) {
		if (comment == null) {
			comment = "";
		}
		this.afterComment = comment;
	}
	
	public String getAfterComment() {
		return this.afterComment;
	}

	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case NODE : {
				StatementList stListCopy = (StatementList) (getStatements()==null?null:getStatements().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				String afterCommentsCopy = getAfterComment();
				Block res = new Block(stListCopy);
				res.setAfterComment(afterCommentsCopy);
				copyObjectIDTo(res);
				res.copyAdditionalInfo(this);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
