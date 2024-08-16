package adt;
import java.util.Iterator;
import java.util.Objects;

public class LinkedList<T> implements ListInterface<T>, CollectionInterface<T> {
    private int elementCount;
    Node<T> firstNode;
    Node<T> lastNode;

    
    private static class Node<T>{
        T element;
        Node<T> prev;
        Node<T> next;
        
        Node(Node<T> prev, T element, Node<T> next){
            this.prev = prev;
            this.element = element;
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
        int currentIndex = 0;
        Node currentNode = firstNode;
        
        Itr(){}
        
        @Override
        public boolean hasNext() {
            return currentIndex < elementCount;
        }

        @Override
        public T next() {
            currentIndex++;
            currentNode = currentNode.next;
            return (T)currentNode.element;
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }
    
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(lastNode, element, null);
        if (firstNode == null) 
            firstNode = newNode;
        else
            lastNode.next = newNode;

        lastNode = newNode;
        elementCount++;
    }

    @Override
    public void add(int position, T element) {
        if(position-1 == elementCount){
            add(element);
            return;
        }
        Node<T> oldEntry = getNode(position);
        Node<T> newEntry = new Node<>(oldEntry.prev, element, oldEntry);
        
        if(oldEntry.prev == null)
            firstNode = newEntry;
        else
            oldEntry.prev.next = newEntry;
        
        oldEntry.prev = newEntry;
        
        elementCount++;
    }

    @Override
    public void addAll(CollectionInterface<? extends T> anotherCollection) {
        addAll(elementCount, anotherCollection, 0, anotherCollection.size());
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
        Node<T> currentNode = getNode(position);
        Node<T> endingNode = currentNode.next;
        
        Object[] arr = anotherCollection.toArray();
        for(Object elem : arr){
            Node<T> newNode = new Node<>(currentNode, (T)elem, currentNode.next);
            currentNode.next = newNode;
            currentNode.next.prev = newNode;
            currentNode = newNode;
        }
        //endingNode.prev = currentNode;
        elementCount += arr.length;
    }
    
    @Override
    public void replace (int position, T e) {
        Node<T> currentNode = getNode(position);
        
        currentNode.element = e;
    }

    @Override
    public T get(int position) {
        Node<T> currentNode = getNode(position);
        return currentNode.element;
    }

    @Override
    public int indexOf(T element){
        Node<T> currentNode = firstNode;
        if(currentNode.element.equals(element))
            return 1;
        
        for(int i = 1; i < elementCount; i++){
            currentNode = currentNode.next;
            if(currentNode.element.equals(element))
                return i;
        }
        
        return -1;
    };
    
    @Override
    public boolean contains(T elem) {
        Node<T> currentNode = firstNode;
        if(currentNode.element.equals(elem))
            return true;
        
        for(int i = 2; i <= elementCount; i++){
            currentNode = currentNode.next;
            if(currentNode.element.equals(elem))
                return true;
        }    
        
        return false;
    }

    @Override
    public T remove(int position) {
        Node<T> targetNode = getNode(position);
        if(targetNode == firstNode){
            firstNode = firstNode.next;
            firstNode.prev = null;
        }else if(targetNode == lastNode){
            lastNode = lastNode.prev;
            lastNode.next = null;
        }else{
            targetNode.prev.next = targetNode.next;
            targetNode.next.prev = targetNode.prev;
        }
        elementCount--;
        return targetNode.element;
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
        result[0] = currentNode.element;
        
        for(int i = 1; i < elementCount; i++){
            currentNode = currentNode.next;
            result[i] = currentNode.element;
        }
        return result;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        Node<T> currentNode = firstNode;
        str.append(String.format("[%2s] %s\n", 1, currentNode.element));
        
        for(int i = 2; i <= elementCount; i++){
            currentNode = currentNode.next;
            str.append(String.format("[%2s] %s\n", i, currentNode.element));
        }
        return str.toString();
    }
}
