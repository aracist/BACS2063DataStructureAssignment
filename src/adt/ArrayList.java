package adt;

import java.util.Collection;
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
    
    private T[] getEntryArray(){
        return entryArray;
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
    public void add(T entry) {
        checkCapacity();
        entryArray[entryCount] = entry;
        entryCount++;
    }
    
    @Override
    public void add(T entry, int position){
        position--;
        Objects.checkIndex(position, entryCount);
        checkCapacity();
        entryArray[position] = entry;
        entryCount++;
    }

    @Override
    public void addAll(Collection<? extends T> anotherCollection) {
        addAll(entryCount, anotherCollection, 0, anotherCollection.size());
    }

    @Override
    public void addAll(int position, Collection<? extends T> anotherCollection) {
        addAll(position, anotherCollection, 0, anotherCollection.size());
    }

    @Override
    public void addAll(int position, Collection<? extends T> anotherCollection, int startingPosition) {
        addAll(position, anotherCollection, startingPosition, anotherCollection.size());
    }

    @Override
    public void addAll(int position, Collection<? extends T> anotherCollection, int startingPosition, int stoppingPosition) {
        int collectionSize = anotherCollection.size();
        position--;
        Objects.checkIndex(position, entryCount);
        startingPosition--;
        Objects.checkIndex(startingPosition, collectionSize);
        stoppingPosition--;
        Objects.checkIndex(stoppingPosition, collectionSize);
        int newSize = collectionSize + entryCount;
        if(newSize > entryArray.length){
            expend(entryArray.length - newSize);
        }
        System.arraycopy(anotherCollection.toArray(), startingPosition, entryArray, position, stoppingPosition - startingPosition + 1);
        entryCount = newSize;
    }
    
    @Override
    public void replace(T entry, int position){
        position--;
        Objects.checkIndex(position, entryCount);    
        entryArray[position] = entry;
    }
    
    @Override
    public T get(int position) {
        position--;
        Objects.checkIndex(position, entryCount);
        return (T)entryArray[position];
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
    public T remove(int position) {
        position--;
        Objects.checkIndex(position, entryCount);
        T result = (T)entryArray[position];
        entryCount--;
        System.arraycopy(entryArray, position+1, entryArray, position, entryCount - position);

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
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < entryCount; i++){
            str.append(String.format("[%2s] %s\n",i+1,entryArray[i]));
        }
        return str.toString();
    }
}
