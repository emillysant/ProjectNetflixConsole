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
                case LIST_MOVIES -> listMoviesScreen();
                case LIST_SERIES -> listSeriesScreen();
                case SEARCH_MOVIES -> searchMoviesScreen();
                case SEARCH_SERIES -> searchSeriesScreen();
                case LIST_MOVIES_BY_CATEGORY -> listMoviesByCategoryScreen();
                case LIST_SERIES_BY_CATEGORY -> listSeriesByCategoryScreen();
                case EDIT_PROFILE -> editProfileScreen();
                case DELETE_PROFILE -> deleteProfileScreen();
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
        System.out.println("1. List movies");
        System.out.println("2. List series");
        System.out.println("3. Search movies");
        System.out.println("4. Search series");
        System.out.println("5. List movies by category");
        System.out.println("6. List series by category");
        System.out.println("7. Edit profile");
        System.out.println("8. Delete profile");
        System.out.println("0. Close profile");

        switch (ConsoleUtils.getChoice(8)) {
            case 0 -> setCurrentScreen(ConsoleAppScreen.PROFILE_PICKER);
            case 1 -> setCurrentScreen(ConsoleAppScreen.LIST_MOVIES);
            case 2 -> setCurrentScreen(ConsoleAppScreen.LIST_SERIES);
            case 3 -> setCurrentScreen(ConsoleAppScreen.SEARCH_MOVIES);
            case 4 -> setCurrentScreen(ConsoleAppScreen.SEARCH_SERIES);
            case 5 -> setCurrentScreen(ConsoleAppScreen.LIST_MOVIES_BY_CATEGORY);
            case 6 -> setCurrentScreen(ConsoleAppScreen.LIST_SERIES_BY_CATEGORY);
            case 7 -> setCurrentScreen(ConsoleAppScreen.EDIT_PROFILE);
            case 8 -> setCurrentScreen(ConsoleAppScreen.DELETE_PROFILE);
        }
    }

    private void listMoviesScreen() {
        var movies = app.getAllMovies();

        for (int i = 0; i < movies.size(); i++) {
            var movie = movies.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, movie.getTitle(), movie.getReleaseYear());
        }

        setCurrentScreen(null);
    }

    private void listSeriesScreen() {
        var series = app.getAllSeries();

        for (int i = 0; i < series.size(); i++) {
            var series1 = series.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, series1.getTitle(), series1.getReleaseYear());
        }

        setCurrentScreen(null);
    }

    private void searchMoviesScreen() {
        var query = ConsoleUtils.getEntry("Search query");

        var movies = app.searchMovie(query);

        for (int i = 0; i < movies.size(); i++) {
            var movie = movies.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, movie.getTitle(), movie.getReleaseYear());
        }

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void searchSeriesScreen() {
        var query = ConsoleUtils.getEntry("Search query");

        var series = app.searchSeries(query);

        for (int i = 0; i < series.size(); i++) {
            var series1 = series.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, series1.getTitle(), series1.getReleaseYear());
        }

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void listMoviesByCategoryScreen() {
        var movies = app.getAllMovies();

        for (int i = 0; i < movies.size(); i++) {
            var movie = movies.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, movie.getTitle(), movie.getReleaseYear());
        }

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void listSeriesByCategoryScreen() {
        var series = app.getAllSeries();

        for (int i = 0; i < series.size(); i++) {
            var series1 = series.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, series1.getTitle(), series1.getReleaseYear());
        }

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void editProfileScreen() {
        System.out.println("editProfileScreen");
        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void deleteProfileScreen() {
        // XXX: Must have confirmation dialog before actually deleting it
        System.out.println("deleteProfileScreen");
        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }
}
