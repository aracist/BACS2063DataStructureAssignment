package adt;

public interface QueueInterface<T> {
    void enqueue(T data);
    T dequeue();
    T getFront();
    int size();
    void clear();
    boolean isEmpty();
}
