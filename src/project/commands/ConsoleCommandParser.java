package project.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleCommandParser {
    public Command parseCommand(String input) {
        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(input);
        while (m.find()) {
            list.add(m.group(1).replace("\"", ""));
        }
        List<String> sub = new ArrayList<>();
        if (list.size() > 1) {
            sub = list.subList(1, list.size());
        }
        Command consoleCommand;

        try {
            Commands enumValue = Commands.valueOf(list.get(0).toUpperCase());
            switch (enumValue) {
                case ADD:
                    if (sub.size() == 2 && sub.get(0).equals("project")) {
                        consoleCommand = new AddProjectCommand(sub.get(1));
                    } else if (sub.size() == 4 && sub.get(0).equals("task")) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date deadline = df.parse(sub.get(3));
                            consoleCommand = new AddTaskToProjectCommand(sub.get(1), sub.get(2), deadline);
                        } catch (ParseException e) {
                            consoleCommand = new ErrorCommand("Invalid date format. Please, follow the pattern dd/mm/yyyy");
                        }
                    } else if (sub.size() == 4 && sub.get(0).equals("subtask") && isNumeric(sub.get(1))) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date deadline = df.parse(sub.get(3));
                            consoleCommand = new AddTaskToTaskCommand(Long.parseLong(sub.get(1)), sub.get(2), deadline);
                        } catch (ParseException e) {
                            consoleCommand = new ErrorCommand("Invalid date format. Please, follow the pattern dd/mm/yyyy");
                        }
                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + list.get(0));
                    }
                    break;
                case DELETE:
                    if (sub.size() == 1) {
                        if (isNumeric(sub.get(0))) {
                            consoleCommand = new DeleteTaskCommand(Long.parseLong(sub.get(0)));
                        } else {
                            consoleCommand = new DeleteProjectCommand(sub.get(0));
                        }
                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + list.get(0));
                    }
                    break;
                case QUIT:
                    consoleCommand = new QuitCommand();
                    break;
                case SHOW:
                    if (sub.size() == 1 && isNumeric(sub.get(0))) {
                        consoleCommand = new ShowTaskCommand(Long.parseLong(sub.get(0)));
                    } else {
                        consoleCommand = new ShowCommand();
                    }
                    break;
                case DEADLINE:
                    if (sub.size() == 2 && isNumeric(sub.get(0))) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date deadline = df.parse(sub.get(1));
                            consoleCommand = new DeadlineCommand(Long.parseLong(sub.get(0)), deadline);
                        } catch (ParseException e) {
                            consoleCommand = new ErrorCommand("Invalid date format. Please, follow the pattern dd/mm/yyyy");
                        }

                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + list.get(0));
                    }
                    break;
                case VIEW:
                    if (sub.size() == 2 && sub.get(0).equals("date")) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date date = df.parse(sub.get(1));
                            consoleCommand = new ViewByDateCommand(date);
                        } catch (ParseException e) {
                            consoleCommand = new ErrorCommand("Invalid date format. Please, follow the pattern dd/mm/yyyy");
                        }
                    } else if (sub.size() == 2 && sub.get(0).equals("deadline")) {
                        if (sub.get(1).equals("today")) {
                            consoleCommand = new ViewByDeadlineCommand(new Date());
                        } else {
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date deadline = df.parse(sub.get(1));
                                consoleCommand = new ViewByDeadlineCommand(deadline);
                            } catch (ParseException e) {
                                consoleCommand = new ErrorCommand("Invalid date format. Please, follow the pattern dd/mm/yyyy");
                            }
                        }
                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + list.get(0));
                    }
                    break;
                case CHECK:
                    if (sub.size() == 1 && isNumeric(sub.get(0))) {
                        consoleCommand = new CheckCommand(Long.parseLong(sub.get(0)));
                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + list.get(0));
                    }
                    break;
                case UNCHECK:
                    if (sub.size() == 1 && isNumeric(sub.get(0))) {
                        consoleCommand = new UncheckCommand(Long.parseLong(sub.get(0)));
                    } else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + list.get(0));
                    }
                    break;
                case HELP:
                    consoleCommand = new HelpCommand();
                    break;
                case ATTACH:
                    if (sub.size() == 2 && isNumeric(sub.get(1))){
                        consoleCommand = new AttachTaskToProjectCommand(Long.parseLong(sub.get(1)), sub.get(0));
                    }else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + list.get(0));
                    }
                    break;
                case DETACH:
                    if (sub.size() == 2 && isNumeric(sub.get(1))){
                        consoleCommand = new DetachTaskFromProjectCommand(Long.parseLong(sub.get(1)), sub.get(0));
                    }else {
                        consoleCommand = new ErrorCommand("Invalid arguments for command " + list.get(0));
                    }
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
