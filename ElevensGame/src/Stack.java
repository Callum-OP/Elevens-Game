import java.util.EmptyStackException;

public class Stack<T> implements StackInterface<T> {

    private MyNode<T> topNode;

    public Stack() {
        topNode = null;
    }

    //push an item to the top of the stack
    public void push(T newEntry) {
        MyNode<T> newNode = new MyNode<T>(newEntry);
        newNode.setNext(topNode);
        topNode = newNode;
    }

    //remove what is at the top of the stack
    public T pop() {
        T dataToReturn = peek();
        topNode = topNode.getNext();
        return dataToReturn;
    }

    //show what is at the top of the stack
    public T peek() {
        if (topNode == null) throw new EmptyStackException();
        else return topNode.getData();
    }

    //checks if the stack is empty by checking the top node
    public boolean isEmpty() {
        return (topNode == null);
    }

    public void clear() {
        topNode = null;
    }

    //tests the deck methods
    public static void main(String[] args) {
        //create new stack
        Stack<String> stack = new Stack<String>();
        //push strings to stack
        System.out.print("Pushing to stack");
        stack.push("A court card");
        stack.push("3 of Spades");
        stack.push("King of Diamonds");
        //check if empty
        System.out.println("\nStack empty is: " + stack.isEmpty());

        for (int i = 1; i<=4; i++) {
            try {
                System.out.println("\nPeek = " + stack.peek());
                System.out.println("Pop = " + stack.pop());
            }catch (EmptyStackException e) {
                System.out.println("\nCannot pop anymore as stack is empty");
            }
        }

        System.out.println("Stack empty is: " + stack.isEmpty());

        System.out.print("\nPushing to stack again");
        stack.push("one");
        stack.push("two");
        stack.push("three");

        System.out.println("\nStack empty is: " + stack.isEmpty());

        System.out.print("\nClearing stack");
        stack.clear();
        System.out.println("\nStack empty is: " + stack.isEmpty());
    }
}
