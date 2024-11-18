package CommonUtils;

import CommonUtils.UsefulContainers.Edge;
import CommonUtils.UsefulContainers.iPair;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing Minimum Spanning Tree (MST) utils.  No interface provided because functions are static.
 *
 * <bold>251 students: You may only use java.util.List and java.util.ArrayList from the standard library.
 *   Any other containers used must be ones you created.</bold>
 */
public class MST {
    static class WeightVertex implements Comparable<WeightVertex> {
        double weight;
        int vert;

        public WeightVertex(double weight, int vert) {
            this.weight = weight;
            this.vert = vert;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WeightVertex that = (WeightVertex) o;
            return weight == that.weight && vert == that.vert;
        }

        public int compareTo(WeightVertex that) {
            return (int)(that.weight - weight);
        }
    }
    /**
     * Returns the MST of the given graph, optimized for a dense graph.  Assumes a connected graph.
     *
     * @param weights square matrix representing positive edge weights between every vertex
     * @return MST: list of pairs of indices each indicating an edge between those two indices
     * @throws IllegalArgumentException if weights is not square or edges are not positive
     */
    public static List<iPair> denseMST(double[][] weights) throws IllegalArgumentException {
        //validate weighs matrix (already done)
        int n = weights.length;
        for(int i=0; i<n; i++){
            if(weights[i].length != n)
                throw new IllegalArgumentException("Weights graph not square in row " +
                        i + ", expected " + n + ", actual is " + weights[i].length);
            for(int j=0; j<n; j++){
                if(weights[i][j] < 0)
                    throw new IllegalArgumentException("Edge weight < 0 (" +
                            weights[i][j] + ") at y, x=" + i + ", " + j);
            }
        }

        ArrayList<iPair> edgeTo = new ArrayList(vertices);
        ArrayList<Double> distTo = new ArrayList();
        ArrayList<Boolean> visited = new ArrayList();
        MinHeap<WeightVertex> q = new MinHeap<>();

        for (int i = 1; i < weights.length;i++) {
            edgeTo.set(i, null);
            distTo.set(i, Double.MAX_VALUE);
            visited.set(i, false);
        }
        // start at vertex in index 0
        distTo.set(0, 0.0);
        q.add(new WeightVertex(0, 0));

        while (q.size() != 0) {
            WeightVertex u = q.removeMin();
            int v = u.vert;
            visited.set(v, true);

            for (int i = 0; i < weights.length; i++) {

                if (weights[v][i] < 1 || visited.get(i)) {
                    continue;
                }

                double weight = weights[v][i];
                if (weight < distTo.get(i)) {
                    WeightVertex old_wv = new WeightVertex(distTo.get(i), i);
                    edgeTo.set(i, new iPair(v, i));
                    distTo.set(i, weight);
                    WeightVertex new_wv = new WeightVertex(weight, i);
                    if (!q.set(old_wv, new_wv)) {
                        q.add(new_wv);
                    }
                }
            }
        }
        return edgeTo;
    }

    /**
     * Returns the MST of the given graph, optimized for a sparse graph.  Assumes a connected graph.
     *
     * @param edgeList edge list
     * @param n number of vertices
     * @return MST: list of pairs of indices each indicating an edge between those two indices
     * @throws IllegalArgumentException if edges are not positive
     */
    public static List<iPair> sparseMST(List<Edge> edgeList, int n) throws IllegalArgumentException {
        //validate edge weighs (already done)
        for(var e : edgeList){
            if(e.w < 0)
                throw new IllegalArgumentException("Edge weight < 0 (" +
                        e.w + ") between " + e.a + " and " + e.b);
        }
        MinHeap<Edge> q = new MinHeap<>();
        DisjointSet ds = new DisjointSet(n);

        for (Edge e: edgeList) {
            q.add(e);
        }

        ArrayList<iPair> t = new ArrayList<>();

        while (t.size() < n - 1) {
            Edge curr = q.removeMin();
            if (ds.find(curr.a) != ds.find(curr.b)) {
                t.add(new iPair(curr.a, curr.b));
                ds.union(curr.a, curr.b);
            }
        }
        for (iPair ip: t) {
            System.out.println(ip);
        }
        return t;
    }
}
