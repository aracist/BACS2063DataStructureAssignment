package adt;

public interface StackInterface<T> {
    void push(T data);
    T pop();
    T peek();
    int size();
    void clear();
    boolean isEmpty();
}
