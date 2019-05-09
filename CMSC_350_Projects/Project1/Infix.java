import java.util.Stack;
import java.util.StringTokenizer;

/*
 * The Infix class is in charge of taking an expression and calculating the result. It
 * makes use of the DivideByZero class to throw an exception if it encounters division by
 * zero.
 * 
 * The class has a constructor, calculate method, precedence method, and value method. The constructor will
 * take an equation as a parameter and then tokenize the string on the operators. The class has 6 private variables
 * that keep track of two operands, two Stacks, the tokens from the equation, and an operator.
 */

public class Infix {
	
	private int operand1;
	private int operand2;
	private Stack<Integer> nums;
	private Stack<String> ops;
	private StringTokenizer tokens;
	private String operator;
	
	public Infix(String equation) {
		
		nums = new Stack<Integer>();
		ops = new Stack<String>();
		tokens = new StringTokenizer(equation, "\\s*[()+-/*]\\s*", true);
		
	}
	
	public void calculate() throws DivideByZero {
		while(tokens.hasMoreTokens()) {
					
					String nextToken = tokens.nextToken().trim();
					
					if (nextToken.matches("\\d+")) {
						nums.push(Integer.parseInt(nextToken));
					}
					
					else if (nextToken.matches("[(]")) {
						ops.push(nextToken);
					}
					
					else if (nextToken.matches("[)]")) {
						while ((ops.peek().matches("[(]")) && (!ops.empty()) && (nums.size() > 1)) {
							operator = ops.pop();
							operand2 = nums.pop();
							operand1 = nums.pop();
								
							switch(operator) {

							case "+":
								nums.push(operand1 + operand2);
								break;
										
							case "-":
								nums.push(operand1 - operand2);
								break;
											
							case "/":
								if (operand2 == 0) {
									throw new DivideByZero();
								}
								nums.push(operand1 / operand2);						
								break;
										
							case "*":
								nums.push(operand1 * operand2);						
								break;
											
							}
						}
					}
										
					else if (nextToken.matches("[+-/*()]")){
						while ((!ops.empty()) && (precedence(ops.peek()) >= precedence(nextToken)) && (nums.size() > 1)) {
							
							operand2 = nums.pop();
							operand1 = nums.pop();
							operator = ops.pop();
							
							switch(operator) {
							case "+":
								nums.push(operand1 + operand2);
								break;
							
							case "-":
								nums.push(operand1 - operand2);
								break;
								
							case "/":
								if (operand2 == 0) {
									throw new DivideByZero();
								}
								nums.push(operand1 / operand2);
								break;
								
							case "*":
								nums.push(operand1 * operand2);
								break;
							}
						}
						
						ops.push(nextToken);
					}
			}
	
		while(!ops.empty() && (nums.size() > 1)) {
			
			operator = ops.pop();
			
			if (operator.matches("[(]")) {
				operator = ops.pop();
			}
			
			operand2 = nums.pop();
			operand1 = nums.pop();
			
			
			switch(operator) {
			case "+":
				nums.push(operand1 + operand2);
				break;
			
			case "-":
				nums.push(operand1 - operand2);
				break;
				
			case "/":
				if (operand2 == 0) {
					throw new DivideByZero();
				}
				nums.push(operand1 / operand2);
				break;
				
			case "*":
				nums.push(operand1 * operand2);
				break;	
			}
		}
	}
	
	public int precedence(String op) {
		int value = 0;
		
		switch(op) {
		case "+":
		case "-":
			value = 1;
			break;
		case "/":
		case "*":
			value = 2;
			break;
		}
		return value;
	}
	
	public String value() {
		
		return nums.pop().toString();
		
	}
}
