/*
 * ForStatement.java 1.0
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

import openjava.ptree.util.ParseTreeVisitor;

/**
 * The <code>ForStatement</code> class represents a for statement
 * node of parse tree.
 * <br>
 * The specification of the initialization part may be modified
 * in the later version of OpenJava.
 *
 * @see openjava.ptree.ParseTree
 * @see openjava.ptree.NonLeaf
 * @see openjava.ptree.Statement
 * @see openjava.ptree.Expression
 * @see openjava.ptree.ExpressionList
 * @see openjava.ptree.StatementList
 */
public class ForStatement extends NonLeaf implements Statement, ParseTree {

	private int constructorUsed = -1;
	
	/**
	 * Allocates a new ForStatement object.
	 *
	 */
	ForStatement() {
		super();
	}

	/**
	 * Allocates a new ForStatement object.
	 *
	 * @param  init  the initialization part.
	 * @param  expr  the condition part.
	 * @param  iterator  the increments part.
	 * @param  stmts  the stement list of the body.
	 */
	public ForStatement(
		ExpressionList init,
		Expression expr,
		ExpressionList iterator,
		StatementList stmts) {
		super();
		if (iterator == null)
			iterator = new ExpressionList();
		if (stmts == null)
			stmts = new StatementList();
		set(null, null, init, expr, iterator, stmts, null, null);
		this.constructorUsed = 1;
	}

	public ForStatement(
		TypeName tspec,
		VariableDeclarator[] vdecls,
		Expression expr,
		ExpressionList iterator,
		StatementList stmts) {
		super();
		if (stmts == null)
			stmts = new StatementList();
		if (tspec == null || vdecls == null || vdecls.length == 0) {
			set(null, null, null, expr, iterator, stmts, null);
		} else {
			set(tspec, vdecls, null, expr, iterator, stmts, null, null);
			VariableDeclarator[] varDecls = this.getInitDecls();
			for (VariableDeclarator vd : varDecls) {
				vd.setParent(this);
			}
		}
		this.constructorUsed = 2;
	}
	/***
	 * A constructor for an enhanced for statement
	 * @param modifier
	 * @param tspec
	 * @param identifier
	 * @param expr
	 * @param stmts
	 */
	public ForStatement(
			String modifier,
			TypeName tspec,
			String identifier,
			Expression expr,
			StatementList stmts) {
			super();
			if (stmts == null)
				stmts = new StatementList();
			set(tspec, null, null, expr, null, stmts, identifier, modifier);
			this.constructorUsed = 3;
		}
	
	public void setModifier(String modifier) {
		setElementAt(modifier, 7);
	}

	/**
	 * Gets the identifier part of an enhanced for-statement.
	 *
	 * @return  the initialization part.
	 */
	public String getIdentifier() {
		return (String) elementAt(6);
	}
	
	/**
	 * Gets the modifier part of an enhanced for-statement.
	 *
	 * @return  the initialization part.
	 */
	public String getModifier() {
		return (String) elementAt(7);
	}
	
	/**
	 * Gets the initialization part of this for-statement.
	 *
	 * @return  the initialization part.
	 */
	public ExpressionList getInit() {
		return (ExpressionList) elementAt(2);
	}

	/**
	 * Sets the initialization part of this for-statement.
	 *
	 * @param  init  the initialization part.
	 */
	public void setInit(ExpressionList init) {
		setElementAt(null, 0);
		setElementAt(null, 1);
		setElementAt(init, 2);
	}

	/**
	 * Gets the initialization part of this for-statement.
	 *
	 * @return  the initialization part.
	 */
	public TypeName getInitDeclType() {
		return (TypeName) elementAt(0);
	}

	/**
	 * Gets the initialization part of this for-statement.
	 *
	 * @return  the initialization part.
	 */
	public VariableDeclarator[] getInitDecls() {
		return (VariableDeclarator[]) elementAt(1);
	}

	/**
	 * Sets the initialization part of this for-statement.
	 *
	 * @param  init  the initialization part.
	 */
	public void setInitDecl(
		TypeName type_specifier,
		VariableDeclarator[] init) {
		if (type_specifier == null || init == null || init.length == 0) {
			setElementAt(null, 0);
			setElementAt(null, 1);
		} else {
			setElementAt(type_specifier, 0);
			setElementAt(init, 1);
		}
		setElementAt(null, 2);
	}

