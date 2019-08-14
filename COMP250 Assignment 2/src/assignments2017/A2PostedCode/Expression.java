package assignments2017.A2PostedCode;

/*	
 * 	 STUDENT NAME      :  Nayem Alam
 *   STUDENT ID        :  260743549 
 *   
 *   If you have any issues that you wish the T.A.s to consider, then you
 *   should list them here.   If you discussed on the assignment in depth 
 *   with another student, then you should list that student's name here.   
 *   We insist that you each write your own code.   But we also expect 
 *   (and indeed encourage) that you discuss some of the technical
 *   issues and problems with each other, in case you get stuck.    
 
 *   
 */

import java.util.Stack;
import java.util.ArrayList;
import java.lang.Integer;

public class Expression  {
	private ArrayList<String> tokenList;

	//  Constructor    
	/**
	 * The constructor takes in an expression as a string
	 * and tokenizes it (breaks it up into meaningful units)
	 * These tokens are then stored in an array list 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException{
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();

		//ADD YOUR CODE BELOW HERE
		for(int i=0; i<expressionString.length(); i++) {
			
			if(Character.isDigit(expressionString.charAt(i)) == true) { 
				token.append(expressionString.charAt(i)); //if it's a digit, append it to token
				
				while(Character.isDigit(expressionString.charAt(i+1)) == true) {
					token.append(expressionString.charAt(i+1)); //if there are digits one after the other, then append those to the token
					i++;	
				}
				tokenList.add(token.toString()); //turn appended token to string and then add it to the list
				token.setLength(0); //clear the list
			}
			
			else if (expressionString.charAt(i) == ' ') { //if there is a whitespace
				continue; //ignore the whitespace
			}
			else if (expressionString.charAt(i) == '+' | expressionString.charAt(i) == '-') {
				token.append((expressionString).charAt(i)); //otherwise if there is a + or -, append that to the token
				
				while(expressionString.charAt(i+1) == expressionString.charAt(i)) {
					token.append(expressionString.charAt(i+1)); //if there is a ++ or --, then append that to the token
					i++;
				}
				tokenList.add(token.toString()); //convert token to string and then add to list
				token.setLength(0); //clear list
			}
			else if(expressionString.charAt(i) == '*' | expressionString.charAt(i) == '/' | expressionString.charAt(i) == '(' | expressionString.charAt(i) == ')' | expressionString.charAt(i) == '[' | expressionString.charAt(i) == ']') {
				token.append(expressionString.charAt(i)); //otherwise if there are any of the operations listed above this line, then append that to token
				tokenList.add(token.toString()); //convert token to string and then add to list
				token.setLength(0); //clear list
			}
		}

		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * This method evaluates the expression and returns the value of the expression
	 * Evaluation is done using 2 stack ADTs, operatorStack to store operators
	 * and valueStack to store values and intermediate results.
	 * - You must fill in code to evaluate an expression using 2 stacks
	 */
	public Integer eval(){
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();
		
		//ADD YOUR CODE BELOW HERE
		int result = 0; String e; String op; Integer x; Integer y; //using big Integer because the Stack uses Integer 
		for(int i=0; i<tokenList.size(); i++) {
			e = tokenList.get(i); //initialize 'e' (element) to get the value at i'th position
			
			if(isInteger(e)) { //if it's an integer, push it to valueStack
				valueStack.push(Integer.parseInt(e)); //convert String to int
			}
			else if(e.equals("*") || e.equals("/") || e.equals("+") || e.equals("-") || e.equals("++") || e.equals("--")) {
				operatorStack.push(e); //otherwise if it is any of the operators above, push the element to the operator stack
			}
			else if(e.equals(")") && operatorStack.isEmpty() == false) { //if a right parentheses is encountered and the operator stack is empty, the code won't care about it (i.e. if empty, no further operation to execute)
				op = operatorStack.pop();
				
				if(op.equals("*")) { //if the popped operator is *
					x = valueStack.pop(); //store the first popped value to x
					y = valueStack.pop(); //store the second popped value to y
					result = x * y; //multiply the two values
					valueStack.push(result); //push the result to the value stack
				}
				if(op.equals("+")){
					x = valueStack.pop();
					y = valueStack.pop();
					result = x + y;
					valueStack.push(result);
				}
				if(op.equals("-")) {
					x = valueStack.pop();
					y = valueStack.pop();
					result = y - x;
					valueStack.push(result);
				}
				if(op.equals("/")) {
					x = valueStack.pop();
					y = valueStack.pop();
					result = y/x;
					valueStack.push(result);
				}
				if(op.equals("++")) { //if the popped operator is ++
					x = valueStack.pop(); //pop the first value
					valueStack.push(x+1); //push that value back to value stack and add 1
				}
				if(op.equals("--")) { //if the popped operator is --
					x = valueStack.pop(); //pop the first value
					valueStack.push(x-1); //push that value back to value stack and minus 1
				}
			}
			if(e.equals("]")) { //however, if our tokenList.get(i) = ']', then pop value, if it's neg, make it pos, then push back to value stack
				x = valueStack.pop(); //pop the right value
				if(x < 0) { //if that value is negative
					x = x*(-1); //make it positive
				}
				valueStack.push(x); //push that value back to the value stack
			}
			
		}
		
		//ADD YOUR CODE ABOVE HERE
		return valueStack.pop();
	}

	//Helper methods
	/**
	 * Helper method to test if a string is an integer
	 * Returns true for strings of integers like "456"
	 * and false for string of non-integers like "+"
	 * - DO NOT EDIT THIS METHOD
	 */
	private boolean isInteger(String element){
		try{
			Integer.valueOf(element);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList.
	 * - DO NOT EDIT THIS METHOD    
	 */

	@Override
	public String toString(){	
		String s = new String(); 
		for (String t : tokenList )
			s = s + "~"+  t;
		return s;		
	}

}

