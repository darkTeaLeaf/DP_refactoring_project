package project.tasks;

import project.views.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list functionality
 * @param <G> represents the type of stored instances (can be Project or Task)
 */

public class TaskList<G extends TaskUnit> {
    private List<TaskUnit> taskUnits = new ArrayList<>();

    public List<TaskUnit> getTaskUnits() {
        return taskUnits;
    }

    public void add(G taskUnit){
        this.taskUnits.add(taskUnit);
    }

    public void remove(G taskUnit){
        this.taskUnits.remove(taskUnit);
    }

    public ArrayList<Task> view(View view){
        return view.getObjects(this.taskUnits);
    }
}
