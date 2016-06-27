package openjava.ptree;

import openjava.ptree.util.ParseTreeVisitor;

public class TypeParameterList extends List {
	private static final String LNLN = ParseTreeObject.LN + ParseTreeObject.LN;
	
	/**
	 * Allocates a new TypeParameterList object.
	 *
	 */
	public TypeParameterList() {
		super(LNLN);
	}


	public TypeParameterList(TypeParameter e0) {
		super(LNLN, (ParseTree) e0);
	}
	
	/**
	 * Gets the specified element at the index.
	 *
	 * @param  n  index
	 */
	public TypeParameter get(int n) {
		return (TypeParameter) contents_elementAt(n);
	}

	/**
	 * Adds the specified element after the list
	 * This causes side-effect.
	 *
	 * @param  p  TypeParameter to be inserted into the list
	 */
	public void add(TypeParameter p) {
		contents_addElement(p);
	}
	
	public String toString(){
		if(contents_size() == 0)
			return "";
		String string = "<" + get(0).toString();
		for(int i = 1; i < contents_size();i++){
			string += ", " + get(i).toString();
		}
		return string += ">"; 
	}
	
	public void accept(ParseTreeVisitor visitor) throws ParseTreeException {
		visitor.visit(this);		
	}


	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case NODE : {
				TypeParameterList res = new TypeParameterList();
				for (int i = 0; i < size(); i++) {
					TypeParameter tp = get(i);
					TypeParameter tpCopy = (TypeParameter) (tp==null?null:tp.makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					res.add(tpCopy);
				}
				copyObjectIDTo(res);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
