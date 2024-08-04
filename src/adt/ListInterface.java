package adt;
public interface ListInterface<T> {
    /**Get the number of elements within this list
     * 
     * @return the number of elements within this list.
     */
    int size();
    
    /**Adds a new element into list.
     * 
     * @param e element to be added
     * @return {@code true} if successfully added
     */
    boolean add(T e);
    
    /**Adds a new element into list.
     * 
     * @param index position to be inserted.
     * @param e element to be added
     * @return {@code true} if successfully added
     */
    boolean add(T e, int index);
    
    /**Retrieve element at given index in this list.
     * 
     * @param index element to be added
     * @return the element with the given index in this list
     */
    T get(int index);
    
    /**Removes and return element at the given index in the list.
     * Element at the given index is returned and removed. 
     * All elements after the removed element is shifted left.
     * 
     * @param index element to be removed
     * @return the element with the given index in the list
     */
    T remove(int index);
    
    /**Removes all element from list.
     */
    void clear();
    
    /**Check if list is empty
     * 
     * @return {@code true} if list is empty
     */
    boolean isEmpty();
}
