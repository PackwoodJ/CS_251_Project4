package CommonUtils;

import CommonUtils.UsefulContainers.iPair;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MSTTestDense {
    private static void checkSizeAndEdges(int n, List<iPair> mst, List<iPair> expectedEdges) {
        assertEquals(n - 1, mst.size()); // MST should have n-1 edges

        // Verify that each edge in the MST matches an expected edge
        for (iPair edge : mst) {
            assertTrue(expectedEdges.contains(edge),
                    "MST contains an unexpected edge: (" + edge.a + ", " + edge.b + ")");
        }

        // Check that the MST contains all expected edges
        for (iPair edge : expectedEdges) {
            assertTrue(mst.contains(edge),
                    "MST is missing an expected edge: (" + edge.a + ", " + edge.b + ")");
        }
    }
    public static void main(String[] args) {
        double[][] weights = {{0,3,9,0,2,0,0,0},
                              {3,0,1,0,0,2,0,0},
                              {9,1,0,0,0,9,1,0},
                              {0,0,0,0,0,2,0,0},
                              {2,0,0,0,0,0,5,0},
                              {0,2,9,2,0,0,8,0},
                              {0,0,1,0,5,8,0,0},
                              {0,0,0,0,0,0,0,0}};
        List<iPair> mst = MST.denseMST(weights);

        List<iPair> actual = new ArrayList<>();
        actual.add(new iPair(0,1));
        actual.add(new iPair(0,4));
        actual.add(new iPair(1,2));
        actual.add(new iPair(1,5));
        actual.add(new iPair(2,6));
        actual.add(new iPair(3,5));

        MSTTestDense.checkSizeAndEdges(8,mst,actual);
    }
}
