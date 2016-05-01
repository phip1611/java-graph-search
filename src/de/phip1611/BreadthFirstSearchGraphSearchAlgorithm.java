package de.phip1611;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class BreadthFirstSearchGraphSearchAlgorithm extends GraphSearchAlgorithm {
    @Override
    public Stack<Graph.Node> search(Graph graph, Graph.Node startNode, Graph.Node destinationNode) {
        Stack<Graph.Node> pathStack = new Stack<>();
        ArrayList<Graph.Node> nodesOnCurrentLevel, nodesOnNextLevel, nodesVisited;
        HashMap<Graph.Node,Graph.Node> nodesDiscoveredThrough;
        boolean foundNode;

        foundNode = false;
        nodesOnCurrentLevel = new ArrayList<>();
        nodesOnNextLevel = new ArrayList<>();
        nodesVisited = new ArrayList<>();
        nodesDiscoveredThrough = new HashMap<>();

        nodesOnNextLevel.add(startNode); // Der STARTKNOTEN
        while (!foundNode) {
            nodesOnCurrentLevel.clear();
            nodesOnCurrentLevel = (ArrayList<Graph.Node>) nodesOnNextLevel.clone();
            nodesOnNextLevel.clear();

            // gibt nichts mehr zu entdecken
            if (nodesOnCurrentLevel.isEmpty()) break;

            // die aktuelle Knoten-Generation komplett durchiterieren
            for (Graph.Node currentNode : nodesOnCurrentLevel) {
                if (foundNode) break;
                //System.out.println("Aktiver Knoten: "+currentNode);
                //System.out.println("Knoten auf aktiver Ebene:"+ Arrays.toString(nodesOnCurrentLevel.toArray()));
                for (Graph.Edge edge : graph.getEdges()) {
                    //System.out.println("nodes visited: "+nodesVisited);
                    //System.out.println("nodesVisited.contains(edge.getNodeTo():"+nodesVisited.contains(edge.getNodeTo()));
                    // alle Kanten die vom aktiven Knoten abgehen herausfiltern
                    if (edge.getFrom().getKey() == currentNode.getKey()
                            && !nodesVisited.contains(edge.getTo())
                            && !nodesOnCurrentLevel.contains(edge.getTo())) {
                        //System.out.println(edge.getNodeTo()+" ist ein durch "+currentNode+" neu entdeckter Knoten");
                        nodesDiscoveredThrough.put(edge.getTo(),currentNode);
                        // Folgeknoten durch aktuellen Knoten gefunden
                        if (edge.getTo() == destinationNode) {
                            //System.out.println("Knoten gefunden!");
                            foundNode = true;
                            break;
                        } else {
                            nodesOnNextLevel.add(edge.getTo());
                        }
                    }
                }
                nodesVisited.add(currentNode);
            }
        }
        if (foundNode) {
            Graph.Node nodeFoundThroughPrevious = destinationNode;
            while (nodeFoundThroughPrevious != startNode) {
                pathStack.add(0,nodeFoundThroughPrevious);
                nodeFoundThroughPrevious = nodesDiscoveredThrough.get(nodeFoundThroughPrevious);
            }
        }
        return pathStack;
    }
}
