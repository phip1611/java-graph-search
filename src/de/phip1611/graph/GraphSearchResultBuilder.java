package de.phip1611.graph;

public class GraphSearchResultBuilder extends SearchResultBuilder {
    @Override
    public ArrayListStack<Integer> build(ArrayListStack<Graph.Node> stack) {
        ArrayListStack<Integer> returnThis = new ArrayListStack<>();
        stack.stream().forEach(node -> {
            returnThis.add(node.getKey());
        });
        return returnThis;

    }
}
