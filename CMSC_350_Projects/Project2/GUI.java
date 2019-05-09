import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
		
		JTextField input = new JTextField(20);
		JTextField result = new JTextField(20);
		
		JButton evaluate = new JButton("Construct Tree");
		
		ActionListener evaluator = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == evaluate) {
					
					Tree tree = new Tree(input.getText());
					
					
					try {
						FileOutputStream fos = new FileOutputStream("output.txt", false);
						
						PrintWriter writer = new PrintWriter(fos);
						tree.getInfixExpression(writer);
						writer.flush();
					}
						
					catch (RuntimeException error) {
						JOptionPane.showMessageDialog(null, error, "Message", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					result.setText(tree.getInfix()); 
					
				}
			}			
		};
		
		evaluate.addActionListener(evaluator);
		
		add(new JLabel("Enter Postfix Expression"));
		add(input);
		add(evaluate);
		add(new JLabel("Infix Expression"));
		add(result);
	}

	public static void main(String[] args) {
		
		GUI frame = new GUI("Three Address Generator");
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

	}

}

