package de.phip1611.graph;

public abstract class SearchResultBuilder extends Search {
    public abstract ArrayListStack<Integer> build(ArrayListStack<Graph.Node> stack);
}
