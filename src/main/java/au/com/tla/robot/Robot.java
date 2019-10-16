package au.com.tla.robot;

import au.com.tla.robot.command.Command;

/**
 * A class to model a toy robot, that can move around a Grid and respond to basic Commands.
 * 
 * Joke for Asimov fans: could have an interface called IRobot ;-)
 * 
 * @author Clinton
 * 
 */
public class Robot {
    private GridPosition gridPosition;
    private Heading heading;
    private String name;

    public Robot(Grid grid) {
        this.gridPosition = new GridPosition(grid);
    }

    public boolean setHeading(Heading compassPoint) {
        if (gridPosition.isPlaced() && compassPoint != null) {
            this.heading = compassPoint;
            return true;
        }
        return false;
    }

    public Heading getHeading() {
        return this.heading;
    }

    public GridPosition getGridPosition() {
        return this.gridPosition;
    }

    public boolean execute(Command command) {
        return command.execute();
    }

    @Override
    public String toString() {
        if (gridPosition.isPlaced()) {
            Position pos = gridPosition.getPosition();
            return String.format("%d,%d,%s", pos.x, pos.y, heading);
        } else {
            return "NOT PLACED";
        }
    }
}
