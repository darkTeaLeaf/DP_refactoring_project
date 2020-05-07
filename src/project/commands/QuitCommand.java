package project.commands;


import project.Manager;

/**
 * Command to quit from application
 */

public class QuitCommand implements Command {
    @Override
    public void execute(Manager manager) {
        System.exit(0);
    }
}
