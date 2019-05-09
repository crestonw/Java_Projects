/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Lecture Slides and Example Code 
 */

// Import Statements. 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

// The class that extends JPanel and includes the color buttons that determines the color of the points.
public class ColorPanel extends JPanel {
	
	// Override class for ActionListener.
	public class blackButtonListener implements ActionListener {
		
		// Method that tells the Black button what color to switch to.
		public void actionPerformed(ActionEvent e) {
			PaintPanel.pointColor = new Color(0, 0, 0);
		}
	}
	
	// Override class for ActionListener.
	public class redButtonListener implements ActionListener {
		
		//  Method that tells the Red button what color to switch to.
		public void actionPerformed(ActionEvent e) {
			PaintPanel.pointColor = new Color(255, 0, 0);
		}
	}
	
	// Override class for ActionListener.
	public class blueButtonListener implements ActionListener {
		
		// Method that tells the Blue button what color to switch to.
		public void actionPerformed(ActionEvent e) {
			PaintPanel.pointColor = new Color(0, 0, 255);
		}
	}
	
	// Override class for ActionListener.
	public class greenButtonListener implements ActionListener {
		
		// Method that tells the Green button what color to switch to.
		public void actionPerformed(ActionEvent e) {
			PaintPanel.pointColor = new Color(0, 255, 0);
		}
	}
	
	// Private instance variables.
	private final JButton blackButton;
	private final JButton redButton;
	private final JButton greenButton;
	private final JButton blueButton;
	
	// Constructor for the ColorPanel class.
	public ColorPanel() {
		
		// Sets the GridLayout to be 4 rows by 1 column with 50 pixel vertical gap between buttons.
		setLayout(new GridLayout(4,1, 0, 50));
		
		// Black button initialized and added to grid.
		blackButton = new JButton("Black");
		blackButton.setMinimumSize(new Dimension(150,150));
		blackButton.addActionListener(new blackButtonListener());
		add(blackButton);
		
		// Red button initialized and added to grid.
		redButton = new JButton("Red");
		redButton.setMinimumSize(new Dimension(150,150));
		redButton.addActionListener(new redButtonListener());
		add(redButton);
		
		// Green button initialized and added to grid.
		greenButton = new JButton("Green");
		greenButton.setMinimumSize(new Dimension(150,150));
		greenButton.addActionListener(new greenButtonListener());
		add(greenButton);
		
		// Blue button initialized and added to grid.
		blueButton = new JButton("Blue");
		blueButton.setMinimumSize(new Dimension(150,150));
		blueButton.addActionListener(new blueButtonListener());
		add(blueButton);			
				
	}
}
