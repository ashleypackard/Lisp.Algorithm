package ch6Stacks;

@SuppressWarnings("unchecked")
public class MyStack<E> implements StackInterface<E>
{
	private int topIndex; 
	private E[] myStack;
	

	public MyStack()
	{
		topIndex = -1;
		myStack = (E[]) new Object[0];
	}

	public MyStack(int newSize)
	{
		topIndex = -1;
		myStack = (E[]) new Object[newSize];

	}
	
	public int size()
	{
		return myStack.length;
	}

	public int stackSize()
	{
		return topIndex+1;
	}
	public boolean isEmpty() 
	{
		return (stackSize() == 0) ? true : false;
	}

	public int getTopIndex() 
	{
		return topIndex;
	}
	
	public E peek()
	{
		E top = null; 
		if (!isEmpty()) 
			top = myStack[topIndex];
		return top; 

	}

	public void push(E element) 
	{
		topIndex++; 
	      if (topIndex >= myStack.length)
	         doubleArray(); 		// expand array 
	      myStack[topIndex] = element;
	}

	public E pop() 
	{
		E top = null; 
		if (!isEmpty ()) 
		{ 
			top = myStack[topIndex]; 
		    myStack[topIndex] = null; 
		    topIndex--; 
		}  
		return top;

	}

	private void doubleArray() 
	{
		E[] tempStack;
		
		if(size() == 0)
		{
			tempStack = (E[]) new Object[1];
		}
		else
		{
			tempStack = (E[]) new Object[(size() * 2)];
			
			for (int i = 0; i < size(); i++)
			{
				tempStack[i] = myStack[i];
			}
			
		}
				
		myStack = tempStack;
	}
	
	public String toString()
	{
		String printThisOut = "";
		
		for (int i = 0; i < size(); i++)
		{
			if(printThisOut == "")
			{
				if (myStack[i] != null)
				{
					printThisOut = "" + myStack[i];
				}

			}
			else
			{
				if (myStack[i] != null)
				{
					printThisOut = printThisOut + " " + myStack[i];
				}
			}
		}
		
		return printThisOut;
	}
	
}
