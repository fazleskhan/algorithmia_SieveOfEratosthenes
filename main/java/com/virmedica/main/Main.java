package com.virmedica.main;

import java.util.logging.Logger;

/**
 * Main entry point for questions asked of VirMedica.
 * Basic usage is when app is started the user is presented with a command prompt
 * The user can then type on of 4 commands with 1 or two inputs
 * inputs are validated and the target solution is run
 */
public class Main {

    private static final String COMMAND_LINE_ARGS_NOT_SUPPORTED_MESSAGE
            = "Commandline arguments are not supported. Please enter commands in the console prompt. Type Help on the console prompt for more information";
    private static final String UNHANDLED_EXCEPTION_MESSAGE = "Unhandled Exception";

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private Main() {
        //default constructor
    }

    public static void main(final String[] args) {
        logger.info("Starting main");
        if (args.length != 0) {
            System.out.println(COMMAND_LINE_ARGS_NOT_SUPPORTED_MESSAGE);
        }
        Console console = null;
        try {
            console = Console.factory();
            console.start();
        } catch (Throwable t) {
            System.err.println(UNHANDLED_EXCEPTION_MESSAGE);
            System.err.println(t.getMessage());
            t.printStackTrace(System.err);
            System.exit(1);
        } finally {
            if (null != console) {
                console.close();
            }
        }
    }
}
