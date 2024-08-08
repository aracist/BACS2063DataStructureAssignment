package adt;

public interface ListInterface<T>{
    /**Adds a new element into list.
     * 
     * @param element element to be added
     */
    void add(T element);
    
    /**Adds a new element into list.
     * 
     * @param position position (inclusive) to be inserted, starting from 1.
     * @param element element to be added
     */
    void add(T element, int position);
    
    /**Appends the entries in the given collection to the end of this list.
     * 
     * @param anotherCollection a collection to be appended to the end of this list.
     */
    void addAll(CollectionInterface<? extends T> anotherCollection);
    
    /**Appends the entries in the given collection to the end of this list.
     * 
     * Positions starts from 1
     * 
     * @param position the position (inclusive) in this list to start inserting the entries into.
     * @param anotherCollection a collection to be appended to the end of this list.
     */
    void addAll(int position, CollectionInterface<? extends T> anotherCollection);
    
    /**Appends the entries in the given collection to the end of this list.
     * 
     * @param position the position (inclusive) in this list to start inserting the entries into.
     * @param anotherCollection a collection to be appended to the end of this list.
     * @param startingPosition the position (inclusive) in the given collection to start copying from.
     */
    void addAll(int position, CollectionInterface<? extends T> anotherCollection, int startingPosition);
    
    /**Appends the entries in the given collection to the end of this list.
     * 
     * @param position the position (inclusive) in this list to start inserting the entries into.
     * @param anotherCollection a collection to be appended to the end of this list.
     * @param startingPosition the position (inclusive) in the given collection to start copying from
     * @param stoppingPosition the position (inclusive) in the given collection to copy until.
     */
    void addAll(int position, CollectionInterface<? extends T> anotherCollection, int startingPosition, int stoppingPosition);
    
    /**Replace existing element at {@code position} with given element.
     * 
     * @param position position to be inserted, starting from 1.
     * @param e element to replace the existing element.
     */
    void replace(T e, int position);
    
    //==========================================================================
    /**Retrieve element at given position in this list.
     * 
     * @param position position of element to retrieve, starting from 1.
     * @return the element with the given position in this list
     */
    T get(int position);
    
    /**Check if this list contains given element.
     * 
     * @param elem element to be checked if present in this list.
     * @return {@code true} if given element can be found within the list.
     */
    boolean contains(T elem);
    
    //==========================================================================
    /**Removes and return element at the given position in the list.
     * Element at the given position is returned and removed. 
     * All entries after the removed element is shifted left.
     * 
     * @param position position of element to be removed, starting from 1.
     * @return the element with the given position in the list
     */
    T remove(int position);
    
    //==========================================================================
    /**Get the number of entries within this list
     * 
     * @return the number of entries within this list.
     */
    int size();
    
    /**Removes all element from list.
     */
    void clear();
    
    /**Check if list is empty
     * 
     * @return {@code true} if list is empty
     */
    boolean isEmpty();
}
