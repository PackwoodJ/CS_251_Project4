import CommonUtils.MST;
import CommonUtils.UsefulContainers.Edge;
import CommonUtils.UsefulContainers.iPair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class TestMST {
    @Test
    private void checkSizeAndEdges(int n, List<iPair> mst, List<iPair> expectedEdges) {
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

    @Test
    public void testSparseGraph1() {
        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(0,1,5));
        edgeList.add(new Edge(0,2,7));
        edgeList.add(new Edge(0,3,9));
        edgeList.add(new Edge(1,3,2));
        edgeList.add(new Edge(1,5,7));
        edgeList.add(new Edge(1,6,7));
        edgeList.add(new Edge(2,4,3));
        edgeList.add(new Edge(2,6,5));
        edgeList.add(new Edge(3,5,2));
        edgeList.add(new Edge(3,7,3));
        edgeList.add(new Edge(4,7,9));
        edgeList.add(new Edge(5,7,5));
        edgeList.add(new Edge(6,7,7));

        List<iPair> mst = MST.sparseMST(edgeList, 8);

        List<iPair> expectedEdges = new ArrayList<>();
        expectedEdges.add(new iPair(0,1));
        expectedEdges.add(new iPair(0,2));
        expectedEdges.add(new iPair(1,3));
        expectedEdges.add(new iPair(2,4));
        expectedEdges.add(new iPair(2,6));
        expectedEdges.add(new iPair(3,5));
        expectedEdges.add(new iPair(3,7));

        checkSizeAndEdges(8, mst, expectedEdges);
    }

    @Test
    public void testSparseGraph2() {
        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(0,1,6));
        edgeList.add(new Edge(0,4,4));
        edgeList.add(new Edge(1,2,9));
        edgeList.add(new Edge(1,6,2));
        edgeList.add(new Edge(2,5,6));
        edgeList.add(new Edge(2,6,5));
        edgeList.add(new Edge(3,5,6));
        edgeList.add(new Edge(4,7,5));

        List<iPair> mst = MST.sparseMST(edgeList, 8);

        List<iPair> expectedEdges = new ArrayList<>();
        expectedEdges.add(new iPair(0,1));
        expectedEdges.add(new iPair(0,4));
        expectedEdges.add(new iPair(1,6));
        expectedEdges.add(new iPair(2,5));
        expectedEdges.add(new iPair(2,6));
        expectedEdges.add(new iPair(3,5));
        expectedEdges.add(new iPair(4,7));

        checkSizeAndEdges(8, mst, expectedEdges);
    }

    @Test
    public void testSparseGraph3() {
        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(0,4,3));
        edgeList.add(new Edge(1,2,1));
        edgeList.add(new Edge(1,3,1));
        edgeList.add(new Edge(1,5,6));
        edgeList.add(new Edge(2,4,9));
        edgeList.add(new Edge(2,5,8));
        edgeList.add(new Edge(2,6,2));
        edgeList.add(new Edge(5,6,6));
        edgeList.add(new Edge(5,7,8));
        edgeList.add(new Edge(6,7,5));

        List<iPair> mst = MST.sparseMST(edgeList, 8);

        List<iPair> expectedEdges = new ArrayList<>();
        expectedEdges.add(new iPair(0,4));
        expectedEdges.add(new iPair(1,2));
        expectedEdges.add(new iPair(1,3));
        expectedEdges.add(new iPair(1,5));
        expectedEdges.add(new iPair(2,4));
        expectedEdges.add(new iPair(2,6));
        expectedEdges.add(new iPair(6,7));

        checkSizeAndEdges(8, mst, expectedEdges);
    }

    @Test
    public void testSparseGraph4() {
        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(0,1,8));
        edgeList.add(new Edge(0,2,5));
        edgeList.add(new Edge(0,3,1));
        edgeList.add(new Edge(1,3,4));
        edgeList.add(new Edge(1,5,3));
        edgeList.add(new Edge(2,5,4));
        edgeList.add(new Edge(3,7,9));
        edgeList.add(new Edge(4,6,4));
        edgeList.add(new Edge(4,7,6));
        edgeList.add(new Edge(5,6,7));

        List<iPair> mst = MST.sparseMST(edgeList, 8);

        List<iPair> expectedEdges = new ArrayList<>();
        expectedEdges.add(new iPair(0,3));
        expectedEdges.add(new iPair(1,3));
        expectedEdges.add(new iPair(1,5));
        expectedEdges.add(new iPair(2, 5));
        expectedEdges.add(new iPair(4,6));
        expectedEdges.add(new iPair(4,7));
        expectedEdges.add(new iPair(5,6));

        checkSizeAndEdges(8, mst, expectedEdges);
    }

    @Test
    public void testSparseGraph5() {
        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(0,1,6));
        edgeList.add(new Edge(1,3,3));
        edgeList.add(new Edge(1,5,4));
        edgeList.add(new Edge(2,4,6));
        edgeList.add(new Edge(3,5,5));
        edgeList.add(new Edge(3,7,1));
        edgeList.add(new Edge(4,6,3));
        edgeList.add(new Edge(4,7,9));
        edgeList.add(new Edge(5,7,1));
        edgeList.add(new Edge(6,7,9));

        List<iPair> mst = MST.sparseMST(edgeList, 8);

        List<iPair> expectedEdges = new ArrayList<>();
        expectedEdges.add(new iPair(0,1));
        expectedEdges.add(new iPair(1,3));
        expectedEdges.add(new iPair(2,4));
        expectedEdges.add(new iPair(3,7));
        expectedEdges.add(new iPair(4,6));
        expectedEdges.add(new iPair(6,7)); //Note: this can also be iPair(4, 7) bc the weights are the same
        expectedEdges.add(new iPair(5,7));

        checkSizeAndEdges(8, mst, expectedEdges);
    }

    @Test
    public void testSparseGraphWithNegativeWeight() {
        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(0, 1, -1)); // Negative weight
        edgeList.add(new Edge(1, 2, 2));

        int n = 3; // Number of vertices

        assertThrows(IllegalArgumentException.class, () -> {
            MST.sparseMST(edgeList, n);
        });
    }
}
