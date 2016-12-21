import java.util.*;
import java.io.*;

/*
 * Jay Shah
 * Assignment 6
 * 
 * 1.) Purpose of this program is to get infix equation from the user and convert it to posftix using 
 * 	   a tree. After that it uses the stack to find the result of the postfix.
 * 2.) Problem: using tree to convert infix to postfix.
 * 		solution: making a tree that will use the infix input and iterates through and sorting into a stack. 
 * 		depending on the operators and operands. 
 * 		problem2: once in postfix equation it must solve it.
 * 		solution: using the same algorithm from assignment 5. we can use stack to go through the postfix 
 * 		expression and solves it, by pushing in operands and popping when coming across operators. Solves it according to 
 * 		operator presidency.
 * 3.) Program is using Stack, and Tree.
 * 4.)	User is prompt to enter an infix equation. If the equation is entered with an x variable then they will have input the x value.
 * 		if no x variable user inputs 1 for the value of x. 
 * 		once that happens it will display the postfix expression of the infix input and displays the result of the equation. 
 * 		User can keep inputing different equation until they input "q" to quit. 
 * 
 *5.)	There is a stackNode class which is used with the tree class to store inputs and convert it to postfix.
 *		
 */

public class expressionTree {
	//stack class used for the tree to store and convert to postfix.
	static class StackNode {
		protected int size;
		protected char[] sArray;
		protected int top;

		public StackNode(int max) {
			this.size = max;
			this.sArray = new char[size];
			top = -1;
		}

		public void push(char ch) {
			this.sArray[++top] = ch;
		}

		public char pop() {
			return this.sArray[top--];
		}

		public char peek() {
			return this.sArray[top];
		}

		public boolean isEmpty() {
			return (top == -1);
		}

	}
//variables for tree(infused with stack) and two string variabls. 
	protected expressionTree.StackNode treeStack;
	protected static String input;
	protected static String output = "";
//expressiontree constructor
	public expressionTree(String input) {
		this.input = input;
		int stackSize = input.length();
		treeStack = new expressionTree.StackNode(stackSize);
	}
	/*
	 * pre- infix input string is introduced to this method
	 * post: converts the infix input to postfix.
	 */

	public String Translate() {
		char ch;
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			switch (ch) {
			case '+':
			case '-':
				operator(ch, 1);
				break;
			case '*':
			case '/':
			case '%':
				operator(ch, 2);
				break;
			case '(':
				treeStack.push(ch);
				break;
			case ')':
				braces(ch);
				break;
			default:
				output = output + ch;
				break;
			}
		}
		while (!treeStack.isEmpty()) {
			output = output + treeStack.pop();
		}
		return output;
	}
/*
 * pre: infix input string character from user input
 * post: if it starts with ( it breaks, if not output is ouput plus whatever is on top of stack.
 */
	private void braces(char ch) {
		while (!treeStack.isEmpty()) {
			char ch1 = treeStack.pop();
			if (ch1 == '(') {
				break;
			} else {
				output = output + ch1;
			}
		}
	}
/*
 * pre: gets the operator of the infix input
 * post: gets in which order the operators should be executed in and pushes it all in the stack.
 */
	private void operator(char ch, int i1) {
		while (!treeStack.isEmpty()) {
			char onTop = treeStack.pop();
			if (onTop == '(') {
				treeStack.push(onTop);
				break;
			} else {
				int i2;
				if (onTop == '+' || onTop == '-') {
					i2 = 1;
				} else {
					i2 = 2;
				}
				if (i2 < i1) {
					treeStack.push(onTop);
					break;
				} else {
					output = output + onTop;
				}
			}
		}
		treeStack.push(ch);

	}
//////////////////////////////////////////////////////////////////////////////////
	/*
	 * main method. to execute what will executed. 
	 * Pre: prompts the user to enter the equation. 
	 * post: gets the infix, shows postifx, and displays results
	 */
	public static void main(String[] args) {							    /////
		Scanner input = new Scanner(System.in);							    /////
																			 ////
		String infix, postfix, valueOfx;									 ////
																			 ////
		do{																	 ////
			System.out.println("Enter the infix equation (q to quit)");		/////
			infix = input.nextLine();										/////
																			/////
			if (!infix.equals("q")){										  ///
				ifStartWithOperator(infix);									  ///
				checkForFloat(infix);										   //
				evenNumberBraces(infix);									  ///
			}																/////
			if(infix.equalsIgnoreCase("q")){								/////
				System.exit(0);											/////////
			}else{														   	  ///
				System.out.println("Enter a value for x");				     ////
				valueOfx = input.nextLine();							     ////
				if(valueOfx.equalsIgnoreCase("q")){							 ////
					System.exit(0);											/////
				}else{														/////
					infix = infix.replace("x", valueOfx);					/////
					ifStartWithOperator(infix);							/////////
					checkForFloat(infix);									/////
					evenNumberBraces(infix);							/////////
																			/////
					expressionTree tree = new expressionTree(infix);	    /////
					postfix = tree.Translate();								/////
					System.out.println("Postfix is: "+ postfix);			/////
					computePostfix(postfix);								/////
				}															/////
			}															     ////
																			/////
																			/////
		}while(!infix.equals("q"));	//end do while							/////
																			/////
																			/////
	}																		 ////
