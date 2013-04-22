package ch5StacksUsingMyStack;

import java.util.Stack;

import ch6Stacks.MyStack;

public class LispAlgorithmWithMyStack
{

	// create a stack to house the expression and a temporary stack to evaluate the expression
	private MyStack<String> myStack;
	private MyStack<String> tempHolding;
	
	// in case of invalid expression
	AlgorithmTesterWithMyStack startOverIndicator = new AlgorithmTesterWithMyStack(); 

	public LispAlgorithmWithMyStack(String newExpression) 
	{
		myStack = new MyStack<String>();
		tempHolding = new MyStack<String>();

		solve(newExpression);
	}
	
	private void solve(String newExpression)
	{
		System.out.println("\nThe expression given was: \n" + newExpression + "\n");

		// go through the full expression and evaluate appropriately
		for (int i = 0; i < newExpression.length(); i++) 
		{
			// trash all spaces
			if (newExpression.substring(i, i + 1).equals(" ")) 
			{
				continue;
			} 
			// if the stack is empty or if there was an invalid expression thrown stop loop
			else if ((!myStack.isEmpty()) && (myStack.peek().contentEquals("Invalid Expression!")))
			{
				System.out.println("Invalid Expression!");
				startOverIndicator.changeStartOver(true);
			}
			// if you come upon a ) take off all the elements on the stack until you hit the matching (
			else if (newExpression.substring(i, i + 1).equals(")")) 
			{
				for (int a = 0; a < myStack.stackSize(); a++) 
				{
					while (!myStack.peek().contentEquals("("))
						tempHolding.push(myStack.pop());
				}

				// trash the (
				myStack.pop();
				
				// determine which operator and evaluate accordingly
				if (tempHolding.peek().contentEquals("+"))
					add();
				else if (tempHolding.peek().contentEquals("-"))
					subtract();
				else if (tempHolding.peek().contentEquals("*"))
					multiply();
				else if (tempHolding.peek().contentEquals("/"))
					divide();
					
				} 
			else
				// if none of these is true, default to just adding it to the stack
				myStack.push(newExpression.substring(i, i + 1));

		}
	}

	private void multiply() 
	{
		// pop off the operator
		tempHolding.pop();
	
		// sum up the following numbers
		if (tempHolding.isEmpty() == true) 
		{
			// return a one upon request
			myStack.push("1");
		} 
		else if (tempHolding.stackSize() == 1)
		{
			// If there is only one operand return it!
			myStack.push(tempHolding.pop());
		}
		else 
		{
			// get the first operand and multiply all additonal ones to it
			// while the stack still has elements
			double num = Double.parseDouble(tempHolding.pop());
			
			while (!tempHolding.isEmpty()) 
			{
				num = num * Double.parseDouble(tempHolding.pop());
			}
				
			// return the calulated number
			myStack.push(Double.toString(num));

		}
	}

	private void divide() 
	{
		// pop off the operator
		tempHolding.pop();
		
		// cannot divide by zero
		if (tempHolding.peek().contentEquals("0"))
		{
			tempHolding.pop();
			myStack.push("Invalid Expression!");
		}

		// if only one operand then 1/a
		else if (tempHolding.stackSize() == 1)
		{
			double num = 1 / Double.parseDouble(tempHolding.pop());
			myStack.push(Double.toString(num));
		}
		else 
		{
			// if more operands then take the first one and divide by all 
			// the elements afterwards, a/b/c
			double num = Double.parseDouble(tempHolding.pop());
			while (!tempHolding.isEmpty()) 
			{
				// cannot divide by zero
				if(tempHolding.peek().contentEquals("0") || tempHolding.peek().contentEquals("0.0"))
				{
					tempHolding.pop();
					num = 0;
				}
				else
					num = num / Double.parseDouble(tempHolding.pop());
			}

			// if divide by zero then send an invalid expression back
			if(num == 0)
				myStack.push("Invalid Expression!");
			else
				// add the evaluated expression back on the stack
				myStack.push(Double.toString(num));
		}


	}

	private void subtract() 
	{
		// pop off the operator
		tempHolding.pop();
		
		// if the stack is empty yell at user
		if(tempHolding.isEmpty())
		{
			myStack.push("Invalid Expression!");
		}
		// if there is one operand return -a
		else if(tempHolding.stackSize() == 1)
		{
			myStack.push(Double.toString(-(Double.parseDouble(tempHolding.pop()))));
		}
		else
		{
			// sum up all the operands
			double num = Double.parseDouble(tempHolding.pop());
			while (!tempHolding.isEmpty()) 
			{
				if (tempHolding.peek() == "-") 
				{
					tempHolding.pop();
				}
				
				// if the number is negative add it to the stack so it doesn't get
				// confused and try to subtract a negative which is adding
				if (Double.parseDouble(tempHolding.peek()) < 0)
					num = num + Double.parseDouble(tempHolding.pop());
				else
					num = num - Double.parseDouble(tempHolding.pop());
			}
				
			// return the calculated answer
			myStack.push(Double.toString(num));
		}

	}

	private void add() 
	{
		// pop off the operator
		tempHolding.pop();
		
		// if there is just a + then return 0
		if(tempHolding.isEmpty())
		{
			myStack.push("0");
		}
		else
		{
			// sum up the expression
			double num = Double.parseDouble(tempHolding.pop());
			while (!tempHolding.isEmpty()) 
			{
				// if there is a + 9 - 8, subtract instead, not sure is this is actually neccessary...
				if (tempHolding.peek() == "-") 
				{
					// pop off the -
					tempHolding.pop();
					num = num - Double.parseDouble(tempHolding.pop());
				} 
				else 
				{
					num = num + Double.parseDouble(tempHolding.pop());
				}
		}
				
				// add calculated expression to stack
				myStack.push(Double.toString(num));
			}
		}

		// cycle through and print out the stack
		public String toString() 
		{
			String printThisOut = "";

			if (myStack.isEmpty()) 
			{
				printThisOut = "Contents empty";
			}
			else 
			{
				printThisOut = myStack.toString();
			}

			return printThisOut;
		}
	}
