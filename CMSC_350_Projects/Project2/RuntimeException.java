
public class RuntimeException extends Exception{
	public RuntimeException(String err) {
		super("Invalid token " + err);
	}
}