	/**
	 * Gets the condition part of this for-statement.
	 *
	 * @return  the expression of the condtion part.
	 */
	public Expression getCondition() {
		return (Expression) elementAt(3);
	}

	/**
	 * Sets the condition part of this for-statement.
	 *
	 * @param  cond  the expression of the condtion part.
	 */
	public void setCondition(Expression cond) {
		setElementAt(cond, 3);
	}

	/**
	 * Gets the increments part of this for-statement.
	 *
	 * @return  the expression list of the increments part.
	 */
	public ExpressionList getIncrement() {
		return (ExpressionList) elementAt(4);
	}

	/**
	 * Sets the increments part of this for-statement.
	 *
	 * @param  incs  the expression list of the increments part.
	 */
	public void setIncrement(ExpressionList incs) {
		if (incs == null)
			incs = new ExpressionList();
		setElementAt(incs, 4);
	}

	/**
	 * Gets the body of this for-statement.
	 *
	 * @return  the statement list of the body.
	 */
	public StatementList getStatements() {
		return (StatementList) elementAt(5);
	}

	/**
	 * Sets the body of this for-statement.
	 *
	 * @param  stmts  the statement list of the body.
	 */
	public void setStatements(StatementList stmts) {
		if (stmts == null)
			stmts = new StatementList();
		setElementAt(stmts, 5);
	}

	public void accept(ParseTreeVisitor v) throws ParseTreeException {
		v.visit(this);
	}
	
	public boolean isEnhancedFor() {
		return constructorUsed == 3;
	}

	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case STATEMENT:
			case NODE: {
				ForStatement res = null;
				if (this.constructorUsed == 1) {
					/*
					 * 	ExpressionList init,
						Expression expr,
						ExpressionList iterator,
						StatementList stmts
					 */
					ExpressionList initCopy = (ExpressionList) (getInit()==null?null:getInit().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					Expression condCopy = (Expression) (getCondition()==null?null:getCondition().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					ExpressionList incCopy = (ExpressionList) (getIncrement()==null?null:getIncrement().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					StatementList bodyCopy = (StatementList) (getStatements()==null?null:getStatements().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					res = new ForStatement(initCopy, condCopy, incCopy, bodyCopy);
					String modsCopy = getModifier();
					res.setModifier(modsCopy);
				} else if (this.constructorUsed == 2) {
					/*
					 * 	TypeName tspec,
						VariableDeclarator[] vdecls,
						Expression expr,
						ExpressionList iterator,
						StatementList stmts
					 */
					TypeName initDeclTypeCopy = (TypeName) (getInitDeclType()==null?null:getInitDeclType().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					VariableDeclarator[] varDeclCopy = null;
					if (getInitDecls() != null) {
						int size = getInitDecls().length;
						varDeclCopy = new VariableDeclarator[size];
						for (int i = 0; i < size; i++) {
							VariableDeclarator vdecl = getInitDecls()[i];
							varDeclCopy[i] = (VariableDeclarator) (vdecl==null?null:vdecl.makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
						}
					}
					Expression condCopy = (Expression) (getCondition()==null?null:getCondition().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					ExpressionList incCopy = (ExpressionList) (getIncrement()==null?null:getIncrement().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					StatementList bodyCopy = (StatementList) (getStatements()==null?null:getStatements().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					res = new ForStatement(initDeclTypeCopy, varDeclCopy, condCopy, incCopy, bodyCopy);
					String modsCopy = getModifier();
					res.setModifier(modsCopy);
				} else if (this.constructorUsed == 3) {
					/*
					 * 	String modifier,
						TypeName tspec,
						String identifier,
						Expression expr,
						StatementList stmts
					 */
					String modsCopy = getModifier();
					TypeName initDeclTypeCopy = (TypeName) (getInitDeclType()==null?null:getInitDeclType().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					String idCopy = getIdentifier();
					Expression condCopy = (Expression) (getCondition()==null?null:getCondition().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					StatementList bodyCopy = (StatementList) (getStatements()==null?null:getStatements().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					res = new ForStatement(modsCopy, initDeclTypeCopy, idCopy, condCopy, bodyCopy);
				} else {
					System.err.println("Error while cloning ForStatement (constructor used is : " + this.constructorUsed + ")");
					return null;
				}
				copyObjectIDTo(res);
				res.copyAdditionalInfo(this);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
