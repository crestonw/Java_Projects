import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This GUI class is in charge of creating the graphical user interface that will
 * utilize both the Infix class to calculate the expression and the DivideByZero class
 * to catch the DivideByZero exception thrown by the Infix class.
 * 
 * The class includes a constructor with 2 text fields, 2 labels, and 1 button that has an
 * action listener to calculate the result. This class also has a main method that will create a frame
 * that has an instance of the GUI in the frame.
 */


public class GUI extends JFrame{
	
	public GUI(String name) {
		
		setLayout(new FlowLayout());
		
		JTextField input = new JTextField(10);
		JTextField result = new JTextField(10);
		
		JButton evaluate = new JButton("Evaluate");
		
		ActionListener evaluator = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == evaluate) {
					
					Infix total = new Infix(input.getText());
					
					try {
						total.calculate();
					}
						
					catch (DivideByZero error) {
						JOptionPane.showMessageDialog(null, error, "DivideByZero Error", JOptionPane.ERROR_MESSAGE);
					}
					
					result.setText(total.value()); 
					
				}
			}			
		};
		
		evaluate.addActionListener(evaluator);
		
		add(new JLabel("Enter Infix Expression"));
		add(input);
		add(evaluate);
		add(new JLabel("Result"));
		add(result);
	}

	public static void main(String[] args) {
		
		GUI frame = new GUI("Infix Expression Evaluator");
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

	}

}
