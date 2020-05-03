package project;

import project.commands.ConsoleCommand;
import project.commands.ConsoleCommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Manager implements Runnable {
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private final PrintWriter out = new PrintWriter(System.out);
    ConsoleCommandParser consoleCommandParser = new ConsoleCommandParser();


    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ConsoleCommand consoleCommand = consoleCommandParser.parseCommand(command);
            defineTheCommand(consoleCommand);
        }
    }
    private void defineTheCommand(ConsoleCommand consoleCommand){
        String com = consoleCommand.getCommand();



    }
}
