package project.commands;

import project.Manager;

/**
 * Main interface for commands
 */

public interface Command {
    void execute(Manager manager);
}
