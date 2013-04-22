// Ashley Packard & Mike Kucharski

package ch5StacksUsingMyStack;

import java.util.Scanner;

public class AlgorithmTesterWithMyStack 
{
	
	public static boolean startOver; 
		
	public static void main(String[] args) 
	{
		changeStartOver(false);
		
		System.out.println("Welcome to the Lisp Algorithm!");
			
		System.out.println("\nHere is an example: ");
		String expression = "(+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1)))";
		
		LispAlgorithmWithMyStack test = new LispAlgorithmWithMyStack(expression);
			
		System.out.println("The stack's contents are: " + test.toString());
			
		System.out.println("\nNow try it for yourself.");
		
		boolean letsPlay = true;
			
		while(letsPlay)
		{
			Scanner in = new Scanner(System.in);
			
			System.out.println("Please enter an expression or q to quit: ");
				
			String userExpression = in.nextLine();
				
			if(userExpression.contentEquals("q"))
			{
				System.out.println("Thanks for playing!");
				System.exit(0);
			}
				
			if(!isValidExpression(userExpression))
			{
				System.out.println("\nInvalid expression: " + userExpression + "\nPlease try again.");
				changeStartOver(true);
			}
			
			while(startOver)
			{
				System.out.println("Please enter a new expression or q to quit: ");
				
				userExpression = in.nextLine();
				
				if(userExpression.contentEquals("q"))
				{
					System.out.println("Thanks for playing!");
					System.exit(0);
				}
				
				if(!isValidExpression(userExpression))
				{
					System.out.println("\nInvalid expression: " + userExpression + "\nPlease try again.");
					changeStartOver(true);
				}
				else
					changeStartOver(false);
			
			}	
			
			LispAlgorithmWithMyStack userTest = new LispAlgorithmWithMyStack(userExpression);
				
			// Don't print out the stack's contents if its an invalid expression
			if(userTest.toString().contentEquals("Invalid Expression!"))
				System.out.println(userTest.toString());
			else
				System.out.println("The stack's contents are: " + userTest.toString());
				
		}
			
			
			
			
	}

	// kudos to Mike K. :D
	static boolean isOperator(char op)
	{
		return (op == '+' || op == '-' || op == '*' || op == '/') ? true : false;
	}
	
	static boolean isValidExpression(String fullExp) 
	{
		fullExp = fullExp.replaceAll("\\s", "");
		//verify that the first element is a paren and the second is an op
		if((fullExp.charAt(0) != '(') || !isOperator(fullExp.charAt(1)) ) {return false;}
		
		int openCount = 0, closeCount = 0;
		
		for(int i = 0; i < fullExp.length(); i++)
		{
			// verify that an open parens precedes every operator
			switch (fullExp.charAt(i) )
			{
				case '+': case '-': case '*': case '/': 
					if(fullExp.charAt(i-1) != '('){return false;}
					break;
				
				case '(':	
					if(!isOperator(fullExp.charAt(i+1))) {return false;}
					openCount++;
					break;
					
				case ')': 
					closeCount++;
					break;
					
				default: continue;
			}
			}
			
		if(openCount != closeCount) {return false;}
		
		// verify that every character in exp is either a space, operator, or parens
		for(int i = 0; i < fullExp.length(); i++)
		{
			char ch = fullExp.charAt(i);
			if(Character.isDigit(ch) || isOperator(ch) || ch == ' ' || ch == '(' || ch == ')') 
				continue;
			else	{return false;}
		}
		
		//this is allllll me :) verify that the divides and subtracts has at least one operand
		for(int i = 0; i < fullExp.length(); i++)
		{
			char ch = fullExp.charAt(i);
			if(ch == '/' || ch == '-') 
			{
				// duh! since we got rid of the spaces the immediate ch should be a digit or a (!
				char ch2 = fullExp.charAt(i+1);
				if(ch2 == '(')
					continue;
				else if (!Character.isDigit(ch2))
					return false;
			}
			else
				continue;
		}
		
		
		return true;
	}
		
	public static void changeStartOver(boolean swap)
	{
		startOver = swap;
	}

}
