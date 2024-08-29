package adt;

public interface ListInterface<T>{
    /**Adds a new data into this list.
     * 
     * @param data data to be added
     */
    void add(T data);
    
    /**Adds a new data into this list at specified position.
     * 
     * @param position position (inclusive) to be inserted, starting from 1.
     * @param data data to be added
     */
    void add(int position, T data);
    
    /**Adds a new data to the front of this list.
     * 
     * @param data data to be added
     */
    default void addFirst(T data){
        add(1, data);
    };
    
    /**Adds a new data to the end of this list.
     * 
     * @param data data to be added
     */
    default void addLast(T data){
        add(data);
    };
    
    /**Appends the data in the given collection to the end of this list.
     * 
     * @param anotherCollection a collection to be appended to the end of this list.
     */
    default void addAll(CollectionInterface<? extends T> anotherCollection){
        //pass in 1 as position if elementCount = 0
        addAll(anotherCollection, 1, anotherCollection.size());
    };
    
    /**Appends the data in the given collection to the end of this list.
     * 
     * @param anotherCollection a collection to be appended to the end of this list.
     * @param startingPosition the position (inclusive) in the given collection to start copying from.
     */
    default void addAll(CollectionInterface<? extends T> anotherCollection, int startingPosition){
        addAll(anotherCollection, startingPosition, anotherCollection.size() - startingPosition + 1);
    };
    
    /**Appends the data in the given collection to the end of this list.
     * 
     * @param anotherCollection a collection to be appended to the end of this list.
     * @param startingPosition the position (inclusive) in the given collection to start copying from.
     * @param length the amount of elements to copy.
     */
    void addAll(CollectionInterface<? extends T> anotherCollection, int startingPosition, int length);
    
    /**Appends the data in the given collection to a given position in this list.
     * 
     * Positions starts from 1
     * 
     * @param position the position (inclusive) in this list to start inserting the data into.
     * @param anotherCollection a collection to be appended to the end of this list.
     */
    default void addAll(int position, CollectionInterface<? extends T> anotherCollection){
         addAll(position, anotherCollection, 1, anotherCollection.size());
    };
    
    /**Appends the data in the given collection to a given position in this list.
     * 
     * @param position the position (inclusive) in this list to start inserting the data into.
     * @param anotherCollection a collection to be appended to the end of this list.
     * @param startingPosition the position (inclusive) in the given collection to start copying from.
     */
    default void addAll(int position, CollectionInterface<? extends T> anotherCollection, int startingPosition){
        addAll(position, anotherCollection, startingPosition, anotherCollection.size() - startingPosition + 1);
    };
    
    
    /**Appends the data in the given collection to a given position in this list.
     * 
     * @param position the position (inclusive) in this list to start inserting the data into.
     * @param anotherCollection a collection to be appended to the end of this list.
     * @param startingPosition the position (inclusive) in the given collection to start copying from.
     * @param length the amount of elements to copy.
     */
    void addAll(int position, CollectionInterface<? extends T> anotherCollection, int startingPosition, int length);
    
    /**Replace existing data at {@code position} with given data.
     * 
     * @param position position to be inserted, starting from 1.
     * @param e data to replace the existing data.
     */
    void replace(int position, T e);
    
    //==========================================================================
    /**Retrieve data at given position in this list.
     * 
     * @param position position of data to retrieve, starting from 1.
     * @return the data with the given position in this list
     */
    T get(int position);
    
    /**Retrieve the first data in this list.
     * 
     * @return the first data in this list
     */
    default T getFirst(){
        return get(1);
    };
    
    /**Retrieve the last data in this list.
     * 
     * @return the last data in this list
     */
    default T getLast(){
        return get(size());
    };
    
    /**Return the position where the data is first found.
     * 
     * @param data position of data to retrieve, starting from 1.
     * @return the position of data; -1 if it does not exist in this list.
     */
    int positionOf(T data);
    
    /**Check if this list contains given data.
     * 
     * @param elem data to be checked if present in this list.
     * @return {@code true} if given data can be found within the list.
     */
    boolean contains(T elem);
    
    //==========================================================================
    /**Removes and return data at the given position in the list.
     * data at the given position is returned and removed. 
     * All data after the removed data is shifted left.
     * 
     * @param position position of data to be removed, starting from 1.
     * @return the data with the given position in the list
     */
    T remove(int position);
    
    /**Removes and return the first data in the list.
     * data at the given position is returned and removed. 
     * All data after the removed data is shifted left.
     * 
     * @return the first data in the list
     */
    default T removeFirst(){
        return remove(1);
    };
    
    /**Removes and return the first data in the list.
     * data at the given position is returned and removed. 
     * All data after the removed data is shifted left.
     * 
     * @return the first data in the list
     */
    default T removeLast(){
        return remove(size());
    };
    
    //==========================================================================
    /**Get the number of data within this list
     * 
     * @return the number of data within this list.
     */
    int size();
    
    /**Removes all data from list.
     */
    void clear();
    
    /**Check if list is empty
     * 
     * @return {@code true} if list is empty
     */
    boolean isEmpty();
}
