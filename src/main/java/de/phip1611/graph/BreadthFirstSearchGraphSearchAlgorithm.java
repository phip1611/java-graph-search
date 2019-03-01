package de.phip1611.graph;


import java.util.HashMap;
import java.util.List;

public class BreadthFirstSearchGraphSearchAlgorithm implements GraphSearchAlgorithm {
    @Override
    public NodeList search(List<Graph.Edge> edges, Graph.Node startNode, Graph.Node destinationNode) {
        NodeList pathNodeList, nodesOnCurrentLevel, nodesOnNextLevel, nodesVisited;
        HashMap<Graph.Node, Graph.Node> nodesDiscoveredThrough;
        boolean foundNode;

        foundNode = false;

        pathNodeList           = new NodeList();
        nodesOnCurrentLevel    = new NodeList();
        nodesOnNextLevel       = new NodeList();
        nodesVisited           = new NodeList();

        nodesDiscoveredThrough = new HashMap<>();

        nodesOnNextLevel.add(startNode); // Der STARTKNOTEN
        while (!foundNode) {
            nodesOnCurrentLevel.clear();
            nodesOnCurrentLevel = (NodeList) nodesOnNextLevel.clone();
            nodesOnNextLevel.clear();

            // gibt nichts mehr zu entdecken
            if (nodesOnCurrentLevel.isEmpty()) break;

            // die aktuelle Knoten-Generation komplett durchiterieren
            for (Graph.Node currentNode : nodesOnCurrentLevel) {
                if (foundNode) break;
                //System.out.println("Aktiver Knoten: "+currentNode);
                //System.out.println("Knoten auf aktiver Ebene:"+ Arrays.toString(nodesOnCurrentLevel.toArray()));
                for (Graph.Edge edge : edges) {
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
                pathNodeList.add(0,nodeFoundThroughPrevious);
                nodeFoundThroughPrevious = nodesDiscoveredThrough.get(nodeFoundThroughPrevious);
            }
        }
        return pathNodeList;
    }
}
