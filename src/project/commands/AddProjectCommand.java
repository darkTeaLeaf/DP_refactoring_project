package project.commands;


import project.Manager;
import project.tasks.Project;

/**
 * Command to add new project in the ain list
 */

public class AddProjectCommand implements Command {
    private String projectName;

    AddProjectCommand(String projectName){
        this.projectName = projectName;
    }

    @Override
    public void execute(Manager manager) {
        manager.getTaskList().add(new Project(this.projectName));
    }
}
