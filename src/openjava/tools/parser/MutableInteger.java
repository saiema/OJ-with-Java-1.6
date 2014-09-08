package openjava.tools.parser;

/**
 * A mutable integer
 * 
 * @author Simón Emmanuel Gutiérrez Brida
 * @version 0.1
 */
public class MutableInteger {
	private int value;
	
	public MutableInteger(int value) {
		this.value = value;
	}
	
	public void setIntValue(int value) {
		this.value = value;
	}
	
	public int intValue() {
		return this.value;
	}

}
