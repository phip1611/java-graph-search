package de.phip1611;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by phip1611 on 20.04.16.
 */
public class GraphOld {
    private int nodeCount;
    private ArrayList<EdgeOld> edges;

    public GraphOld() {
        edges = new ArrayList<>(nodeCount * (nodeCount - 1) / 2);
    }

    public void addEdge(int nodeFrom, int nodeTo) {
        edges.add(new EdgeOld(nodeFrom,nodeTo));
    }

    public boolean hasEdge(int nodeFrom, int nodeTo) {
        for (EdgeOld edge : edges) {
            if (edge.getNodeFrom() == nodeFrom && edge.getNodeTo() == nodeTo) {
                return true;
            }
        }
        return false;
    }

    public Stack breadthFirstSearch(int startNode, int targetNode) {

        java.util.Stack<Integer> pathStack = new Stack<>();

        ArrayList<Integer> currentNodeGeneration, nextNodeGeneration, nodesVisited;
        HashMap<Integer,Integer> discoveredNodesThrough;
        boolean foundNode;

        foundNode = false;
        currentNodeGeneration = new ArrayList<>();
        nextNodeGeneration = new ArrayList<>();
        discoveredNodesThrough = new HashMap<>();
        nodesVisited = new ArrayList<>();

        nextNodeGeneration.add(startNode); // Der STARTKNOTEN
        while (!foundNode) {
            System.out.println("-------- nächste Knoten Generation/Ebene überprüfen");
            currentNodeGeneration.clear();
            currentNodeGeneration = (ArrayList<Integer>) nextNodeGeneration.clone();
            nextNodeGeneration.clear();

            // gibt nichts mehr zu entdecken
            if (currentNodeGeneration.isEmpty()) {
                break;
            }
            // die aktuelle Knoten-Generation komplett durchiterieren
            for (int currentNode : currentNodeGeneration) {
                if (foundNode) break;
                System.out.println("Aktiver Knoten: "+currentNode);
                System.out.println("Knoten auf aktiver Ebene:"+ Arrays.toString(currentNodeGeneration.toArray()));
                for (EdgeOld edge : edges) {
                    //System.out.println("nodes visited: "+nodesVisited);
                    //System.out.println("nodesVisited.contains(edge.getNodeTo():"+nodesVisited.contains(edge.getNodeTo()));
                    if (edge.getNodeFrom() == currentNode && !nodesVisited.contains(edge.getNodeTo()) && !currentNodeGeneration.contains(edge.getNodeTo())) {
                        System.out.println(edge.getNodeTo()+" ist ein durch "+currentNode+" neu entdeckter Knoten");
                        discoveredNodesThrough.put(edge.getNodeTo(),currentNode);
                        // Folgeknoten durch aktuellen Knoten gefunden
                        if (edge.getNodeTo() == targetNode) {
                            System.out.println("Knoten gefunden!");
                            foundNode = true;
                            break;
                        } else {
                            nextNodeGeneration.add(edge.getNodeTo());
                        }
                    }
                }
                nodesVisited.add(currentNode);
            }
            /*for (int node : currentNodeGeneration) {
                nodesVisited.add(node);
            }*/
        }
        if (foundNode) {
            int nodeFoundThroughPrevious = targetNode;
            while (nodeFoundThroughPrevious != startNode) {
                pathStack.add(0,nodeFoundThroughPrevious);
                nodeFoundThroughPrevious = discoveredNodesThrough.get(nodeFoundThroughPrevious);
            }
        }
        return pathStack;
    }

    /*public Stack depthFirstSearch(int startNode, int targetNode) {
        ArrayList<Integer> nodesVisited = new ArrayList<>();
        /*java.util.Stack<Integer> pathStack = new Stack<>();
        ArrayList<Integer> currentNodeGeneration, nextNodeGeneration, nodesVisited;
        HashMap<Integer,Integer> discoveredNodesThrough;
        boolean foundNode;
        foundNode = false;
        currentNodeGeneration = new ArrayList<>();
        nextNodeGeneration = new ArrayList<>();
        discoveredNodesThrough = new HashMap<>();
        nodesVisited = new ArrayList<>();*/

/*        return recursiveDepthFirstSearch(startNode, targetNode, nodesVisited);
    }

    private Stack recursiveDepthFirstSearch(int startNode, int targetNode, ArrayList<Integer> nodesVisited) {
        for (EdgeOld edge : edges) {
            // Alle Kanten filtern die vom Startknoten ausgehen
            if (edge.getNodeFrom() == startNode && ) {
                nodesVisited.add(startNode);
                if (edge.getNodeTo() == targetNode) {
                    System.out.println("found!");
                } else {
                    recursiveDepthFirstSearch(edge.getNodeTo(), targetNode, nodesVisited);
                }
            }
        }

        return null;
    }*/

}
