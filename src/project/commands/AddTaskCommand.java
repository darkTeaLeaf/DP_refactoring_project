package project.commands;


public class AddTaskCommand  implements Command{
    private String projectName;
    private String description;

    AddTaskCommand(String projectName, String description){
        this.projectName = projectName;
        this.description = description;
    }

    @Override
    public void execute() {

    }
}
