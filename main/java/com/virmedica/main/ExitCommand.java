package com.virmedica.main;

import java.util.logging.Logger;

/**
 * Created by Fazle Khan on 10/18/2016.
 */
public class ExitCommand extends Command {

    private static final String HELP_TEXT = "Command closes the application";

    private static final Logger logger = Logger.getLogger(ExitCommand.class.getName());

    protected ExitCommand() {
        super(0, 0, HELP_TEXT);
    }

    @Override
    protected String[] myWork(String[] args) {
        Console.shutdown();
        return new String[0];
    }
}
