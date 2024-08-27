package adt;

/**
 *
 * @author User
 */
public class Stack<T> implements StackInterface<T>{
    LinkedList<T> elementStack = new LinkedList<>();
    
    @Override
    public void push(T data) {
        elementStack.addFirst(data);
    }

    @Override
    public T pop() {
        return elementStack.removeFirst();
    }

    @Override
    public T peek() {
        return elementStack.getFirst();
    }

    @Override
    public int size() {
        return elementStack.size();
    }

    @Override
    public void clear() {
        elementStack = new LinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return elementStack.size() == 0;
    }
    
}
