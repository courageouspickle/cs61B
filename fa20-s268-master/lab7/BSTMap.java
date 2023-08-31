import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node<K extends Comparable<K>, V> {
        public V value;
        public K key;
        public Node parent;
        public Node front;
        public Node back;
        public Node(K key, V value) {
            this.value = value;
            this.key = key;
        }
        public boolean containsKey(K k) {
            if (k.compareTo(key) == 0) {
                return true;
            }
            if (k.compareTo(this.key) < 0 && back != null) {
                return back.containsKey(k);
            }
            if (k.compareTo(this.key) > 0 && front != null) {
                return front.containsKey(k);
            }
            return false;
        }
        public V get(K k) {
            if (k.compareTo(key) == 0) {
                return value;
            }
            if (k.compareTo(this.key) < 0 && back != null) {
                return (V) back.get(k);
            }
            if (k.compareTo(this.key) > 0 && front != null) {
                return (V) front.get(k);
            }
            throw new IllegalArgumentException("no such key in map");
        }
        public void put(K k, V value) {
            if (k.compareTo(key) == 0) {
                throw new IllegalArgumentException("key already in the map");
            }
            if (k.compareTo(this.key) < 0 && back != null) {
                back.put(k, value);
            }
            if (k.compareTo(this.key) > 0 && front != null) {
                front.put(k, value);
            }
            if (k.compareTo(this.key) < 0 && back == null) {
                back = new Node(k, value);
                back.parent = this;
            }
            if (k.compareTo(this.key) > 0 && front == null) {
                front = new Node(k, value);
                front.parent = this;
            }
        }
    }
    private Node root;
    private int size;
    public BSTMap() {
        size = 0;
    }
    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        return root.containsKey(key);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        return (V) root.get(key);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        size++;
        if (root == null || root.key == null) {
            root = new Node(key, value);
            return;
        }
        root.put(key, value);
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
    public void printInOrder() {
        Node cur = root;
        while (cur.back != null) {
            cur = cur.back;
        }
        System.out.print(cur.value + " " + cur.parent.value);
    }
}
