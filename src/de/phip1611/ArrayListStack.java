package de.phip1611;

import java.util.ArrayList;

public class ArrayListStack<T> extends ArrayList<T> {
    public T pop() {
        return this.remove(this.size()-1);
    }
}