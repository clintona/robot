package au.com.tla.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GridPositionTest extends TestCommon {

    private GridPosition gridPosition;

    @Before
    public void setUp() {
        Grid testGrid = new Grid(TEST_GRID_SIZE);
        this.gridPosition = new GridPosition(testGrid);
    }

    @Test
    public void testCreateGridPositionSuccess() {
        assertNotNull(gridPosition);
        assertNull(gridPosition.getPosition());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateGridPositionNullGrid() {
        new GridPosition(null);
    }

    @Test
    public void testIsValidPosition() {
        assertTrue(gridPosition.isValidPosition(CENTRE));
    }

    @Test
    public void testIsValidPositionNull() {
        assertFalse(gridPosition.isValidPosition(null));
    }

    @Test
    public void testIsValidPositionOffGrid() {
        Position offGrid = new Position(-1, 999);

        assertFalse(gridPosition.isValidPosition(offGrid));
    }

    @Test
    public void testSetPositionNull() {
        assertFalse(gridPosition.setPosition(null));
        assertEquals(null, gridPosition.getPosition());
    }

    @Test
    public void testSetPositionOnGrid() {
        assertTrue(gridPosition.setPosition(CENTRE));
        assertEquals(CENTRE, gridPosition.getPosition());
    }

    @Test
    public void testSetPositionOffGrid() {
        Position offGrid = new Position(-1, 999);

        assertFalse(gridPosition.setPosition(offGrid));
        assertEquals(null, gridPosition.getPosition());
    }

    @Test
    public void testMoveForward() {
        gridPosition.setPosition(CENTRE);

        assertTrue(gridPosition.moveForward(Heading.NORTH));

        // move NORTH from CENTRE(2,2) = (2,3)
        assertEquals(new Position(2, 3), gridPosition.getPosition());
    }

    @Test
    public void testIsPlaced() {
        gridPosition.setPosition(CENTRE);

        assertTrue(gridPosition.isPlaced());
    }

    @Test
    public void testIsNotPlaced() {
        // no position straight after construction
        assertFalse(gridPosition.isPlaced());
    }

    @Test
    public void testMoveForwardNullHeading() {
        gridPosition.setPosition(CENTRE);

        assertFalse(gridPosition.moveForward(null));
    }

    @Test
    public void testMoveForwardWhenNotPlaced() {
        assertFalse(gridPosition.moveForward(Heading.NORTH));
    }

    @Test
    public void testMoveForwardOffGridNorth() {
        Position northernPoint = new Position(4, 4);

        assertInvalidMoveFrom(northernPoint, Heading.NORTH);
    }

    @Test
    public void testMoveForwardOffGridSouth() {
        Position southernPoint = new Position(0, 0);
        assertInvalidMoveFrom(southernPoint, Heading.SOUTH);
    }

    @Test
    public void testMoveForwardOffGridEast() {
        Position easternPoint = new Position(4, 4);

        assertInvalidMoveFrom(easternPoint, Heading.EAST);
    }

    @Test
    public void testMoveForwardOffGridWest() {
        Position westernPoint = new Position(0, 0);

        assertInvalidMoveFrom(westernPoint, Heading.WEST);
    }

    private void assertInvalidMoveFrom(Position startingPoint, Heading facing) {
        gridPosition.setPosition(startingPoint);

        assertFalse(gridPosition.moveForward(facing));

        assertEquals(startingPoint, gridPosition.getPosition());
    }

    @Test(expected = NullPointerException.class)
    public void testGetProjectedPositionNullHeading() {
        gridPosition.getProjectedPosition(null);
    }

}
