/**
 * This class creates a Deque based on a DLList.
 * @param <T> the data type in the deque
 * @author harry
 */
public class LinkedListDeque<T> {
    /**
     * This class creates Nodes, which make up every DLList.
     */
    public class Node {
        /**
         * This is the value of the Node.
         */
        private T value;
        /**
         * This is the following Node.
         */
        private Node next;
        /**
         * This is the preceding Node.
         */
        private Node prev;
        /**
         * This method initializes a node.
         */
        public Node() {
            value = null;
            next = null;
            prev = null;
        }
        /**
         * This method initializes a node.
         * @param val of the node
         */
        public Node(T val) {
            value = val;
            next = null;
            prev = null;
        }
        /**
         * This method initializes a node.
         * @param val of the node
         * @param pre the Node before
         * @param nex the following Node
         */
        public Node(T val, Node pre, Node nex) {
            value = val;
            next = nex;
            prev = pre;
        }
    }

    /**
     * This is a Node that marks the front and back of the list circularly.
     */
    private Node sentinel;
    /**
     * This is the length of the list.
     */
    private int length;

    /**
     * This method initializes a LinkedListDeque.
     */
    public LinkedListDeque() {
        this.sentinel = new Node(null);
        this.sentinel.prev = sentinel;
        this.sentinel.next = sentinel;
        this.length = 0;
    }

    /**
     * This method adds item to the front of the list.
     * @param item value of the item
     */
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        this.length += 1;
    }

    /**
     * This method adds item to the back of the list.
     * @param item value of the item
     */
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        this.length += 1;
    }

    /**
     * This method returns whether or not the list is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    /**
     * This method returns the size of the list.
     * @return int size of the list
     */
    public int size() {
        return this.length;
    }

    /**
     * This method prints the list.
     */
    public void printDeque() {
        Node temp = sentinel.next;
        while (temp != sentinel) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * This method removes the first element of the list and returns it.
     * @return T value of the removed element
     */
    public T removeFirst() {
        if (sentinel.next != sentinel) {
            T temp = sentinel.next.value;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            length--;
            return temp;
        }
        return null;
    }
    /**
     * This method removes the last element of the list and returns it.
     * @return T value of the removed element
     */
    public T removeLast() {
        if (sentinel.next != sentinel) {
            T temp = sentinel.prev.value;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            length--;
            return temp;
        }
        return null;
    }
    /**
     * This method returns value of the element at the provided index.
     * @param index of the desired value
     * @return T value of the element
     */
    public T get(int index) {
        int i = 0;
        Node temp = sentinel.next;
        while (i < index) {
            temp = temp.next;
            i++;
        }
        return temp.value;
    }
    /**
     * This method returns value of the element at the provided index.
     * @param index of the desired value
     * @return T value of the element
     */
    public T getRecursive(int index) {
        Node temp = sentinel.next;
        return getRecursiveHelper(index, temp);
    }

    /**
     * This method is a helper method of getRecursive.
     * @param index of the desired value
     * @param current Current node in the recursive process
     * @return T value of the element
     */
    private T getRecursiveHelper(int index, Node current) {
        if (index == 0) {
            return current.value;
        }
        return getRecursiveHelper(index - 1, current.next);
    }
}
