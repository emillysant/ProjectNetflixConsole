package org.example.ui.tui;

import org.example.App;
import org.example.repository.AccountRepository;

public class ConsoleApp {
    private final App app;

    private ConsoleAppScreen currentScreen = ConsoleAppScreen.STARTING;

    public ConsoleApp(App app) {
        this.app = app;
    }

    public App getApp() {
        return app;
    }

    public ConsoleAppScreen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(ConsoleAppScreen currentScreen) {
        this.currentScreen = currentScreen;
    }

    public void run() {
        while (currentScreen != null) {
            switch (currentScreen) {
                case STARTING -> startingScreen();
                case LOGIN -> loginScreen();
                case PROFILE_PICKER -> profilePickerScreen();
            }
        }
    }

    private void startingScreen() {
        System.out.println("1. Login");
        System.out.println("0. Quit");

        switch (ConsoleUtils.getChoice(1)) {
            case 1 -> setCurrentScreen(ConsoleAppScreen.LOGIN);
            case 0 -> setCurrentScreen(null);
        }
    }

    private void loginScreen() {
        for (; ; ) {
            var email = ConsoleUtils.getEntry("E-mail");
            var password = ConsoleUtils.getEntry("Password");

            var isLogged = app.login(email, password);
            if (isLogged) {
                setCurrentScreen(ConsoleAppScreen.PROFILE_PICKER);
                break;
            }

            System.out.println("Invalid email or password");
        }
    }

    private void profilePickerScreen() {
        System.out.println("profilePickerScreen");
        setCurrentScreen(null);
    }
}
