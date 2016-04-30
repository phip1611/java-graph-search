package de.phip1611;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.IllegalFormatCodePointException;

/**
 * Created by phip1611 on 30.04.16.
 */
public class GraphTestCase extends TestCase {
    private Graph g;

    @Before
    public void setUp() {
        g = new Graph();
    }

    @After
    public void coolDown() {
        g = null;
    }


    @Rule
    public IllegalArgumentException thrown;
    @Test(expected = IllegalArgumentException.class)
    public void testReflexiveEdge() {
        g.addEdge(0,0);
        /*try {
            g.addEdge(0,0);
            fail("There should be a IllegalArgumentException when adding a reflexive edge.");
        }
        catch (IllegalArgumentException ex) {
            // Everything fine
        }
        catch (Exception ex) {
            fail("There was another exception instead of IllegalArgumentException");
        }*/
    }

}
