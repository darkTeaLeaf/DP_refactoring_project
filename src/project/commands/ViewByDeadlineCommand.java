package project.commands;

import java.util.Date;

public class ViewByDeadlineCommand implements Command {
    private Date deadline;

    public ViewByDeadlineCommand(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute() {

    }
}
