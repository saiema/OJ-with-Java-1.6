/*
 * This code was generated by ojc.
 */
package examples.bca;


import java.io.PrintStream;


public class Test implements examples.bca.Writable, examples.bca.Printable
{

    public void write( PrintStream out )
    {
        out.println( "Hello" );
    }

    
    public void print()
    {
        this.write( java.lang.System.out );
    }

}