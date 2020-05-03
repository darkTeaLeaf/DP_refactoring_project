package project.commands;


import project.Manager;

public class UnknownCommand implements Command {
    @Override
    public void execute(Manager manager) {
        manager.getOut().printf("I don't know what the command");
        manager.getOut().println();
    }
}
