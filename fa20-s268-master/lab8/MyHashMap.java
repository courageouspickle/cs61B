import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private ArrayList<Pair>[] hashMap;
    private double loadFactor;
    private int size;

    private class Pair {
        public K key;
        public V value;
        public Pair(K k, V v) {
            key = k;
            value = v;
        }
    }

    public MyHashMap() {
        this.hashMap = (ArrayList<Pair>[]) new ArrayList[16];
        this.loadFactor = .75;
        size = 0;
    }

    public MyHashMap(int initialSize) {
        this.hashMap = (ArrayList<Pair>[]) new ArrayList[initialSize];
        this.loadFactor = .75;
        size = 0;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.hashMap = (ArrayList<Pair>[]) new ArrayList[(initialSize)];
        this.loadFactor = loadFactor;
        size = 0;
    }

    private void resize() {
        ArrayList<Pair>[] temp = hashMap;
        size = 0;
        hashMap = (ArrayList<Pair>[]) new ArrayList[temp.length * 2];
        for(ArrayList<Pair> bucket : temp) {
            if (bucket != null) {
                for (Pair p : bucket) {
                    put(p.key, p.value);
                }
            } else bucket = new ArrayList<Pair>();
        }
    }

    @Override
    public void clear() {
        this.hashMap = (ArrayList<Pair>[]) new ArrayList[size];
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        ArrayList<Pair> temp = hashMap[Math.floorMod(key.hashCode(), hashMap.length)];
        for(Pair p : temp) {
            if (p.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        ArrayList<Pair> temp = hashMap[Math.floorMod(key.hashCode(), hashMap.length)];
        for(Pair p : temp) {
            if (p.key.equals(key)) {
                return p.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public void put(K key, V value) {
        if (hashMap.length <= size / loadFactor) {
            resize();
        }
        ArrayList<Pair> temp = hashMap[Math.floorMod(key.hashCode(), hashMap.length)];
        if (temp != null) {
            for (Pair p : temp) {
                if (p.key.equals(key)) {
                    temp.remove(p);
                    temp.add(new Pair((K) key, (V) value));
                    return;
                }
            }
        } else hashMap[Math.floorMod(key.hashCode(), hashMap.length)] = temp = new ArrayList<Pair>();
        temp.add(new Pair((K) key,(V) value));
        size++;
    }

    @Override
    public Set<K> keySet() {
        return new HashSet(Set.of(hashMap));
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new hashIterator(this);
    }

    private class hashIterator implements Iterator<K> {

        private MyHashMap map;
        private int index1 = 0;
        private int index2 = 0;

        public hashIterator(MyHashMap m) {
            map = m;
        }

        @Override
        public boolean hasNext() {
            if (index1 >= map.hashMap.length
                    || (index1 == map.hashMap.length - 1 && index2 == ((ArrayList<Pair>) map.hashMap[index1]).size())) {
                return false;
            } else return true;
        }

        @Override
        public K next() {
            if (index1 >= map.hashMap.length) {
                return null;
            }
            K temp = ((ArrayList<Pair>) map.hashMap[index1]).get(index2).key;
            index2++;
            if (index2 >= ((ArrayList<Pair>) map.hashMap[index1]).size() - 1) {
                index1++;
                index2 = 0;
            }
            return temp;
        }
    }
}
