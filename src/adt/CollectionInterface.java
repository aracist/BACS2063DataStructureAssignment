package adt;
public interface CollectionInterface<T> extends Iterable<T>{
    Object[] toArray();
    int size();
}
