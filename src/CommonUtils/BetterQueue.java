package CommonUtils;

import java.awt.*;
import java.util.Queue;
import CommonUtils.Interfaces.BetterQueueInterface;

/**
 * @implNote implement a queue using a circular array with initial capacity 8.
 *
 * Implement BetterQueueInterface and add a constructor
 *
 * You are explicitly forbidden from using java.util.Queue and any subclass
 * (including LinkedList, for example) and any other java.util.* library EXCEPT java.util.Objects.
 * Write your own implementation of a Queue.
 *
 * Another great example of why we are implementing our own queue here is that
 * our queue is actually FASTER than Java's LinkedList (our solution is 2x faster!). This is due
 * to a few reasons, the biggest of which are 1. the overhead associated with standard library
 * classes, 2. the fact that Java's LinkedList doesn't store elements next to each other, which
 * increases memory overhead for the system, and 3. LinkedList stores 2 pointers with each element,
 * which matters when you store classes that aren't massive (because it increases the size of each
 * element, making more work for the system).
 *
 * @param <E> the type of object this queue will be holding
 */
public class BetterQueue<E> implements BetterQueueInterface<E> {

    /**
     * Initial size of queue.  Do not decrease capacity below this value.
     */
    private final int INIT_CAPACITY = 8;


    /**
     * If the array needs to increase in size, it should be increased to
     * old capacity * INCREASE_FACTOR.
     *
     * If it cannot increase by that much (old capacity * INCREASE_FACTOR > max int),
     * it should increase by CONSTANT_INCREMENT.
     *
     * If that can't be done either throw OutOfMemoryError()
     *
     */
    private final int INCREASE_FACTOR = 2;
    private final int CONSTANT_INCREMENT = 1 << 5; // 32



    /**
     * If the number of elements stored is < capacity * DECREASE_FACTOR, it should decrease
     * the capacity of the UDS to max(capacity * DECREASE_FACTOR, initial capacity).
     *
     */
    private final double DECREASE_FACTOR = 0.5;

    private int front;
    private int back;

    private int capacity;
    /**
     * Array to store elements in (according to the implementation
     * note in the class header comment).
     *
     * Circular arrays work as follows:
     *
     *   1. Removing an element increments the "first" index
     *   2. Adding an element inserts it into the next available slot. Incrementing
     *      the "last" index WRAPS to the front of the array, if there are spots available
     *      there (if we have removed some elements, for example).
     *   3. The only way to know if the array is full is if the "last" index
     *      is right in front of the "first" index.
     *   4. If you need to increase the size of the array, put the elements back into
     *      the array starting with "first" (i.e. "first" is at index 0 in the new array).
     *   5. No other implementation details will be given, but a good piece of advice
     *      is to draw out what should be happening in each operation before you code it.
     *
     *   hint: modulus might be helpful
     */
    private E[] queue;


    public void print() {
        for (int i = 0; i < capacity; i++) {
            System.out.println(queue[i]);
        }
    }
    /**
     * Constructs an empty queue
     */
    @SuppressWarnings("unchecked")
    public BetterQueue(){
        queue = (E[]) new Object[INIT_CAPACITY];
        front = 0;
        back = 0;
        capacity = INIT_CAPACITY;
    }

    /**
     * Add an item to the back of the queue
     *
     * @param item item to push
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public void add(E item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if ((back == front - 1) || (back == capacity - 1 && front == 0)) {
            if (capacity * INCREASE_FACTOR > capacity) {
                Object[] temp = new Object[capacity * INCREASE_FACTOR];
                for (int i = 0; i < capacity; i++) {
                    /*
                    if (front + i < capacity) {
                        temp[i] = queue[front + i];
                    }
                    else {
                        temp[i] = queue[i - (capacity - 1 - front)];
                    }
                     */
                    temp[i] = queue[(front + i) % capacity];
                }
                queue = (E[])temp;
                front = 0;
                back = capacity - 1;
                capacity *= INCREASE_FACTOR;
            }
            else if (capacity * (CONSTANT_INCREMENT) > capacity) {
                Object[] temp = new Object[capacity * CONSTANT_INCREMENT];
                for (int i = 0; i < capacity; i++) {
                    /*
                    if (front + i < capacity) {
                        temp[i] = queue[front + i];
                    }
                    else {
                        temp[i] = queue[i - (capacity - 1 - front)];
                    }
                     */
                    temp[i] = queue[(front + i) % capacity];
                }
                queue = (E[])temp;
                front = 0;
                back = capacity - 1;
                capacity *= CONSTANT_INCREMENT;
            }
            else {
                throw new OutOfMemoryError();
            }
        }
        if (queue[front] == null) {
            queue[back] = item;
            return;
        }
        if (++back == capacity) {
            back = 0;
        }
        queue[back] = item;
        //System.out.println("POST ADD");
        //print(this);
    }

    /**
     * Returns the front of the queue (does not remove it) or <code>null</code> if the queue is empty
     *
     * @return front of the queue or <code>null</code> if the queue is empty
     */

    @Override
    public E peek() {
        return queue[front];
    }

    /**
     * Returns and removes the front of the queue
     *
     * @return the head of the queue, or <code>null</code> if this queue is empty
     */

    @Override
    public E remove() {
        E ret = queue[front];
        queue[front] = null;
        if (front != back) {
            front++;
        }
        if (front == capacity) {
            front = 0;
        }
        //System.out.println("SIZE " + this.size() + " CAPACITY " + capacity);
        if ((this.size() > INIT_CAPACITY) && (this.size() < capacity * DECREASE_FACTOR)) {
            //System.out.println("DECREASES FOR " + ret);
            int max = INIT_CAPACITY > capacity * DECREASE_FACTOR? INIT_CAPACITY : (int)(capacity * DECREASE_FACTOR);
            Object[] temp = new Object[max];
            int i;
            for (i = 0; i < max; i++) {
                temp[i] = queue[(front + i) % capacity];
            }
            back = i - 2;
            front = 0;
            //System.out.println("FRONT: " + front + "\tBACK: " + back);
            queue = (E[]) temp;
            capacity = max;
        }
        //System.out.println("POST REMOVE");
        //print(this);
        return ret;
    }

    /**
     * Returns the number of elements in the queue
     *
     * @return integer representing the number of elements in the queue
     */

    @Override
    public int size() {
        //print(this);
        if (front == back) {
            if (queue[front] == null) {
                return 0;
            }
            return 1;
        }
        else if (front < back) {
            return back - front + 1;
        }
        else {
            return capacity - front + back + 1;
        }
    }

    /**
     * Returns whether the queue is empty
     *
     * @return true if the queue is empty, false otherwise
     */

    @Override
    public boolean isEmpty() {
        return queue[front] == null;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    public void draw(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the queue how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
