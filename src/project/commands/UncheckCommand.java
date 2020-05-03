package project.commands;

import project.Manager;
import project.tasks.Task;
import project.views.ViewById;

import java.util.ArrayList;

public class UncheckCommand implements Command {
    private long id;

    public UncheckCommand(long id) {
        this.id = id;
    }

    @Override
    public void execute(Manager manager) {
        ArrayList<Task> tasks = manager.getTaskList().view(new ViewById(this.id));

        if(!tasks.isEmpty()){
            tasks.get(0).setDone(false);
        }else {
            new ErrorCommand("No task with such ID").execute(manager);
        }
    }
}
