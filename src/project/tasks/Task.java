package project.tasks;

import java.util.Date;

public class Task extends TaskUnit {
    private final long id;
    private final String description;
    private boolean done;
    private Date deadline;
    private Date creationDate;

    public Task(long id, String description, boolean done, Date deadline, Date creationDate) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadline = deadline;
        this.creationDate = creationDate;
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
}
