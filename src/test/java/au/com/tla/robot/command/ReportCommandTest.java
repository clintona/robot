package au.com.tla.robot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import au.com.tla.robot.Heading;
import au.com.tla.robot.TestCommon;

public class ReportCommandTest extends TestCommon {

    @Test(expected = NullPointerException.class)
    public void testReportCommandWithNullStream() {
        new ReportCommand(robot, null);
    }

    @Test
    public void testReportWhenRobotPlaced() {
        placeRobotInCentreFacing(Heading.NORTH);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(buffer);
        Command command = new ReportCommand(robot, out);

        assertTrue(command.execute());

        assertState(CENTRE, Heading.NORTH, true);
        assertEquals("2,2,NORTH", buffer.toString().trim());
    }

    @Test
    public void testReportWhenRobotNotPlaced() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(buffer);
        Command command = new ReportCommand(robot, out);

        assertTrue(command.execute());

        assertState(null, null, false);
        assertEquals("NOT PLACED", buffer.toString().trim());

    }

    @Test
    public void testToString() {
        Command cmd = new ReportCommand(robot, System.out);
        assertEquals("REPORT", cmd.toString());
    }
}
