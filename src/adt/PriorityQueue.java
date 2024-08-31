package adt;

public class PriorityQueue<T> implements QueueInterface<T>{
    private ArrayList<Entry> heap;
    
    public PriorityQueue(){
        heap = new ArrayList<>();
    }
    
    private class Entry{
        int importance;
        T data;
        int index;
        
        Entry(){}
        
        Entry(int importance, T data, int index){
            this.importance = importance;
            this.data = data;
            this.index = index;
        }
    }
    
    private int getParent(int index){
        return Math.floorDiv(index - 1, 2);
    }
    
    private int getLeft(int index){
        int left = index * 2 + 1;
        return (left < heap.size()) ? left : -1;
    }
    
    private int getRight(int index){
        int right = index * 2 + 2;
        return (right < heap.size()) ? right : -1;
    }
    
    @Override
    public void enqueue(T data){
        enqueue(0, data);
    }
    
    /**Queues an element into this queue and sort according to its importance.
     * 
     * @param importance the data is more important as this number increases, 
     * e.g. 0 = least important, 1 = more important than 0.
     * @param data the data to be stored
     */
    public void enqueue(int importance, T data){
        heap.addLast(new Entry(importance, data, heap.size()));
        
    }

    @Override
    public T dequeue() {
        T result = getFront();
        heap.removeFirst();
        return result;
    }

    @Override
    public T getFront() {
        return heap.getFirst().data;
    }

    @Override
    public int size() {
        return heap.size();
    }
    
    @Override
    public void clear() {
        heap = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return heap.size() == 0;
    }
    
}