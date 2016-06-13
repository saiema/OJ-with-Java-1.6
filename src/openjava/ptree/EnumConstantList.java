package openjava.ptree;

import openjava.ptree.util.ParseTreeVisitor;

public class EnumConstantList extends List {
	private static final String LNLN = ParseTreeObject.LN + ParseTreeObject.LN;
	
	/**
	 * Allocates a new EnumDeclaration object.
	 *
	 */
	public EnumConstantList() {
		super(LNLN);
	}


	public EnumConstantList(EnumConstant e0) {
		super(LNLN, (ParseTree) e0);
	}
	
	/**
	 * Gets the specified element at the index.
	 *
	 * @param  n  index
	 */
	public EnumConstant get(int n) {
		return (EnumConstant) contents_elementAt(n);
	}

	/**
	 * Adds the specified element after the list
	 * This causes side-effect.
	 *
	 * @param  p  ClassDeclaration to be inserted into the list
	 */
	public void add(EnumConstant p) {
		contents_addElement(p);
	}
	
	@Override
	public void accept(ParseTreeVisitor visitor) throws ParseTreeException {
		visitor.visit(this);
		
	}


	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case NODE : {
				EnumConstantList res = (EnumConstantList) makeCopy_keepOriginalID();
				for (int i = 0; i < size(); i++) {
					EnumConstant econst = get(i);
					EnumConstant econstCopy = (EnumConstant) (econst==null?null:econst.makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					res.add(econstCopy);
				}
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
