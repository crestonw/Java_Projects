/*
 * Author: Creston Wilson
 * References: CIS212 Lecture Slides
 */
public class Circle implements Measurable {

	// Class protected variable initialized and set.
	protected double radius = 0;
	
	// Constructor.
	public Circle(double radius) {
		
		// Setting new value for protected variable.
		this.radius = radius;
		
	}
	
	// Override for the method implemented in the interface.
	public double getArea() {
		
		// Returns the area of the circle. 
		return (Math.PI * Math.pow(radius, 2));
		
	}

}
