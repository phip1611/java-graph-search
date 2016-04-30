package de.phip1611.junittests;

import de.phip1611.Main;
import junit.framework.TestCase;
import org.junit.Before;

import java.util.InputMismatchException;

/**
 * Created by phip1611 on 30.04.16.
 */
public class TestTestCase {
    private DemoClass inst;
    public TestTestCase() {

    }
    @Before
    public void setUp() {
        inst = new DemoClass();
    }
    @org.junit.Test(expected = InputMismatchException.class)
    public void testTest() {
        inst.test();
    }
}
