@SuppressWarnings({"unchecked", "rawtypes"})
/**
 * 
 * @author Allen Payne CMSC256 - 002
 * Takes in the infix expressions and values and then solves them.
 * Uses the MyStack class, which implements StackInterface
 */
public class InfixExpressionEvaluator{
	/**
	 * takes command line input and calls calculate method
	 * @param args
	 */
	public static void main(String[] args) {
		String infix = args[0];
		String values = args[1];
		infix = infix.toLowerCase();
		values = values.toLowerCase();
		InfixExpressionEvaluator ex = new InfixExpressionEvaluator();
		int result = ex.calculate(infix, values);
		System.out.println(result);
	}
	/**
	 * Determines the result of the infix expression given by the user with the values given
	 * @param infix
	 * @param values
	 * @return result
	 */
	public int calculate(String infix, String values) {
		MyStack opStack = new MyStack();
		MyStack valueStack = new MyStack();
		char topOperator;
		int opOne;
		int opTwo;
		int result;
		String[] valueArray = values.split(" ");
		int counter = 0;
		/*
		 * Loops through the individual characters in the infix expression to determine
		 * what they are
		 */
		for(int x = 0; x < infix.length(); x++) {
			char nextChar = infix.charAt(x);
			switch(nextChar) {
			case 'a': case 'b': case 'c':
			case 'd': case 'e': case 'f':
				int value = Integer.MAX_VALUE; //arbitrary value
				for(String s : valueArray) {
					/*
					 * Sets the values of the variables as given by the user
					 */
					switch(s.charAt(0)) {
					case 'a':
						if(nextChar == 'a') {
							String[] equals = s.split("=");
							value = Integer.parseInt(equals[1]);
							counter++;
						}
						break;
					case 'b':
						if(nextChar == 'b') {
							String[] equals = s.split("=");
							value = Integer.parseInt(equals[1]);
							counter++;
						}
						break;
					case 'c':
						if(nextChar == 'c') {
							String[] equals = s.split("=");
							value = Integer.parseInt(equals[1]);
							counter++;
						}
						break;
					case 'd':
						if(nextChar == 'd') {
							String[] equals = s.split("=");
							value = Integer.parseInt(equals[1]);
							counter++;
						}
						break;
					case 'e':
						if(nextChar == 'e') {
							String[] equals = s.split("=");
							value = Integer.parseInt(equals[1]);
							counter++;
						}
						break;
					case 'f':
						if(nextChar == 'f') {
							String[] equals = s.split("=");
							value = Integer.parseInt(equals[1]);
							counter++;
						}
						break;
					default: //If a variable is not a-f it shouldn't get here, but just in case.
						throw new IllegalArgumentException("Variable not recognized");
					}
				}
				/*
				 * If the variable is not the max value of an integer as assigned, that means it was changed
				 * and can then be pushed into the stack.
				 */
				if(value != Integer.MAX_VALUE) {
					valueStack.push(value);
				}
				/*
				 * if the value hasn't changed, then there was a variable unaccounted for in the infix expression
				 */
				else {
					throw new IllegalArgumentException("Unrecognized variable in infix expression");
				}
				break;
			/*
			 * if the operator is a (, it is to be pushed to the stack
			 */
			case '(':
				opStack.push(nextChar);
				break;
			/*
			 * If the operator is a ), the stack pops everything until it reaches a (. If it never reaches
			 * a (, and the next operator is null, an exception is thrown because parenthesis mismatched
			 */
			case ')':
				if(!opStack.isEmpty()) {
					topOperator = (char)opStack.pop();
					while(topOperator != '(') {
						opTwo = (int)valueStack.pop();
						opOne = (int)valueStack.pop();
						result = operate(topOperator, opOne, opTwo);
						valueStack.push(result);
						if(opStack.peek() == null) {
							throw new IllegalArgumentException("Invalid parenthesis");
						}
						topOperator = (char)opStack.pop();
					}
				}
				else {
					throw new IllegalArgumentException("Invalid parenthesis");
				}
				break;
			/*
			 * does the operation of the indicated character
			 */
			case '+': case'-': case '*': case '/':
				while(!opStack.isEmpty() && precedence(opStack, nextChar) && (char)opStack.peek() != '(') {
					topOperator = (char)opStack.pop();
					opTwo = (int)valueStack.pop();
					opOne = (int)valueStack.pop();
					result = operate(topOperator, opOne, opTwo);
					valueStack.push(result);
				}
				opStack.push(nextChar);
				break;
			/*
			 * Exponents should be pushed to the stack
			 */
			case '^':
				opStack.push(nextChar);
				break;
			default:
				break;
			}
		}
		/*
		 * Counter is here to make sure that the second string given doesn't have extra values for characters
		 * that are not in the infix expression. eg "a+b" - values "a=2 b=3 c=9"
		 */
		if(counter != valueArray.length) {
			throw new IllegalArgumentException("Too many values");
		}
		while(!opStack.isEmpty()) {
			topOperator = (char)opStack.pop();
			opTwo = (int)valueStack.pop();
			opOne = (int)valueStack.pop();
			result = operate(topOperator, opOne, opTwo);
			valueStack.push(result);
		}
		return (int)valueStack.peek();
	}
	/**
	 * Figures out the operator given by the user and then does the appropriate
	 *  calculation on the two operands given
	 * @param operator
	 * @param opOne
	 * @param opTwo
	 * @return result
	 */
	public int operate(char operator, int opOne, int opTwo) {
		int num1 = opOne;
		int num2 = opTwo;
		int result;
		switch(operator) {
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num1 - num2;
			break;
		case '*':
			result = num1 * num2;
			break;
		case '/':
			result = num1 / num2;
			break;
		case '^':
			result = (int) Math.pow(num1, num2);
			break;
		default:
			if(operator == '(') {
				throw new IllegalArgumentException("Mismatched parenthesis");
			}
			else {
			throw new IllegalArgumentException("unknown operator");
			}
		}
		return result;
	}
	
	/**
	 * Compares the precedence value of the scanned operator and the operator on top of the stack
	 * returns true is the value of the scanned operator is less than or equal to the precedence 
	 * of the operator on top, if it is greater than, returns false
	 * @param opStack
	 * @param nextChar
	 * @return
	 */
	public boolean precedence(MyStack opStack, char nextChar) {
		int charValue;
		int opVal;
		
		switch(nextChar) {
		case '+': case '-':
			charValue = 0;
			break;
		case '*': case '/':
			charValue = 1;
			break;
		case '^':
			charValue = 2;
			break;
		case '(':
			charValue = 3;
			break;
		default:
			throw new IllegalArgumentException();
		}
		
		switch((char)opStack.peek()) {
		case '+': case '-':
			opVal = 0;
			break;
		case '*': case '/':
			opVal = 1;
			break;
		case '^':
			opVal = 2;
			break;
		case '(':
			opVal = 3;
			break;
		default:
			throw new IllegalArgumentException();
		}
		if(charValue <= opVal) {
			return true;
		}
		else {
			return false;
		}
	}
}