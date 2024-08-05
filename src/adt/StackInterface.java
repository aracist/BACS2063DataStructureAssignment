package adt;

public interface StackInterface<T> {
    void push();
    T pop();
    T peek();
    int size();
    boolean clear();
    boolean isEmpty();
}
