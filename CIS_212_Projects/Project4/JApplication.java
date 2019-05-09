/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Lecture Slides and Example Code 
 */

// Import Statements.
import javax.swing.JFrame;

// JApplication class that sets up the window that holds the frame where all the action takes place.
public class JApplication {
	
	// Main method.
	public static void main(String[] args) {
		
		// PaintFrame is initialized, this holds all the Panels.
		PaintFrame mainFrame = new PaintFrame();
		
		// Location of the window is set to pop up in the middle of screen.
		mainFrame.setLocation(175,175);
		
		// Program closes upon clicking the close button.
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Size of window is set.
		mainFrame.setSize(600, 500);
		
		// The GUI is visible.
		mainFrame.setVisible(true);
		
	}
}
