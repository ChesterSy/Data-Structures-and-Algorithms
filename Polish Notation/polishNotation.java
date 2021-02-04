import java.util.Scanner;

public class polishNotation {
	
	static String toPostfix(String str) {
		Stack<Character> stk = new Stack<Character>();
		String post = "";
		for (int i = 0; i < str.length(); i++) {	  
			if (isAlphaOrDigit(str.charAt(i))) {
				post += str.charAt(i);
			} else if (str.charAt(i) == '(') {
				stk.push(str.charAt(i));
			} else if (str.charAt(i) == ')') {
				while (stk.peek() != '(' && !stk.isEmpty()) {
					post += stk.pop();
				}
				if (stk.peek() != '(' || stk.isEmpty()) {
					return "==PARSING ERROR==";
				} else {
					stk.pop();
				}
			} else if ((stk.isEmpty() && isOperator(str.charAt(i))) || stk.peek() == '(') {
				stk.push(str.charAt(i));
			} else if (!stk.isEmpty() && isOperator(str.charAt(i))) {
				while((precedence(stk.peek()) >= precedence(str.charAt(i))) && !stk.isEmpty()) {
					if (stk.peek() == '(') {
						//return "==PARSING ERROR==";
						break;
					}
					post += stk.pop();
					if (stk.peek() == null) {
						break;
					}
				}
				stk.push(str.charAt(i));
			} else {
				return "==PARSING ERROR==";
			}
		}
		while (!stk.isEmpty()) {
			if (stk.peek() == '(') {
				return "==PARSING ERROR==";
			}
			post += stk.pop();
		}
		return post;
	}
	
	static String toPrefix(String str) {
		String reversed = toReverse(str);
		String answer = toPostfix(reversed);
		if (!answer.contains("ERROR")) {
			return toReverse(answer);
		} else {
			return answer;
		}
	}
	
	static int evalPostfix(String str) {
		Stack<Integer> stk = new Stack<Integer>();
		for (int i = 0; i < str.length(); i++) {
			if(Character.isDigit(str.charAt(i))) {
				stk.push(Integer.parseInt(String.valueOf(str.charAt(i))));
			} else {
				int x = stk.pop();
				int y = stk.pop();
				switch(str.charAt(i)) {
					case '+':
						stk.push(y+x);
						break;
					case '-':
						stk.push(y-x);
						break;
					case '*':
						stk.push(y*x);
						break;
					case '/':
						stk.push(y/x);
						break;
					case '^':
						int pow = (int) Math.pow(y,x);
						stk.push(pow);
						break;
				}
			}
		}
		return stk.pop();
	}
	
	static int evalPrefix(String str) {
		Stack<Integer> stk = new Stack<Integer>();
		for (int i = str.length()-1; i >= 0; i--) {
			if (Character.isDigit(str.charAt(i))) {
				stk.push(Integer.parseInt(String.valueOf(str.charAt(i))));
			} else {
				int x = stk.pop();
				int y = stk.pop();
				switch(str.charAt(i)) {
					case '+':
						stk.push(x+y);
						break;
					case '-':
						stk.push(x-y);
						break;
					case '*':
						stk.push(x*y);
						break;
					case '/':
						stk.push(x/y);
						break;
					case '^':
						int pow = (int) Math.pow(x,y);
						stk.push(pow);
						break;
				}
			}
		}
		return stk.pop();
	}
	
	static String postToInfix(String str) {
		Stack<String> stk = new Stack<String>();
		for (int i = 0; i < str.length(); i++) {
			if (isAlphaOrDigit(str.charAt(i))) {
				stk.push(str.charAt(i) + "");
			} else {
				String x = stk.pop();
				String y = stk.pop();
				stk.push("(" + y + str.charAt(i) + x + ")");
			}
		}
		return stk.pop();
	}
	
	static String preToInfix(String str) {
		Stack<String> stk = new Stack<String>();
		for (int i = str.length()-1; i >= 0; i--) {
			if (isAlphaOrDigit(str.charAt(i))) {
				stk.push(str.charAt(i) + "");
			} else {
				String x = stk.pop();
				String y = stk.pop();
				stk.push("(" + x + str.charAt(i) + y + ")");
			}
		}
		return stk.pop();
	}
	
	static String toReverse(String str) {
		char[] arr = str.toCharArray();
		String reverse = "";
		for (int i = str.length()-1; i >= 0; i--) {
			if (arr[i] == '(') {
				reverse += ')';
			} else if (arr[i] == ')') {
				reverse += '(';
			} else {
				reverse += arr[i];
			}
		}
		return reverse;
	}
	
	static int precedence(char c) {
		switch(c) {
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 2;
			case '^':
				return 3;
		}
		return -1;
	}
	
	static boolean isAlphaOrDigit(char c) {
        return (Character.isDigit(c) || Character.isLetter(c));
    }
	
	static boolean isOperator(char c) {
		String op = "+-*/^";
		if (op.indexOf(c) != -1) {
			return true;
		}
		return false;
	}
		
	public static void main (String[] args) {
		String infix = "(7+(4*5))-(2+0)"; //a+b*(c^d-e)^(f+g*h)-i  //2+(4+3*2+1)/3  //5*((9+3)*(4*2)+7)  //(7+(4*5))-(2+0)
		try {
			String output = toPrefix(infix.replaceAll(" ", ""));
			int eval = evalPrefix(output); //this will throw a NullPointerException if operands in infix expression is not a digit
			String in = preToInfix(output);
			System.out.println(output);
			System.out.println(eval);
			System.out.println(in);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("==PARENTHESIS MISMATCH==");
		}
	}
}