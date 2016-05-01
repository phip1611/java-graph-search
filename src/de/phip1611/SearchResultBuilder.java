package de.phip1611;

import java.util.Stack;

public abstract class SearchResultBuilder extends Search {
    public abstract ArrayListStack<Integer> build(ArrayListStack<Graph.Node> stack);
}
