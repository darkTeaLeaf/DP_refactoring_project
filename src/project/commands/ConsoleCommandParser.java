package project.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ConsoleCommandParser {
    public Command parseCommand(String input) {
        String[] input_n = input.strip().replaceAll("\\s+", " ").split(" ");
        String[] sub = new String[0];
        if (input_n.length > 1) {
            sub = Arrays.copyOfRange(input_n, 1, input_n.length);
        }
        Command consoleCommand;

        try {
            Commands enumValue = Commands.valueOf(input_n[0].toUpperCase());
            switch (enumValue) {
                case ADD:
                    if (sub.length == 2 && sub[0].equals("project")) {
                        consoleCommand = new AddProjectCommand(sub[1]);
                    } else if (sub.length == 4 && sub[0].equals("task")) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date deadline = df.parse(sub[3]);
                            consoleCommand = new AddTaskToProjectCommand(sub[1], sub[2], deadline);
                        } catch (ParseException e) {
                            consoleCommand = new ErrorCommand("Invalid date format. Please, follow the pattern dd/mm/yyyy");
                        }
                    } else if (sub.length == 4 && sub[0].equals("subtask") && isNumeric(sub[1])) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date deadline = df.parse(sub[3]);
                            consoleCommand = new AddTaskToTaskCommand(Long.parseLong(sub[1]), sub[2], deadline);
                        } catch (ParseException e) {
                            consoleCommand = new ErrorCommand("Invalid date format. Please, follow the pattern dd/mm/yyyy");
                        }
                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + input_n[0]);
                    }
                    break;
                case DELETE:
                    if (sub.length == 1) {
                        if (isNumeric(sub[0])) {
                            consoleCommand = new DeleteTaskCommand(Long.parseLong(sub[0]));
                        } else {
                            consoleCommand = new DeleteProjectCommand(sub[0]);
                        }
                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + input_n[0]);
                    }
                    break;
                case QUIT:
                    consoleCommand = new QuitCommand();
                    break;
                case SHOW:
                    if (sub.length == 1 && isNumeric(sub[0])) {
                        consoleCommand = new ShowTaskCommand(Long.parseLong(sub[0]));
                    } else {
                        consoleCommand = new ShowCommand();
                    }
                    break;
                case DEADLINE:
                    if (sub.length == 2 && isNumeric(sub[0])) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date deadline = df.parse(sub[1]);
                            consoleCommand = new DeadlineCommand(Long.parseLong(sub[0]), deadline);
                        } catch (ParseException e) {
                            consoleCommand = new ErrorCommand("Invalid date format. Please, follow the pattern dd/mm/yyyy");
                        }

                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + input_n[0]);
                    }
                    break;
                case VIEW:
                    if (sub.length == 2 && sub[0].equals("date")) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date date = df.parse(sub[1]);
                            consoleCommand = new ViewByDateCommand(date);
                        } catch (ParseException e) {
                            consoleCommand = new ErrorCommand("Invalid date format. Please, follow the pattern dd/mm/yyyy");
                        }
                    } else if (sub.length == 2 && sub[0].equals("deadline")) {
                        if (sub[1].equals("today")) {
                            consoleCommand = new ViewByDeadlineCommand(new Date());
                        } else {
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date deadline = df.parse(sub[1]);
                                consoleCommand = new ViewByDeadlineCommand(deadline);
                            } catch (ParseException e) {
                                consoleCommand = new ErrorCommand("Invalid date format. Please, follow the pattern dd/mm/yyyy");
                            }
                        }
                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + input_n[0]);
                    }
                    break;
                case CHECK:
                    if (sub.length == 1 && isNumeric(sub[0])) {
                        consoleCommand = new CheckCommand(Long.parseLong(sub[0]));
                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + input_n[0]);
                    }
                    break;
                case UNCHECK:
                    if (sub.length == 1 && isNumeric(sub[0])) {
                        consoleCommand = new UncheckCommand(Long.parseLong(sub[0]));
                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + input_n[0]);
                    }
                    break;
                case HELP:
                    consoleCommand = new HelpCommand();
                    break;
                default:
                    consoleCommand = new UnknownCommand();
            }
        } catch (IllegalArgumentException e) {
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
