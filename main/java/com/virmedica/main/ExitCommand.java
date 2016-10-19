package com.virmedica.main;

public class ExitCommand extends Command {

    private static final String HELP_TEXT = "Command closes the application";

    ExitCommand() {
        super(0, 0, HELP_TEXT);
    }

    @Override
    protected String[] myWork(String[] args) {
        Console.shutdown();
        return new String[0];
    }
}
