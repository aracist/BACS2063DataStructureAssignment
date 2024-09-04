package adt;

import java.util.Iterator;
import java.util.Objects;

public class Map<K, V> implements MapInterface<K,V>, Iterable{
    final private RBTree<mapElement> redBlackTree;
    private int elementCount;

    
    private class mapElement{
        K key;
        V value;
        
        mapElement(K key, V value){
            this.key = key;
            this.value = value;
        }
        
        @Override
        public int hashCode(){
            return key.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final mapElement other = (mapElement) obj;
            return Objects.equals(this.key, other.key);
        }
    }
    
    public Map(){
        redBlackTree = new RBTree<>();
        elementCount = 0;
    }
    
    @Override
    public boolean containsKey(K key) {
        return (redBlackTree.search(key.hashCode()) != null);
    }

    @Override
    public boolean containsValue(V value) {
        for(mapElement mE : redBlackTree){
            if(mE.value.equals(value))
                return true;
        }
        return false;
    }

    @Override
    public boolean add(K key, V value) {
        if(containsKey(key))
            return false;
        redBlackTree.insert(new mapElement(key, value));
        elementCount ++;
        return true;
    }

    @Override
    public V get(K key) {
        mapElement result = redBlackTree.search(key.hashCode());
        return (result != null) ? result.value : null;
    }

    @Override
    public V remove(K key) {
        mapElement result = redBlackTree.delete(key.hashCode());
        elementCount--;
        return (result != null) ? result.value : null;
   }

    @Override
    public <K> K[] keyArray() {
        K[] result = (K[])new Object[elementCount];
        
        int count = 0;
        
        for(mapElement me : redBlackTree){
            result[count++] = (K)me.key;
        }
        
        return result;
    }

    @Override
    public <V> V[] valueArray() {
        V[] result = (V[])new Object[elementCount];
        
        int count = 0;
        
        for(mapElement me : redBlackTree){
            result[count++] = (V)me.value;
        }
        
        return result;    
    }

    @Override
    public Object[][] elementArray() {
        Object[][] result = new Object[2][elementCount];
        result[1] = keyArray();
        result[2] = valueArray();
        return result;
    }

    @Override
    public void clear() {
        redBlackTree.clear();
        elementCount = 0;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }
    
    private class Itr implements Iterator{
        Iterator rbtitr = redBlackTree.iterator();

        @Override
        public boolean hasNext() {
            return rbtitr.hasNext();
        }

        @Override
        public Object next() {
            return ((mapElement)rbtitr.next()).value;
        }
        
        
    }
    @Override
    public Iterator iterator() {
        return new Itr();
    }
}
