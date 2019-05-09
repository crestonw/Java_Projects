/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Lecture Slides and Example Code 
 */

//Import Statements
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

// The class that extends JPanel and includes the point size buttons and clear button.
public class SizePanel extends JPanel{
	
	// Override class for ActionListener.
	public class clearButtonListener implements ActionListener {
		
		// Method that tells the clear button what to do when clicked.
		public void actionPerformed(ActionEvent e) {
			PaintPanel.points.removeAll(PaintPanel.points);
			PaintFrame.paintPanel.repaint();	
		}
	}

	// Override class for ActionListener.
	public class largeButtonListener implements ActionListener {
		
		// Method that tells the large button what to clicked.
		public void actionPerformed(ActionEvent e) {
			PaintPanel.pointSize = 20;
		}
	}

	// Override class for ActionListener.
	public class mediumButtonListener implements ActionListener {
		
		// Method that tells the medium button what to do when clicked.
		public void actionPerformed(ActionEvent e) {
			PaintPanel.pointSize = 10;		}
	}

	// Override class for ActionListener.
	public class smallButtonListener implements ActionListener {
		
		// Method that tells the small button what to do when clicked.
		public void actionPerformed(ActionEvent e) {
			PaintPanel.pointSize = 5;		}
	}

	// Private instance variables declared.
	private final JButton smallButton;
	private final JButton mediumButton;
	private final JButton largeButton;
	private final JButton clearButton;
	
	// Constructor for SizePanel class.
	public SizePanel() {
		
		// Sets the GridLayout to be 4 rows by 1 column with 50 pixel vertical gap between buttons.
		setLayout(new GridLayout(4,1, 0 , 50));
		
		// Small button initialized and added to grid.
		smallButton = new JButton("Small");
		smallButton.setMinimumSize(new Dimension(150,150));
		smallButton.addActionListener(new smallButtonListener());
		add(smallButton);
		
		// Medium button initialized and added to grid.
		mediumButton = new JButton("Medium");
		mediumButton.setMinimumSize(new Dimension(150,150));
		mediumButton.addActionListener(new mediumButtonListener());
		add(mediumButton);
		
		// Large button initialized and added to grid.
		largeButton = new JButton("Large");
		largeButton.setMinimumSize(new Dimension(150,150));
		largeButton.addActionListener(new largeButtonListener());
		add(largeButton);
		
		// Clear button initialized and added to grid.
		clearButton = new JButton("Clear");
		clearButton.setMinimumSize(new Dimension(150,150));
		clearButton.addActionListener(new clearButtonListener());
		add(clearButton);			
				
	}
}
