package project.commands;

public class DeleteTaskCommand implements Command {
    private long id;

    DeleteTaskCommand(long id){
        this.id = id;
    }

    @Override
    public void execute() {

    }
}
