package project.views;

import project.tasks.Task;
import project.tasks.TaskUnit;

import java.util.ArrayList;
import java.util.List;

public interface View {
    public ArrayList<Task> getObjects(List<TaskUnit> tasks);
}
