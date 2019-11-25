package au.com.tla.robot.command;

import java.io.PrintStream;

import au.com.tla.robot.Heading;
import au.com.tla.robot.Position;
import au.com.tla.robot.Robot;

/**
 * A simple Factory object to create Command instances from Strings.
 * 
 * @author Clinton
 * 
 */
public class CommandFactory {

    private Robot robot;

    public CommandFactory(Robot robot) {
        if (robot == null) {
            throw new NullPointerException("Robot cannot be null");
        }
        this.robot = robot;
    }

    public Command makeCommand(String line) {
        if (line == null) {
            throw new NullPointerException("Command line cannot be null");
        }
        Command cmd = parse(line.split("[ ,]"));
        if (cmd == null) {
            throw new IllegalArgumentException("Invalid command line '" + line + "'");
        }
        return cmd;
    }

    protected Command parse(String[] tokens) {
        Command cmd = null;
        switch (tokens[0]) {
        case "PLACE":
            cmd = makePlaceCommand(tokens);
            break;
        case "MOVE":
            cmd = new MoveCommand(robot);
            break;
        case "LEFT":
            cmd = new LeftCommand(robot);
            break;
        case "RIGHT":
            cmd = new RightCommand(robot);
            break;
        case "REPORT":
            cmd = new ReportCommand(robot, new PrintStream(System.out));
            break;
        case "HELP":
            cmd = new HelpCommand(robot, new PrintStream(System.out));
            break;
        }
        return cmd;
    }

    protected PlaceCommand makePlaceCommand(String[] tokens) {
        if (tokens == null || tokens.length != 4) {
            throw new IllegalArgumentException("Missing (x,y,Heading) parameters for PlaceCommand");
        }
        Position xy = makePosition(tokens[1], tokens[2]);
        Heading facing = Heading.valueOf(tokens[3]);
        return new PlaceCommand(robot, xy, facing);
    }

    protected Position makePosition(String xStr, String yStr) {
        int x = Integer.parseInt(xStr);
        int y = Integer.parseInt(yStr);
        return new Position(x, y);
    }

}
