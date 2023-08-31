package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private class Node {
        private T value;
        private double prio;

        private Node(T v, double p) {
            value = v;
            prio = p;
        }

        private Node(Node n) {
            this.value = n.value;
            this.prio = n.prio;
        }

        private void setPrio(double p) {
            prio = p;
        }
        private double getPrio() {
            return this.prio;
        }
        @Override
        public int hashCode() {
            return value.hashCode();
        }
        @Override
        public boolean equals(Object n) {
            return value == ((Node) n).value;
        }
    }

    private ArrayList<Node> heap;
    private HashMap<T, Integer> items;
    public ArrayHeapMinPQ() {
        this.heap = new ArrayList<>();
        heap.add(null);
        this.items = new HashMap<T, Integer>();
    }
    /**
     * Adds an item with the given priority value. Throws an
     * IllegalArgumentException
     * if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority) {
        Node temp = new Node(item, priority);
        if (items.containsKey(item)) {
            throw new IllegalArgumentException("item already in heap");
        }
        items.put(item, size());
        heap.add(temp);
        rectify(size());
    }

    private void rectify(int index) {
        if (heap.get(index) != null && heap.get(index / 2) != null
                && heap.get(index).prio < heap.get(index / 2).prio) {
            swap(index, index / 2);
            rectify(index / 2);
        }
    }
    private void swap(int index1, int index2) {
        Node temp = new Node(heap.get(index1));
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
        items.put(heap.get(index2).value, index2);
        items.put(heap.get(index1).value, index1);
        items.remove(heap.get(index2).value, index1);
        items.remove(heap.get(index1).value, index2);
    }

    private void demote(int index) {
        if (index * 2 + 1 > heap.size() - 1) {
            return;
        }
        if (heap.get(index).prio > heap.get(index * 2).prio
                || heap.get(index).prio > heap.get(index * 2 + 1).prio) {
            if (heap.get(index * 2).prio <= heap.get(index * 2 + 1).prio) {
                swap(index, index * 2);
                demote(index * 2);
            } else {
                swap(index, index * 2 + 1);
                demote(index * 2 + 1);
            }
        }

    }
    /**
     *  Returns true if the PQ contains the given item.
     */
    public boolean contains(T item) {
        return items.containsKey(item);
    }
    /**
     *  Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (size() == 2) {
            if (heap.get(1).prio < heap.get(2).prio) {
                return heap.get(1).value;
            } else {
                return heap.get(2).value;
            }
        }
        if (size() < 1) {
            throw new NoSuchElementException();
        }
        return heap.get(1).value;
    }
    /**
     *  Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (size() > 2) {
            swap(1, size());
            T result = heap.remove(size()).value;
            demote(1);
            return result;
        } else {
            if (size() == 2) {
                if (heap.get(1).prio < heap.get(2).prio) {
                    return heap.remove(1).value;
                } else {
                    return heap.remove(2).value;
                }
            } else if (heap.size() == 2) {
                return heap.remove(1).value;
            }
            throw new NoSuchElementException("heap is empty");
        }
    }
    /**
     * Returns the number of items in the PQ.
     */
    public int size() {
        return heap.size() - 1;
    }
    /**
     * Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!items.containsKey(item)) {
            throw new NoSuchElementException(item + "");
        }
        heap.get(items.get(item)).setPrio(priority);
        rectify(items.get(item));
        demote(items.get(item));
    }
    private double getPrio(T item) {
        return heap.get(items.get(item)).prio;
    }
}
