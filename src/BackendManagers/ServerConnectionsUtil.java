package BackendManagers;

import BackendManagers.Interfaces.ServerConnectionsUtilInterface;
import CommonUtils.MST;
import CommonUtils.UsefulContainers.Edge;
import CommonUtils.UsefulContainers.iPair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains helpful functions for dealing with our server connections. Will not be integrated with
 *   production classes, as it is a tool for helping us with backend setup.
 *
 * <bold>251 students: you may use any of the data structures you have previously created, but may not use
 *   any Java util library except List/ArrayList and java.util.stream.* .</bold>
 */
public class ServerConnectionsUtil implements ServerConnectionsUtilInterface {
    /**
     * Selects server connections per the specifications (minimum cost to connect all servers).  Assumes
     * all servers can be connected.
     *
     * @param filename file to read input from
     * @return list of server connections, per the specifications
     */
    @Override
    public List<ServerConnection> getMinimumServerConnections(String filename) {
        int nServers = 0;
        int nEdges = 0;
        List<Edge> edgeList = new ArrayList();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            String[] input = bf.readLine().split(" ");
            nServers = Integer.parseInt(input[0]);
            nEdges = Integer.parseInt(input[1]);
            String[] line;
            for (int i = 0; i < nEdges; i++) {
                line = bf.readLine().split(" ");
                edgeList.add(new Edge(Integer.parseInt(line[0]), Integer.parseInt(line[1]),
                        Double.parseDouble(line[2])));
            }
        } catch (IOException e) {
            //This should never happen... uh oh o.o
            System.err.println("ATTENTION TAs: Couldn't find test file: \"" + filename + "\":: " + e.getMessage());
            System.exit(1);
        }
        List<iPair> mst = MST.sparseMST(edgeList, nServers);
        List<ServerConnection> ret = new ArrayList();
        for (iPair edge: mst) {
            ret.add(new ServerConnection(edge.a, edge.b));
        }
        return ret;
    }
}
