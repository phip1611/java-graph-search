package de.phip1611.graph;

import java.util.ArrayList;

/**
 * An ArrayList, that offers the pop()-Operation.
 * @param <T>
 */
public class Stack<T> extends ArrayList<T> {

    /**
     * Remove last Element from queue.
     * @return
     */
    public T pop() {
        return this.remove(this.size()-1);
    }
}