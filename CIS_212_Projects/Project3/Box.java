/*
 * Author: Creston Wilson
 * References: CIS Lecture Slides
 */

public class Box implements Measurable {
	
	
	protected double length = 0;
	protected double width = 0;
	protected double height = 0;
	
	// Constructor.
	public Box(double length, double width, double height) {
		
		// Setting new values for the protected variables.
		this.length = length;
		this.width = width;
		this.height = height;
		
	}
	
	// Override for the method implemented in the interface.
	public double getArea() {
		
		// Returns the area of the Box.
		return (length * width * height);
		
	}
	
}
