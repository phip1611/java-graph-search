package de.phip1611.graph;

import java.util.ArrayList;
import java.util.List;

import static de.phip1611.graph.GraphSearchResultBuilder.build;

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

    protected ArrayList<Edge> getEdges() {
        return edges;
    }

    private ArrayList<Node> getNodes() {
        return nodes;
    }

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

    private boolean containsEdge(Edge e) {
        return edges.contains(e);
    }

    public boolean containsEdge(int from, int to) {
        Node fromNode, toNode;
        fromNode = getNode(from);
        toNode   = getNode(to);
        return edges.contains(new Edge(fromNode, toNode));
    }

    private boolean containsNode(int key) {
        for (Node node : nodes) {
            if (node.getKey() == key) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNode(Node node) {
        return nodes.contains(node);
    }

    /**
     * Returns the node object.
     * If it's not available yet a node
     * with the specific key <strong>will be created</strong>.
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

    /**
     * Durchsucht den Graphen nach einem Pfad von A nach B
     * mit dem gewählten Algorithmus.
     * @param from
     * @param to
     * @param algorithm
     * @return
     */
    public List<Integer> search(int from, int to, SearchAlgorithms algorithm) {
        GraphSearchAlgorithm graphSearchAlgorithm;
        graphSearchAlgorithm = algorithm.init();
        if (graphSearchAlgorithm == null) {
            throw new IllegalArgumentException("Wrong algorithm.");
        }

        graphSearchAlgorithm = algorithm.init();
        Stack<Node> result = graphSearchAlgorithm.search(this.getEdges(), this.getNode(from), this.getNode(to));
        return build(result);
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

    /**
     * Repräsentiert eine Kante, die stets gerichtet
     * von einem Knoten zum anderen verläuft.
     */
    public class Edge {
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

        @Override
        public int hashCode() {
            int result = from.hashCode();
            result = 31 * result + to.hashCode();
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;

            Edge edge = (Edge) o;

            if (!from.equals(edge.from)) return false;
            return to.equals(edge.to);

        }
    }

    /**
     * Repräsentiert einen Knoten.
     * Eindeutig identifiziert durch seine Knotennummer.
     */
    public class Node {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            return key == node.key;

        }

        @Override
        public int hashCode() {
            return key;
        }
    }

}
