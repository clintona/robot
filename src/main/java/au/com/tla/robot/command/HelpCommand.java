package au.com.tla.robot.command;

import java.io.PrintStream;

import au.com.tla.robot.Robot;

/**
 * Command to print a list of Commands to the given Printstream.
 * 
 * @author Clinton
 * 
 */
public class HelpCommand extends Command {
	
	private PrintStream out;
	
    public HelpCommand(Robot robot, PrintStream out) {
    	 super(robot);
         if (out == null) {
             throw new NullPointerException("PrintStream cannot be null");
         }
         this.out = out;
    }

    @Override
    public boolean execute() {
    	String crlf = System.lineSeparator(); // shorthand
        StringBuffer buf = new StringBuffer("Valid Commands are:").append(crlf);
        buf.append("HELP\tList this help text").append(crlf);
        buf.append("REPORT\tList the Robot's status").append(crlf);
        buf.append("PLACE\tPlace the robot at a grid location and heading ").append(crlf);
        buf.append("MOVE\tMove the robot forward one unit using its current heading").append(crlf);
        buf.append("RIGHT\tRotate the robot 90 degrees clockwise").append(crlf);
        buf.append("LEFT\tRotate the robot 90 degrees anticlockwise").append(crlf);
        buf.append(crlf);
        
        out.println(buf.toString());
        return !out.checkError();
    }

    public String toString() {
        return "HELP";
    }

}
