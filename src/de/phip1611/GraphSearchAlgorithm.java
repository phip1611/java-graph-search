package de.phip1611;

import java.util.Stack;

public abstract class GraphSearchAlgorithm extends SearchAlgorithm {
    public abstract Stack<Graph.Node> search(Graph graph, Graph.Node fromNode, Graph.Node toNode);
}
