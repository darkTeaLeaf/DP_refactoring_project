package project.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ConsoleCommandParser {
    public Command parseCommand(String input) {
        String[] input_n = input.strip().replaceAll("\\s+", " ").split(" ");
        String[] sub = Arrays.copyOfRange(input_n, 1, (input_n.length - 1));
        Command consoleCommand = null;

        Commands enumValue = Commands.valueOf(input_n[0].toUpperCase());
        switch (enumValue) {
            case ADD:
                if (sub.length == 2 && sub[0].equals("project")) {
                    consoleCommand = new AddProjectCommand(sub[1]);
                } else if (sub.length == 3 && sub[0].equals("task")) {
                    consoleCommand = new AddTaskCommand(sub[1], sub[2]);
                }else {
                    consoleCommand = new ErrorCommand();
                }
                break;
            case DELETE:
                if (sub.length == 1 && isNumeric(sub[0])) {
                    consoleCommand = new DeleteTaskCommand(Long.parseLong(sub[0]));
                }else {
                    consoleCommand = new ErrorCommand();
                }
                break;
            case QUIT:
                consoleCommand = new QuitCommand();
                break;
            case SHOW:
                consoleCommand = new ShowCommand();
                break;
            case DEADLINE:
                if (sub.length == 2 && isNumeric(sub[0])) {
                    DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
                    try {
                        Date deadline = df.parse(sub[1]);
                        consoleCommand = new DeadlineCommand(Long.parseLong(sub[0]), deadline);
                    } catch (ParseException e) {
                        consoleCommand = new ErrorCommand();
                    }
                }else {
                    consoleCommand = new ErrorCommand();
                }
                break;
            case VIEW:
                if (sub.length == 2 && sub[0].equals("date")) {
                    DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
                    try {
                        Date date = df.parse(sub[1]);
                        consoleCommand = new ViewByDateCommand(date);
                    } catch (ParseException e) {
                        consoleCommand = new ErrorCommand();
                    }
                } else if (sub.length == 2 && sub[0].equals("deadline")) {
                    DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
                    try {
                        Date deadline = df.parse(sub[1]);
                        consoleCommand = new ViewByDeadlineCommand(deadline);
                    } catch (ParseException e) {
                        consoleCommand = new ErrorCommand();
                    }
                }else {
                    consoleCommand = new ErrorCommand();
                }
                break;
            case CHECK:
                if (sub.length == 1 && isNumeric(sub[0])) {
                    consoleCommand = new CheckCommand(Long.parseLong(sub[0]));
                }else {
                    consoleCommand = new ErrorCommand();
                }
                break;
            case UNCHECK:
                if (sub.length == 1 && isNumeric(sub[0])) {
                    consoleCommand = new UncheckCommand(Long.parseLong(sub[0]));
                }else {
                    consoleCommand = new ErrorCommand();
                }
                break;
            case HELP:
                consoleCommand = new HelpCommand();
                break;
            default:
                consoleCommand = new UnknownCommand();
        }

        return consoleCommand;
    }

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
