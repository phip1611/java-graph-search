package de.phip1611;

import java.util.Stack;

public abstract class SearchResultBuilder extends Search {
    public abstract Stack<Integer> build(Stack<Graph.Node> stack);
}
