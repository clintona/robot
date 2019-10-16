package au.com.tla.robot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.com.tla.robot.Heading;
import au.com.tla.robot.Position;
import au.com.tla.robot.TestCommon;

public class MoveCommandTest extends TestCommon {

    @Test
    public void testMoveSuccess() {
        placeRobotInCentreFacing(Heading.NORTH);

        assertTrue(robot.execute(new MoveCommand(robot)));

        // move one NORTH from CENTRE(2,2) = (2,3)
        assertState(new Position(2, 3), Heading.NORTH, true);
    }

    @Test
    public void testMoveWhenNotPlaced() {
        // setUp robot is unplaced
        assertFalse(robot.execute(new MoveCommand(robot)));

        assertState(null, null, false);
    }

    @Test
    public void testMoveOffGridNorth() {
        Position northernPoint = new Position(4, 4);
        assertInvalidMoveFrom(northernPoint, Heading.NORTH);
    }

    @Test
    public void testMoveOffGridSouth() {
        Position southernPoint = new Position(0, 0);
        assertInvalidMoveFrom(southernPoint, Heading.SOUTH);
    }

    @Test
    public void testMoveOffGridEast() {
        Position easternPoint = new Position(4, 4);
        assertInvalidMoveFrom(easternPoint, Heading.EAST);

    }

    @Test
    public void testMoveOffGridWest() {
        Position westernPoint = new Position(0, 0);
        assertInvalidMoveFrom(westernPoint, Heading.WEST);
    }

    private void assertInvalidMoveFrom(Position point, Heading facing) {
        placeRobotAt(point, facing);

        assertFalse(robot.execute(new MoveCommand(robot)));

        assertState(point, facing, true);
    }

    @Test
    public void testMoveToBoundary() {
        placeRobotAt(new Position(1, 0), Heading.WEST);

        assertTrue(robot.execute(new MoveCommand(robot)));

        assertState(new Position(0, 0), Heading.WEST, true);
    }

    @Test
    public void testToString() {
        Command cmd = new MoveCommand(robot);
        assertEquals("MOVE", cmd.toString());
    }

}
