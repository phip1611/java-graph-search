package de.phip1611.graph;

import java.lang.reflect.InvocationTargetException;

/**
 * Alle Graph-Suche-Algorithmen, die es so gibt.
 * Gibt eine neue Instanz zurück, die zum entsprechenden
 * Kürzel passt.
 */
public enum SearchAlgorithms {
    BFS(BreadthFirstSearchGraphSearchAlgorithm.class),
    DFS(DepthFirstSearchGraphSearchAlgorithm.class);

    private Class<? extends GraphSearchAlgorithm> clazz;

    SearchAlgorithms(Class<? extends GraphSearchAlgorithm> clazz) {
        this.clazz = clazz;
    }

    public GraphSearchAlgorithm init() {
        try {
            return this.clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.err.println("Fehler beim initialisieren der Klasse!");
            return null;
        }
    }
}


