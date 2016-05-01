package de.phip1611;

import java.util.Stack;

public class GraphSearchResultBuilder extends SearchResultBuilder {
    public static final Stack<Integer> buildStatic(Stack<Graph.Node> stack) {
        Stack<Integer> returnThis = new Stack<>();
        stack.stream().forEach(node -> {
            returnThis.add(node.getKey());
        });
        return returnThis;
    }

    @Override
    public Stack<Integer> build(Stack<Graph.Node> stack) {
        return null;
    }
}
