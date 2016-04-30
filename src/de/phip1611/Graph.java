package de.phip1611;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * This class can be used to build graphs
 * as in the mathematically graph theory.
 *
 * Nodes are represented through integers.
 * You (the programmer) just have to handle
 * with edges. For example if you add a edge
 * from 7 to 2 the class automatically
 * creates the necessary node objects
 * for internally use.
 * @author Philipp Schuster (@phip1611)
 */
public class Graph {
    public final static int BREADTH_FIRST_SEARCH = 0;
    public final static int DEPTH_FIRST_SEARCH = 1;

    private ArrayList<Edge> edges;
    private ArrayList<Node> nodes;

    public Graph() {
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<>();
    }

    public void addEdge(int from, int to) {
        this.addEdge(from, to, false);
    }

    public void addEdge(int from, int to, boolean edgeIsBidirectional) {
        Node nodeFrom, nodeTo;
        Edge edge;
        nodeFrom = getNode(from);
        nodeTo   = getNode(to);
        edge = new Edge(nodeFrom, nodeTo);
        if (edge.getFrom() == edge.getTo()) {
            //System.err.printf("Reflexive edges (%d => %d) are NOT allowed.\n", from, to);
            throw new IllegalArgumentException(
                    String.format("Reflexive edges (%d => %d) are NOT allowed.\n", from, to));
        }
        else if (containsEdge(edge)) {
            //System.err.printf("Edge (%d => %d) already in Graph! Cannot add same edges multiple times.\n", from, to);
            throw new IllegalArgumentException(
                    String.format("Edge (%d => %d) already in Graph! Cannot add same edges multiple times.\n", from, to));
        }
        else {
            this.edges.add(edge);
        }
        if (edgeIsBidirectional) {
            this.addEdge(to, from, false);
        }
    }

    public boolean containsEdge(Edge e) {
        for (Edge edge : edges) {
            if (edge.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsEdge(int from, int to) {
        for (Edge edge : edges) {
            if (edge.getFrom().getKey() == from
                    && edge.getTo().getKey() == to) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNode(int key) {
        for (Node node : nodes) {
            if (node.getKey() == key) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the node object.
     * If it's not available yet a node
     * with the specific key will be created.
     * @param key
     * @return
     */
    private Node getNode(int key) {
        // make sure node is available
        if (!containsNode(key)) {
            Node node = new Node(key);
            this.nodes.add(node);
            return node;
        }

        for (Node node : nodes) {
            if (node.getKey() == key) {
                return node;
            }
        }
        return null;
    }

    public void clear() {
        this.edges.clear();
        this.nodes.clear();
    }

    public Stack<Integer> search(int from, int to, int searchAlgorithm) {
        SearchProvider sp = new SearchProvider();
        Stack<Node> path = sp.setParams(from, to, searchAlgorithm);
        System.out.println(path.toString());
        Stack<Integer> pathInt = new Stack<>();
        path.stream().forEach(node -> pathInt.add(node.getKey()));
        return pathInt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Graph contains %d edges.\n", edges.size()));
        for (Edge edge : edges) {
            sb.append(edge.toString());
        }
        return sb.toString();
    }

    private class Edge {
        private Node from;
        private Node to;
        public Edge(Node from, Node to) {
            this.from = from;
            this.to   = to;
        }
        public Node getTo() {
            return to;
        }
        public Node getFrom() {
            return from;
        }
        @Override
        public String toString() {
            return String.format("Edge (%d => %d)\n", getFrom().getKey(), getTo().getKey());
        }
        /*@Override
        public int hashCode() {
            return this.from.getKey() + this.to.getKey();
        }*/
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Edge) {
                if (((Edge) obj).getTo() == this.to && ((Edge) obj).getFrom() == this.from) {
                    return true;
                }
                return false;
            }
            else {
                return false;
            }
        }
    }

    private class Node {
        private int key;
        private Node (int key) {
            this.key = key;
        }
        public int getKey() {
            return key;
        }
        public void setKey(int key) {
            this.key = key;
        }
        @Override
        public String toString() {
            return "Node:"+this.getKey();
        }
    }

    private class SearchProvider {
        public int searchMethod;
        public SearchProvider() {}
        public Stack<Node> setParams(int start, int destination, int searchAlgorithm) {
            Node startNode, destinationNode;
            if (!containsNode(start) && !containsNode(destination)) {
                throw new de.phip1611.InvalidParameterException();
            } else {
                startNode       = getNode(start);
                destinationNode = getNode(destination);
                if (this.searchMethod == BREADTH_FIRST_SEARCH) {
                    return breadthFirstSearch(startNode, destinationNode);
                }
                else if (this.searchMethod == DEPTH_FIRST_SEARCH)  {
                    return depthFirstSearch(startNode, destinationNode);
                }
                else {
                    throw new de.phip1611.InvalidParameterException();
                }
            }
        }
        public Stack<Node> breadthFirstSearch(Node start, Node destination) {
            Stack<Node> pathStack = new Stack<>();
            ArrayList<Node> nodesOnCurrentLevel, nodesOnNextLevel, nodesVisited;
            HashMap<Node,Node> nodesDiscoveredThrough;
            boolean foundNode;

            foundNode = false;
            nodesOnCurrentLevel = new ArrayList<>();
            nodesOnNextLevel = new ArrayList<>();
            nodesVisited = new ArrayList<>();
            nodesDiscoveredThrough = new HashMap<>();

            nodesOnNextLevel.add(start); // Der STARTKNOTEN
            while (!foundNode) {
                nodesOnCurrentLevel.clear();
                nodesOnCurrentLevel = (ArrayList<Node>) nodesOnNextLevel.clone();
                nodesOnNextLevel.clear();

                // gibt nichts mehr zu entdecken
                if (nodesOnCurrentLevel.isEmpty()) break;

                // die aktuelle Knoten-Generation komplett durchiterieren
                for (Node currentNode : nodesOnCurrentLevel) {
                    if (foundNode) break;
                            //System.out.println("Aktiver Knoten: "+currentNode);
                            //System.out.println("Knoten auf aktiver Ebene:"+ Arrays.toString(nodesOnCurrentLevel.toArray()));
                    for (Edge edge : edges) {
                            //System.out.println("nodes visited: "+nodesVisited);
                            //System.out.println("nodesVisited.contains(edge.getNodeTo():"+nodesVisited.contains(edge.getNodeTo()));
                        // alle Kanten die vom aktiven Knoten abgehen herausfiltern
                        if (edge.getFrom().getKey() == currentNode.getKey()
                                && !nodesVisited.contains(edge.getTo())
                                && !nodesOnCurrentLevel.contains(edge.getTo())) {
                                    //System.out.println(edge.getNodeTo()+" ist ein durch "+currentNode+" neu entdeckter Knoten");
                            nodesDiscoveredThrough.put(edge.getTo(),currentNode);
                            // Folgeknoten durch aktuellen Knoten gefunden
                            if (edge.getTo() == destination) {
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
                Node nodeFoundThroughPrevious = destination;
                while (nodeFoundThroughPrevious != start) {
                    pathStack.add(0,nodeFoundThroughPrevious);
                    nodeFoundThroughPrevious = nodesDiscoveredThrough.get(nodeFoundThroughPrevious);
                }
            }
            return pathStack;
        }
        public Stack<Node> depthFirstSearch(Node start, Node destination) {
            throw new UnsupportedOperationException("to be implemented");
            //return null;
        }
    }
}
