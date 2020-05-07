package project.commands;

import project.Manager;

/**
 * Command to display an error
 */

public class ErrorCommand implements Command{
    private String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Manager manager) {
        manager.getOut().printf(this.message);
        manager.getOut().println();
    }
}
