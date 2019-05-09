/*
 * Author: Creston Wilson
 * References: CIS212 Lecture Slides
 */
public class Sphere implements Measurable {

	// Initializing and setting a protected class variable.
	protected double radius = 0;
	
	// Constructor.
	public Sphere(double radius) {
		
		// Set new value for the class variable.
		this.radius = radius;
				
	}
	
	// Override for the method implemented in the interface.
	public double getArea() {
		
		// Return the area of the Sphere.
		return (4 * Math.PI * Math.pow(radius, 2));
	}

}
