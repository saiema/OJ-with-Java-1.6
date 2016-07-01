package openjava.ptree;

import openjava.ptree.util.ParseTreeVisitor;

public class AnnotationDeclaration extends NonLeaf implements MemberDeclaration {

	public AnnotationDeclaration(String ad) {
		set(ad);
	}
	
	public String getDeclaration() {
		return (String) elementAt(0);
	}
	
	@Override
	public void accept(ParseTreeVisitor v) throws ParseTreeException {
		v.visit(this);
	}

	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case MEMBER_DECLARATION:
			case STATEMENT:
			case STATEMENT_LIST:
			case NODE : {
				AnnotationDeclaration res = new AnnotationDeclaration(getDeclaration());
				copyObjectIDTo(res);
				res.copyAdditionalInfo(this);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
