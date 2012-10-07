package openjava.test;

import openjava.mop.OJSystem;
import openjava.ptree.CompilationUnit;
import openjava.tools.parser.ParseException;
import openjava.tools.parser.Parser;

public class ParserTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		//String file = "src/openjava/test/Planet.java";
		//String file = "src/openjava/test/VendingMachine.java";
		String file = "src/openjava/test/Box.java";
		//String file = "src/openjava/test/RequestForEnhancement.java";
	      Parser parser = null;
	      try
	      {
	         parser = new Parser(new java.io.FileInputStream( file ) );
	      } 
	      catch ( java.io.FileNotFoundException e ) 
	      {
	         System.err.println( "File " + file + " not found." );
	      }
	      catch (Exception e)
	      {
	    	  e.printStackTrace();
	      }
	      
	      try
	      {
	    	 OJSystem.initConstants();
	         CompilationUnit result = parser.CompilationUnit( OJSystem.env );
	         //System.out.println("result: " + result);

	        // System.out.println("getComment: " + result.getComment());
	         /*
	         for(int i = 0; i < result.getDeclaredImports().length;i++)
	         {
	        	 System.out.println("getDeclaredImports: " + result.getDeclaredImports()[i]);
	         }
	         for(int i = 0; i < result.getContents().length;i++)
	         {
	        	 System.out.println("getContents: " + result.getContents()[i]);
	         }
	          */
	      } 
	      catch (ParseException e) 
	      {
	    	  e.printStackTrace();
	    	  System.out.println(" can't generate parse tree");
	      }
	      catch (Exception e)
	      {
	    	  e.printStackTrace();
	      }

	}

}