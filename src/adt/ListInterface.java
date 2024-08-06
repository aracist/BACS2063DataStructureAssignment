package adt;

import java.util.Collection;

public interface ListInterface<T> {
    /**Adds a new entry into list.
     * 
     * @param entry entry to be added
     */
    void add(T entry);
    
    /**Adds a new entry into list.
     * 
     * @param position position to be inserted, starting from 1.
     * @param entry entry to be added
     */
    void add(T entry, int position);
    
    /**Appends the entries in the given list to the end of this list.
     * 
     * @param anoterList a list to be appended to the end of this list.
     */
    //void append(Collection<? extends T> anoterList);
    
    /**Replace existing entry at {@code position} with given entry.
     * 
     * @param position position to be inserted, starting from 1.
     * @param e entry to replace the existing entry.
     */
    void replace(T e, int position);
    
    //==========================================================================
    /**Retrieve entry at given position in this list.
     * 
     * @param position entry to be added, starting from 1.
     * @return the entry with the given position in this list
     */
    T get(int position);
    
    /**Check if this list contains given entry.
     * 
     * @param elem entry to be checked if present in this list.
     * @return {@code true} if given entry can be found within the list.
     */
    boolean contains(T elem);
    
    //==========================================================================
    /**Removes and return entry at the given position in the list.
     * Element at the given position is returned and removed. 
     * All entries after the removed entry is shifted left.
     * 
     * @param position entry to be removed, starting from 1.
     * @return the entry with the given position in the list
     */
    T remove(int position);
    
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
