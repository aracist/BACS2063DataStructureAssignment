package adt;

public interface StackInterface<T> {
    void push();
    T pop();
    T peek();
    boolean clear();
    boolean isEmpty();
}
