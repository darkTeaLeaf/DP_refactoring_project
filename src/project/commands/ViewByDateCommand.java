package project.commands;

import project.Manager;
import project.tasks.Task;
import project.views.ViewByDate;

import java.util.ArrayList;
import java.util.Date;

public class ViewByDateCommand implements Command {
    private Date date;

    public ViewByDateCommand(Date date) {
        this.date = date;
    }

    @Override
    public void execute(Manager manager) {
        ArrayList<Task> tasks = manager.getTaskList().view(new ViewByDate(this.date));

        for (Task task : tasks) {
            manager.getOut().println(task.getDescription());
        }

        if(tasks.isEmpty()){
            new ErrorCommand("No tasks with such creation date").execute(manager);
        }
    }
}
