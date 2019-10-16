package au.com.tla.robot.command;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import au.com.tla.robot.TestCommon;

public class CommandFactoryTest extends TestCommon {

    private CommandFactory factory;

    @Before
    public void setUp() {
        super.setUp(); // creates a robot for testing
        this.factory = new CommandFactory(super.robot);
    }

    @Test(expected = NullPointerException.class)
    public void testCommandFactoryWithNullRobot() {
        new CommandFactory(null);
    }

    @Test(expected = NullPointerException.class)
    public void testMakeCommandWithNullLine() {
        factory.makeCommand(null);
    }

    @Test
    public void testMakePlaceCommand() {
        Command cmd = factory.makeCommand("PLACE 1,2,NORTH");

        assertTrue(cmd instanceof PlaceCommand);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMakePlaceCommandBadLine() {
        factory.makeCommand("BAD LINE");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMakePlaceCommandMissingYArg() {
        factory.makeCommand("PLACE x");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMakePlaceCommandMissingHeadingArg() {
        factory.makeCommand("PLACE x,y");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMakePlaceCommandMissingArgs() {
        factory.makeCommand("PLACE");
    }

    @Test(expected = NumberFormatException.class)
    public void testMakePlaceCommandBadX() {
        factory.makeCommand("PLACE x,y,NORTH");
    }

    @Test(expected = NumberFormatException.class)
    public void testMakePlaceCommandBadY() {
        factory.makeCommand("PLACE 1,y,NORTH");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMakePlaceCommandBadHeading() {
        factory.makeCommand("PLACE 1,1,BLAH");
    }

    @Test
    public void testMakeReportCommand() {
        Command cmd = factory.makeCommand("REPORT");

        assertTrue(cmd instanceof ReportCommand);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMakeReportCommandBadLine() {
        factory.makeCommand("RePorT");
    }

    @Test
    public void testMakeMoveCommand() {
        Command cmd = factory.makeCommand("MOVE");

        assertTrue(cmd instanceof MoveCommand);
    }

    @Test
    public void testMakeLeftCommand() {
        Command cmd = factory.makeCommand("LEFT");

        assertTrue(cmd instanceof LeftCommand);
    }

    @Test
    public void testMakeRightCommand() {
        Command cmd = factory.makeCommand("RIGHT");

        assertTrue(cmd instanceof RightCommand);
    }

}
