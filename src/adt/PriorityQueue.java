package adt;

public class PriorityQueue<T> implements QueueInterface<T>{
    private ArrayList<T> heap;
    private int elementCount;
    
    public PriorityQueue(){
        heap = new ArrayList<>();
        elementCount = 0;
    }
    
    private class Entry{
        int ranking;
        int count;
        T data;
        int position;
        
        Entry(){
            this(0,0,null,0);
        }
        
        Entry(int ranking, T data, int position){
            this(ranking, 0, data, position);
        }
        
        Entry(int ranking, int count, T data, int position){
            this.ranking = ranking;
            this.count = count;
            this.data = data;
            this.position = position;
        }
    }
    
    @Override
    public void enqueue(T data) {
        heap.addLast(data);
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T dequeue() {
        T result = getFront();
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        return result;
    }

    @Override
    public T getFront() {
        return heap.getFirst();
    }

    @Override
    public int size() {
        return elementCount;
    }
    
    @Override
    public void clear() {
        heap = new ArrayList<>();
        elementCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }
    
}
