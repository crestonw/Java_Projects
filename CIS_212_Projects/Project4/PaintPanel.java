/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Lecture Slides and Example Code 
 */

// Import Statements.
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

// PaintPanel class that extends JPanel and creates the JPanel for which the points are drawn on.
public class PaintPanel extends JPanel {
	
	// Public Instance Variables.
	public static ArrayList<Point> points;
	public static Color pointColor = new Color(0,0,0);
	public static int pointSize = 5;
	
	// Constructor class for PointPanel.
	public PaintPanel() {
		
		points = new ArrayList<Point>();
		
		// MouseAdapter generated that will keep track of our mouse activity.
		MouseAdapter adapter = new MouseAdapter() {
			
			// Override of mouseDragged method that creates a point of type Paint every time the mouse is drug across the JPanel.
		    public void mouseDragged(MouseEvent e){
				Point point = new Point(e.getX(), e.getY(), pointColor, pointSize);
				points.add(point);
				repaint();
			}
		};
		
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
	}
	
	// Override the paintComponent so that the points inside our ArrayList of points are drawn on JPanel.
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		for (Point point : points) {
			point.draw(g);
		}
	}
}