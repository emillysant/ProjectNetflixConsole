package org.example.factory;

import org.example.App;
import org.example.ui.tui.ConsoleApp;

/**
 * This class is responsible for creating and running a console-based application.
 */
public class ConsoleAppFactory {
    /**
     * Runs a console-based application using the provided {@link App} instance.
     *
     * @param app The application instance to be used by the console application.
     *            This instance should be properly initialized and ready for use.
     */
    public static void run(App app) {
        var consoleApp = new ConsoleApp(app);
        consoleApp.run();
    }
}
