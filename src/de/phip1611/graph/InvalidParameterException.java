package de.phip1611.graph;

/**
 * Created by phip1611 on 01.05.16.
 */
public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException() {
        super();
    }
    public InvalidParameterException(String msg) {
        super(msg);
    }
}
