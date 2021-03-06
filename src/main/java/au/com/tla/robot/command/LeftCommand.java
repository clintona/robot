package au.com.tla.robot.command;

import au.com.tla.robot.Heading;
import au.com.tla.robot.Robot;

/**
 * Command to rotate the Robot left 90 degrees.
 * 
 * @author Clinton
 * 
 */
public class LeftCommand extends Command {

    public LeftCommand(Robot robot) {
        super(robot);
    }

    @Override
    public boolean execute() {
        Heading currentHeading = robot.getHeading();
        return robot.setHeading(currentHeading != null ? currentHeading.turnLeft90() : null);
    }

    public String toString() {
        return "LEFT";
    }

}
