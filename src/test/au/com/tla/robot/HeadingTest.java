package au.com.tla.robot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HeadingTest {

    @Test
    public void testTurnLeft90FromNorth() {
        assertEquals(Heading.WEST, Heading.NORTH.turnLeft90());
    }

    @Test
    public void testTurnLeft90FromSouth() {
        assertEquals(Heading.EAST, Heading.SOUTH.turnLeft90());
    }

    @Test
    public void testTurnLeft90FromEast() {
        assertEquals(Heading.NORTH, Heading.EAST.turnLeft90());
    }

    @Test
    public void testTurnLeft90FromWest() {
        assertEquals(Heading.SOUTH, Heading.WEST.turnLeft90());
    }

    @Test
    public void testCreateHeadingFromString() {
        assertEquals(Heading.NORTH, Heading.valueOf("NORTH"));
        assertEquals(Heading.SOUTH, Heading.valueOf("SOUTH"));
        assertEquals(Heading.EAST, Heading.valueOf("EAST"));
        assertEquals(Heading.WEST, Heading.valueOf("WEST"));
    }
}
