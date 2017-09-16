package au.com.tla.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import au.com.tla.robot.command.Command;
import au.com.tla.robot.command.LeftCommand;
import au.com.tla.robot.command.MoveCommand;
import au.com.tla.robot.command.PlaceCommand;

public class RobotSimulatorTest extends TestCommon {

    private RobotSimulator simulator;

    @Before
    public void setUp() {
        super.setUp();
        this.simulator = new RobotSimulator(TEST_GRID_SIZE);
    }

    @Test
    public void testRobotSimulator() {
        // setup creates a RobotSimulator
        assertNotNull(simulator);
    }

    @Test(expected = NullPointerException.class)
    public void testReadCommandsFromStreamNull() throws Exception {
        simulator.readCommandsFromStream(null);
    }

    @Test(expected = IOException.class)
    public void testReadCommandsFromStreamIOException() throws Exception {
        InputStream in = mock(InputStream.class);
        simulator.readCommandsFromStream(in);
    }

    @Test
    public void testReadCommandsFromStream() throws Exception {
        String CRLF = System.lineSeparator();
        String commandString = "PLACE 0,0,EAST" + CRLF + "MOVE" + CRLF + "LEFT";
        ByteArrayInputStream in = new ByteArrayInputStream(commandString.getBytes());

        List<Command> commandList = simulator.readCommandsFromStream(in);

        assertTrue(commandList.get(0) instanceof PlaceCommand);
        assertTrue(commandList.get(1) instanceof MoveCommand);
        assertTrue(commandList.get(2) instanceof LeftCommand);
    }

    @Test
    public void testExecute() {
        placeRobotInCentreFacing(Heading.NORTH);

        List<Command> commands = Arrays.asList(new LeftCommand(robot), new MoveCommand(robot));

        simulator.execute(commands);

        assertState(new Position(1, 2), Heading.WEST, true);
    }

    @Test
    public void testGetInputStreamFromStdin() throws Exception {
        String[] args = new String[0]; // forces stdin stream
        InputStream in = RobotSimulator.getInputStream(args);

        assertEquals(System.in, in);
    }

    @Test
    public void testGetInputStreamFromFile() throws Exception {
        // create a temp file that will always exist
        File temp = File.createTempFile("temp", ".txt");
        temp.deleteOnExit();
        String[] args = new String[] { "-f", temp.getAbsolutePath() };
        InputStream in = RobotSimulator.getInputStream(args);

        assertTrue(in instanceof FileInputStream);
    }

    @Test(expected = NullPointerException.class)
    public void testGetInputStreamNull() throws Exception {
        RobotSimulator.getInputStream(null);
    }

    @Test
    public void testRobotWontMoveOffEdge() {
        placeRobotInCentreFacing(Heading.NORTH);
        Command move = new MoveCommand(robot);
        List<Command> commands = Arrays.asList(move, move, move);

        simulator.execute(commands);

        assertState(new Position(2, 4), Heading.NORTH, true);
    }

    @Test
    public void testRobotIgnoresCommandsUntilPlaced() {
        Command move = new MoveCommand(robot);
        Command place = new PlaceCommand(robot, CENTRE, Heading.NORTH);
        List<Command> commands = Arrays.asList(move, move, place);

        simulator.execute(commands);

        assertState(CENTRE, Heading.NORTH, true);
    }

    @Test
    public void testSubsequentPlaceCommand() {
        placeRobotInCentreFacing(Heading.NORTH);
        assertState(CENTRE, Heading.NORTH, true);

        Command place = new PlaceCommand(robot, ORIGIN, Heading.EAST);
        List<Command> commands = Arrays.asList(place);

        simulator.execute(commands);

        assertState(ORIGIN, Heading.EAST, true);
    }
}
