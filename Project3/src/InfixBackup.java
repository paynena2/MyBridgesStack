@SuppressWarnings({"rawtypes","unchecked", "unused","hiding"})
public class InfixBackup {
	public static void main(String[] args) {
//		String input = args[0];
//		String values = args[1];
		String input = "a+b*c";
		String values = "A=2 B=3 c=7";
		InfixBackup ev = new InfixBackup();
		ev.calculate(input, values);
	}
	public void calculate(String input, String values){
		int plusMinus = 0;
		int multDiv = 1;
		int exp = 2; 
		int par = 3;
		MyStack opStack = new MyStack();
		MyStack prec = new MyStack(); //holds the precedence of the operators associated with opStack
		String postfix = new String();
		/*
		 * Gets the infix expression and converts to postfix
		 */
		for(int x = 0; x < input.length(); x++) {
			char value = input.charAt(x);
			if(value == '+' || value == '-' || value == '*'|| //Checks to see if the values is an operator
					value == '/' || value == '(' || value == '^' || value == ')') {
				/*
				 * If the stack is empty, push the first operator to the stack
				 */
				if(opStack.isEmpty()) {
					/**
					 * Assigns a numbers based on precedence of the operator, higher number = higher precedence
					 */
					if(value == '+' || value == '-') {
						prec.push(plusMinus);
					}
					else if(value == '*' || value == '/') {
						prec.push(multDiv);
					}
					else if(value == '^') {
						prec.push(exp);
					}
					else if(value == '(') {
						prec.push(par);
					}
					opStack.push(value);
				}
				/*
				 * If the stack is not empty, check and see if the precedence is less than or greater
				 * than the precedence of the operator in the stack.
				 */
				else {
					if(value == '+' || value == '-') {
						/*
						 * If the precedence of the scanned operator is lower than the one at the top of the stack,
						 * and the top of the stack is not a parenthesis, 
						 */
						if((int) prec.peek() >= plusMinus) {
							while((int)prec.peek() >= plusMinus && (int) prec.peek() != 3) {
								prec.pop();
								postfix += opStack.pop();
								if(prec.peek() == null) {
									break;
								}
							}
							prec.push(plusMinus);
							opStack.push(value);
						}
						/*
						 * If the precedence is higher, push the operator to
						 * the top of the stack
						 */
						else {
							prec.push(plusMinus);
							opStack.push(value);
						}
					}
					/*
					 * Multiplication and division should pop only exponents or parenthesis
					 */
					if(value == '*' || value == '/') {
						if((int) prec.peek() >= multDiv) {
							while((int)prec.peek() >= multDiv && (int) prec.peek() != 3) {
								prec.pop();
								postfix += opStack.pop();
								if(prec.peek() == null) {
									break;
								}
							}
							prec.push(multDiv);
							opStack.push(value);
						}
						else {
							prec.push(multDiv);
							opStack.push(value);
						}
					}
					/*
					 * Exponents should pop only exponents and parenthesis
					 */
					if(value == '^') {
						if((int) prec.peek() >= exp) {
							while((int)prec.peek() >= exp && (int) prec.peek() != 3) {
								prec.pop();
								postfix += opStack.pop();
								if(prec.peek() == null) {
									break;
								}
							}
							prec.push(exp);
							opStack.push(value);
						}
						else {
							prec.push(exp);
							opStack.push(value);
						}
					}
					/*
					 * if the operator is an open parenthesis push it to the 
					 * stack
					 */
					if(value == '(') {
						prec.push(par);
						opStack.push(value);
					}
					/*
					 * if the scanned operator is a close parenthesis,
					 * pop the stack until an open parenthesis is encountered,
					 * then discard both
					 */
					if(value == ')') {
						while((char)opStack.peek() != '(') {
							postfix += opStack.pop();
							prec.pop();
							if(opStack.peek() == null) {
								throw new IndexOutOfBoundsException("Mismatched parenthesis");
							}
						}
						opStack.pop();
					}
				}
			}
			/*
			 * determines whether the character is a valid letter, a-f, or that pesky white space.
			 */
			else {
				if(value == 'a' || value == 'b' ||value == 'c' ||
						value == 'd' ||value == 'e' ||value == 'f') {
					postfix += value;
				}
				else if(value == ' ') {
					//heck yeah, come at me white space
				}
				else {
					throw new IllegalArgumentException("character not a-f or valid operator");
				}
				
			}
		}
		while(!opStack.isEmpty()) {
			postfix += opStack.pop();
		}
		/*
		 * Gets the values for each letter
		 */
		int a = 0,b = 0,c = 0,d = 0,e = 0,f = 0;
		values = values.toLowerCase();
		for(int y = 0; y < values.length(); y++) {
			if(values.charAt(y) == 'a') {
				if(values.charAt(y+1) == '=') {
					a = Integer.parseInt(values.substring(y+2, y+3));
				}	
			}
			if(values.charAt(y) == 'b') {
				if(values.charAt(y+1) == '=') {
					b = Integer.parseInt(values.substring(y+2, y+3));
				}
			}
			if(values.charAt(y) == 'c') {
				if(values.charAt(y+1) == '=') {
					c = Integer.parseInt(values.substring(y+2, y+3));
				}
			}
			if(values.charAt(y) == 'd') {
				if(values.charAt(y+1) == '=') {
					d = Integer.parseInt(values.substring(y+2, y+3));
				}	
			}
			if(values.charAt(y) == 'e') {
				if(values.charAt(y+1) == '=') {
					e = Integer.parseInt(values.substring(y+2, y+3));
				}	
			}
			if(values.charAt(y) == 'f') {
				if(values.charAt(y+1) == '=') {
					f = Integer.parseInt(values.substring(y+2, y+3));
				}	
			}
		}
		/*
		 * Add code here to solve the postfix expression
		 */
		int loopControl = 2;
		for(int z = 0; z<3; z++) {
			for(int j = 0; j < postfix.length(); j++) {
				if(postfix.charAt(j) == '^' && loopControl == 2) {
					
				}
				if(postfix.charAt(j) == '*' && loopControl == 1) {
					
				}
				if(postfix.charAt(j) == '/' && loopControl == 1) {
					
				}
				if(postfix.charAt(j) == '+' && loopControl == 0) {
					
				}
				if(postfix.charAt(j) == '-' && loopControl == 0) {
					
				}
			}
		}
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(postfix);
	}
}
