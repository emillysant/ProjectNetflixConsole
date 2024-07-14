package org.example.ui.tui;

import org.example.App;

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
                case REGISTER -> registerScreen();
                case PROFILE_PICKER -> profilePickerScreen();
                case MAIN_MENU -> mainMenuScreen();
            }
        }
    }

    private void startingScreen() {
        System.out.println("1. Login");
        System.out.println("2. Cadastrar");
        System.out.println("0. Quit");

        switch (ConsoleUtils.getChoice(2)) {
            case 1 -> setCurrentScreen(ConsoleAppScreen.LOGIN);
            case 2 -> setCurrentScreen(ConsoleAppScreen.REGISTER);
            case 0 -> setCurrentScreen(null);
        }
    }

    private void registerScreen() {
        for (; ; ) {
            var email = ConsoleUtils.getEntry("E-mail");
            var password = ConsoleUtils.getEntry("Password");

            boolean isRegister = app.register(email, password);
            if (isRegister) {
                System.out.println("Registration successful. You are logged in.");
                setCurrentScreen(ConsoleAppScreen.PROFILE_PICKER);
                break;
            }

            System.out.println("Registration failed. Email Already in use or invalid email");
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
        var profiles = app.getProfiles();

        for (int i = 0; i < profiles.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, profiles.get(i).getName());
        }
        System.out.println("0. Logout\n");

        int choice = ConsoleUtils.getChoice(0, profiles.size());
        if (choice == 0) {
            app.logout();
            setCurrentScreen(ConsoleAppScreen.LOGIN);
        }
        var chosenProfile = profiles.get(choice - 1);

        app.setCurrentProfile(chosenProfile);
        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void mainMenuScreen() {
        System.out.println("Main Menu " + app.getCurrentProfile().getName());
        setCurrentScreen(null);
    }
}
