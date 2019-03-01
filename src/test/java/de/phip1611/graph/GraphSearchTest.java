package de.phip1611.graph;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GraphSearchTest {
    private Graph graph;

    @Before
    public void setUp() {
        this.graph = new Graph();
        graph.addEdge(0,1); graph.addEdge(0,2);
        graph.addEdge(1,3); graph.addEdge(1,4);
        graph.addEdge(2,0); graph.addEdge(2,5); graph.addEdge(2,8);
        graph.addEdge(3,4); graph.addEdge(3,6);
        graph.addEdge(4,3); graph.addEdge(4,1); graph.addEdge(4,2);
        graph.addEdge(5,2); graph.addEdge(5,4);
        graph.addEdge(6,7);
        graph.addEdge(7,6); graph.addEdge(7,5); graph.addEdge(7,8);
        graph.addEdge(8,7);
    }

    @Test
    public void test() {
        List<Integer> expected1 = List.of(2, 8);
        List<Integer> expected2 = List.of();
        List<Integer> expected3 = List.of(2, 8, 7);
        List<Integer> expected4 = List.of();
        List<Integer> expected5 = List.of(7, 5, 2, 0);

        List<Integer> actual1 = graph.search(0,  8, SearchAlgorithms.BFS);
        List<Integer> actual2 = graph.search(0,  0, SearchAlgorithms.BFS);
        List<Integer> actual3 = graph.search(0,  7, SearchAlgorithms.BFS);
        List<Integer> actual4 = graph.search(0, 20, SearchAlgorithms.BFS);
        List<Integer> actual5 = graph.search(8,  0, SearchAlgorithms.BFS);

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);
        Assert.assertEquals(expected4, actual4);
        Assert.assertEquals(expected5, actual5);
    }
}
