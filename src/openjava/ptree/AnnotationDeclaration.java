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

}
