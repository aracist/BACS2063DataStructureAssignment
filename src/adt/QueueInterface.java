package adt;

public interface QueueInterface<T> {
    void enqueue();
    T dequeue();
    T getFront();
    boolean clear();
    boolean isEmpty();
}
