package de.phip1611;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(0,2);
        g.addEdge(1,0);
        g.addEdge(1,3);
        g.addEdge(2,0);
        g.addEdge(2,3);
        g.addEdge(2,4);
        g.addEdge(3,1);
        g.addEdge(3,4);
        g.addEdge(4,5);
        g.addEdge(4,3);
        g.addEdge(4,2);
        g.addEdge(5,4);

        g.addEdge(5,7);
        g.addEdge(7,5);
        g.addEdge(7,8);
        g.addEdge(8,7);
        g.addEdge(8,6);
        g.addEdge(6,4);
        g.addEdge(9,6);
        ArrayListStack<Integer> result = g.search(0, 7, Graph.SEARCH_ALGORITHMS.BFS);

        LinkedListStack<Integer> yoStack = new LinkedListStack<>();
        yoStack.add(0);
        yoStack.add(2);
        yoStack.add(4);
        yoStack.add(6);

        int yo = yoStack.size();
        for (int i = 0; i < yo; i++) {
            System.out.println(yoStack.pop());
            System.out.println(yoStack);
        }
    }
}
