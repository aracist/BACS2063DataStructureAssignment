package adt;

public interface BinaryTreeInterface<T> {
    T search(int hash);
    boolean insert(T data);
    T delete(int hash);
    int size();
    boolean isEmpty();
}
