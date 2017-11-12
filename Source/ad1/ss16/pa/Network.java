package ad1.ss16.pa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Network {

    private int numberOfConnections;
    private int time;

    private int discoveryTime[];
    private int low[];
    private int parent[];

    // O(|V|)
    Network(int numberOfNodes) {
        for (int node = 0; node < numberOfNodes; node++) {
        }
        numberOfConnections = 0;
    }

    // O(1)
    public int numberOfNodes() {
        return adjacencyList.size();
    }

    // O(1)
    public int numberOfConnections() {
        return numberOfConnections;
    }


    // Average run time: theta(1)
    public void addConnection(int v, int w) {
            // get: O(1)
            // add: theta(1), O(|V|) <- should not happen usually
            adjacencyList.get(v).add(w);
            adjacencyList.get(w).add(v);
            numberOfConnections++;
        }
    }

    // O(|V|) (assuming addConnection has O(1))
    public void addAllConnections(int v) {
        for (int w = 0; w < numberOfNodes(); w++) {
            addConnection(v, w);
        }
    }

    public void deleteConnection(int v, int w) {
        if (adjacencyList.get(v).contains(w)) {
            adjacencyList.get(v).remove(w);
            adjacencyList.get(w).remove(v);
            numberOfConnections--;
        }
    }

    public void deleteAllConnections(int v) {
        for (int w = 0; w < numberOfNodes(); w++) {
            deleteConnection(v, w);
        }
    }

    // Helper method to return neighboursOf a node
    // O(1)

        return adjacencyList.get(node);
    }

    // Source: Algorithmen und Datenstrukturen 1, Graphen // RT = N + M
    int numberOfComponents() {
        boolean[] discovered = new boolean[numberOfNodes()];

        int numberOfComponents = 0;
        for (int node = 0; node < numberOfNodes(); node++) {
                numberOfComponents++;
                dfs1(node, discovered);
            }
        }

        return numberOfComponents;
    }

    private void dfs1(int node, boolean[] discovered) {
        discovered[node] = true;
                dfs1(neighbour, discovered);
            }
        }
    }

    // Source: Algorithmen und Datenstrukturen 1, P. 124 (Raidl, Hu)
    // Worst-Case: O(|V| + |E|) (DFS)
    boolean hasCycle() {
        boolean[] discovered = new boolean[numberOfNodes()];
        int[] parent = new int[numberOfNodes()];

        for (int node = 0; node < numberOfNodes(); node++) {
                if (hasCycle(node, discovered, parent)) {
                    return true;
                }

            }
        }

        return false;
    }


    private boolean hasCycle(int node, boolean[] discovered, int[] parent) {
        discovered[node] = true;

            if (discovered[neighbour] && parent[node] != neighbour) {
                return true;
                parent[neighbour] = node;
                if (hasCycle(neighbour, discovered, parent)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Worst-Case: O(|V| + |E|) (BFS)
    public int minimalNumberOfConnections(int start, int end) {
        if (start == end) {
            return 0;
        }

        if (neighboursOf(start).contains(end)) {
            return 1;
        }


        boolean[] discovered = new boolean[numberOfNodes()]; // false for all nodes
        int[] distance = new int[numberOfNodes()]; // //
        discovered[start] = true; // except start node
        bfsQueue.add(start);

            int node = bfsQueue.remove();
                    discovered[neighbour] = true;
                    distance[neighbour] = distance[node] + 1;
                    if (neighbour == end) {
                        return distance[neighbour];
                    }
                    bfsQueue.add(neighbour);
                }
            }
        }

    }

    // Worst-Case: O(|V| + |E|) (DFS)
    // http://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph

        // Stores discovery times of visited vertices
        discoveryTime = new int[numberOfNodes()];
        low = new int[numberOfNodes()];
        // Stores parent vertices in DFS tree
        parent = new int[numberOfNodes()];
        time = 0; // to set discovery time


        // Call the recursive helper function to find articulation
        // points in DFS tree rooted with vertex node
        for (int node = 0; node < numberOfNodes(); node++) {
            if (discoveryTime[node] <= 0) {
                critNodeDFS(node);
            }
        }

    }

    // A recursive function that find articulation points using DFS
    // node --> The vertex to be visited next
    private void critNodeDFS(int node) {

        // Count of children in DFS Tree
        int children = 0;
        // Initialize discovery time and low value

        // Go through all vertices adjacent to this
        while (neighbours.hasNext()) {

            int neighbour = neighbours.next();
            // If neighbour is not visited yet, then make it a child of node
            // in DFS tree and recur for it
            if (discoveryTime[neighbour] <= 0) {
                children++;
                parent[neighbour] = node;
                critNodeDFS(neighbour);

                // Check if the subtree rooted with neighbour has a connection to
                // one of the ancestors of node
                low[node] = Math.min(low[node], low[neighbour]);

                // node is an articulation point in following cases

                // (1) node is root of DFS tree and has two or more children.
                // (2) If node is not root and low value of one of its child
                // is more than discovery value of node.
                    criticalNodes.add(node);
                }

            }

            // Update low value of node for parent function calls.
            else if (neighbour != parent[node])
                low[node] = Math.min(low[node], discoveryTime[neighbour]);
        }
    }

}
