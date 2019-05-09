/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Lecture Slides and Example Code 
 */

// Import Statements.
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

// Class PaintFrame that extends JFrame and organizes the panels within the frame.
public class PaintFrame extends JFrame {
	
	// Private and Public Instance Variables.
	public static PaintPanel paintPanel;
	private final ColorPanel colorPanel;
	private final SizePanel sizePanel;
	
	// Constructor for PaintFrame class.
	public PaintFrame() {
		
		// Creates a FlowLayout.
		setLayout(new FlowLayout());
		
		// Initializes all the Panels.
		colorPanel = new ColorPanel();
		paintPanel = new PaintPanel();
		sizePanel = new SizePanel();
		
		// Size of PaintPanel is set.
		paintPanel.setPreferredSize(new Dimension(400, 500));
		
		// All the Panels are organized in the layout.
		add(colorPanel, FlowLayout.LEFT);
		add(paintPanel, FlowLayout.CENTER);
		add(sizePanel, FlowLayout.RIGHT);
		
	}
	
}
