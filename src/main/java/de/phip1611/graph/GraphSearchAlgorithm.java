package de.phip1611.graph;

import java.util.List;

/**
 * Interface für Graph-Such-Algorithmen.
 * Übergeben werden müssen die Kanten (die
 * implizit die Knoten + Beschriftungen mitliefern),
 * der Startknoten, sowie der Zielknoten.
 *
 * Zurück kommt ein Stack mit Knoten-Nummern, die
 * nacheinander besucht werden müssen, bis man am
 * Ziel ankommt.
 */
public interface GraphSearchAlgorithm {
    Stack<Graph.Node> search(List<Graph.Edge> edges, Graph.Node fromNode, Graph.Node toNode);
}
