package project.commands;

import project.Manager;

public class HelpCommand implements Command {
    @Override
    public void execute(Manager manager) {
        manager.getOut().println("Commands:");
        manager.getOut().println("  show");
        manager.getOut().println("  add project <project name>");
        manager.getOut().println("  add task <project name> <task description> <deadline>");
        manager.getOut().println("  add subtask <task ID> <task description> <deadline>");
        manager.getOut().println("  check <task ID>");
        manager.getOut().println("  uncheck <task ID>");
        manager.getOut().println("  deadline <task ID> <deadline>");
        manager.getOut().println("  view date <date>");
        manager.getOut().println("  view deadline <deadline>");
        manager.getOut().println("  view deadline today");
        manager.getOut().println("  delete <task ID>");
        manager.getOut().println("  delete <project name>");
        manager.getOut().println("  quit");
        manager.getOut().println();
    }
}
