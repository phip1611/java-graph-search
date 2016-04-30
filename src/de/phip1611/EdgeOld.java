package de.phip1611;

/**
 * Created by phip1611 on 20.04.16.
 */
public class EdgeOld {

    public EdgeOld(int nodeFrom, int nodeTo) {
        this.nodeFrom = nodeFrom;
        this.nodeTo = nodeTo;
    }

    public int getNodeFrom() {
        return nodeFrom;
    }

    public void setNodeFrom(int nodeFrom) {
        this.nodeFrom = nodeFrom;
    }

    public int getNodeTo() {
        return nodeTo;
    }

    public void setNodeTo(int nodeTo) {
        this.nodeTo = nodeTo;
    }

    private int nodeFrom;
    private int nodeTo;
}
