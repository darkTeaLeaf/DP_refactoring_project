package project.commands;

import project.Manager;
import project.tasks.Project;
import project.tasks.Task;
import project.tasks.TaskUnit;
import project.views.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Command to attach existing task to project,
 * so it can appear in a few projects
 */

public class AttachTaskToProjectCommand implements Command {
    private long id;
    private String projectName;

    public AttachTaskToProjectCommand(long id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

    @Override
    public void execute(Manager manager) {
        ArrayList<Task> tasks = manager.getTaskList().view(new ViewById(this.id));

        if (tasks.isEmpty()) {
            new ErrorCommand("No task with such ID").execute(manager);
        } else {
            boolean mark = false;
            if (!tasks.get(0).isSubtask()) {
                List<TaskUnit> list = manager.getTaskList().getTaskUnits();
                List<TaskUnit> projects = tasks.get(0).getProjectList();
                System.out.println(this.projectName);

                for (TaskUnit taskUnit : list) {
                    if (((Project) taskUnit).getName().equals(this.projectName)) {
                        if (!projects.contains(taskUnit)) {
                            tasks.get(0).getProjectList().add(taskUnit);
                            mark = true;
                        } else {
                            new ErrorCommand("Task is already attached to this project").execute(manager);
                        }
                        break;
                    }
                }

                if (!mark) {
                    new ErrorCommand("No project with such name").execute(manager);
                }

            } else {
                new ErrorCommand("Task is attached to another task and cannot be attached to project").execute(manager);
            }
        }
    }
}
