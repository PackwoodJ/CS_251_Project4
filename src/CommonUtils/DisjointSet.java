package CommonUtils;

import CommonUtils.Interfaces.DisjointSetInterface;

import java.awt.*;

/**
 * Implements our DisjointSetInterface and adds a constructor
 *
 * @apiNote uses union by size so that more queries hit closer to the top of the tree (even if
 *   a few queries are made longer because of it -- the majority will hit closer to the top).
 */
public class DisjointSet implements DisjointSetInterface {

    int[] id;
    int[] size;
    int nsets;
    /**
     * Initializes a disjoint set of size n
     * @param n size of disjoint set
     * @throws IllegalArgumentException if passed an invalid size (<0)
     */
    public DisjointSet(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        nsets = n;
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Finds the root of x.
     * <p>
     * <bold>251 students: it is recommended to use path compression (i.e. setting the parent of
     *   every node on the path from x to root(x) to be root(x))</bold>
     *
     * @param p node to find the root of
     * @return root of x
     * @throws IndexOutOfBoundsException if x is out of bounds
     */
    @Override
    public int find(int p) throws IndexOutOfBoundsException {
        if (p == id[p]) {
            return p;
        }
        id[p] = find(id[p]);
        return id[p];
    }

    /**
     * Unions the two sets containing x and y.  Joins the smaller size to the larger size,
     *   or if they are the same size, joins x to y (makes y the parent of x, etc.).  Does
     *   not change information about any node except possibly the roots of each set.
     *
     * @param x node in set 1
     * @param y node in set 2
     * @throws IndexOutOfBoundsException if x or y are out of bounds
     */
    @Override
    public void union(int x, int y) throws IndexOutOfBoundsException {
        int idX = find(x);
        int idY = find(y);
        if (idX == idY) {
            return;
        }
        if (size[idX] <= size[idY]) {
            id[idX] = idY;
            size[idY] = size[idX] + size[idY];
        }
        else {
            id[idY] = idX;
            size[idX] = size[idY] + size[idX];
        }
        nsets--;
    }

    /**
     * Returns the size of the set that node x is contained in
     *
     * @param x node to identify the desired set
     * @return size of the set containing x
     * @throws IndexOutOfBoundsException if x is out of bounds
     */
    @Override
    public int getSetSize(int x) throws IndexOutOfBoundsException {
        return size[find(x)];
    }

    /**
     * Returns the size of the disjoint set (total number of elements)
     *
     * @return size of disjoint set
     */
    @Override
    public int getDSSize() {
        return size.length;
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
        //todo GRAPHICS DEVELOPER:: draw the disjoint set how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void visualize(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: visualization is to be time-based -- how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
