package au.com.tla.robot.command;

import au.com.tla.robot.GridPosition;
import au.com.tla.robot.Heading;
import au.com.tla.robot.Position;
import au.com.tla.robot.Robot;

/**
 * Command to place the Robot on a Grid position and Heading.
 * 
 * @author Clinton
 * 
 */
public class PlaceCommand extends Command {
    private Position position;
    private Heading facing;

    public PlaceCommand(Robot robot, Position xy, Heading compassDirection) {
        super(robot);
        this.position = xy;
        this.facing = compassDirection;
    }

    @Override
    public boolean execute() {
        GridPosition gridPosition = robot.getGridPosition();
        if (gridPosition.isValidPosition(position) && facing != null) {
            return gridPosition.setPosition(position) && robot.setHeading(facing);
        }
        return false;
    }

    public String toString() {
        return "PLACE " + position.x + "," + position.y + "," + facing;
    }

}
