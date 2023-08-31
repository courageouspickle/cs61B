package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     * @param capacity the size of the array
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = capacity / 2;
        last = capacity / 2;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last++;
        if (last == rb.length) {
            last = 0;
        }
        fillCount++;

        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T temp = rb[first];
        rb[first] = null;
        first++;
        if (first == rb.length) {
            first = 0;
        }
        fillCount--;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

    /**
     * Return the capacity of the buffer.
     * @return capacity
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Return the number of items input by the user.
     * @return number of items
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Override the Object method equals(), checking if every element in the buffer is the same and in the same order.
     * @param o the object to be checked
     * @return whether they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArrayRingBuffer)) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer) o;
        Iterator<T> me = iterator();
        Iterator<T> them = other.iterator();
        if (fillCount() != other.fillCount()) {
            return false;
        }
        while (me.hasNext() && them.hasNext()) {
            if (me.next() != them.next()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Create a new iterator to iterate over the ArrayRingBuffer.
     * @return the iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Uhhh<T>();
    }

    /**
     * Define the iterator that the above method returns.
     * @param <T> the objects in the buffer.
     */
    private class Uhhh<T> implements Iterator<T> {
        private int index = 0;

        /**
         * Return whether or not the buffer has another element after the current one.
         * @return whether it does.
         */
        @Override
        public boolean hasNext() {
            if (first + index > rb.length - 1) {
                return (first + index - rb.length) < last;
            }
            return (first + index) < last;
        }

        /**
         * Return the next element in the iteration.
         * @return the element of type T
         */
        @Override
        public T next() {
            if (first + index > rb.length - 1) {
                index++;
                return (T) rb[first + index - rb.length];
            }
            index++;
            return (T) rb[first + index];
        }
    }

}
