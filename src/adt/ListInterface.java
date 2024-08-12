package adt;

public interface ListInterface<T>{
    /**Adds a new element into this list.
     * 
     * @param element element to be added
     */
    void add(T element);
    
    /**Adds a new element into this list at specified position.
     * 
     * @param position position (inclusive) to be inserted, starting from 1.
     * @param element element to be added
     */
    void add(int position, T element);
    
    /**Adds a new element to the front of this list.
     * 
     * @param element element to be added
     */
    default void addFirst(T element){
        add(1, element);
    };
    
    /**Adds a new element to the end of this list.
     * 
     * @param element element to be added
     */
    default void addLast(T element){
        add(element);
    };
    
    /**Appends the elements in the given collection to the end of this list.
     * 
     * @param anotherCollection a collection to be appended to the end of this list.
     */
    void addAll(CollectionInterface<? extends T> anotherCollection);
    
    /**Appends the elements in the given collection to the end of this list.
     * 
     * Positions starts from 1
     * 
     * @param position the position (inclusive) in this list to start inserting the elements into.
     * @param anotherCollection a collection to be appended to the end of this list.
     */
    void addAll(int position, CollectionInterface<? extends T> anotherCollection);
    
    /**Appends the elements in the given collection to the end of this list.
     * 
     * @param position the position (inclusive) in this list to start inserting the elements into.
     * @param anotherCollection a collection to be appended to the end of this list.
     * @param startingPosition the position (inclusive) in the given collection to start copying from.
     */
    void addAll(int position, CollectionInterface<? extends T> anotherCollection, int startingPosition);
    
    /**Appends the elements in the given collection to the end of this list.
     * 
     * @param position the position (inclusive) in this list to start inserting the elements into.
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
    void replace(int position, T e);
    
    //==========================================================================
    /**Retrieve element at given position in this list.
     * 
     * @param position position of element to retrieve, starting from 1.
     * @return the element with the given position in this list
     */
    T get(int position);
    
    /**Retrieve the first element in this list.
     * 
     * @return the first element in this list
     */
    default T getFirst(){
        return get(1);
    };
    
    /**Retrieve the last element in this list.
     * 
     * @return the last element in this list
     */
    default T getLast(){
        return get(size());
    };
    
    /**Return the position where the element is first found.
     * 
     * @param element position of element to retrieve, starting from 1.
     * @return the position of element; -1 if it does not exist in this list.
     */
    int indexOf(T element);
    
    /**Check if this list contains given element.
     * 
     * @param elem element to be checked if present in this list.
     * @return {@code true} if given element can be found within the list.
     */
    boolean contains(T elem);
    
    //==========================================================================
    /**Removes and return element at the given position in the list.
     * Element at the given position is returned and removed. 
     * All elements after the removed element is shifted left.
     * 
     * @param position position of element to be removed, starting from 1.
     * @return the element with the given position in the list
     */
    T remove(int position);
    
    /**Removes and return the first element in the list.
     * Element at the given position is returned and removed. 
     * All elements after the removed element is shifted left.
     * 
     * @return the first element in the list
     */
    default T removeFirst(){
        return remove(1);
    };
    
    /**Removes and return the first element in the list.
     * Element at the given position is returned and removed. 
     * All elements after the removed element is shifted left.
     * 
     * @return the first element in the list
     */
    default T removeLast(){
        return remove(size());
    };
    
    //==========================================================================
    /**Get the number of elements within this list
     * 
     * @return the number of elements within this list.
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
