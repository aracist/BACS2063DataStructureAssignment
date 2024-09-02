package adt;

import java.util.Iterator;

public class PriorityQueue<T> implements QueueInterface<T>, CollectionInterface<T>{
    final private RBTree<Entry> rbTree;
    int elementCount;
    
    public PriorityQueue(){
        rbTree = new RBTree<>();
    }
    
    private class Entry{
        int importance;
        LinkedList<T> linkedList;
        
        Entry(){}
        
        Entry(int importance, T data){
            this.importance = importance;
            this.linkedList = new LinkedList<>();
            linkedList.add(data);
        }
        
        @Override
        public int hashCode(){
            return importance;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Entry other = (Entry) obj;
            return this.importance == other.importance;
        }
    }

    private class Itr implements Iterator<T>{
        Iterator importanceItr;
        Entry currentEntry;
        Iterator entryItr;
        
        Itr(){
            importanceItr = rbTree.iterator();
            if(importanceItr.hasNext()){
                currentEntry = (Entry)importanceItr.next();
                entryItr = currentEntry.linkedList.iterator();
            }
        }
        
        @Override
        public boolean hasNext() {
            return importanceItr.hasNext() || entryItr.hasNext();
        }

        @Override
        public T next() {
            if(!entryItr.hasNext()){
               currentEntry = (Entry)importanceItr.next();
               entryItr = currentEntry.linkedList.iterator();
            }
            return (T)entryItr.next();
        }
    
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public void enqueue(T data){
        enqueue(0, data);
    }
    
    public void enqueue(int importance, T data){
        Entry e = rbTree.search(0);
        if(e != null)
            e.linkedList.add(data);
        else
            rbTree.insert(new Entry(importance, data));
        
        elementCount++;
    }

    @Override
    public T dequeue() {
        if(isEmpty())
            return null;
        
        Entry entry = rbTree.getSmallest();
        System.out.println(entry.linkedList.size());
        T result = entry.linkedList.removeFirst();
        
        if(entry.linkedList.isEmpty())
            rbTree.delete(entry.hashCode());
        elementCount--;
        return result;
    }

    @Override
    public T getFront() {
        if(isEmpty())
            return null;
        
        return rbTree.getSmallest().linkedList.getFirst();
    }

    @Override
    public int size() {
        return elementCount;
    }
    
    @Override
    public void clear() {
        rbTree.clear();
        elementCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }
    
    @Override
    public Object[] toArray() {
        ArrayList<T> arr = new ArrayList<>(elementCount);
        for(T data : this)
            arr.add(data);
        
        return arr.toArray();
    }
    
    @Override
    public String toString() {
        ArrayList<T> arr = new ArrayList<>(elementCount);
        for(T data : this)
            arr.add(data);
        
        return arr.toString();
    }
}