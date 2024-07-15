package org.example.ui.tui;

import org.example.App;
import org.example.entity.*;

import java.util.List;

public class ConsoleApp {
    private final App app;

    private ConsoleAppScreen currentScreen = ConsoleAppScreen.STARTING;
    private Movie selectedMovie = null;
    private Series selectedSeries = null;
    private SeriesSeason selectedSeriesSeason = null;
    private SeriesEpisode selectedSeriesEpisode = null;

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
                case CREATE_PROFILE -> createProfileScreen();
                case MAIN_MENU -> mainMenuScreen();
                case LIST_MOVIES -> listMoviesScreen();
                case LIST_SERIES -> listSeriesScreen();
                case SEARCH_MOVIES -> searchMoviesScreen();
                case SEARCH_SERIES -> searchSeriesScreen();
                case LIST_MOVIES_BY_CATEGORY -> listMoviesByCategoryScreen();
                case LIST_SERIES_BY_CATEGORY -> listSeriesByCategoryScreen();
                case LIST_MOVIES_BY_YEAR -> searchMoviesByYearScreen();
                case LIST_SERIES_BY_YEAR -> searchSeriesByYearScreen();
                case MOVIE_DETAILS -> movieDetails();
                case SERIES_DETAILS -> seriesDetails();
                case SERIES_SEASON_DETAILS -> seriesSeasonDetails();
                case WATCHED_MOVIES -> watchedMoviesScreen();
                case WATCHED_SERIES_EPISODES -> watchedSeriesEpisodesScreen();
                case PLAY_MOVIE -> playMovie();
                case PLAY_SERIES_EPISODE -> playSeriesEpisode();
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
        var createNewProfileOption = profiles.size() + 1;
        System.out.printf("%d. Create new profile\n", createNewProfileOption);
        System.out.println("0. Logout\n");

        int choice = ConsoleUtils.getChoice(profiles.size() + 1);
        if (choice == 0) {
            app.logout();
            setCurrentScreen(ConsoleAppScreen.STARTING);
            return;
        } else if (choice == createNewProfileOption) {
            setCurrentScreen(ConsoleAppScreen.CREATE_PROFILE);
            return;
        }

        var chosenProfile = profiles.get(choice - 1);

        app.setCurrentProfile(chosenProfile);
        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void createProfileScreen() {
        for (; ; ) {
            var profileName = ConsoleUtils.getEntry("Profile name");

            var profile = new Profile();
            profile.setName(profileName);
            if (app.createProfile(profile)) {
                System.out.println("Profile created successfully!");
                setCurrentScreen(ConsoleAppScreen.PROFILE_PICKER);
                return;
            } else {
                System.out.println("Profile already exists.");
            }
        }
    }

    private void mainMenuScreen() {
        System.out.println("1. List movies");
        System.out.println("2. List series");
        System.out.println("3. Search movies");
        System.out.println("4. Search series");
        System.out.println("5. List movies by category");
        System.out.println("6. List series by category");
        System.out.println("7. List movies by year");
        System.out.println("8. List series by year");
        System.out.println("9. Watch History (Movies)");
        System.out.println("10. Watch History (Series)");
        System.out.println("11. Edit profile");
        System.out.println("12. Delete profile");
        System.out.println("0. Close profile");

        switch (ConsoleUtils.getChoice(12)) {
            case 0 -> setCurrentScreen(ConsoleAppScreen.PROFILE_PICKER);
            case 1 -> setCurrentScreen(ConsoleAppScreen.LIST_MOVIES);
            case 2 -> setCurrentScreen(ConsoleAppScreen.LIST_SERIES);
            case 3 -> setCurrentScreen(ConsoleAppScreen.SEARCH_MOVIES);
            case 4 -> setCurrentScreen(ConsoleAppScreen.SEARCH_SERIES);
            case 5 -> setCurrentScreen(ConsoleAppScreen.LIST_MOVIES_BY_CATEGORY);
            case 6 -> setCurrentScreen(ConsoleAppScreen.LIST_SERIES_BY_CATEGORY);
            case 7 -> setCurrentScreen(ConsoleAppScreen.LIST_MOVIES_BY_YEAR);
            case 8 -> setCurrentScreen(ConsoleAppScreen.LIST_SERIES_BY_YEAR);
            case 9 -> setCurrentScreen(ConsoleAppScreen.WATCHED_MOVIES);
            case 10 -> setCurrentScreen(ConsoleAppScreen.WATCHED_SERIES_EPISODES);
            case 11 -> setCurrentScreen(ConsoleAppScreen.EDIT_PROFILE);
            case 12 -> setCurrentScreen(ConsoleAppScreen.DELETE_PROFILE);
        }
    }

