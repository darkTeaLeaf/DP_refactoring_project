package project.tasks;

public abstract class TaskUnit {
    private TaskList<Task> taskList = new TaskList<>();

    public TaskList<Task> getTaskList() {
        return taskList;
    }
}
