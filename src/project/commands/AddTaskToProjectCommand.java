package project.commands;

import project.Manager;
import project.tasks.Project;
import project.tasks.Task;
import project.tasks.TaskUnit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddTaskToProjectCommand implements Command {
    private String projectName;
    private String description;
    private Date deadline;

    AddTaskToProjectCommand(String projectName, String description, Date deadline) {
        this.projectName = projectName;
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(Manager manager) {
        List<TaskUnit> list = manager.getTaskList().getTaskUnits();
        boolean mark = false;

        for (TaskUnit taskUnit : list) {
            if (((Project) taskUnit).getName().equals(this.projectName)) {
                long id = manager.getTaskCounter();
                manager.increaseTaskCounter();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date today = null;
                try {
                    today = formatter.parse(formatter.format(new Date()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                taskUnit.getTaskList().add(new Task(id, description, false, deadline, today, (Project) taskUnit));
                manager.getOut().println("Task created with ID " + id);
                mark = true;
            }
        }

        if (!mark) {
            new ErrorCommand("No project with such name").execute(manager);
        }
    }
}
