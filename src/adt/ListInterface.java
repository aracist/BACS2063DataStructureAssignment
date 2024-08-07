package adt;

import java.util.Collection;

public interface ListInterface<T>{
    /**Adds a new entry into list.
     * 
     * @param entry entry to be added
     */
    void add(T entry);
    
    /**Adds a new entry into list.
     * 
     * @param position position (inclusive) to be inserted, starting from 1.
     * @param entry entry to be added
     */
    void add(T entry, int position);
    
    /**Appends the entries in the given collection to the end of this list.
     * 
     * @param anotherCollection a collection to be appended to the end of this list.
     */
    void addAll(Collection<? extends T> anotherCollection);
    
    /**Appends the entries in the given collection to the end of this list.
     * 
     * Positions starts from 1
     * 
     * @param position the position (inclusive) in this list to start inserting the entries into.
     * @param anotherCollection a collection to be appended to the end of this list.
     */
    void addAll(int position, Collection<? extends T> anotherCollection);
    
    /**Appends the entries in the given collection to the end of this list.
     * 
     * @param position the position (inclusive) in this list to start inserting the entries into.
     * @param anotherCollection a collection to be appended to the end of this list.
     * @param startingPosition the position (inclusive) in the given collection to start copying from.
     */
    void addAll(int position, Collection<? extends T> anotherCollection, int startingPosition);
    
    /**Appends the entries in the given collection to the end of this list.
     * 
     * @param position the position (inclusive) in this list to start inserting the entries into.
     * @param anotherCollection a collection to be appended to the end of this list.
     * @param startingPosition the position (inclusive) in the given collection to start copying from
     * @param stoppingPosition the position (inclusive) in the given collection to copy until.
     */
    void addAll(int position, Collection<? extends T> anotherCollection, int startingPosition, int stoppingPosition);
    
    /**Replace existing entry at {@code position} with given entry.
     * 
     * @param position position to be inserted, starting from 1.
     * @param e entry to replace the existing entry.
     */
    void replace(T e, int position);
    
    //==========================================================================
    /**Retrieve entry at given position in this list.
     * 
     * @param position position of entry to retrieve, starting from 1.
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
     * @param position position of entry to be removed, starting from 1.
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
