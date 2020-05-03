package project.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList<G extends TaskUnit> {
    private List<G> taskUnits = new ArrayList<>();

    public void add(G taskUnit){
        this.taskUnits.add(taskUnit);
    }

    public void remove(G taskUnit){
        this.taskUnits.remove(taskUnit);
    }
}
