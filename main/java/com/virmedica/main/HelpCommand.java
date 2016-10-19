package com.virmedica.main;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Shows the user the help docs
 */
public class HelpCommand extends Command {

    private static final String HELP_TEXT = "Help displays each command usage with the pattern \"help <command>\".";
    private static final String COMMAND_NOT_FOUND_TEXT = "Command was not found: %s";

    private static final Logger logger = Logger.getLogger(HelpCommand.class.getName());

    HelpCommand() {
        super(0, 1, HELP_TEXT);
    }

    @Override
    protected String[] myWork(final String[] args) {
        final ArrayList<String> messages = new ArrayList<>();
        if (args.length == 0) {
            logInfo(this.getHelp(), messages);
        } else {
            final Command command = Command.factory(args[0]);
            if (null == command) {
                logWarning(String.format(COMMAND_NOT_FOUND_TEXT, args[0]), messages);
            } else {
                logInfo(command.getHelp(), messages);
            }
        }
        return messages.toArray(new String[0]);
    }

    private void logInfo(final String message, final ArrayList<String> messages) {
        logger.info(message);
        messages.add(message);
    }

    private void logWarning(final String message, final ArrayList<String> messages) {
        logger.warning(message);
        messages.add(message);
    }

}

