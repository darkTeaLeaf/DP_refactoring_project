package project;

import project.commands.Command;
import project.commands.ConsoleCommandParser;
import project.tasks.Project;
import project.tasks.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Organization of the whole application process
 *  - stating the main application thread
 *  - getting command from user and transfering it to parser
 *  - getting the instance of @code{Command} and execute it
 *  - collection the global for all application instances of input,
 *    output, parser, main task list and task counter
 */

public class Manager implements Runnable {
    private BufferedReader in;
    private PrintWriter out;
    private final ConsoleCommandParser consoleCommandParser = new ConsoleCommandParser();
    private TaskList<Project> taskList = new TaskList<>();
    private long taskCounter = 0;

    public Manager(){
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.out = new PrintWriter(System.out);
    }

    public Manager(BufferedReader in, PrintWriter out){
        this.in = in;
        this.out = out;
    }

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
            Command consoleCommand = consoleCommandParser.parseCommand(command);
            consoleCommand.execute(this);
        }
    }

    public PrintWriter getOut() {
        return out;
    }

    public TaskList<Project> getTaskList() {
        return taskList;
    }

    public long getTaskCounter() {
        return taskCounter;
    }

    public void increaseTaskCounter(){
        taskCounter++;
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.run();
    }
}
