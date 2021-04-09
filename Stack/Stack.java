public class Stack<type> {
		
	private static final int MAX_CAPACITY = 50;
	private int top;
	private type[] stk;
	
	public Stack(int capacity) {
		if (capacity <= 0) { //if nagbigay ng lower than 0, yng MAX_CAPACITY
			stk = (type[]) new Object[MAX_CAPACITY];
		} else {
			stk = (type[]) new Object[capacity];
		}
		top = -1;
	}
	
	public Stack() {
		this(MAX_CAPACITY);
	}
	
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}
		return false;
	}
	
	public void clear() {
		for (int i = 0; i < top; i++) {
			stk[i] = null;
		}
		top = -1;
	}
	
	public void push(type el) {
		if (top == stk.length) {
			throw new StackException("Stack Overflow");
		} else {
			stk[++top] = el;
		}
	}
	
	public type pop() {
		type x = peek();
		stk[top] = null;
		top--;
		return x;
	}
	
	public type peek() {
		if(isEmpty()) {
			return null;
		}
		return stk[top];
	}
	
	public String toString() {
      if(isEmpty()) {
		  return "[ ]";
	  }
	  
      StringBuffer out = new StringBuffer("[");
      for(int i = 0; i < top; i++) {
         out.append(stk[i] + ", ");
	  }
	  
      out.append(stk[top] + "]");
      return out.toString();
    }
	/*
	public static void main (String[] args) {
		Stack<Integer> s = new Stack<Integer>(6);

		try {
			for(int i = 0; i < 6; i++) {
				s.push(i);
			}
			//s.clear();
			System.out.println(s.toString());

			for(int i = 0; i < 5; i++) {
				s.pop();
			}
			
			System.out.println(s.toString());
		} catch (StackException e) {
			System.err.println(e);
		}
	}*/
}