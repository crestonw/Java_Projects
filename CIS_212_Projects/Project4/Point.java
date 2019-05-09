/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Lecture Slides and Example Code 
 */

// Import Statements.
import java.awt.Color;
import java.awt.Graphics;

// Paint class where the point objects are defined.
public class Point {
	
	// Private Instance Variables.
	private final int x_loc;
	private final int y_loc;
	private final Color color;
	private final int size;
	
	// Constructor for Point class that takes the x and y coordinates as an integer, and a Color variable along with a size integer. 
	public Point(int x, int y, Color colorInt, int sizeInt) {
		
		// Variables initialized.
		x_loc = x;
		y_loc = y;
		color = colorInt;
		size = sizeInt;
	}
	
	// Getter for x-coordinate.
	public int getX() {
		return x_loc;
	}

	// Getter for y-coordinate.
	public int getY() {
		return y_loc;
	}

	// Getter for Color variable.
	public Color getColor() {
		return color;
	}
	
	// Getter for size of point variable.
	public int getSize() {
		return size;
	}

	// Method that defines the color and shape of graphic that is referenced to Paint object.
	public void draw(Graphics g) {
		
		g.setColor(color);
		g.fillOval(x_loc, y_loc, size, size);
		
	}
}