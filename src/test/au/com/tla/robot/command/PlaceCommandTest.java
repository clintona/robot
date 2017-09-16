package au.com.tla.robot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.com.tla.robot.Heading;
import au.com.tla.robot.Position;
import au.com.tla.robot.TestCommon;

public class PlaceCommandTest extends TestCommon {

    @Test
    public void testPlaceSuccess() {
        Command command = new PlaceCommand(robot, CENTRE, Heading.NORTH);

        assertTrue(command.execute());

        assertState(CENTRE, Heading.NORTH, true);
    }

    @Test
    public void testPlaceFailureNullHeading() {
        Command command = new PlaceCommand(robot, CENTRE, null);

        assertFalse(command.execute());

        assertState(null, null, false);
    }

    @Test
    public void testPlaceFailureBadPosition() {
        Position xy = new Position(99, 99); // off grid
        Command command = new PlaceCommand(robot, xy, Heading.NORTH);

        assertFalse(command.execute());

        assertState(null, null, false);
    }

    @Test
    public void testValidSubsequentPlacement() {
        placeRobotInCentreFacing(Heading.NORTH);

        Position xy = new Position(0, 0);
        Command command = new PlaceCommand(robot, xy, Heading.EAST);

        assertTrue(command.execute());

        assertState(xy, Heading.EAST, true);
    }

    @Test
    public void testInvalidSubsequentPlacement() {
        placeRobotInCentreFacing(Heading.NORTH);

        Position xy = new Position(99, 99); // off grid
        Command command = new PlaceCommand(robot, xy, Heading.NORTH);

        assertFalse(command.execute());

        assertState(CENTRE, Heading.NORTH, true);
    }

    @Test
    public void testToString() {
        Position xy = new Position(1, 2);
        Command command = new PlaceCommand(robot, xy, Heading.NORTH);

        assertEquals("PLACE 1,2,NORTH", command.toString());
    }

}
