package de.phip1611;

public abstract class GraphSearchConfig extends SearchConfig {
    protected Graph.Node startNode;
    protected Graph.Node destinationNode;
    protected GraphSearchAlgorithm graphSearchAlgorithm;
    protected Graph graph;
    public abstract void setParams(Graph graph, Graph.Node startNode, Graph.Node destinationNode, GraphSearchAlgorithm graphSearchAlgorithm);
    public String toString() {
        return String.format("(%d => %d) using %s\n", startNode.getKey(), destinationNode.getKey(), graphSearchAlgorithm.getClass().getName());
    }
}
