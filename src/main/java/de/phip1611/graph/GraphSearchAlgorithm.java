package de.phip1611.graph;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Interface für Graph-Such-Algorithmen.
 * Übergeben werden müssen die Kanten (die
 * implizit die Knoten + Beschriftungen mitliefern),
 * der Startknoten, sowie der Zielknoten.
 *
 * Zurück kommt ein NodeList mit Knoten-Nummern, die
 * nacheinander besucht werden müssen, bis man am
 * Ziel ankommt.
 */
public interface GraphSearchAlgorithm {
    /**
     * Durchsucht alle Kanten eines Graphen nach einem Pfad vom Knoten A mit dem Knoten B.
     * Gibt eine Liste der Keys zurück.
     *
     * @param edges Kanten des Graphen
     * @param fromNode Startknoten
     * @param toNode Endknoten
     * @return Liste der Keys
     */
    NodeList search(List<Graph.Edge> edges, Graph.Node fromNode, Graph.Node toNode);

    /**
     * Macht aus einem Nodestack eine Liste der Keys.
     * @param nodeList Nodestack
     * @return Liste der Keys
     */
    default List<Integer> toKeyList(NodeList nodeList) {
        return nodeList.stream().map(Graph.Node::getKey).collect(toList());
    }
}
