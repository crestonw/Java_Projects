/*
 * This is a simple exception class that only includes a constructor with all
 * the functionality of the Exception class. This exception will specifically
 * be used whenever someone tries to divide by zero.
 */

public class DivideByZero extends Exception{
	public DivideByZero(){
		super("You cannot divide by zero!\n Please enter a valid expression.");
	}
}
