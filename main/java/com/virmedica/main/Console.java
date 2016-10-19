package com.virmedica.main;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Using my own Console class because System.console() does not work inside IDEs like IntelliJ
 */
public class Console {
    private static final String PROMPT = "VirMedica>%n";
    private static final String NO_INPUT_MESSAGE = "No input found";
    private static final String SPACE = " ";

    private static final Logger logger = Logger.getLogger(Console.class.getName());

    private static Console instance;
    private static boolean running;
    private final Scanner scanner;


    private Console() {
        instance = this;
        scanner = new Scanner(System.in);
    }

    public static Console factory() {
        if (null == instance) {
            instance = new Console();
        }
        return instance;
    }

    public static void shutdown() {
        running = false;
    }

    public void start() {
        running = true;
        while (running) {
            printOut(PROMPT);
            final String s = scanner.nextLine().trim();
            if (s.isEmpty()) {
                logger.warning(NO_INPUT_MESSAGE);
                continue;
            }

            final String[] inputs = s.split(SPACE);
            final Command command = Command.factory(inputs[0]);
            if (command == null) {
                continue;
            }
            final String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
            command.doWork(args);
        }
    }

    private void printOut(String s, Object... args) {
        System.out.printf(s, args);
        System.out.flush();
    }

    public void close() {
        scanner.close();
    }

}
