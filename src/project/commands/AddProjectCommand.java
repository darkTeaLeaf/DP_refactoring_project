package project.commands;


public class AddProjectCommand implements Command {
    private String projectName;

    AddProjectCommand(String projectName){
        this.projectName = projectName;
    }

    @Override
    public void execute() {

    }
}
