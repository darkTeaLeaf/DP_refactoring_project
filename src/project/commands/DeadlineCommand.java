package project.commands;

import java.util.Date;

public class DeadlineCommand implements Command {
    private long id;
    private Date deadline;

    DeadlineCommand(long id, Date deadline){
        this.id = id;
        this.deadline = deadline;
    }

    @Override
    public void execute() {

    }
}
