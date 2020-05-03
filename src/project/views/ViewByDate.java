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
        ArrayList<Task> result = new ArrayList<>();
        return filter(result, tasks);
    }

    private ArrayList<Task> filter(ArrayList<Task> result, List<TaskUnit> tasks) {
        for (TaskUnit task : tasks) {
            TaskList<Task> list = task.getTaskList();
            if (list != null) {
                filter(result, list.getTaskUnits());
            } else {
                if ((task instanceof Task) && (((Task) task).getCreationDate().equals(this.criterion))) {
                    result.add((Task) task);
                }
            }
        }
        return result;
    }
}
