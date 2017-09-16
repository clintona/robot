package au.com.tla.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import au.com.tla.robot.command.Command;
import au.com.tla.robot.command.PlaceCommand;

/**
 * Unit test convenience class, shared test methods etc.
 * 
 * @author Clinton
 * 
 */
public class TestCommon {

    /** Robot instance to test */
    protected Robot robot;

    /** CENTRE Grid Position assuming TEST_GRID_SIZE = 5 */
    public final Position CENTRE = new Position(2, 2);

    /** ORIGIN Grid Position */
    public final Position ORIGIN = new Position(0, 0);

    /** Test Grid size */
    public static final int TEST_GRID_SIZE = 5;

    @Before
    public void setUp() {
        this.robot = new Robot(new Grid(TEST_GRID_SIZE));
    }

    protected void placeRobotInCentreFacing(Heading facing) {
        placeRobotAt(CENTRE, facing);
    }

    protected void placeRobotAt(Position xy, Heading facing) {
        Command placeCommand = new PlaceCommand(robot, xy, facing);
        assertTrue(robot.execute(placeCommand));
    }

    protected void assertState(Position expectedPosition, Heading expectedHeading,
            boolean expectedPlacement) {
        assertEquals(expectedPosition, robot.getGridPosition().getPosition());
        assertEquals(expectedHeading, robot.getHeading());
        assertEquals(expectedPlacement, robot.getGridPosition().isPlaced());
    }
}
