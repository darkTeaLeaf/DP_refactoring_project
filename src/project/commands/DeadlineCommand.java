package project.commands;

import project.Manager;
import project.tasks.Task;
import project.views.ViewById;

import java.util.ArrayList;
import java.util.Date;

/**
 * Command to set new deadline to concrete task
 */

public class DeadlineCommand implements Command {
    private long id;
    private Date deadline;

    DeadlineCommand(long id, Date deadline){
        this.id = id;
        this.deadline = deadline;
    }

    @Override
    public void execute(Manager manager) {
        ArrayList<Task> tasks = manager.getTaskList().view(new ViewById(this.id));

        if(!tasks.isEmpty()){
            tasks.get(0).setDeadline(this.deadline);
        }else {
            new ErrorCommand("No task with such ID").execute(manager);
        }
    }
}
