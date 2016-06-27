/*
 * Parameter.java 1.0
 *
 * This interface is made to type ptree-node into
 * the parameter in the body of class.
 *
 * Jun 20, 1997 by mich
 * Sep 29, 1997 by bv
 * Oct 10, 1997 by mich
 *
 * @see openjava.ptree.ParseTree
 * @version 1.0 last updated:  Sep 29, 1997
 * @author  Michiaki Tatsubori
 */
package openjava.ptree;

import openjava.ptree.util.ParseTreeVisitor;

/**
 * The Parameter class represents parameter node of parse tree.
 * Modifiers of parameter are supported from JDK 1.1.
 * The code like:
 * <br><blockquote><pre>
 *     void test( final int i ){
 *         ....
 *     }
 * </pre></blockquote><br>
 * is allowed from JDK 1.1.
 * 
 * The support for Varargs of Java 1.5 is added
 *
 * @see openjava.ptree.ParseTree
 * @see openjava.ptree.NonLeaf
 * @see openjava.ptree.ModifierList
 */
public class Parameter extends NonLeaf {
	boolean isVarargs = false;

	/**
	 * Allocates a new object.
	 *
	 * @param  modfiers  modifier list of the new parameter
	 * @param  type_specifier  type specifier includes array dimension info
	 * @param  declname  the parameter's name, including no array dim.
	 */
	public Parameter(
		ModifierList modiflist,
		TypeName type_specifier,
		String declname,
		boolean isVarargs) {
		super();
		if (modiflist == null) {
			modiflist = new ModifierList();
		}
		set(modiflist, type_specifier, declname);
		this.isVarargs = isVarargs;
	}
	
	// +++++++++++++++++++++++++++++++++++++++
	// +++++++++++++++added (15/09/14) [simon]
	public Parameter(
			ModifierList modiflist,
			TypeName type_specifier,
			String declname) {
			this(modiflist, type_specifier, declname, false);
		}
	// ---------------------------------------
	
	/**
	 * Allocates a new object.
	 * 
	 *
	 * @param type_specifier type specifier includes array dimension info
	 * @param declname the parameter's name, also includes array dim
	 *        arg modfier is null means parameter has no modifier
	 */
	public Parameter(TypeName type_specifier, String declname) {
		this(new ModifierList(), type_specifier, declname, false);
	}

	/**
	 * Gets the modifiers of this parameter.
	 *
	 * @return the modfiers.
	 */
	public ModifierList getModifiers() {
		return (ModifierList) elementAt(0);
	}

	/**
	 * Sets the modifiers of this parameter.
	 *
	 * @param  modifs  the modfiers to set.
	 */
	public void setModifiers(ModifierList modifs) {
		setElementAt(modifs, 0);
	}

	/**
	 * Gets the type specifier of this parameter.
	 *
	 * @return the type specifier.
	 */
	public TypeName getTypeSpecifier() {
		return (TypeName) elementAt(1);
	}

	/**
	 * Sets the type specifier of this parameter.
	 *
	 * @param  tspec  the type specifier to set.
	 */
	public void setTypeSpecifier(TypeName tspec) {
		setElementAt(tspec, 1);
	}

	/**
	 * Gets the variable name of this parameter.
	 *
	 * @return the variable name.
	 */
	public String getVariable() {
		return (String) elementAt(2);
	}

	/**
	 * Sets the variable name of this parameter.
	 *
	 * @param  varname  the variable name to set.
	 */
	public void setVariable(String varname) {
		setElementAt(varname, 2);
	}
	
	/**
	 * If this parameter accepts a variable number of arguments
	 * 
	 * @return a boolean isVarargs
	 */
	public boolean isVarargs(){
		return this.isVarargs;
	}

	public void accept(ParseTreeVisitor v) throws ParseTreeException {
		v.visit(this);
	}
	
	public void setAnnotations(AnnotationsList annotations) {
		setElementAt(annotations, 3);
	}
	
	public AnnotationsList getAnnotations() {
		return (AnnotationsList) elementAt(3);
	}

	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case NODE : {
				AnnotationsList annCopy = (AnnotationsList) (getAnnotations()==null?null:getAnnotations().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				ModifierList modsCopy = (ModifierList) (getModifiers()==null?null:getModifiers().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				TypeName typeSpecCopy = (TypeName) (getTypeSpecifier()==null?null:getTypeSpecifier().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				String varCopy = getVariable();
				/*
				 * 	ModifierList modiflist,
					TypeName type_specifier,
					String declname,
					boolean isVarargs
				 */
				Parameter res = new Parameter(modsCopy, typeSpecCopy, varCopy, isVarargs());
				copyObjectIDTo(res);
				res.setAnnotations(annCopy);
				res.copyAdditionalInfo(this);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
