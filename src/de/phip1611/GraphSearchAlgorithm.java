package de.phip1611;

public abstract class GraphSearchAlgorithm extends SearchAlgorithm {
    public abstract ArrayListStack<Graph.Node> search(Graph graph, Graph.Node fromNode, Graph.Node toNode);
}
