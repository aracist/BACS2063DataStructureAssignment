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
    
    public ArrayList(T[] arr){
        this(arr.length);
        
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == null)
                break;
            
            elementArray[i] = arr[i];
            elementCount++;
        }
    }
    
    public static <T> ListInterface<T> asList(T... a) { 
        return new ArrayList<>(a); 
    }
    
    private void checkCapacity(){
        if(elementCount == elementArray.length)
            expend(DEFAULT_SIZE);
    }
    
    //Create expend into a bigger array
    private void expend(int size){
        T[] newArray = (T[]) new Object[elementArray.length + size];
        System.arraycopy(elementArray, 0, newArray, 0, elementArray.length);
        elementArray = newArray;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }
    
    private class Itr implements Iterator<T>{
        int current = 0;
        
        Itr(){}
        
        @Override
        public boolean hasNext() {
            return current != elementCount;
        }

        @Override
        public T next() {
            return elementArray[current++];
        }
        
        @Override
        public void remove(){
            ArrayList.this.remove(current+1);
            current--;
        }
    }
    
    @Override
    public void add(T data) {
        add(elementCount, data);
    }
    
    @Override
    public void add(int position, T data){
       if(elementCount != 0){
            //if insert at index 0 with 0 elementCount will cause error 
            Objects.checkIndex(position-1, elementCount);
            checkCapacity();
        }
        
        //only shift elements to right if position is not at end of array
        if(position != elementCount){
            position--;
            System.arraycopy(elementArray, position,
                    elementArray, position+1,
                    elementCount - position);
        }
        elementArray[position] = data;
        elementCount++;
    }
    
    @Override
    public void addAll(CollectionInterface<? extends T> anotherCollection, int startingPosition, int length){
        addAll((elementCount == 0)?1:elementCount+1, anotherCollection, startingPosition, length);
    };

    @Override
    public void addAll(int position, CollectionInterface<? extends T> anotherCollection, int startingPosition, int length) {
        T[] collectionArr = (T[])anotherCollection.toArray();
        
        //check if positions are in bound
        Objects.checkIndex(--position, elementCount+1); //+1 to make it possible to be added at end of array
        Objects.checkIndex(--startingPosition, collectionArr.length);
        
        //make sure lenght is <= count of elements after startingPosition
        Objects.checkIndex(length, collectionArr.length - startingPosition + 1);
        
        int newSize = length + elementCount;
        if(newSize > elementArray.length){
            expend(Math.abs(elementArray.length - newSize));
        }
        
        System.arraycopy(elementArray, position, elementArray, position + length, elementCount - position);
        System.arraycopy(collectionArr, startingPosition, elementArray, position, length);
        elementCount = newSize;
    }
    
    @Override
    public void replace(int position, T data){
        position--;
        Objects.checkIndex(position, elementCount);    
        elementArray[position] = data;
    }
    
    @Override
    public T get(int position) {
        position--;
        Objects.checkIndex(position, elementCount);
        return (T)elementArray[position];
    }
    
    @Override
    public int positionOf(T data){
        for(int i = 0; i < elementCount; i++){
            if(elementArray[i].equals(data))
                return i+1;
        }
        return -1;
    };
    
    @Override
    public boolean contains(T data){
        for(int i = 0; i < elementCount; i++){
            if(elementArray[i].equals(data))
                return true;
        }
        return false;
    }

    @Override
    public T remove(int position) {
        T result = get(position);
        elementCount--;
        System.arraycopy(elementArray, position, 
                elementArray, --position, 
                elementCount - position);// shift element to left
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
        
        Iterator<?> otherItr = other.iterator();
        
        for(int i = 0; i < elementCount; i++){
            if(!elementArray[i].equals(otherItr.next()))
                return false;
        }
        
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