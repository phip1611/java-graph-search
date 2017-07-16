package de.phip1611.graph;

/**
 * Alle Graph-Suche-Algorithmen, die es so gibt.
 * Gibt eine neue Instanz zurück, die zum entsprechenden
 * Kürzel passt.
 */
public enum SearchAlgorithms {
    BFS, DFS;

    public GraphSearchAlgorithm init() {
        switch (this) {
            case BFS: {
                return new BreadthFirstSearchGraphSearchAlgorithm();
            }
            case DFS: {
                return new DepthFirstSearchGraphSearchAlgorithm();
            }
            default: {
                return null;
            }
        }
    }
}


