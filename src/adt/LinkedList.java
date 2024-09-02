package adt;
import java.util.Iterator;
import java.util.Objects;

public class LinkedList<T> implements ListInterface<T>, CollectionInterface<T> {
    private int elementCount;
    Node<T> firstNode;
    Node<T> lastNode;

    public LinkedList(){
        elementCount = 0;
    }
    
    private static class Node<T>{
        T data;
        Node<T> prev;
        Node<T> next;
        
        Node(Node<T> prev, T data, Node<T> next){
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
    
    private Node<T> getNode(int position){
        position--;
        Objects.checkIndex(position, elementCount);
        Node<T> current;
        
        if(position < elementCount/2){
            current = firstNode;
            for(int i = 0; i < position; i++)
                current = current.next;
        }else{
            current = lastNode;
            for(int i = 1; i < elementCount - position; i++)
                current = current.prev;
        }
        return current;
    }
    private class Itr implements Iterator<T>{
        int currentIndex;
        Node currentNode;
        
        Itr(){}
        
        @Override
        public boolean hasNext() {
            return currentIndex < elementCount;
        }

        @Override
        public T next() {
            if (currentNode != null){
                currentIndex++;
                currentNode = currentNode.next;
            }else{
                currentIndex = 1;
                currentNode = firstNode;
            }
            return (T)currentNode.data;
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }
    
    @Override
    public void add(T data) {
        Node<T> newNode = new Node<>(lastNode, data, null);
        if (firstNode == null) 
            firstNode = newNode;
        else
            lastNode.next = newNode;

        lastNode = newNode;
        elementCount++;
    }

    @Override
    public void add(int position, T data) {
        if(position-1 == elementCount){
            add(data);
            return;
        }
        Node<T> oldEntry = getNode(position);
        Node<T> newEntry = new Node<>(oldEntry.prev, data, oldEntry);
        
        if(oldEntry.prev == null)
            firstNode = newEntry;
        else
            oldEntry.prev.next = newEntry;
        
        oldEntry.prev = newEntry;
        elementCount++;
    }

    @Override
    public void addAll(CollectionInterface<? extends T> anotherCollection, int startingPosition, int length){
        addAll((elementCount == 0)?1:elementCount+1, anotherCollection, startingPosition, length);
    };

    @Override
    public void addAll(int position, CollectionInterface<? extends T> anotherCollection, int startingPosition, int length) {
        Object[] arr = anotherCollection.toArray();
        
        //check if positions are in bound
        Objects.checkIndex(--position, elementCount+1); //+1 to make it possible to be added at end of array
        Objects.checkIndex(--startingPosition, arr.length);
        
        //make sure lenght is <= count of elements after startingPosition
        Objects.checkIndex(length, arr.length - startingPosition + 1);
        
        //if at the last of the node
        if(position == elementCount){
            for(int i = 0 ; i < length; i++)
                add((T)arr[startingPosition + i]);
        }else{
            Node<T> currentNode = getNode(position);
            for(int i = 0 ; i < length; i++){
                Node<T> newNode = new Node<>(currentNode, (T)arr[i], currentNode.next);
                currentNode.next.prev = newNode;
                currentNode.next = newNode;
                currentNode = newNode;
            }
            elementCount += arr.length;
        }
    }
    
    @Override
    public void replace (int position, T data) {
        Node<T> currentNode = getNode(position);
        
        currentNode.data = data;
    }

    @Override
    public T get(int position) {
        Node<T> currentNode = getNode(position);
        return currentNode.data;
    }

    @Override
    public int positionOf(T data){
        Node<T> currentNode = firstNode;
        if(currentNode.data.equals(data))
            return 1;
        
        for(int i = 1; i < elementCount; i++){
            currentNode = currentNode.next;
            if(currentNode.data.equals(data))
                return i;
        }
        
        return -1;
    };
    
    @Override
    public boolean contains(T data) {
        Node<T> currentNode = firstNode;
        if(currentNode.data.equals(data))
            return true;
        
        for(int i = 2; i <= elementCount; i++){
            currentNode = currentNode.next;
            if(currentNode.data.equals(data))
                return true;
        }    
        
        return false;
    }

    @Override
    public T remove(int position) {
        Node<T> targetNode = getNode(position);
        if(targetNode == firstNode){
            firstNode = firstNode.next;
            if(firstNode != null)
                firstNode.prev = null;
        }else if(targetNode == lastNode){
            lastNode = lastNode.prev;
            lastNode.next = null;
        }else{
            targetNode.prev.next = targetNode.next;
            targetNode.next.prev = targetNode.prev;
        }
        elementCount--;
        return targetNode.data;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        elementCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }
    
    @Override
    public Object[] toArray(){
        Object[] result = new Object[elementCount];
        Node<T> currentNode = firstNode;
        result[0] = currentNode.data;
        
        for(int i = 1; i < elementCount; i++){
            currentNode = currentNode.next;
            result[i] = currentNode.data;
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;
        
        if (!(obj instanceof CollectionInterface<?>))
            return false;
        
        return (obj.getClass() == this.getClass() 
                ? equalsLinkedList((LinkedList<?>) obj)
                : equalsCollection((CollectionInterface) obj));
    }
    
    private boolean equalsLinkedList(LinkedList<?> other){
        if (other.elementCount != elementCount)
            return false;
        
        if (other.firstNode.data.getClass() != firstNode.data.getClass())
            return false;
        
        Node thisNode = firstNode; 
        Node otherNode = other.firstNode; 
        
        for(int i = 0; i < elementCount ; i++){
            if(!thisNode.data.equals(otherNode.data))
                return false;
            
            thisNode = thisNode.next;
            otherNode = otherNode.next;
        }
        return true;
    }
    
    private boolean equalsCollection(CollectionInterface other){
        if(other.size() != elementCount)
            return false;
        
        Iterator<?> otherItr = other.iterator();
        Node thisNode = firstNode;
        
        for(int i = 0; i < elementCount; i++){
            if(!thisNode.data.equals(otherItr.next()))
                return false;
            
            thisNode = thisNode.next;
        }
        
        return true;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        Node<T> currentNode = firstNode;
        str.append(String.format("[%2s] %s\n", 1, currentNode.data));
        
        for(int i = 2; i <= elementCount; i++){
            currentNode = currentNode.next;
            str.append(String.format("[%2s] %s\n", i, currentNode.data));
        }
        return str.toString();
    }
}
