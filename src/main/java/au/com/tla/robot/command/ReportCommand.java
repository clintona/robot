package au.com.tla.robot.command;

import java.io.PrintStream;

import au.com.tla.robot.Robot;

/**
 * A Command to print the Robot's status to stdout.
 * 
 * @author Clinton
 *
 */
public class ReportCommand extends Command {

    private PrintStream out;

    public ReportCommand(Robot robot, PrintStream out) {
        super(robot);
        if (out == null) {
            throw new NullPointerException("PrintStream cannot be null");
        }
        this.out = out;
    }

    @Override
    public boolean execute() {
        out.println(this.robot);
        return !out.checkError();
    }

    public String toString() {
        return "REPORT";
    }

}
