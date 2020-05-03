package project.commands;

public class ConsoleCommand {

        String command;
        String[] parameters;

        public ConsoleCommand(String command, String[] parameters) {
            this.command = command;
            this.parameters = parameters;
        }

        public String getCommand() {
            return command;
        }

        public String[] getParameters() {
            return parameters;
        }
    }