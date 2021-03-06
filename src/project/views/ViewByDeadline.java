package project.views;

import project.tasks.Task;
import project.tasks.TaskList;
import project.tasks.TaskUnit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewByDeadline implements View {
    private Date criterion;

    public ViewByDeadline(Date deadline) {
        this.criterion = deadline;
    }

    @Override
    public ArrayList<Task> getObjects(List<TaskUnit> tasks) {
        ArrayList<Task> result = new ArrayList<>();
        return filter(result, tasks);
    }

    private ArrayList<Task> filter(ArrayList<Task> result, List<TaskUnit> tasks) {
        for (TaskUnit task : tasks) {
            TaskList<Task> list = task.getTaskList();
            if (!list.getTaskUnits().isEmpty()) {
                filter(result, list.getTaskUnits());
            }
            if ((task instanceof Task) && (((Task) task).getDeadline().compareTo(this.criterion) == 0)) {
                result.add((Task) task);
            }
        }
        return result;
    }
}
