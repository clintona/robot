package au.com.tla.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PositionTest {

    @Test
    public void testPosition() {
        Position pos = new Position(1, 2);

        assertEquals(1, pos.x);
        assertEquals(2, pos.y);
    }

    @Test
    public void testEquals() {
        Position p1 = new Position(1, 2);
        Position p2 = new Position(1, 2);

        assertTrue(p1.equals(p2));
    }

    @Test
    public void testEqualsSameInstance() {
        Position p1 = new Position(1, 2);
        Position p2 = p1;

        assertTrue(p1.equals(p2));
    }

    @Test
    public void testNotEqualsNull() {
        Position p1 = new Position(1, 2);
        Position p2 = null;

        assertFalse(p1.equals(p2));
    }

    @Test
    public void testNotEqualsX() {
        Position p1 = new Position(1, 2);
        Position p2 = new Position(0, 2);

        assertFalse(p1.equals(p2));
    }

    @Test
    public void testNotEqualsY() {
        Position p1 = new Position(1, 2);
        Position p2 = new Position(1, 0);

        assertFalse(p1.equals(p2));
    }

    @Test
    public void testNotEqualsXY() {
        Position p1 = new Position(1, 2);
        Position p2 = new Position(-1, -2);

        assertFalse(p1.equals(p2));
    }

}
