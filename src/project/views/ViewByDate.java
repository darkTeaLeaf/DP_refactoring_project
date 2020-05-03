package project.views;

import project.tasks.Project;
import project.tasks.Task;
import project.tasks.TaskList;
import project.tasks.TaskUnit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewByDate implements View {
    private Date criterion;

    public ViewByDate(Date creationDate) {
        this.criterion = creationDate;
    }

    @Override
    public ArrayList<Task> getObjects(List<TaskUnit> tasks) {
        return null;
    }
}
