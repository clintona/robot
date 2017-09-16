package au.com.tla.robot.command;

import au.com.tla.robot.GridPosition;
import au.com.tla.robot.Heading;
import au.com.tla.robot.Robot;

/**
 * Command to move the Robot forward one unit on the Grid following its current Heading.
 * 
 * @author Clinton
 * 
 */
public class MoveCommand extends Command {

    public MoveCommand(Robot robot) {
        super(robot);
    }

    @Override
    public boolean execute() {
        Heading heading = robot.getHeading();
        GridPosition gridPosition = robot.getGridPosition();
        return gridPosition.moveForward(heading);
    }

    public String toString() {
        return "MOVE";
    }

}
