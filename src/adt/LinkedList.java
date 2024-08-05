package adt;

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
    
    @Override
    public void add(T entry) {
        Node<T> newNode = new Node<>(lastNode, entry, null);
        if (firstNode == null) 
            firstNode = newNode;
        else
            lastNode.next = newNode;

        lastNode = newNode;
    }

    @Override
    public boolean add(T entry, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean replace(T e, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contains(T elem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T remove(int index) {
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
    
}
