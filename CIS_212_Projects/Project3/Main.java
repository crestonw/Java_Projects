/*
 * Author: Creston Wilson
 * References: CIS212 Lecture Slides
 */

// Import Statements.
import java.util.ArrayList;
import java.util.Random;
import java.lang.Double;


public class Main {

	// Main method
	public static void main(String[] args) {
		
		// Local variables.
		int listLength = 1000;
		int circles = 0;
		int boxes = 0;
		int rectangles = 0;
		int spheres = 0;
		
		// Random number generator.
		Random generator = new Random();
		
		// Initialize the ArrayList variable of Measurables.
		ArrayList<Measurable> list = new ArrayList<Measurable>(listLength);
		
		// A for loop that iterates a 1000 times.
		for (int i = 0; i < listLength; i++) {
			
			// Each iteration, value has a random integer anywhere between 0 and 3
			int value =  generator.nextInt(4);
			
			// Uses a switch to add to ArrayList depending on the variable value.
			switch(value) {
			
			// Rectangle case
			case 0:
				list.add(new Rectangle(nextDouble(), nextDouble()));
				rectangles++;
				break;
				
			// Circle case
			case 1:
				list.add(new Circle(nextDouble()));
				circles++;
				break;
				
			// Sphere case
			case 2:
				list.add(new Sphere(nextDouble()));
				spheres++;
				break;
				
			// Box case
			case 3:
				list.add(new Box(nextDouble(), nextDouble(), nextDouble()));
				boxes++;
				break;
			}
		}
		// Prints out the individual totals of the boxes, circles, rectangles, and spheres.
		System.out.printf("rectangles: %d boxes: %d circles: %d spheres: %d%n\n", rectangles, boxes, circles, spheres);
	
		// Calls calculateSum method to evaluate the sum of all the areas in the ArrayList.
		double sum = calculateSum(list);
		
		// Prints the total sum of areas.
		System.out.printf("sum: %f%n\n", sum);

	}
	
	// Method that generates a random double within the range (0,1).
	private static double nextDouble() {
		
		// Sets and initializes the max and min.
		double min = Double.MIN_VALUE;
		double max = 1.0;
		
		// Random number generator.
		Random generator = new Random();
		
		// Generates a double that is saved in a variable
		double nextDouble = min + (max - min) * generator.nextDouble();
		
		// Double variable is printed.
		return nextDouble;
	
	}
	
	// Method that calculates the sum of all Measurable objects within a given ArrayList.
	private static double calculateSum(ArrayList<Measurable> array) {
		
		// Initialize and set sum.
		double sum =  0;
		
		// Loop through the ArrayList of objects.
		for (Measurable object: array) {
			
			// Add the area to the tally.
			sum += object.getArea();
			
		}
		
		// Return total sum of areas.
		return sum;
		
	}

}
