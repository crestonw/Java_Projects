import java.io.PrintWriter;

public class Operand extends Node {
	
	public Operand(String value) {
		this.setValue(value);
	}
	
	@Override
	public String inorderWalk(PrintWriter writer) {
		return this.getValue();
	}

	public String toString() {
		return this.getValue();
	}
}
