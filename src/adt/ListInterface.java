package adt;
public interface ListInterface<T> {
    /**Adds a new entry into list.
     * 
     * @param entry entry to be added
     * @return {@code true} if successfully added
     */
    boolean add(T entry);
    
    /**Adds a new entry into list.
     * 
     * @param index position to be inserted, starting from 1.
     * @param entry entry to be added
     * @return {@code true} if successfully added
     */
    boolean add(T entry, int index);
    
    /**Replace existing entry at {@code index} with given entry.
     * 
     * @param index position to be inserted, starting from 1.
     * @param e entry to replace the existing entry.
     * @return {@code true} if successfully replaced;
     * {@code false} if no preexisting entry at given index.
     */
    boolean replace(T e, int index);
    
    //==========================================================================
    /**Retrieve entry at given index in this list.
     * 
     * @param index entry to be added, starting from 1.
     * @return the entry with the given index in this list
     */
    T get(int index);
    
    /**Check if this list contains given entry.
     * 
     * @param elem entry to be checked if present in this list.
     * @return {@code true} if given entry can be found within the list.
     */
    boolean contains(T elem);
    
    //==========================================================================
    /**Removes and return entry at the given index in the list.
     * Element at the given index is returned and removed. 
     * All entries after the removed entry is shifted left.
     * 
     * @param index entry to be removed, starting from 1.
     * @return the entry with the given index in the list
     */
    T remove(int index);
    
    //==========================================================================
    /**Get the number of entries within this list
     * 
     * @return the number of entries within this list.
     */
    int size();
    
    /**Removes all entry from list.
     */
    void clear();
    
    /**Check if list is empty
     * 
     * @return {@code true} if list is empty
     */
    boolean isEmpty();
}
