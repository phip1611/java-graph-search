package de.phip1611;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {
    public void test() {
        Result result = org.junit.runner.JUnitCore.runClasses(GraphTestCase.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail);
        }
        System.out.println(result.getFailureCount());
    }



    public static void main(String[] args) {
        new Main().test();
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
        System.out.println(g.search(0,7,Graph.BREADTH_FIRST_SEARCH));
    }
}
