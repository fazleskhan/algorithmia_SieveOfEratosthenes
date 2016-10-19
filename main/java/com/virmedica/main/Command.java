package com.virmedica.main;

import com.virmedica.question1.Solution1;
import com.virmedica.question2.Solution2;
import com.virmedica.question4.Solution2a;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by Fazle Khan on 10/18/2016.
 */
public abstract class Command {

    private static final String INCORRECT_NUMBER_OF_ARGUMENTS_MESSAGE = "Incorrect number of arguments found.%n Expected betweem %s and %s %n Found %s %n %s ";
    private static final String UNRECOGNIZED_COMMAND_MESSAGE = "%nUnrecognized command %s %n";

    private static final HashMap<Action, Command> commands;

    private static final Logger logger = Logger.getLogger(Command.class.getName());

    static {
        commands = new HashMap<>(3);
        commands.put(Action.help, new HelpCommand());
        commands.put(Action.exit, new ExitCommand());
        commands.put(Action.solution1, new Solution1());
        commands.put(Action.solution2, new Solution2());
        commands.put(Action.solution2a, new Solution2a());
    }

    final private int minArgCount;
    final private int maxArgCount;
    final private String helpText;

    protected Command(int minArgCount, int maxArgCount, String helpText) {
        this.minArgCount = minArgCount;
        this.maxArgCount = maxArgCount;
        this.helpText = helpText;
    }

    public static Command factory(Action action) {
        return commands.get(action);
    }

    public static Command factory(String name) {
        Command.Action action = null;
        try {
            action = Command.Action.valueOf(name);
        } catch (IllegalArgumentException iae) {
            logger.warning(String.format(UNRECOGNIZED_COMMAND_MESSAGE, name));
        }
        return Command.factory(action);
    }

    public String[] doWork(final String[] args) {
        if (args.length < minArgCount || args.length > maxArgCount) {
            final String message = String.format(INCORRECT_NUMBER_OF_ARGUMENTS_MESSAGE,
                    minArgCount, maxArgCount, args.length, Arrays.toString(args));
            logger.warning(message);
            return new String[]{message};
        } else {
            return myWork(args);
        }

    }

    public String getHelp() {
        return helpText;
    }

    abstract protected String[] myWork(final String[] args);

    public enum Action {
        help,
        solution1,
        solution2,
        solution2a,
        exit
    }
}
