package au.com.tla.robot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.com.tla.robot.Heading;
import au.com.tla.robot.TestCommon;

public class LeftCommandTest extends TestCommon {

    @Test
    public void testLeftFromNorth() {
        testRotateLeftFrom(Heading.NORTH, Heading.WEST);
    }

    @Test
    public void testLeftFromSouth() {
        testRotateLeftFrom(Heading.SOUTH, Heading.EAST);
    }

    @Test
    public void testLeftFromEast() {
        testRotateLeftFrom(Heading.EAST, Heading.NORTH);
    }

    @Test
    public void testLeftFromWest() {
        testRotateLeftFrom(Heading.WEST, Heading.SOUTH);
    }

    @Test
    public void testLeftFailsWhenNotPlaced() {
        Heading originalHeading = robot.getHeading();

        // expect failure because robot not placed
        boolean result = robot.execute(new LeftCommand(robot));

        assertFalse(result);
        assertEquals(originalHeading, robot.getHeading());
    }

    @Test
    public void testPositionUnchangedOnTurn() {
        placeRobotInCentreFacing(Heading.NORTH);

        boolean result = robot.execute(new LeftCommand(robot));

        assertTrue(result);
        assertEquals(CENTRE, robot.getGridPosition().getPosition());
    }

    @Test
    public void testToString() {
        Command cmd = new LeftCommand(robot);
        assertEquals("LEFT", cmd.toString());
    }

    private void testRotateLeftFrom(Heading startHeading, Heading finalHeading) {
        // robot needs to be placed before we can turn it
        placeRobotInCentreFacing(startHeading);

        boolean result = robot.execute(new LeftCommand(robot));

        assertTrue(result);
        assertEquals(finalHeading, robot.getHeading());
    }

}
