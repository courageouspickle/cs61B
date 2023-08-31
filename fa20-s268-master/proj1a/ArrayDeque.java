/**
 * This class creates a Deque based on a DLList.
 * @param <T> the data type in the deque
 * @author harry
 */
public class ArrayDeque<T> {
    /**
     * This is the array holding our data.
     */
    private T[] list;
    /**
     * This is the length of the Deque (from the user's perspective).
     */
    private int size;
    /**
     * This is the index where the next addFirst() would be added.
     */
    private int nextFirst;

    /**
     * This is the index where the next addLast() would be added.
     */
    private int nextLast;
    /**
     * This method initializes an ArrayDeque.
     */
    public ArrayDeque() {
        list = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }
    /**
     * Add an item to the front of the list.
     * @param item the item to be added
     **/
    public void addFirst(T item) {
        if (nextFirst == nextLast && size != 0) {
            scaleLength();
        }
        list[nextFirst] = item;
        nextFirst--;
        if (nextFirst < 0) {
            nextFirst = list.length - 1;
        }
        size++;
    }

    /**
     * Add an item to the back of the list.
     * @param item the item to be added
     **/
    public void addLast(T item) {
        if (nextFirst == nextLast && size != 0) {
            scaleLength();
        }
        list[nextLast] = item;
        nextLast++;
        if (nextLast > list.length - 1) {
            nextLast = 0;
        }
        size++;
    }

    /**
     * Double the length of the list.
     **/
    private void scaleLength() {
        T[] temp = (T[]) new Object[(int) (list.length * 2)];
        System.arraycopy(list, 0, temp, 0, nextLast);
        System.arraycopy(list, nextFirst, temp, (temp.length
                - (list.length - nextFirst)), list.length - nextFirst);
        this.nextFirst = temp.length - list.length + nextFirst;
        this.list = temp;
    }
    /**
     * Halve the length of the list.
     **/
    private void halveLength() {
        T[] temp = (T[]) new Object[(int) (list.length / 2)];
        if (nextFirst > nextLast) {
            System.arraycopy(list, 0, temp, 0, nextLast);
            System.arraycopy(list, nextFirst, temp, (temp.length
                    - (list.length - nextFirst)), list.length - nextFirst);
            this.nextFirst = temp.length - (list.length - nextFirst);
        } else {
            System.arraycopy(list, nextFirst + 1, temp, 0, size);
            this.nextFirst = temp.length - 1;
            this.nextLast = size;
        }
        this.list = temp;
        if (this.size < list.length / 4 && list.length != 8) {
            halveLength();
        }
    }
    /**
     * This method removes the first element of the list and returns it.
     * @return T value of the removed element
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = null;
        if (nextFirst != list.length - 1) {
            temp = list[nextFirst + 1];
            list[nextFirst + 1] = null;
            nextFirst++;
        } else {
            temp = list[0];
            list[0] = null;
            nextFirst = 0;
        }
        size--;
        if (size < list.length / 3 && list.length != 8) {
            halveLength();
        }
        return temp;
    }
    /**
     * This method removes the last element of the list and returns it.
     * @return T value of the removed element
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T temp = null;
        if (nextLast != 0) {
            temp = list[nextLast - 1];
            list[nextLast - 1] = null;
            nextLast--;
            if (nextLast < 0) {
                nextLast = list.length - 1;
            }
        } else {
            temp = list[list.length - 1];
            list[list.length - 1] = null;
            nextLast = list.length - 1;
        }
        size--;
        if (size < list.length / 4 && list.length != 8) {
            halveLength();
        }
        return temp;
    }

    /**
     * Return whether the Deque is empty.
     * @return whether the deck is empty
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Return the size of the deque.
     * @return size of the deque
     */
    public int size() {
        return this.size;
    }

    /**
     * Print the Deque.
     */
    public void printDeque() {
        int i = 1;
        while (i < size + 1) {
            if (nextFirst + i < list.length) {
                System.out.print(list[nextFirst + i] + " ");

            } else {
                System.out.print(list[nextFirst + i - list.length] + " ");
            }
            i++;
        }
    }
    /**
     * This method returns value of the element at the provided index.
     * @param index of the desired value
     * @return T value of the element
     */
    public T get(int index) {
        if (index + nextFirst >= list.length - 1) {
            return list[index + nextFirst - list.length + 1];
        } else {
            return list[index + nextFirst + 1];
        }
    }
}
