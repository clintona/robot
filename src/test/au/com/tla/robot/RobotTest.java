package au.com.tla.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RobotTest extends TestCommon {

    // Note: Robot.execute commands tested under 'command' package

    @Test(expected = NullPointerException.class)
    public void testRobotConstructionWithNullGrid() {
        new Robot(null);
    }

    @Test
    public void testRobotConstructionWithValidGrid() {
        // TestCommon.setUp creates a Robot with valid Grid

        assertNotNull(this.robot);
        assertNotNull(this.robot.getGridPosition());
    }

    @Test
    public void testSetHeadingWhenNotPlaced() {
        assertFalse(this.robot.setHeading(Heading.NORTH));
    }

    @Test
    public void testSetNullHeadingWhilstPlaced() {
        placeRobotInCentreFacing(Heading.NORTH);

        assertFalse(this.robot.setHeading(null));
    }

    @Test
    public void testSetValidHeadingWhilstPlaced() {
        placeRobotInCentreFacing(Heading.NORTH);

        assertTrue(this.robot.setHeading(Heading.SOUTH));

        assertEquals(Heading.SOUTH, this.robot.getHeading());
    }

    @Test
    public void testToStringWhenPlaced() {
        placeRobotInCentreFacing(Heading.NORTH);

        assertEquals("2,2,NORTH", this.robot.toString());
    }

    @Test
    public void testToStringWhenNotPlaced() {
        // setUp creates an unplaced Robot
        assertEquals("NOT PLACED", this.robot.toString());
    }

}
