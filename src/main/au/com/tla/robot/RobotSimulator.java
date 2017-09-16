package au.com.tla.robot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.tla.robot.command.Command;
import au.com.tla.robot.command.CommandFactory;

/**
 * Creates a Robot instance and executes instructions on that instance using Commands from stdin.
 * 
 * @author Clinton
 * 
 */
public class RobotSimulator {
    public static final int GRID_SIZE = 5;

    private Robot robot;
    private CommandFactory factory;

    RobotSimulator(int gridSize) {
        this.robot = new Robot(new Grid(gridSize));
        this.factory = new CommandFactory(this.robot);
    }

    public Robot getRobot() {
        return this.robot;
    }

    public void setCommandFactory(CommandFactory factory) {
        this.factory = factory;
    }

    List<Command> readCommandsFromStream(InputStream inp) throws IOException {
        List<Command> commands = new ArrayList<Command>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inp));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                commands.add(factory.makeCommand(line));
            }
        } finally {
            reader.close();
        }
        return commands;
    }

    void execute(List<Command> commands) {
        for (Command cmd : commands) {
            System.out.println(cmd);
            robot.execute(cmd);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Initialising Robot with Grid size " + GRID_SIZE + "...");
        RobotSimulator simulator = new RobotSimulator(GRID_SIZE);

        System.out.println("Reading commands (blank line to end)...");
        List<Command> commands = simulator.readCommandsFromStream(getInputStream(args));

        System.out.println("Executing " + commands.size() + " commands...");
        simulator.execute(commands);

        System.out.println("Shutting down. Goodbye.");
    }

    /**
     * This method is used for environments like the Eclipse IDE, where there is no way to redirect
     * stdin from a file, eg: program < file.txt.
     * Instead, use program -f file.txt
     * 
     * @param args
     * @return InputStream
     * @throws FileNotFoundException
     */
    public static InputStream getInputStream(String[] args) throws FileNotFoundException {
        if (args.length > 0 && args[0].equals("-f")) {
            return new FileInputStream(new File(args[1]));
        } else {
            return System.in;
        }
    }

}
