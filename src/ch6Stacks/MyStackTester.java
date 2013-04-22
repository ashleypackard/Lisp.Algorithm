package ch6Stacks;

public class MyStackTester 
{

	public static void main(String[] args) 
	{
		MyStack<Integer> mike = new MyStack<Integer>();
		
		System.out.println("Size: " + mike.size() + " & Size of Stack: " + mike.stackSize());
		
		mike.push(1);
		System.out.println(mike.toString());
		mike.push(3);
		mike.push(66);
		System.out.println(mike.toString());
		System.out.println("Size: " + mike.size() + " & Size of Stack: " + mike.stackSize());
		mike.pop();
		System.out.println(mike.toString());
		System.out.println("Peeked: " + mike.peek());
		mike.push(678);
		System.out.println(mike.toString());
		mike.push(111111);
		System.out.println(mike.toString());
		System.out.println("Size: " + mike.size() + " & Size of Stack: " + mike.stackSize());
		mike.push(27);
		System.out.println(mike.toString());
		System.out.println("Size: " + mike.size() + " & Size of Stack: " + mike.stackSize());
		mike.pop();
		System.out.println(mike.toString());
		mike.pop();
		System.out.println(mike.toString());
		System.out.println("Size: " + mike.size() + " & Size of Stack: " + mike.stackSize());
		System.out.println("Peeked: " + mike.peek() + "\n\n");

		MyStack<String> becca = new MyStack<String>(4);
		becca.push("Becca");
		System.out.println(becca.toString());
		becca.push("is");
		becca.push("awesome");
		System.out.println(becca.toString());
		System.out.println("Size: " + becca.size() + " & Size of Stack: " + becca.stackSize());
		becca.pop();
		System.out.println(becca.toString());
		System.out.println("Peeked: " + becca.peek());
		becca.push("amazing");
		System.out.println(becca.toString());
		becca.push("and");
		System.out.println(becca.toString());
		System.out.println("Size: " + becca.size() + " & Size of Stack: " + becca.stackSize());
		becca.push("cool");
		System.out.println(becca.toString());
		System.out.println("Size: " + becca.size() + " & Size of Stack: " + becca.stackSize());
		becca.pop();
		System.out.println(becca.toString());
		becca.pop();
		System.out.println(becca.toString());
		System.out.println("Size: " + becca.size() + " & Size of Stack: " + becca.stackSize());
		System.out.println("Peeked: " + becca.peek());

	}

}
