package adt;

import java.util.Objects;

public class ArrayList<T> implements ListInterface<T>{
    private final static int DEFAULT_SIZE = 5;
    private int elemCount = 0;
    private T[] elemArray;
    
    public ArrayList(){
        this(DEFAULT_SIZE);
    }
    
    public ArrayList(int capacity){
        this.elemArray = (T[]) new Object[capacity];
    }
    
    private void checkCapacity(){
        if(elemCount + 1 == elemArray.length)
            expend(DEFAULT_SIZE);
    }
    
    private void expend(int size){
        T[] newArray = (T[]) new Object[elemArray.length + size];
        System.arraycopy(elemArray, 0, newArray, 0, elemArray.length);
        elemArray = newArray;
    }

    @Override
    public int size() {
        return elemCount;
    }
    
    @Override
    public boolean add(T e) {
        checkCapacity();
        elemArray[elemCount] = e;
        elemCount++;
        return elemArray[elemCount-1] == e;
    }
    
    @Override
    public boolean add(T e, int index){
        Objects.checkIndex(index, elemCount);
        checkCapacity();
        elemArray[index] = e;
        elemCount++;
        return true;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, elemCount);
        return (T)elemArray[index];
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, elemCount);
        T result = (T)elemArray[index];
        T[] newArray = (T[]) new Object[elemArray.length];
        System.arraycopy(elemArray, 0, newArray, 0, index);
        System.arraycopy(elemArray, index + 1, newArray, index, elemCount-index);
        elemArray = newArray;
        elemCount--;
        return result;
    }

    @Override
    public void clear() {
        elemArray = (T[]) new Object[DEFAULT_SIZE];
        elemCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return elemCount == 0;
    }
}
