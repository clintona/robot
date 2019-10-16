package au.com.tla.robot.command;

import org.junit.Test;

import au.com.tla.robot.TestCommon;

public class CommandTest extends TestCommon {

    @Test(expected = NullPointerException.class)
    public void testCommandWithNullRobot() {
        new MoveCommand(null);
    }

}
