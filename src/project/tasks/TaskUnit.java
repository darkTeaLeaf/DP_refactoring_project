package project.tasks;

/**
 * Abstract unit to unify concepts of project
 * and task and represent common features of
 * both
 */

public abstract class TaskUnit {
    private TaskList<Task> taskList = new TaskList<>();

    public TaskList<Task> getTaskList() {
        return taskList;
    }
}
