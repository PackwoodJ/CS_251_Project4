package BackendManagers;

import BackendManagers.Interfaces.ServerConnectionsUtilInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));

            //todo
        } catch (IOException e) {
            //This should never happen... uh oh o.o
            System.err.println("ATTENTION TAs: Couldn't find test file: \"" + filename + "\":: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
