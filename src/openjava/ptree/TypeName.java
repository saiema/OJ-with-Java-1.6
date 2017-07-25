/*
 * TypeName.java 1.0
 *
 * This interface is made to type ptree-node into the type
 * specifier in the body of class.
 *
 * Jun 20, 1997 by mich
 * Sep 29, 1997 by bv
 * Oct 11, 1997 by mich
 * Dec 27, 1998 by mich	
 *
 * @see openjava.ptree.ParseTree
 * @version 1.0 last updated:  Oct 11, 1997
 * @author  Michiaki Tatsubori
 */
package openjava.ptree;

import java.util.Hashtable;

import openjava.mop.OJClass;
import openjava.ptree.util.ParseTreeVisitor;

/**
 * The <code>TypeName</code> class represents a type specifier
 * node of parse tree.
 *
 * @see openjava.ptree.ParseTree
 * @see openjava.ptree.NonLeaf
 */
public class TypeName extends NonLeaf {
	Hashtable suffixes;

	int dim;
	
	private boolean constructedFromOJClass = false;

	public TypeName(String typename, int n, Hashtable suffixes) {
		super();
		set(typename);
		setDimension(n);
		this.suffixes = suffixes;
	}

	/**
	 * Allocates a new object.
	 *
	 * @param typename type name
	 * @param n array dimension
	 */
	public TypeName(String typename, int n) {
		this(typename, n, null);
	}

	/**
	 * Allocates a new object.
	 *
	 * @param typename type name
	 * @param n array dimension
	 */
	public TypeName(String typename, Hashtable suffixes) {
		this(typename, 0, suffixes);
	}

	/**
	 * Allocates a new object.
	 * No array dimension will set.
	 * <br><blockquote><pre>
	 *     new TypeName( typename )
	 * </pre></blockquote><br>
	 * equals:
	 * <br><blockquote><pre>
	 *     new TypeName( typename, 0 )
	 * </pre></blockquote><br>
	 *
	 * @param typename type name
	 */
	public TypeName(String typename) {
		this(typename, 0, null);
	}

	TypeName() {
		super();
	}

	public static TypeName forOJClass(OJClass clazz) {
		int demension = 0;
		while (clazz.isArray()) {
			++demension;
			clazz = clazz.getComponentType();
		}
		String name = clazz.getName();
		TypeName res = new TypeName(name, demension); 
		res.constructedFromOJClass(true);
		return res;
	}
	
	private void constructedFromOJClass(boolean b) {
		this.constructedFromOJClass = b;
	}
	
	public boolean constructedFromOJClass() {
		return this.constructedFromOJClass;
	}

	public ParseTree makeRecursiveCopy() {
		TypeName result = (TypeName) super.makeRecursiveCopy();
		result.dim = this.dim;
		result.suffixes = this.suffixes;
		return result;
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++added (08/09/14) [simon]
	
	public ParseTree makeRecursiveCopy_keepOriginalID() {
		TypeName result = (TypeName) super.makeRecursiveCopy_keepOriginalID();
		result.dim = this.dim;
		result.suffixes = this.suffixes;
		return result;
	}
	
	//------------------------------------------------------------------

	public ParseTree makeCopy() {
		TypeName result = (TypeName) super.makeCopy();
		result.dim = this.dim;
		result.suffixes = this.suffixes;
		return result;
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++added (08/09/14) [simon]
	
	public ParseTree makeCopy_keepOriginalID() {
		TypeName result = (TypeName) super.makeCopy_keepOriginalID();
		result.dim = this.dim;
		result.suffixes = this.suffixes;
		return result;
	}
	
	//------------------------------------------------------------------

	/**
	 * Gets array dimension of declarated type
	 *
	 * @return array dimension
	 */
	public int getDimension() {
		return dim;
	}

	/**
	 * Sets array dimension of declarated type
	 *
	 * @param dim array dimension
	 */
	public void setDimension(int n) {
		dim = n;
	}

	public void addDimension(int n) {
		this.dim += n;
	}

	public void addDimension(String dimstr) {
		this.addDimension(dimstr.length() / 2);
	}

	/**
	 * Gets the type name of this type specifiºer.
	 *
	 * @return  the type name.
	 */
	public String getName() {
		String tname = (String) elementAt(0);
		if (!tname.contains("<") && this.getGenerics() != null && !this.getGenerics().isEmpty()) {
			tname += this.getGenerics();
		}
		return tname;
	}
	
	public String getSimpleName() {
		String tname = getName();
		int lastDot = tname.lastIndexOf('.');
		if (lastDot > 0) {
			tname = tname.substring(lastDot+1);
		}
		return tname;
	}
	
	/**
	 * Gets the type name of this type specifier without generics
	 * 
	 * @return the type name without generics
	 */
	public String getNameWithoutGenerics() {
		return (String) elementAt(0);
	}

	/**
	 * Sets the type name of this type specifier.
	 *
	 * @param typename the type name to set.
	 * @deprecated
	 * @see openjava.ptree.TypeName#setName(String)
	 */
	public void setTypeName(String typename) {
		setElementAt(typename, 0);
	}

	/**
	 * Sets the type name except array dimension of this type specifier.
	 *
	 * @param name the type name to set.
	 */
	public void setName(String name) {
		setElementAt(name, 0);
	}

	public static String stringFromDimension(int dimension) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < dimension; ++i) {
			buf.append("[]");
		}
		return buf.toString();
	}

	public static int toDimension(String typename) {
		int result = 0;
		for (int i = typename.length() - 1; 0 < i; i -= 2) {
			if (typename.lastIndexOf(']', i) != i
				|| typename.lastIndexOf('[', i) != i - 1) {
				return result;
			}
			result++;
		}
		return result;
	}

	public void accept(ParseTreeVisitor v) throws ParseTreeException {
		v.visit(this);
	}

	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case NODE : {
				int dimCopy = getDimension();
				String nameCopy = getNameWithoutGenerics();
				/*
				 * String typename, int n, Hashtable suffixes
				 */
				TypeName res = new TypeName(nameCopy, dimCopy, suffixes);
				copyObjectIDTo(res);
				res.copyAdditionalInfo(this);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
