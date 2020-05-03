package project.commands;


import project.Manager;

public class QuitCommand implements Command {
    @Override
    public void execute(Manager manager) {
        System.exit(0);
    }
}
