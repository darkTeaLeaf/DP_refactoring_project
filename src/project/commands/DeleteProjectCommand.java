package project.commands;

import project.Manager;
import project.tasks.Project;
import project.tasks.TaskUnit;

import java.util.List;

/**
 * Command to delete project with all
 * tasks attached to it (except whose
 * attached to another projects)
 */

public class DeleteProjectCommand implements Command {
    private String projectName;

    DeleteProjectCommand(String projectName){
        this.projectName = projectName;
    }

    @Override
    public void execute(Manager manager) {
        List<TaskUnit> tasks = manager.getTaskList().getTaskUnits();
        boolean mark = false;
        for (TaskUnit taskUnit : tasks) {
            if(((Project)taskUnit).getName().equals(this.projectName)){
                manager.getTaskList().remove((Project) taskUnit);
                mark = true;
            }
        }

        if (!mark) {
            new ErrorCommand("No project with such name").execute(manager);
        }
    }
}
