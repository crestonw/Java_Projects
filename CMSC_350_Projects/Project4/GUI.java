
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
		
		JTextField orignal = new JTextField(20);
		JTextField sorted = new JTextField(20);
		JRadioButton ascOrder = new JRadioButton("Ascending Order", true);
		JRadioButton desOrder = new JRadioButton("Descending Order", false);
		JRadioButton integers = new JRadioButton("Integers", true);
		JRadioButton fractions = new JRadioButton("Fractions", false);	
		
		JButton evaluate = new JButton("Preform Sort");
		
		ActionListener evaluator = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == ascOrder) {
					desOrder.setSelected(false);
				}
				
				else if (e.getSource() == desOrder) {
					ascOrder.setSelected(false);
				}
				
				else if (e.getSource() == integers) {
					fractions.setSelected(false);
				}
				
				else if (e.getSource() == fractions) {
					integers.setSelected(false);
				}
				
				String output = "";
				StringTokenizer tokens;
				
				if (e.getSource() == evaluate) {
					try {
						if (integers.isSelected()) {
						
							ArrayList<Integer> intList = new ArrayList<Integer>();
							tokens = new StringTokenizer(orignal.getText());
							Tree<Integer> tree = new Tree<Integer>();
						
							while (tokens.hasMoreTokens()) {
								String token = tokens.nextToken();
								if (token.matches("[^0-9]+")) {
									throw new NumberFormatException();
								}
								intList.add(Integer.parseInt(token));
							}
						
							tree.insertTree(intList);
							tree.inorder(tree.root);
							
							if (desOrder.isSelected()) {
							
								tree.reverse();
								sorted.setText(tree.toString());
							}
						
							else if (ascOrder.isSelected()) {
								sorted.setText(tree.toString());
							}
						}
					
						else if (fractions.isSelected()) {
						
							ArrayList<Fractions> fracList = new ArrayList<Fractions>();
							tokens = new StringTokenizer(orignal.getText());
							Tree<Fractions> tree = new Tree<Fractions>();
						
							while (tokens.hasMoreTokens()) {		
								String fraction = tokens.nextToken();
								if (fraction.matches("[^0-9/]+")) {
									throw new NumberFormatException();
								}
								
								String list[] = fraction.split("/");
								
								if (list.length > 2) {
									throw new NumberFormatException();
								}
								
								fracList.add(new Fractions(Integer.parseInt(list[0]), Integer.parseInt(list[1])));
							}
						
							tree.insertTree(fracList);
							tree.inorder(tree.root);
						
							if (desOrder.isSelected()) {
								tree.reverse();
								sorted.setText(tree.toString());
							}
						
							else if (ascOrder.isSelected()) {
								sorted.setText(tree.toString());
							}
						}
					}
					catch(NumberFormatException error) {
						JOptionPane.showMessageDialog(null, error, "Message", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}			
		};		
		ascOrder.addActionListener(evaluator);
		desOrder.addActionListener(evaluator);
		integers.addActionListener(evaluator);
		fractions.addActionListener(evaluator);
		evaluate.addActionListener(evaluator);
		
		add(new JLabel("Orignal List"));
		add(orignal);
		add(new JLabel("Sorted List"));
		add(sorted);
		add(ascOrder);
		add(desOrder);
		add(integers);
		add(fractions);
		add(evaluate);

	}

	public static void main(String[] args) {
		
		GUI frame = new GUI("Binary Search Tree Sort");
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

	}

}

