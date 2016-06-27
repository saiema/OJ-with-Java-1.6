package openjava.ptree;

import openjava.ptree.util.ParseTreeVisitor;

public class EnumDeclaration extends NonLeaf implements MemberDeclaration{

	/**
	 * Allocates a new EnumDeclaration object.
	 *
	 */
	EnumDeclaration() {
		super();
	}

	/**
	 * An enumeration declaration
	 * @param modifier
	 * @param identifier
	 * @param tn
	 * @param enumConstantsList
	 * @param mdl
	 */
	public EnumDeclaration(ModifierList modifiers, String identifier, TypeName [] tn, EnumConstantList enumConstantsList, MemberDeclarationList mdl) {
		super();
		set(modifiers, identifier, tn, enumConstantsList, mdl);
	}
	
	/**
     * Get the modifier list
     * 
     * @return there is no modifiers, getModifierList returns an empty list.
     */
    public ModifierList getModifiers() {
        return (ModifierList) elementAt(0);
    }
    
	/**
     * set the modifier list
     * 
     */
    public void setModifiers(ModifierList modifiers) {
    	setElementAt(modifiers, 0);
    }
    
	/**
     * Get the identifier of an Enumeration Constant
     * 
     */
    public String getName() {
        return (String) elementAt(1);
    }
    
	/**
     * Get the implementsList of an Enumeration Constant
     * 
     */
    public TypeName[] getImplementsList() {
        return (TypeName[]) elementAt(2);
    }
    
	/**
     * Get the list of Enumeration Constants
     * 
     */
    public EnumConstantList getEnumConstantList() {
        return (EnumConstantList) elementAt(3);
    }
    
	/**
     * Get the an inner class 
     * 
     */
    public MemberDeclarationList getClassBodyDeclaration() {
        return (MemberDeclarationList) elementAt(4);
    }
	
	@Override
	public void accept(ParseTreeVisitor visitor) throws ParseTreeException {
		visitor.visit(this);
		
	}

	@Override
	public ParseTree makeRecursiveCopy_keepOriginalID(COPY_SCOPE scope) {
		switch (scope) {
			case MEMBER_DECLARATION:
			case NODE: {
				MemberDeclarationList cbodyDeclCopy = (MemberDeclarationList) (getClassBodyDeclaration()==null?null:getClassBodyDeclaration().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				EnumConstantList enConstListCopy = (EnumConstantList) (getEnumConstantList()==null?null:getEnumConstantList().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				TypeName[] implListCopy = null;
				if (getImplementsList() != null) {
					int implSize = getImplementsList().length;
					implListCopy = new TypeName[implSize];
					for (int i = 0; i < implSize; i++) {
						TypeName tn = getImplementsList()[i];
						implListCopy[i] = (TypeName) (tn==null?null:tn.makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
					}
				}
				ModifierList modsCopy = (ModifierList) (getModifiers()==null?null:getModifiers().makeRecursiveCopy_keepOriginalID(COPY_SCOPE.NODE));
				String nameCopy = getName();
				EnumDeclaration res = new EnumDeclaration(modsCopy, nameCopy, implListCopy, enConstListCopy, cbodyDeclCopy);
				copyObjectIDTo(res);
				res.copyAdditionalInfo(this);
				return res;
			}
			default : return getParent().makeRecursiveCopy_keepOriginalID(scope);
		}
	}

}
