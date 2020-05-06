package project.commands;

import project.Manager;
import project.tasks.Task;
import project.views.ViewById;

import java.util.ArrayList;

public class ShowTaskCommand implements Command {
    private long id;

    public ShowTaskCommand(long id) {
        this.id = id;
    }

    @Override
    public void execute(Manager manager) {
        ArrayList<Task> tasks = manager.getTaskList().view(new ViewById(this.id));

        if(!tasks.isEmpty()){
            manager.getOut().println("description: " + tasks.get(0).getDescription());
            manager.getOut().println("deadline: " + tasks.get(0).getDeadline());
            manager.getOut().println("creation date: " + tasks.get(0).getCreationDate());
            manager.getOut().println("checked: " + tasks.get(0).isDone());
            if(!tasks.get(0).isSubtask()) {
                manager.getOut().println("projects to which task is attached: " + tasks.get(0).getProjectList());
            }else {
                manager.getOut().println("task to which task is attached: " + tasks.get(0).getProjectList());
            }
        }else {
            new ErrorCommand("No task with such ID").execute(manager);
        }
    }
}
