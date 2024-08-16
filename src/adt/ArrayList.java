package adt;

import java.util.Iterator;
import java.util.Objects;

public class ArrayList<T> implements ListInterface<T>, CollectionInterface<T>{
    private final static int DEFAULT_SIZE = 5;
    private int elementCount = 0;
    private T[] elementArray;
    
    public ArrayList(){
        this(DEFAULT_SIZE);
    }
    
    public ArrayList(int capacity){
        this.elementArray = (T[]) new Object[capacity];
    }
    
    private void checkCapacity(){
        if(elementCount == elementArray.length)
            expend(DEFAULT_SIZE);
    }
    
    private void expend(int size){
        T[] newArray = (T[]) new Object[elementArray.length + size];
        System.arraycopy(elementArray, 0, newArray, 0, elementArray.length);
        elementArray = newArray;
    }
    private class Itr implements Iterator<T>{
        int current = 1;
        
        Itr(){}
        
        @Override
        public boolean hasNext() {
            return current == elementCount;
        }

        @Override
        public T next() {
            return elementArray[current++];
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }
    
    @Override
    public void add(T element) {
        checkCapacity();
        elementArray[elementCount] = element;
        elementCount++;
    }
    
    @Override
    public void add(int position, T element){
        position--;
        Objects.checkIndex(position, elementCount);
        checkCapacity();
        elementArray[position] = element;
        elementCount++;
    }

    @Override
    public void addAll(CollectionInterface<? extends T> anotherCollection) {
        addAll(elementCount, anotherCollection, 1, anotherCollection.size());
    }

    @Override
    public void addAll(int position, CollectionInterface<? extends T> anotherCollection) {
        addAll(position, anotherCollection, 0, anotherCollection.size());
    }

    @Override
    public void addAll(int position, CollectionInterface<? extends T> anotherCollection, int startingPosition) {
        addAll(position, anotherCollection, startingPosition, anotherCollection.size());
    }

    @Override
    public void addAll(int position, CollectionInterface<? extends T> anotherCollection, int startingPosition, int stoppingPosition) {
        int collectionSize = anotherCollection.size();
        Objects.checkIndex(position, elementCount+1);
        startingPosition--;
        Objects.checkIndex(startingPosition, collectionSize);
        stoppingPosition--;
        Objects.checkIndex(stoppingPosition, collectionSize);
        int newSize = collectionSize + elementCount;
        if(newSize > elementArray.length){
            expend(Math.abs(elementArray.length - newSize));
        }
        System.arraycopy(anotherCollection.toArray(), startingPosition, elementArray, position, stoppingPosition - startingPosition + 1);
        elementCount = newSize;
    }
    
    @Override
    public void replace(int position, T element){
        position--;
        Objects.checkIndex(position, elementCount);    
        elementArray[position] = element;
    }
    
    @Override
    public T get(int position) {
        position--;
        Objects.checkIndex(position, elementCount);
        return (T)elementArray[position];
    }
    
    @Override
    public int indexOf(T element){
        for(int i = 0; i < elementCount; i++){
            if(elementArray[i].equals(element))
                return i+1;
        }
        return -1;
    };
    
    @Override
    public boolean contains(T element){
        for(int i = 0; i < elementCount; i++){
            if(elementArray[i].equals(element))
                return true;
        }
        return false;
    }

    @Override
    public T remove(int position) {
        position--;
        Objects.checkIndex(position, elementCount);
        T result = (T)elementArray[position];
        elementCount--;
        System.arraycopy(elementArray, position+1, elementArray, position, elementCount - position);

        return result;
    }
    
    @Override
    public int size() {
        return elementCount;
    }
    
    @Override
    public void clear() {
        elementArray = (T[]) new Object[DEFAULT_SIZE];
        elementCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }
    
    @Override
    public Object[] toArray(){
        Object[] result = new Object[elementCount];
        System.arraycopy(elementArray, 0, result, 0, elementCount);
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;
        
        if (!(obj instanceof CollectionInterface<?>))
            return false;
        
        return (obj.getClass() == this.getClass() 
                ? equalsArrayList((ArrayList<?>) obj)
                : equalsCollection((CollectionInterface) obj));
    }
    
    private boolean equalsArrayList(ArrayList<?> other){
        if (other.elementCount != elementCount)
            return false;
        
        if (other.elementArray.getClass() != elementArray.getClass())
            return false;
       
        for(int i = 0; i < elementCount ; i++){
            if(!other.elementArray[i].equals(elementArray[i])){
                return false;
            }     
        }
        return true;
    }
    
    private boolean equalsCollection(CollectionInterface other){
        if(other.size() != elementCount)
            return false;
        
        Iterator<?> otherIterator = other.iterator();
        
        
        return true;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < elementCount; i++){
            str.append(String.format("[%2s] %s\n",i+1,elementArray[i]));
        }
        return str.toString();
    }
}
