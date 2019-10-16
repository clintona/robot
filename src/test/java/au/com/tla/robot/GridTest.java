package au.com.tla.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GridTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNewGridZeroSize() {
        new Grid(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewGridNegativeSize() {
        new Grid(-1);
    }

    @Test
    public void testNewGridPostiveSize() {
        assertNotNull(new Grid(1));
    }

    @Test
    public void testGridGetSize() {
        assertEquals(2, new Grid(2).getSize());
    }

    @Test
    public void testIsValidPosition() {
        Grid grid = new Grid(2);
        assertTrue(grid.isValidPosition(0, 0));
        assertTrue(grid.isValidPosition(0, 1));
        assertTrue(grid.isValidPosition(1, 0));
        assertTrue(grid.isValidPosition(1, 1));
    }

    @Test
    public void testIsNotValidPosition() {
        Grid grid = new Grid(2);
        assertFalse(grid.isValidPosition(0, 2));
        assertFalse(grid.isValidPosition(2, 0));
        assertFalse(grid.isValidPosition(-1, -1));
    }

}
