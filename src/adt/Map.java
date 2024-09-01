package adt;

public class Map<K, V> implements MapInterface<K,V>{
    ArrayList<K> keyArr;
    ArrayList<V> valueArr;
    int elementCount;
    
    public Map(){
        keyArr = new ArrayList<>();
        valueArr = new ArrayList<>();
        elementCount = 0;
    }
    
    @Override
    public boolean containsKey(K key) {
        for(K k : keyArr){
            if (k.equals(key))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for(V v : valueArr){
            if (v.equals(value))
                return true;
        }
        return false;
    }

    @Override
    public boolean add(K key, V value) {
        if(containsKey(key))
            return false;
        
        elementCount ++;
        keyArr.add(key);
        valueArr.add(value);
        return true;
    }

    @Override
    public V get(K key) {
        for (int position = 1; position <= elementCount; position++){
            if(keyArr.get(position).equals(key))
                return valueArr.get(position);
        }
        return null;
    }

    @Override
    public V remove(K key) {
        V result = null;
        for (int position = 1; position <= elementCount; position++){
            if(keyArr.get(position).equals(key)){
                result = valueArr.get(position);
                keyArr.remove(position);
                valueArr.remove(position);
                return result;
            }
        }
        return result;
   }

    @Override
    public K[] keyArray() {
        return (K[])keyArr.toArray();
    }

    @Override
    public V[] valueArray() {
        return (V[])valueArr.toArray();
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
        keyArr = new ArrayList<>();
        valueArr = new ArrayList<>();
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
}
