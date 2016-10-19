package com.virmedica.question3;

import com.virmedica.main.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Add a GUI to display the results as they are produced in #1 or #2.
 * <p>
 * This GUI may be a Java Application or it may be a Web Application.
 */
public class Solution3 extends HttpServlet {

    private static final String NO_INPUT_MESSAGE = "<H1>No input found<H1>";
    private static final String COMMAND_KEY = "command";
    private static final String INPUT_ECHO_MESSAGE = "The supplied input was: %s";
    private static final String SPACE = " ";
    private static final String COMMAND_NOT_FOUND_MESSAGE = "Command not found: %s";

    public void init() {
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        final String input = request.getParameter(COMMAND_KEY);
        out.println("<html><header><link rel=\"stylesheet\" type=\"text/css\" href=\"virmedica.css\" media=\"screen\" /></header><body>");
        if (input == null || input.isEmpty()) {
            out.println(NO_INPUT_MESSAGE);
        } else {
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>");
            out.println(String.format(INPUT_ECHO_MESSAGE, input));
            out.println("</td>");
            out.println("</tr>");
            final String[] inputs = input.split(SPACE);
            final Command command = Command.factory(inputs[0]);
            if (command == null) {
                out.println("<tr>");
                out.println("<td>");
                out.println(String.format(COMMAND_NOT_FOUND_MESSAGE, inputs[0]));
                out.println("</td>");
                out.println("</tr>");
            } else {
                final String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
                final String[] messages = command.doWork(args);
                for (String message : messages) {
                    out.println("<tr>");
                    out.println("<td>");
                    out.println(message);
                    out.println("</td>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
        }
        out.println("<button onclick=\"window.location.href='/'\">Back</button>");
        out.println("</body></html>");

    }
}
