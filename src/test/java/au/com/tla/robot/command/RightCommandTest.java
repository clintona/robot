package au.com.tla.robot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.com.tla.robot.Heading;
import au.com.tla.robot.TestCommon;

public class RightCommandTest extends TestCommon {

    @Test
    public void testRightFromNorth() {
        testRotateRightFrom(Heading.NORTH, Heading.EAST);
    }

    @Test
    public void testRightFromSouth() {
        testRotateRightFrom(Heading.SOUTH, Heading.WEST);
    }

    @Test
    public void testRightFromEast() {
        testRotateRightFrom(Heading.EAST, Heading.SOUTH);
    }

    @Test
    public void testRightFromWest() {
        testRotateRightFrom(Heading.WEST, Heading.NORTH);
    }

    @Test
    public void testRightFailsWhenNotPlaced() {
        Heading originalHeading = robot.getHeading();

        // expect failure because robot not placed
        boolean result = robot.execute(new RightCommand(robot));

        assertFalse(result);
        assertEquals(originalHeading, robot.getHeading());
    }

    private void testRotateRightFrom(Heading startHeading, Heading finalHeading) {
        // robot needs to be placed before we can turn it
        placeRobotInCentreFacing(startHeading);

        boolean result = robot.execute(new RightCommand(robot));

        assertTrue(result);
        assertEquals(finalHeading, robot.getHeading());
    }

    @Test
    public void testToString() {
        Command cmd = new RightCommand(robot);
        assertEquals("RIGHT", cmd.toString());
    }
}
