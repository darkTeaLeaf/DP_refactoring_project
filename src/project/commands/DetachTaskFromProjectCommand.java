package project.commands;

import project.Manager;
import project.tasks.Project;
import project.tasks.Task;
import project.tasks.TaskUnit;
import project.views.ViewById;

import java.util.ArrayList;
import java.util.List;

public class DetachTaskFromProjectCommand implements Command {
    private long id;
    private String projectName;

    public DetachTaskFromProjectCommand(long id, String projectName) {
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
            Task task = tasks.get(0);
            if (!task.isSubtask()) {
                if(task.getProjectList().size() > 1) {
                    List<TaskUnit> list = task.getProjectList();

                    for (int i = 0; i < list.size(); i++) {
                        if (((Project) list.get(i)).getName().equals(this.projectName)) {
                            list.remove(list.get(i));
                            mark = true;
                            break;
                        }
                    }

                    if (!mark) {
                        new ErrorCommand("Task is not attached to project with such name").execute(manager);
                    }
                }else {
                    new ErrorCommand("Cannot detach task. Task should be attached to at least one project").execute(manager);
                }

            } else {
                new ErrorCommand("Cannot detach task. Task is attached to another task").execute(manager);
            }
        }
    }
}
