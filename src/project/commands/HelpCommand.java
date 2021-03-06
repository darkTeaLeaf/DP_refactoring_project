package project.commands;

import project.Manager;

/**
 * Command to display helpful information
 */

public class HelpCommand implements Command {
    @Override
    public void execute(Manager manager) {
        manager.getOut().println("Commands:");
        manager.getOut().println("  show");
        manager.getOut().println("  add project \"<project name>\"");
        manager.getOut().println("  add task \"<project name>\" \"<task description>\" <deadline>");
        manager.getOut().println("  add subtask <task ID> \"<task description>\" <deadline>");
        manager.getOut().println("  check <task ID>");
        manager.getOut().println("  uncheck <task ID>");
        manager.getOut().println("  deadline <task ID> <deadline>");
        manager.getOut().println("  view date <date>");
        manager.getOut().println("  view deadline <deadline>");
        manager.getOut().println("  view deadline today");
        manager.getOut().println("  delete <task ID>");
        manager.getOut().println("  delete \"<project name>\"");
        manager.getOut().println("  attach \"<project name>\" \"<task ID>\"");
        manager.getOut().println("  detach \"<project name>\" \"<task ID>\"");
        manager.getOut().println("  quit");
        manager.getOut().println("Pattern for entering dates: dd/mm/yyyy");
        manager.getOut().println();
    }
}
