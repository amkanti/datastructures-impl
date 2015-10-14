/****************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:  java RandomizedDeque
 *  Dependencies: StdRandom.java, java.util.Iterator and java.util.NoSuchElementException.
 *
 *  Randomized Queue.
 *
 ****************************************************************************/
/**
 * Class Name: Randomized queue.java 
 * Description: *The <tt>RandomizedQueue</tt> .java
 * program supports adding items at the end and the item to be removed is 
 * chosen uniformly at random from items in the data structure.  It supports 
 * enqueue(), dequeue(), sample() operations. It also has an iterator which
 * iterates over items in random order.
 * <p>
 * This implementation uses resizing array. Each randomized queue operation 
 * other than creating an iterator takes constant amortized time and use space
 * proportional to the number of items currently in the queue. Iterator implementation 
 * supports operations next() and hasNext() in constant worst-case time; and 
 * construction in linear time.
 * 
 * @author Amulya Manchikanti
 * @date : 07/03/2015
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
     private Item[] a;                         // array of items
     private int N;                            // number of elements on stack

     /**
      * Constructs an empty randomized queue
      */
     public RandomizedQueue() // construct an empty randomized queue
     {
          a = (Item[]) new Object[2];
     }

     /**
      * Checks if the randomized queue is empty or not
      * 
      * @return true if it is empty and false if it is not
      */
     public boolean isEmpty() // is the queue empty?
     {
          return N == 0;
     }

     /**
      * Returns the number of items in randomized queue
      * 
      * @return the size of the randomized queue
      */
     public int size() // return the number of items on the queue
     {
          return N;
     }

     // resize the underlying array holding the elements
     /**
      * Resizes the array
      * @param capacity indicates the new size of the array
      */
     private void resize(int capacity) {
          assert capacity >= N;
          Item[] temp = (Item[]) new Object[capacity];
          for (int i = 0; i < N; i++) {
               temp[i] = a[i];
          }
          a = temp;
     }

     /**
      * Adds an element to the queue
      * 
      * @param item
      *            indicates the value to be added
      * @throws java.lang.NullPointerException
      *             if the item added id null
      */
     public void enqueue(Item item) // add the item
     {
          if (item == null)
               throw new NullPointerException("Item is null");
          if (N == a.length)
               resize(2 * a.length); // double size of array if necessary
          a[N++] = item;
     }

     /**
      * Removes an element in the random order
      * 
      * @return an item removed
      * @throws java.util.NoSuchElementException
      *             if the item is removed form empty queue
      */
     public Item dequeue() // remove and return a random item
     {
          if (isEmpty())
               throw new NoSuchElementException("Empty Randomized Queue");
          int m = StdRandom.uniform(N);
          Item item = a[m];
          a[m] = a[N-1];
          a[N-1] = null;
          N--;
          // shrink size of array if necessary
          if (N > 0 && N == a.length / 4)
               resize(a.length / 2);
          return item;
     }

     /**
      * Returns an element int he random order
      * 
      * @return an item removed
      * @throws java.util.NoSuchElementException
      *             if the item is removed form empty queue
      */
     public Item sample() // return (but do not remove) a random item
     {
         if (isEmpty())
             throw new NoSuchElementException("Empty Randomized Queue");
        
          int m = StdRandom.uniform(N);
          Item item = a[m];
          return item;
     }

     /**
      * An iterator is returned
      */
     public Iterator<Item> iterator() // return an independent iterator over
                                                  // items in random order
     {
          return new RandomizedQueueIterator();
     }

     /**
      * Iterator is implemented to iterate items in order
      */
     private class RandomizedQueueIterator implements Iterator<Item> {
          private Item[] aCopy;                 // copy of the array to shuffle
          private int size;                     // loval copy of size

          /**
           * constructs the copy of the array and shuffles it
           */
          public RandomizedQueueIterator() {
               size = 0;
               aCopy = (Item[]) new Object[N];
               for (int i = 0; i < N; i++)
                    aCopy[i] = a[i];
               StdRandom.shuffle(aCopy);
          }

          /**
           * Returns true if there is a next element
           */
          public boolean hasNext() {
               return size <= N-1;
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

               return aCopy[size++];
          }
     }

     public static void main(String[] args) // unit testing
     {
     }
}
