package CommonUtils;

import CommonUtils.Interfaces.MinHeapInterface;

import java.awt.*;

import static java.lang.Math.min;

/**
 * Implements our MinHeapInterface and adds a constructor
 * <p>
 * <b>251 students: You are explicitly forbidden from using java.util.Queue (including any subclass
 *   like PriorityQueue) and any other java.util.* library EXCEPT java.util.Arrays and java.util.Vector.
 *   Write your own implementation of a MinHeap.</b>
 *
 * @param <E> the type of object this heap will be holding
 */
public class MinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {
    /**
     * A recursive method to heapify (sort the root to where it should go) a
     *   subtree with the root at given index
     * Assumes the subtrees are already heapified.
     * (The purpose of this method is to balance tree starting at the root)
     * @param i root of the subtree to heapify
     */

    private final int INIT_CAPACITY = 20;
    private final int INCREASE_FACTOR = 2;
    private final int ADD_FACTOR = 50;
    private int capacity = INIT_CAPACITY;
    private int size = 0;
    private E[] heap = (E[]) new Comparable[INIT_CAPACITY];

    private void heapify(int i) {
        //todo
    }

    /**
     * Constructs an empty min heap
     */
    public MinHeap(){

    }

    /**
     * Adds the item to the min heap
     *
     * @param item item to add
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public void add(E item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == capacity) {
            if (!(capacity * INCREASE_FACTOR < capacity)) {
                E[] temp = (E[]) new Comparable[capacity * INCREASE_FACTOR];
                for (int i = 0; i < capacity; i++) {
                    temp[i] = heap[i];
                }
                heap = temp;
                capacity *= INCREASE_FACTOR;
            }
            else if (!(capacity + ADD_FACTOR < capacity)) {
                E[] temp = (E[]) new Object[capacity + ADD_FACTOR];
                for (int i = 0; i < capacity; i++) {
                    temp[i] = heap[i];
                }
                heap = temp;
                capacity += ADD_FACTOR;
            }
            else {
                return;
            }
        }
        heap[size] = item;
        int curr_ind = size;
        while (curr_ind != 0 && heap[(int) (curr_ind - 1) / 2].compareTo(item) > 0) { // < 0 means less
            heap[curr_ind] = heap[(int) (curr_ind - 1) / 2];
            heap[(int) (curr_ind - 1) / 2] = item;
            curr_ind = (int) (curr_ind - 1) / 2;
        }
        size++;
    }

    /**
     * Empties the heap.
     */
    @Override
    public void clear() {
        heap = (E[]) new Comparable[capacity];
        size = 0;
    }

    /**
     * Returns the minimum element without removing it, or returns <code>null</code> if heap is empty
     *
     * @return the minimum element in the heap, or <code>null</code> if heap is empty
     */
    @Override
    public E peekMin() {
        return heap[0];
    }

    /**
     * Remove and return the minimum element in the heap, or returns <code>null</code> if heap is empty
     *
     * @return the minimum element in the heap, or <code>null</code> if heap is empty
     */
    @Override
    public E removeMin() {
        E ret = heap[0];

        if (heap[1] == null) {
            if (size == 1) {
                size--;
            }
            heap[0] = null;
            return ret;
        }
        int new_min_ind = size - 1;
        heap[0] = heap[new_min_ind];
        heap[new_min_ind] = null;

        int curr_ind = 0;
        int new_curr = 0;
        while (2 * curr_ind + 2 < capacity && (heap[2 * curr_ind + 1] != null ||
                heap[2 * curr_ind + 2] != null)) {
            if (heap[2 * curr_ind + 1] == null) {
                new_curr = 2 * curr_ind + 2;
            }
            else if (heap[2 * curr_ind + 2] == null) {
                new_curr = 2 * curr_ind + 1;
            } else {
                new_curr = (heap[2 * curr_ind + 1].compareTo(heap[2 * curr_ind + 2]) < 0)?
                        2 * curr_ind + 1:2 * curr_ind + 2;
            }
            if (heap[curr_ind].compareTo(heap[new_curr]) > 0) {
                E temp = heap[curr_ind];
                heap[curr_ind] = heap[new_curr];
                heap[new_curr] = temp;
            }
            else {
                break;
            }
            curr_ind = new_curr;
        }
        size--;
        return ret;
    }

    /**
     * Returns the number of elements in the heap
     *
     * @return integer representing the number of elements in the heap
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the MinHeap how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
