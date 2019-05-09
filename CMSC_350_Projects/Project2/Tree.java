import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Tree {

	private String infix;
	private Stack<Operand> nums;
	private Stack<Operator> ops;
	private StringTokenizer tokens;
	
	public Tree(String equation) {
		this.infix = "";
		this.nums = new Stack<Operand>();
		this.ops = new Stack<Operator>();
		this.tokens = new StringTokenizer(equation, "\\s*[+-/*]\\s* && \\s*", true);
		
	}
	
	public String getInfixExpression(PrintWriter writer) throws RuntimeException {
		int count = tokens.countTokens();
		int counter = 0;
		int id = 0;
		
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			System.out.println(token);
			counter++;
			
			if (token.matches("\\s*[^\\sA-Za-z0-9+/*-]\\s*") || token.matches("\\d+[A-Za-z]")) {
				throw new RuntimeException(token);
			}
			
			else if (token.matches("\\d+")) {
				nums.push(new Operand(token));
			}
			
			else if (token.matches("[+-/*]") && (nums.size() > 1)) {
				if (count == counter) {
					Operator op = new Operator(token, nums.pop(), nums.pop());
					op.setRegisterID(id);
					id++;
					ops.push(op);
					this.infix = ops.pop().inorderWalk(writer);
				}
				else {
					Operator op = new Operator(token, nums.pop(), nums.pop());
					op.setRegisterID(id);
					id++;
					ops.push(op);
				}
			}
			
			else if (token.matches("[+-/*]") && (nums.size() == 1)) {
				if (count == counter) {
					Operator op = new Operator(token, ops.pop(), nums.pop());
					ops.push(op);
					op.setRegisterID(id);
					id++;
					this.infix = ops.pop().inorderWalk(writer);
				}
				else {
					Operator op2 = new Operator(token, ops.pop(), nums.pop());
					op2.setRegisterID(id);
					id++;
					ops.push(op2);
				}
			}
			
			else if (count == counter && this.infix == ""){
				Operator op3 = new Operator(token, ops.pop(), ops.pop());
				op3.setRegisterID(id);
				id++;
				ops.push(op3);
				this.infix = ops.pop().inorderWalk(writer);
				
			}
		}
		return infix;
	}

	public String getInfix() {
		return infix;
	}

	public void setInfix(String infix) {
		this.infix = infix;
	}
	
}
