
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
 * utilize the BuildGraph class construct a directed graph, the FileNameException to catch errors with
 * the files users input, and the CycleDetectedException to detect cycles within the directed graph. 
 * 
 * The class includes a constructor with 2 text fields, 2 labels, and 2 button each have an
 * action listener to calculate the result. There's also a text area to output the result 
 * of a topological result. This class also has a main method that will create a frame
 * that has an instance of the GUI in the frame.
 */


public class GUI extends JFrame{
	
	public GUI(String name) {
		
		
		
		setLayout(new FlowLayout());
		
		JTextField fileName = new JTextField(20);
		JTextField className = new JTextField(20);
		JButton build = new JButton("Build Directed Graph");
		JButton order = new JButton("Topological Order");
		JTextArea output = new JTextArea("This is where the recompilation order is output \n");
		
		output.setSize(600, 100);
		output.setColumns(48);
		output.setRows(10);
		
		
		ActionListener evaluator = new ActionListener() {

			BuildGraph<String> dgraph = null;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				if (e.getSource() == build) {
					try {
						dgraph = new BuildGraph<String>(fileName.getText());
						JOptionPane.showMessageDialog(null, "Graph Built Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
					} catch (FileNameException e1) {
						JOptionPane.showMessageDialog(null,"File Did Not Open", "Message", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if (e.getSource() == order) {
					try {
						dgraph.topoOrder(className.getText());
						String text = "";
						
						for (String s : dgraph.getStack()) {
							text = s + " " + text;
						}

						output.setText(text);
					} catch (CycleDetectedException e1) {
						JOptionPane.showMessageDialog(null,"Circular Dependency Detected", "Message", JOptionPane.ERROR_MESSAGE);
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null,"Class Not Found", "Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};		

		build.addActionListener(evaluator);
		order.addActionListener(evaluator);
		
		add(new JLabel("Input file name"));
		add(fileName);
		add(build);
		add(new JLabel("Class to recompile"));
		add(className);
		add(order);
		add(new JLabel("Recompilation Order"));
		add(output);

	}

	public static void main(String[] args) {
		
		GUI frame = new GUI("Class Dependency Graph");
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

	}

}
