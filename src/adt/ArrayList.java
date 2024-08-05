package adt;

import java.util.Objects;

public class ArrayList<T> implements ListInterface<T>{
    private final static int DEFAULT_SIZE = 5;
    private int entryCount = 0;
    private T[] entryArray;
    
    public ArrayList(){
        this(DEFAULT_SIZE);
    }
    
    public ArrayList(int capacity){
        this.entryArray = (T[]) new Object[capacity];
    }
    
    private void checkCapacity(){
        if(entryCount == entryArray.length)
            expend(DEFAULT_SIZE);
    }
    
    private void expend(int size){
        T[] newArray = (T[]) new Object[entryArray.length + size];
        System.arraycopy(entryArray, 0, newArray, 0, entryArray.length);
        entryArray = newArray;
    }

    @Override
    public boolean add(T entry) {
        checkCapacity();
        entryArray[entryCount] = entry;
        entryCount++;
        return entryArray[entryCount-1] == entry;
    }
    
    @Override
    public boolean add(T entry, int index){
        index--;
        try{
            Objects.checkIndex(index, entryCount);
        }catch(Exception ex){
            return false;
        }
        checkCapacity();
        entryArray[index] = entry;
        entryCount++;
        return true;
    }

    @Override
    public boolean replace(T entry, int index){
        index--;
        try{
            Objects.checkIndex(index, entryCount);
        }catch(Exception ex){
            return false;
        }
        entryArray[index] = entry;
        return true;
    }
    
    @Override
    public T get(int index) {
        index--;
        try{
            Objects.checkIndex(index, entryCount);
        }catch(Exception ex){
            return null;
        }
        return (T)entryArray[index];
    }
    
    @Override
    public boolean contains(T entry){
        for(int i = 0; i < entryCount; i++){
            if(entryArray[i] == entry)
                return true;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        index--;
        try{
            Objects.checkIndex(index, entryCount);
        }catch(Exception ex){
            return null;
        }
        T result = (T)entryArray[index];
        entryCount--;
        System.arraycopy(entryArray, index+1, entryArray, index, entryCount - index);

        return result;
    }
    
    @Override
    public int size() {
        return entryCount;
    }
    
    @Override
    public void clear() {
        entryArray = (T[]) new Object[DEFAULT_SIZE];
        entryCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return entryCount == 0;
    }
}
