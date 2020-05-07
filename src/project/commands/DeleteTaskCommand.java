package project.commands;

import project.Manager;
import project.tasks.Task;
import project.tasks.TaskList;
import project.tasks.TaskUnit;

import java.util.List;

/**
 * Command to delete concrete task with all its subtasks
 */

public class DeleteTaskCommand implements Command {
    private long id;

    DeleteTaskCommand(long id) {
        this.id = id;
    }

    @Override
    public void execute(Manager manager) {
        List<TaskUnit> tasks = manager.getTaskList().getTaskUnits();
        Task task = null;
        for (TaskUnit taskUnit : tasks) {
            task = remove(taskUnit.getTaskList());
            if(task != null){
                break;
            }
        }

        if (task == null) {
            new ErrorCommand("No task with such ID").execute(manager);
        }
    }

    private Task remove(TaskList<Task> taskList) {
        List<TaskUnit> tasks = taskList.getTaskUnits();
        for (TaskUnit task : tasks) {
            TaskList<Task> list = task.getTaskList();
            if (!list.getTaskUnits().isEmpty()) {
                return remove(list);
            }

            if ((task instanceof Task) && (((Task) task).getId() == this.id)) {
                taskList.remove((Task) task);
                return (Task) task;
            }
        }

        return null;
    }
}
