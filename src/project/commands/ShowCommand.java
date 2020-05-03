package project.commands;

import project.Manager;
import project.tasks.Project;
import project.tasks.Task;
import project.tasks.TaskList;
import project.tasks.TaskUnit;

import java.util.List;

public class ShowCommand implements Command{
    @Override
    public void execute(Manager manager) {
        List<TaskUnit> list = manager.getTaskList().getTaskUnits();
        for (TaskUnit taskUnit : list) {
            manager.getOut().println(((Project) taskUnit).getName());
            print(manager, taskUnit.getTaskList().getTaskUnits(), "  ");
        }
    }

    private void print(Manager manager, List<TaskUnit> tasks, String spaces) {
        for (TaskUnit task : tasks) {
            TaskList<Task> list = task.getTaskList();
            manager.getOut().println(spaces + ((Task) task).getDescription());
            if (list != null) {
                print(manager, list.getTaskUnits(), spaces + "  ");
            }
        }
    }
}
