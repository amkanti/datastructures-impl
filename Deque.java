/****************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:  java Deque
 *  Dependencies: java.util.Iterator and java.util.NoSuchElementException.
 *
 *  Deque.
 *
 ****************************************************************************/
/**
 * Class Name: Deque.java 
 * Description: *The <tt>Deque</tt> .java
 * program supports adding and removing items from either the front
 *  or the back of the data structure. It supports addFirst(), addLast(),
 * removeFirst() and removeLast() operations. It also has an iterator which
 * iterates over items in order from front to end.
 * <p>
 * This implementation uses doubly linked list. Each Deque operation is in
 * constant worst time and use space proportional to the number of items 
 * currently in the deque. Iterator implemetation supports each operation 
 * (including construction) in constant worst-case time.
 * 
 * @author Amulya Manchikanti
 * @date : 07/03/2015
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N; // size of the deque
    private Node first; // first pointer
    private Node last; // last pointer

    // helper doubly linked list class
    private class Node {
        private Item item; // item to be added
        private Node next; // pointer to next element
        private Node prev; // pointer to previous element
    }

    /**
     * Constructs an empty deque
     */
    public Deque() // construct an empty deque
    {
        first = null;
        last = null;
        N = 0;
    }

    /**
     * Checks if the deque is empty or not
     * 
     * @return true if it is empty and false if it is not
     */
    public boolean isEmpty() // is the deque empty?
    {
        if (first == null && last == null)
            return true;
        return false;
    }

    /**
     * Returns the number of items in deque
     * 
     * @return the size of the deque
     */
    public int size() // return the number of items on the deque
    {
        return N;
    }

    /**
     * Adds an element to the front of the deque
     * 
     * @param item
     *            indicates the value to be added.
     * @throws java.lang.NullPointerException
     *             if the item added id null
     */
    public void addFirst(Item item) // add the item to the front
    {
        Node oldfirst = first;

        if (item == null)
            throw new NullPointerException("Item is null");

        if (isEmpty()) {
            first = new Node();
            first.item = item;
            first.prev = null;
            first.next = null;
            last = first;

        } else {
            first = new Node();
            first.item = item;
            first.prev = null;
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        N++;
    }

    /**
     * Adds an element at the end of the deque
     * 
     * @param item
     *            indicates the value to be added
     * @throws java.lang.NullPointerException
     *             if the item added id null
     */
    public void addLast(Item item) // add the item to the end
    {
        Node oldlast = last;

        if (item == null)
            throw new NullPointerException("Item is null");

        if (isEmpty()) {
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = null;
            first = last;
        } else {
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldlast;
            oldlast.next = last;
        }
        N++;
    }

    /**
     * Removes an element from the beginning of the deque
     * 
     * @return an item removed
     * @throws java.util.NoSuchElementException
     *             if the item is removed form empty deque
     */
    public Item removeFirst() // remove and return the item from the front
    {
        if (isEmpty())
            throw new NoSuchElementException("Empty Queue");
        Item item = first.item;
        if (first.next == null)
        {
            first = null;
            last = null;
        }
        else 
        {
            first = first.next;
            first.prev = null;
        }
        N--;
        return item;
    }

    /**
     * Removes an element from the end of the deque
     * 
     * @return an item removed
     * @throws java.util.NoSuchElementException
     *             if the item is removed form empty deque
     */
    public Item removeLast() // remove and return the item from the end
    {
        if (isEmpty())
            throw new NoSuchElementException("Empty Queue");
        Item item = last.item;
        if (last.prev == null)
        {
            first = null;
            last = null;
        }
        else 
        {
            last = last.prev;
            last.next = null;
        }
        N--;
        return item;
    }

    /**
     * An iterator is returned
     */
    public Iterator<Item> iterator() // return an iterator over items in order
                                        // from front to end
    {
        return new ListIterator();
    }

    /**
     * Iterator is implemented to iterate items in order
     */
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        /**
         * Returns true if there is a next element
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Throws an exception if this is called
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Iterates through the items in the linked list and returns the value
         */
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) // unit testing
    {
    }
}