/////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * pre: gets the postfix conversation 
	 * post: gets the result of the postfix converstion. 
	 */
	public static void computePostfix(String postfix) {
		int i, a, b;

		char c;
		Stack S = new Stack();
		for (i = 0; i < postfix.length(); i++) {
			c = postfix.charAt(i);
			String s = "0" + c;
			if (c == '+') {
				a = Integer.parseInt((String) S.pop()) + Integer.parseInt((String) S.pop());
				S.push(Integer.toString(a));

			} else if (c == '*') {
				a = Integer.parseInt((String) S.pop()) * Integer.parseInt((String) S.pop());
				S.push(Integer.toString(a));
			} else if (c == '/') {
				b = Integer.parseInt((String) S.pop());
				a = Integer.parseInt((String) S.pop()) / b;
				S.push(Integer.toString(a));

			} else if (c == '-') {
				b = Integer.parseInt((String) S.pop());
				a = Integer.parseInt((String) S.pop()) - b;
				S.push(Integer.toString(a));

			} else
				S.push(s);
		}
		System.out.println("Result:" + S.pop());
	}
/////////////////////////////////////////////////////////////////////////////////////////	
	/*
	 * pre: infix input
	 * post: displays error messages if the infix starts with operators or has two operators next to each other, 
	 * 		. none of thses are acceptable.
	 */
	public static void ifStartWithOperator(String infix) {
		char[] stringarray = infix.toCharArray();

		try {
			//to check if infix ends in operator.
			if (stringarray[stringarray.length - 1] == '+' || stringarray[stringarray.length - 1] == '-'
					|| stringarray[stringarray.length - 1] == '*' || stringarray[stringarray.length - 1] == '/'
					|| stringarray[stringarray.length - 1] == '%') {
				System.out.println("Error:  infix Equation cannot have an operator at the end");
				System.exit(0);
			}
			//to check if the infix starts withh operator
			if (stringarray[0] == '+' || stringarray[0] == '-' || stringarray[0] == '*' || stringarray[0] == '/'
					|| stringarray[0] == '%') {
				System.out.println("Error: Infix equation cannot have an operator at the beginning");
				System.exit(0);
			}
			// check to see if two operators are next to each other in infix
			for(int i =0; i<stringarray.length; i++) {
				if((stringarray[i] == '+' && stringarray[i+1] == '+')|| (stringarray[i] == '-' && stringarray[i+1] == '-')
						|| (stringarray[i] == '*' && stringarray[i+1] == '*') || (stringarray[i] == '/' && stringarray[i+1] == '/')
						|| (stringarray[i] == '%' && stringarray[i+1] == '%')){
					System.out.println("Error : Infix equation cannot have operator next to operator.");
					System.exit(0);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
////////////////////////////////////////////////////////////////////////////////////////	
	/*
	 * pre: infix input
	 * post: displays errors message if there is a float point number. 
	 */
	public static void checkForFloat(String infix) {
		char[] infixArray = infix.toCharArray();
		for (int i = 0; i < infix.length(); i++) {
			if (infixArray[i] == '.') {
				System.out.println("Error. Cannot contain float-point");
				System.exit(0);
			}
		}
	}
////////////////////////////////////////////////////////////////////////////////////////	
	/*
	 * pre: infix equation form input
	 * post: checks to input if there is even number of braces. 
	 */
	public static void evenNumberBraces(String infix) {
		char[] c;
		int openbrace = 0;
		int closebrace = 0;
		;
		for (int i = 0; i < infix.length(); i++) {
			c = infix.toCharArray();
			if (c[i] == '(') {
				openbrace++;
			}
			if (c[i] == ')') {
				closebrace++;
			}
		}
		if (openbrace != closebrace) {
			System.out.println("Error: infix must contain matching left and right braces.");
			System.exit(0);
		}
	}

}
