package org.example.factory;

import org.example.App;
import org.example.ui.tui.ConsoleApp;

public class ConsoleAppFactory {
    public static void run(App app) {
        var consoleApp = new ConsoleApp(app);
        consoleApp.run();
    }
}
