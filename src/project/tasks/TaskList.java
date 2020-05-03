package project.tasks;

import project.views.View;

import java.util.ArrayList;
import java.util.List;

public class TaskList<G extends TaskUnit> {
    private List<TaskUnit> taskUnits = new ArrayList<>();

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
