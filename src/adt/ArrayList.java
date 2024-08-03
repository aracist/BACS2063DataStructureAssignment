package adt;

public class ArrayList<T> implements ListInterface<T>{
    private final static int DEFAULT_SIZE = 5;
    private int elemCount = 0;
    private Object[] elementArray;
    
    public ArrayList(){
        this(DEFAULT_SIZE);
    }
    
    public ArrayList(int size){
        this.elementArray = new Object[size];
    }
    
    private void checkCapacity(){
        if(elemCount + 1 == elementArray.length){
            expend();
        }
    }
    
    private void expend(){
        Object[] newArray = new Object[elemCount + 1];
    }

    @Override
    public int size() {
        return elemCount;
    }
    
    @Override
    public boolean add(T e) {
        elemCount++;
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean add(int index, T e){
        elemCount++;
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T remove(int index) {
        if(index < elemCount){
            T result = (T)elementArray[index];
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        elementArray = new Object[DEFAULT_SIZE];
        elemCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return elemCount == 0;
    }
}
