package openjava.ptree;

import openjava.ptree.util.ParseTreeVisitor;

/**
 * Represents a list of {@code Annotation} elements
 * 
 * @author Simon Emmanuel Gutierrez Brida
 * @version 0.1
 */
public class AnnotationsList extends List {
	private static final String LNLN = ParseTreeObject.LN + ParseTreeObject.LN;
	
	/**
	 * Allocates a new AnnotationsList object.
	 *
	 */
	public AnnotationsList() {
		super(LNLN);
	}


	public AnnotationsList(Annotation e0) {
		super(LNLN, (ParseTree) e0);
	}
	
	/**
	 * Gets the specified element at the index.
	 *
	 * @param  n  index
	 */
	public Annotation get(int n) {
		return (Annotation) contents_elementAt(n);
	}

	/**
	 * Adds the specified element after the list
	 * This causes side-effect.
	 *
	 * @param  p  Annotation to be inserted into the list
	 */
	public void add(Annotation p) {
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
				AnnotationsList res = new AnnotationsList();
				for (int i = 0; i < this.size(); i++) {
					Annotation ann = get(i);
					Annotation copy = (Annotation) (ann==null?null:ann.makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					res.add(copy);
				}
				copyObjectIDTo(res);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
