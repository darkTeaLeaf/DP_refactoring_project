package project.commands;

import java.util.Date;

public class ViewByDateCommand implements Command {
    private Date date;

    public ViewByDateCommand(Date date) {
        this.date = date;
    }

    @Override
    public void execute() {

    }
}
