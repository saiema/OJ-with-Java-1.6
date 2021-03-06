/*
 * This code was generated by ojc.
 */
/*
 * VerboseClass.java
 *
 * @author   Michiaki Tatsubori
 * @version  %VERSION% %DATE%
 * @see      java.lang.Object
 *
 * COPYRIGHT 1999 by Michiaki Tatsubori, ALL RIGHTS RESERVED.
 */
package examples.verbose;


import openjava.mop.*;
import openjava.ptree.*;
import openjava.syntax.*;


public class VerboseClass extends openjava.mop.OJClass
{

    /* overrides for translation */
    public void translateDefinition()
        throws openjava.mop.MOPException
    {
        openjava.mop.OJMethod[] methods = getDeclaredMethods();
        for (int i = 0; i < methods.length; ++i) {
            openjava.ptree.Statement printer = makeStatement( "java.lang.System.out.println( \"" + methods[i].toString() + " was called\" );" );
            methods[i].getBody().insertElementAt( printer, 0 );
        }
    }

    public VerboseClass( openjava.mop.Environment oj_param0, openjava.mop.OJClass oj_param1, openjava.ptree.ClassDeclaration oj_param2 )
    {
        super( oj_param0, oj_param1, oj_param2 );
    }

    public VerboseClass( java.lang.Class oj_param0, openjava.mop.MetaInfo oj_param1 )
    {
        super( oj_param0, oj_param1 );
    }

}
