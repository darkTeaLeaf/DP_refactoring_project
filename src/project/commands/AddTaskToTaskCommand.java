package project.commands;

import project.Manager;
import project.tasks.Task;
import project.views.ViewById;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddTaskToTaskCommand implements Command {
    private long id;
    private String description;
    private Date deadline;

    AddTaskToTaskCommand(long id, String description, Date deadline) {
        this.id = id;
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(Manager manager) {
        ArrayList<Task> tasks = manager.getTaskList().view(new ViewById(this.id));

        if(!tasks.isEmpty()){
            long id = manager.getTaskCounter();
            manager.increaseTaskCounter();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date today = null;
            try {
                today = formatter.parse(formatter.format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            tasks.get(0).getTaskList().add(new Task(id, description, false, deadline, today, tasks.get(0)));
            manager.getOut().println("Task created with ID " + id);
        }else {
            new ErrorCommand("No task with such ID").execute(manager);
        }
    }
}
