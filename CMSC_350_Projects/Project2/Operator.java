import java.io.PrintWriter;
import java.util.ArrayList;

public class Operator extends Node{

	private Node left, right;
	private int registerID = 0;
	
	public Operator(String value, Node right, Node left) {
		this.left = left;
		this.right = right;
		this.setValue(value);
	}
	
	@Override
	public String inorderWalk(PrintWriter writer) {
		
		ArrayList<String> data = new ArrayList<String>();
		
		String leftvalue = left.inorderWalk(writer);
		String rightvalue = right.inorderWalk(writer);
		
		switch(this.getValue()) {
		case "+":
			data.add("ADD " + this.toString() + " " + left.toString() + " " + right.toString());
			break;
		case "-":
			data.add("SUB " + this.toString() + " " + left.toString() + " " + right.toString());
			break;
		case "/":
			data.add("DIV " + this.toString() + " " + left.toString() + " " + right.toString());
			break;
		case "*":
			data.add("MUL " + this.toString() + " " + left.toString() + " " + right.toString());
			break;
		}
		
		for (int i = (data.size()-1); i >= 0; i--) {
			writer.println(data.get(i));
		}
		
		return "(" + leftvalue + " " + this.getValue() + " " + rightvalue + ") ";
	}
	
	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getRegisterID() {
		return registerID;
	}

	public void setRegisterID(int registerID) {
		this.registerID = registerID;
	}

	@Override
	public String toString() {
		return "R"+ registerID;
	}
}
