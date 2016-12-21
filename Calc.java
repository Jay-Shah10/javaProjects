import java.util.*;
import java.io.*;

/*
 * Jay Shah 
 * 11/11/2016
 * 
 * 	1> purpose of this program is to understand the use of Stack data structure.
 * we first have to enter an infix equation with a x variable. Then convert that input
 * to postfix using Stack. 
 * Making sure that braces match up both open and close. Must make sure that the operators have priority. 
 * Give a solution for the postfix equation. 
 * 
 * 2>. converting to postfix= using Stack. look at method "toPostfix"
 * -matching braces = must make sure that ( and ) are evenly used in program. check method "evenNumberBrace"
 * 
 * -priority for operators= depending on the type of operator each got a return value. higher it is 
 * 	higher the priority. check method "operatorPriority".
 * 
 * -must make sure that infix input is not a float point = scan through the input using char array to make s
 * 	sure that "." did not show up anywhere. check method "checkForFloat".
 * 
 * -must make sure that infix input does not have two operators in a row, or operator at the end 
 * 	or operator to start with. = used if-else statements within for loop to check that the the char array
 * 	infix input doesnt have those conditions. If it does then error gets printed and program is terminated. 
 * 	check method "ifStartWithOperator". 
 * 
 * - evaluate the the postfix. = push into stack until an operator is seen then the top two integers are popped 
 * out and the operation is performed. Result is then pushed back into the stack. this is repeated until there 
 * is only one value on the top of the stack, and that is being printed out on the screen. 
 * used else if to determine what kind of operation is performed depending on the operator encountered. 
 * check method "computPostfix"
 * 
 * 3> Stack  is used 
 * if-else statements 
 * do-while loops
 * while loop
 *  
 * 
 * 4> User is prompt to enter an infix equation. "Enter infix equation (q to quit)". The user then has the 
 * option to continue or quit. If the user continues, then the expected input is something like "1+x+2". 
 * Then the user is prompt to enter the value of x. once the user enter an integer value of x, the program 
 * replaces x from the original input to the value of x. Program also checks to see if the user enter 'q' or not
 * and check to make sure that the new infix equation for float, operators, and even numbered braces.
 * After that check it will take the infix input and convert it to postfix in method "toPostfix" and displays the result. 
 * The postfix equation is then taken to method "computPostfix" to solve the equation. Answer is displayed to 
 * the user. 
 * 
 * 5> only one class is used for this program.
 * 
 * 
 */
public class Calc {
	//////////////////////// converting to
	//////////////////////// postfix/////////////////////////////////
	
	/*
	 * pre- takes infix user input. 
	 * post - using stack it will create infix input to postfix input (1+2 = 12+).
	 */
	public static String toPostfix(String infixString) {
		String output = "";
		//creating a stack.
		Stack S = new Stack();
		// for loop to see if the character is an operator//
		for (int i = 0; i < infixString.length(); i++) {
			char c = infixString.charAt(i);
			if (c == '+' || c == '*' || c == '-' || c == '/') {
//getting the operator priority at the top of the stack. repeats if it is not empty.
				while (!S.empty() && operationpriority(S.peek()) >= operationpriority(c)) {
					output += S.pop();	//output is put together. 
				}
				S.push(c);	//pushing in characters
			} else if (c == '(') {
				S.push(c); // pushing in characters 
			} else if (c == ')') {
				while (!S.peek().equals('(')) {
					output += S.pop();	//if ) appears the character string before it is popped. as long as the top of the stack is not ( 
				}
				S.pop();	//pops the stack
			} else
				output += c;	//output is equal to the new characters.
		}

		while (!S.empty()) {
			output += S.pop();	// while not empty. output is equal to postfix equation.
		}
		return output;
	}
	
	
	/////////////////// operation priority//////////////////////////////////
/*
 * pre- an operator is brought to this method. 
 * post - operator gets its correct priority. 
 */
	public static int operationpriority(Object x) {
		if (x.equals('+') || x.equals('-'))
			return 1;
		else if (x.equals('*') || x.equals('/'))
			return 2;
		else
			return 0;
	}
	

	///////////////////// calculating postfix////////////////////////////////
	/*////////////////////////////////
	 * pre - postfix equation is called in. (eg. 12+)
	 * post -  postfix is entered in the stack, operations are performed based on priority.
	 * and result is given. (eg. 12+ = 3)
	 *//////////////////////////
	public static void computPostfix(String postfix) {
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

	///////////////////main///////////////////////////////////
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String infix, postfix, valueOfx;
		do {
			System.out.println("Enter infix equation (q to exit): ");
			infix = input.nextLine();
			//////////////////////////////////////////////////////
			if (!infix.equals("q")) {
				//////////////////////////////////////////////////////	
				ifStartWithOperator(infix);
				checkForFloat(infix);
				evenNumberBraces(infix);
			}
			//////////////////////////////////////////////////////
			if (infix.equals("q")) {
				System.exit(0);
			} else {
				//////////////////////////////////////////////////////	
				System.out.println("Enter value for x.");
				valueOfx = input.nextLine();
				//////////////////////////////////////////////////////
				if (valueOfx.equals("q")) {
					System.exit(0);
				} else {
					//////////////////////////////////////////////////////
					infix = infix.replace("x", valueOfx);
					//////////////////////////////////////////////////////
					ifStartWithOperator(infix);
					checkForFloat(infix);
					evenNumberBraces(infix);
					//////////////////////////////////////////////////////
					postfix = toPostfix(infix);
					System.out.println("Postfix is: " + postfix);
					computPostfix(postfix);
				}
			}
		} while (!(infix.equals("q")));

	}

	///////////////////checking infix input///////////////////////////////////
	/*
	 * pre = infix input is sent to this method.
	 * post = checks to see if it ends with an operator, starts with an operator, or has operator next to operator
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

	/////////////////////checks for float point/////////////////////////////////////
	/*
	 * pre= infix input
	 * post = goes through the char array for infix, if "." is found prints error. 
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

	//////////////////////////////////////////////////////////////
	/*
	 * pre = infix input from user.
	 * post = checks to see if the number of open and close braces are equal. 
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
