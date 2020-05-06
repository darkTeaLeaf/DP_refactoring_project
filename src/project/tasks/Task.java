package project.tasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task extends TaskUnit {
    private final long id;
    private final String description;
    private boolean done;
    private Date deadline;
    private Date creationDate;
    private boolean isSubtask;
    private List<TaskUnit> projectList;

    public Task(long id, String description, boolean done, Date deadline, Date creationDate, Task task) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadline = deadline;
        this.creationDate = creationDate;
        this.isSubtask = true;

        this.projectList = new ArrayList<>();
        this.projectList.add(task);
    }

    public Task(long id, String description, boolean done, Date deadline, Date creationDate, Project project) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadline = deadline;
        this.creationDate = creationDate;
        this.isSubtask = false;

        this.projectList = new ArrayList<>();
        this.projectList.add(project);
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isSubtask() {
        return isSubtask;
    }

    public List<TaskUnit> getProjectList() {
        return projectList;
    }

    @Override
    public String toString() {
        return description;
    }
}
