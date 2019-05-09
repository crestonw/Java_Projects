import java.io.PrintWriter;

public abstract class Node {
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	abstract public String toString();
	abstract public String inorderWalk(PrintWriter writer);
}
