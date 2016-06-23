package openjava.ptree;

/**
 * This class represents java annotations of the form <pre>@annotation(values)</pre>
 * <p>
 * for the moment this annotations are just a string 
 * 
 * @author Simon Emmanuel Gutierrez Brida
 * @version 0.1
 */
public class Annotation extends Leaf {

	public Annotation(String str) {
		super(str);
	}
	
	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case NODE : {
				Annotation res = (Annotation) super.makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