    private void listMoviesScreen() {
        var movies = app.getAllMovies();

        for (int i = 0; i < movies.size(); i++) {
            var movie = movies.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, movie.getTitle(), movie.getReleaseYear());
        }

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void listSeriesScreen() {
        var series = app.getAllSeries();

        for (int i = 0; i < series.size(); i++) {
            var series1 = series.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, series1.getTitle(), series1.getReleaseYear());
        }

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
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
        var categories = app.getAllCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, categories.get(i).getName());
        }
        System.out.println("0. Back");

        var choice = ConsoleUtils.getChoice(categories.size());
        if (choice == 0) {
            setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
            return;
        }

        var chosenCategory = categories.get(choice - 1);

        var movies = app.getMoviesByCategory(chosenCategory);

        for (int i = 0; i < movies.size(); i++) {
            var movie = movies.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, movie.getTitle(), movie.getReleaseYear());
        }
        System.out.println("0. Back");

        choice = ConsoleUtils.getChoice(movies.size());
        if (choice == 0) {
            setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
            return;
        }

        selectedMovie = movies.get(choice - 1);
        setCurrentScreen(ConsoleAppScreen.MOVIE_DETAILS);
    }

    private void listSeriesByCategoryScreen() {
        var categories = app.getAllCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, categories.get(i).getName());
        }
        System.out.println("0. Back");

        var choice = ConsoleUtils.getChoice(categories.size());
        if (choice == 0) {
            setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
            return;
        }

        var chosenCategory = categories.get(choice - 1);

        var series = app.getSeriesByCategory(chosenCategory);

        for (int i = 0; i < series.size(); i++) {
            var series1 = series.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, series1.getTitle(), series1.getReleaseYear());
        }
        System.out.println("0. Back");

        choice = ConsoleUtils.getChoice(series.size());
        if (choice == 0) {
            setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
            return;
        }

        selectedSeries = series.get(choice - 1);
        setCurrentScreen(ConsoleAppScreen.SERIES_DETAILS);
    }

    private void movieDetails() {
        System.out.println("[Movie] " + selectedMovie.getTitle() + " " + selectedMovie.getReleaseYear());
        System.out.println(selectedMovie.getDescription() + "\n");

        System.out.println("1. Play");
        System.out.println("0. Back");
        switch (ConsoleUtils.getChoice(1)) {
            case 0 -> setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
            case 1 -> setCurrentScreen(ConsoleAppScreen.PLAY_MOVIE);
        }
    }

    private void seriesDetails() {
        System.out.println("[Series] " + selectedSeries.getTitle() + " (" + selectedSeries.getReleaseYear() + ")");
        System.out.println(selectedSeries.getDescription() + "\n");

        var seasons = selectedSeries.getSeasons().toArray(new SeriesSeason[0]);
        for (int i = 0; i < seasons.length; i++) {
            var season = seasons[i];
            System.out.printf("%d. Season %s\n", i + 1, i + 1);
        }
        System.out.println("0. Back");

        var choice = ConsoleUtils.getChoice(seasons.length);
        if (choice == 0) {
            setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
            return;
        }

        selectedSeriesSeason = seasons[choice - 1];
        setCurrentScreen(ConsoleAppScreen.SERIES_SEASON_DETAILS);
    }

    private void seriesSeasonDetails() {
        System.out.println("[Season] " + selectedSeries.getTitle() + " (" + selectedSeries.getReleaseYear() + ") - Season " + selectedSeriesSeason.getOrderNumber());

        var episodes = selectedSeriesSeason.getEpisodes().toArray(new SeriesEpisode[0]);
        for (int i = 0; i < episodes.length; i++) {
            var episode = episodes[i];
            System.out.printf("%d. Episode %d: %s\n", i + 1, i + 1, episode.getTitle());
        }
        System.out.println("0. Back");

        var choice = ConsoleUtils.getChoice(episodes.length);
        if (choice == 0) {
            setCurrentScreen(ConsoleAppScreen.SERIES_DETAILS);
            return;
        }
        selectedSeriesEpisode = episodes[choice - 1];

        setCurrentScreen(ConsoleAppScreen.PLAY_SERIES_EPISODE);
    }

    private void editProfileScreen() {
        var newName = ConsoleUtils.getEntry("New name");
        var currentProfile = app.getCurrentProfile();
        currentProfile.setName(newName);
        boolean updated = app.updateProfile(currentProfile);
        if (updated) {
            System.out.println("Name Updated");
        } else {
            System.out.println("Fail to Updated");
        }
        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void deleteProfileScreen() {
        String confirmation = ConsoleUtils.getEntry("Are you sure you want to delete your profile? (Y/N)").toUpperCase();
        if (confirmation.equals("Y")) {
            boolean deleted = app.deleteProfile(app.getCurrentProfile());
            if (deleted) {
                System.out.println("Profile deleted successfully!");
                setCurrentScreen(ConsoleAppScreen.PROFILE_PICKER);
            }
        } else {
            setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
        }
    }

    public void searchMoviesByYearScreen() {
        var year = ConsoleUtils.getEntry("Enter the year to search for movies");

        var movies = app.getMoviesByYear(Integer.valueOf(year));
        if (movies.isEmpty()) {
            System.out.println("No movies found for the year " + year);
        } else {
            System.out.printf("Movies released in %d:\n", year);
            for (int i = 0; i < movies.size(); i++) {
                var movie = movies.get(i);
                System.out.printf("%d. %s\n", i + 1, movie.getTitle());
            }
        }

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    public void searchSeriesByYearScreen() {
        var year = ConsoleUtils.getEntry("Enter the year to search for series");

        var series = app.getSeriesByYear(Integer.valueOf(year));
        if (series.isEmpty()) {
            System.out.println("No series found for the year " + year);
        } else {
            System.out.printf("Series released in %d:\n", year);
            for (int i = 0; i < series.size(); i++) {
                var serie = series.get(i);
                System.out.printf("%d. %s\n", i + 1, serie.getTitle());
            }
        }

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void watchedMoviesScreen() {
        List<WatchedMovie> watchedMovies = app.getWatchedMovies();
        if (watchedMovies.isEmpty()) {
            System.out.println("No watched movies found for the current profile");
        } else {
            System.out.println("Watched Movies: ");
            for (var watchEntry : watchedMovies) {
                System.out.println("- " + watchEntry.getMovie().getTitle());
            }
        }
        System.out.println();
        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void watchedSeriesEpisodesScreen() {
        List<WatchedSeries> watchedSeries = app.getWatchedSeries();
        if (watchedSeries.isEmpty()) {
            System.out.println("No watched movies found for the current profile");
        } else {
            System.out.println("Watched Series: ");
            for (var watchEntry : watchedSeries) {
                var seriesEpisode = watchEntry.getSeriesEpisode();
                var seriesSeason = seriesEpisode.getSeason();
                var series = seriesSeason.getSeries();

                System.out.printf("- (%s S%d E%d) %s\n", series.getTitle(), seriesSeason.getOrderNumber(), seriesEpisode.getOrderNumber(), seriesEpisode.getTitle());
            }
        }
        System.out.println();
        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void playMovie() {
        app.addWatchedMovie(selectedMovie);

        System.out.printf("Playing movie: %s\n", selectedMovie.getTitle());
        System.out.println("Press any key to stop playing...");
        ConsoleUtils.getEntry();

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    private void playSeriesEpisode() {
        app.addWatchedSeriesEpisode(selectedSeriesEpisode);

        System.out.printf("Playing series: %s\n", selectedMovie.getTitle());
        System.out.printf("Season %d, episode %d: %s\n", selectedSeriesSeason.getOrderNumber(), selectedSeriesEpisode.getOrderNumber(), selectedSeriesEpisode.getTitle());
        System.out.println("Press any key to stop playing...");
        ConsoleUtils.getEntry();

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }
}
