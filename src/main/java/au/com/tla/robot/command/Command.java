package au.com.tla.robot.command;

import au.com.tla.robot.Robot;

/**
 * Robot commands. Borrows from the GoF Command pattern.
 * 
 * @author Clinton
 * 
 */
public abstract class Command {

    protected Robot robot;

    public Command(Robot robot) {
        if (robot == null) {
            throw new NullPointerException("Robot instance cannot be null");
        }
        this.robot = robot;
    }

    /**
     * @return the target Robot instance used to execute this Command
     */
    public Robot getRobot() {
        return this.robot;
    }

    /**
     * @return true if the command was successfully executed, else false.
     */
    public abstract boolean execute();

    /**
     * @return a formatted String representation of this Command
     */
    public abstract String toString();
}
