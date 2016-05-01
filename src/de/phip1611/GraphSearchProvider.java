package de.phip1611;


import java.util.Stack;

public abstract class GraphSearchProvider extends SearchProvider {
    protected GraphSearchConfig graphSearchConfig;
    public abstract ArrayListStack<Graph.Node> search(GraphSearchConfig graphSearchConfig);
    public String toString() {
        return graphSearchConfig.toString();
    }
}
