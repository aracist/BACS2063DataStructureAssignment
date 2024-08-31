
package adt;

public interface MapInterface<K, V>{
    boolean containsKey(K key);
    boolean containsValue(V value);
    
    boolean add(K key, V value);
    V get(K key);
    V remove(K key);
    
    K[] keyArray();
    V[] valueArray();
    Object[][] elementArray();
    
    void clear();
    int size();
    boolean isEmpty();
}
