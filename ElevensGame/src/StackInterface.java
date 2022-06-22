import java.util.EmptyStackException;

public interface StackInterface<T> {

    public void push(T newEntry);
    //add new entry to top of stack
    //parameter (T) newEntry - entry to be added

    public T pop();
    //remove from top of stack
    //throw EmptyStackException if called on empty stack
    //return (T) - value that was on top of stack

    public T peek();
    //return but don't remove the entry on top of stack
    //throw EmptyStackException if called on empty stack
    //return (T) - value that was on top of stack

    public boolean isEmpty();
    //return true if stack is empty, false otherwise

    public void clear();
    //remove all elements from stack
}
