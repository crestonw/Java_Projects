/*
 * Author: Creston Wilson
 * References: CIS212 Lecture Slides
 */

public class Rectangle implements Measurable {
	// Two protected variables shared amongst the class.
	protected double length = 0;
	protected double width = 0;
	
	// Constructor.
	public Rectangle(double length, double width) {
		
		// Setting the two protected variables.
		this.length = length;
		this.width = width;
		
	}
	
	// Override for the method implemented in the interface.
	public double getArea() {
		
		return (length * width);
		
	}
}
