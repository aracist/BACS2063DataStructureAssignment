package adt;
import java.util.Collection;
import java.util.Objects;

public class LinkedList<T> implements ListInterface<T> {
    private int entryCount;
    Node<T> firstNode;
    Node<T> lastNode;

    
    private static class Node<T>{
        T entry;
        Node<T> prev;
        Node<T> next;
        
        Node(Node<T> prev, T entry, Node<T> next){
            this.prev = prev;
            this.entry = entry;
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
    public void add(T entry) {
        Node<T> newNode = new Node<>(lastNode, entry, null);
        if (firstNode == null) 
            firstNode = newNode;
        else
            lastNode.next = newNode;

        lastNode = newNode;
        entryCount++;
    }

    @Override
    public void add(T entry, int position) {
        Objects.checkIndex(position, entryCount);
        Node<T> current = getNode(position);
        Node<T> newEntry = new Node<>(current.prev, entry, current);
        current.prev.next = newEntry;
        current.prev = newEntry;
        entryCount++;
    }

    @Override
    public void addAll(Collection<? extends T> anotherCollection) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addAll(int position, Collection<? extends T> anotherCollection) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addAll(int position, Collection<? extends T> anotherCollection, int startingPosition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addAll(int position, Collection<? extends T> anotherCollection, int startingPosition, int stoppingPosition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void replace(T e, int position) {
        Objects.checkIndex(position, entryCount);
        Node<T> currentNode = getNode(position);
        currentNode.entry = e;
    }

    @Override
    public T get(int position) {
        Objects.checkIndex(position, entryCount);
        Node<T> currentNode = getNode(position);
        return currentNode.entry;
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
        return entryCount;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        entryCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return entryCount == 0;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        Node<T> currentNode = firstNode;
        str.append(String.format("[%2s] %s\n", 1, currentNode.entry));
        
        for(int i = 2; i <= entryCount; i++){
            currentNode = currentNode.next;
            str.append(String.format("[%2s] %s\n", i, currentNode.entry));
        }
        return str.toString();
    }
}
