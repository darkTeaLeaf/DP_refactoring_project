package project.commands;

import project.Manager;
public interface Command {
    void execute(Manager manager);
}
