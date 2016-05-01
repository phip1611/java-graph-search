package de.phip1611.graph;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(0,1); g.addEdge(0,2);
        g.addEdge(1,3); g.addEdge(1,4);
        g.addEdge(2,0); g.addEdge(2,5); g.addEdge(2,8);
        g.addEdge(3,4); g.addEdge(3,6);
        g.addEdge(4,3); g.addEdge(4,1); g.addEdge(4,2);
        g.addEdge(5,2); g.addEdge(5,4);
        g.addEdge(6,7);
        g.addEdge(7,6); g.addEdge(7,5); g.addEdge(7,8);
        g.addEdge(8,7);
        ArrayListStack<Integer> result;
        result = g.search(0, 8, Graph.SEARCH_ALGORITHMS.BFS); // [2, 8]
        result = g.search(0, 0, Graph.SEARCH_ALGORITHMS.BFS); // []
        result = g.search(0, 7, Graph.SEARCH_ALGORITHMS.BFS); // [2, 8, 7]
        result = g.search(0, 20, Graph.SEARCH_ALGORITHMS.BFS); // []
        result = g.search(8, 0, Graph.SEARCH_ALGORITHMS.BFS); // [7, 5, 2, 0]
    }
}
