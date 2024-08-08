package adt;
import java.util.Objects;

public class LinkedList<T> implements ListInterface<T> {
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
        Node<T> current = firstNode;
        for(int i = 0; i < position; i++)
            current = current.next;
        return current;
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
    public void add(T element, int position) {
        Objects.checkIndex(position, elementCount);
        Node<T> current = getNode(position);
        Node<T> newEntry = new Node<>(current.prev, element, current);
        current.prev.next = newEntry;
        current.prev = newEntry;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void replace(T e, int position) {
        Objects.checkIndex(position, elementCount);
        Node<T> currentNode = getNode(position);
        currentNode.element = e;
    }

    @Override
    public T get(int position) {
        Objects.checkIndex(position, elementCount);
        Node<T> currentNode = getNode(position);
        return currentNode.element;
    }

    @Override
    public boolean contains(T elem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T remove(int position) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
