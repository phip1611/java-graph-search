package de.phip1611.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Baut aus dem Stack des Knotenpfades eine
 * Liste mit den Knotennummer, die hier die
 * eindeutigen Knotenbeschriftungen
 * darstellen.
 */
public class GraphSearchResultBuilder {
    public static List<Integer> build(Stack<Graph.Node> stack) {
        List<Integer> returnThis = new ArrayList<>();
        stack.stream().forEach(node -> {
            returnThis.add(node.getKey());
        });
        return returnThis;
    }
}
