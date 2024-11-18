package BackendManagers;

import BackendManagers.Interfaces.WorldBuildingUtilInterface;
import CommonUtils.MST;
import CommonUtils.UsefulContainers.iPair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains helpful functions for dealing with our world building. Will not be integrated with
 *   production classes, as it is a tool for helping us with content creation.
 *
 * <bold>251 students: you may use any of the data structures you have previously created, but may not use
 *   any Java util library except List/ArrayList and java.util.stream.* .</bold>
 */
public class WorldBuildingUtil implements WorldBuildingUtilInterface {
    /**
     * Selects roads per the specifications (minimum cost to connect all cities).
     *
     * @param filename file to read input from
     * @return list of roads, per the specifications
     */
    @Override
    public List<CityEdge> getMinimumConnectingRoads(String filename) {
        int numCities = 0;
        iPair[] cities = new iPair[0];
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            numCities = Integer.parseInt(bf.readLine());
            cities = new iPair[numCities];
            for (int i = 0; i < numCities; i++) {
                int[] coords = new int[2];
                String[] line = bf.readLine().split(" ");
                for (int j = 0; j < 2; j++) {
                    coords[j] = Integer.parseInt(line[j]);
                }
                cities[i] = new iPair(coords[0], coords[1]);
            }
        } catch (IOException e) {
            //This should never happen... uh oh o.o
            System.err.println("ATTENTION TAs: Couldn't find test file: \"" + filename + "\":: " + e.getMessage());
            System.exit(1);
        }
        double[][] weights = new double[numCities][numCities];
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                double dist = Math.sqrt((cities[i].a-cities[j].a)^2 + (cities[i].b-cities[j].b)^2);
                weights[i][j] = dist;
                weights[j][i] = dist;
            }
        }

        List<iPair> mst = MST.denseMST(weights);
        List<CityEdge> ret = new ArrayList<>();
        for (iPair edge: mst) {
            ret.add(new CityEdge(cities[edge.a], cities[edge.b]));
        }
        return ret;
    }
}
