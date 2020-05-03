package project.commands;

import project.Manager;
import project.tasks.Task;
import project.views.ViewByDeadline;

import java.util.ArrayList;
import java.util.Date;

public class ViewByDeadlineCommand implements Command {
    private Date deadline;

    public ViewByDeadlineCommand(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(Manager manager) {
        ArrayList<Task> tasks = manager.getTaskList().view(new ViewByDeadline(this.deadline));

        for (Task task : tasks) {
            manager.getOut().println(task.getDescription());
        }

        if(tasks.isEmpty()){
            new ErrorCommand("No tasks with such deadline").execute(manager);
        }
    }
}
