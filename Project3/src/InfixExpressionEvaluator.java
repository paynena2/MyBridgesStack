@SuppressWarnings({"unchecked", "rawtypes"})
public class InfixExpressionEvaluator{
	public static void main(String[] args) {
		String infix = "a*b-c^d";//args[0];
		String values = "a=2 b=-1 c=3 d=2";//args[1];
		InfixExpressionEvaluator ex = new InfixExpressionEvaluator();
		int result = ex.calculate(infix, values);
		System.out.println(result);
	}
	public int calculate(String infix, String values) {
		MyStack opStack = new MyStack();
		MyStack valueStack = new MyStack();
		char topOperator;
		int opOne;
		int opTwo;
		int result;
		String[] valueArray = values.split(" ");
		int counter = 0;
		for(int x = 0; x < infix.length(); x++) {
			char nextChar = infix.charAt(x);
			switch(nextChar) {
			case 'a': case 'b': case 'c':
			case 'd': case 'e': case 'f':
				int value = Integer.MAX_VALUE;
				for(String s : valueArray) {
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
					default:
						throw new IllegalArgumentException("Variable not recognized");
					}
				}
				if(value != Integer.MAX_VALUE) {
					valueStack.push(value);
				}
				else {
					throw new IllegalArgumentException("Unrecognized variable in infix expression");
				}
				break;
			case '(':
				opStack.push(nextChar);
				break;
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
			case '^':
				opStack.push(nextChar);
				break;
			default:
				break;
			}
		}
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