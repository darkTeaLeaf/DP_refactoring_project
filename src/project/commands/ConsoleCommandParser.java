package project.commands;

import java.util.Arrays;
import java.util.HashSet;

public class ConsoleCommandParser {
    private HashSet commands = new HashSet() {{
        add("add");
        add("delete");
        add("quit");
        add("show");
        add("deadline");
        add("view");
        add("check");
        add("uncheck");
    }};
    ConsoleCommand currentCommand = new ConsoleCommand(null, null);

    public void setCurrentCommand(ConsoleCommand currentCommand) {
        this.currentCommand = currentCommand;
    }

    public ConsoleCommand getCurrentCommand() {
        return currentCommand;
    }

    public boolean isValid(String command) {

        return commands.contains(command);
    }

    public ConsoleCommand parseCommand(String input) {
        String[] input_n = input.strip().replaceAll("\\s+", " ").split(" ");
        String[] sub = Arrays.copyOfRange(input_n, 1, (input_n.length - 1));
        ConsoleCommand consoleCommand = null;
        if (isValid(input_n[0])) {
            consoleCommand = new ConsoleCommand(input_n[0], sub);
        }
        return consoleCommand;
    }
}
