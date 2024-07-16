package org.example.ui.tui;

import org.example.App;
import org.example.entity.*;

import java.util.List;

/**
 * The main console application class that handles user interactions and manages the application's state.
 */
public class ConsoleApp {
    private final App app;

    private ConsoleAppScreen currentScreen = ConsoleAppScreen.STARTING;
    private Movie selectedMovie = null;
    private Series selectedSeries = null;
    private SeriesSeason selectedSeriesSeason = null;
    private SeriesEpisode selectedSeriesEpisode = null;

    /**
     * Constructor for the ConsoleApp class.
     *
     * @param app The App instance that provides the application's functionality.
     */
    public ConsoleApp(App app) {
        this.app = app;
    }

    /**
     * Sets the current screen of the application.
     *
     * @param currentScreen The new screen to set.
     */
    public void setCurrentScreen(ConsoleAppScreen currentScreen) {
        this.currentScreen = currentScreen;
    }

    /**
     * The main loop of the application that handles user interactions and updates the application's state.
     */
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

    /**
     * Displays the starting screen of the console application.
     * The screen provides options for logging in, registering, or quitting the application.
     *
     * @return void
     */
    private void startingScreen() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Quit");

        switch (ConsoleUtils.getChoice(2)) {
            case 1 -> setCurrentScreen(ConsoleAppScreen.LOGIN);
            case 2 -> setCurrentScreen(ConsoleAppScreen.REGISTER);
            case 0 -> setCurrentScreen(null);
        }
    }

    /**
     * Displays the registration screen of the console application.
     * The screen prompts the user to enter their email and password.
     * If the registration is successful, the user is logged in and redirected to the profile picker screen.
     * If the registration fails due to an existing email or invalid email, an error message is displayed.
     *
     * @return void
     */
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

    /**
     * Displays the login screen of the console application.
     * The screen prompts the user to enter their email and password.
     * If the login is successful, the user is redirected to the profile picker screen.
     * If the login fails due to an invalid email or password, an error message is displayed.
     *
     * @return void
     */
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

    /**
     * Displays the profile picker screen of the console application.
     * The screen lists all available profiles for the logged-in user.
     * The user can select a profile, create a new profile, or log out.
     *
     * @return void
     */
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


    /**
     * Handles the creation of a new profile.
     * This function prompts the user to enter a profile name, creates a new Profile object,
     * and attempts to add it to the application's list of profiles.
     * If the profile creation is successful, it updates the current screen to the profile picker.
     * If the profile already exists, it displays an error message.
     * The function continues to prompt the user until a valid profile name is entered.
     */
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


    /**
     * Displays the main menu screen with various options for the user to choose from.
     * The user can navigate through different functionalities of the application.
     *
     * @see ConsoleAppScreen
     * @see ConsoleUtils#getChoice(int)
     * @see ConsoleApp#setCurrentScreen(ConsoleAppScreen)
     */
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

    /**
     * Displays a list of movies to the user and allows them to select a movie.
     * If a movie is selected, it sets the selected movie and the current screen to {@link ConsoleAppScreen#MOVIE_DETAILS}.
     * If no movie is selected, it sets the current screen to {@link ConsoleAppScreen#MAIN_MENU}.
     */
    private void listMoviesScreen() {
        var movies = app.getAllMovies();
        selectedMovie = ConsoleAppUtils.selectMovieFromList(movies);
        setCurrentScreen(selectedMovie == null ? ConsoleAppScreen.MAIN_MENU : ConsoleAppScreen.MOVIE_DETAILS);
    }

    /**
     * Displays a list of all series in the application and allows the user to select one.
     * If a series is selected, it sets the selected series and navigates to the series details screen.
     * If no series is selected, it returns to the main menu.
     */
    private void listSeriesScreen() {
        var series = app.getAllSeries();
        selectedSeries = ConsoleAppUtils.selectSeriesFromList(series);
        setCurrentScreen(selectedSeries == null ? ConsoleAppScreen.MAIN_MENU : ConsoleAppScreen.SERIES_DETAILS);
    }

    /**
     * Handles the search movies screen.
     * It prompts the user to enter a search query, calls the {@link App#searchMovie(String)} method to retrieve the matching movies,
     * and then displays the list of movies to the user.
     * The user can select a movie from the list, and the selected movie is stored in the {@link #selectedMovie} field.
     * If no movie is selected, the current screen is set to {@link ConsoleAppScreen#MAIN_MENU}.
     * If a movie is selected, the current screen is set to {@link ConsoleAppScreen#MOVIE_DETAILS}.
     */
    private void searchMoviesScreen() {
        var query = ConsoleUtils.getEntry("Search query");

        var movies = app.searchMovie(query);
        selectedMovie = ConsoleAppUtils.selectMovieFromList(movies);
        setCurrentScreen(selectedMovie == null ? ConsoleAppScreen.MAIN_MENU : ConsoleAppScreen.MOVIE_DETAILS);
    }

    /**
     * Handles the search series screen.
     * Prompts the user to enter a search query, searches for series based on the query,
     * and selects a series from the list.
     * If a series is selected, it sets the current screen to {@link ConsoleAppScreen#SERIES_DETAILS}.
     * If no series is selected, it sets the current screen to {@link ConsoleAppScreen#MAIN_MENU}.
     */
    private void searchSeriesScreen() {
        var query = ConsoleUtils.getEntry("Search query");

        var series = app.searchSeries(query);
        selectedSeries = ConsoleAppUtils.selectSeriesFromList(series);
        setCurrentScreen(selectedSeries == null ? ConsoleAppScreen.MAIN_MENU : ConsoleAppScreen.SERIES_DETAILS);
    }

    /**
     * Handles the search series screen.
     * Prompts the user to enter a search query, searches for series based on the query,
     * and selects a series from the list.
     * If a series is selected, it sets the current screen to {@link ConsoleAppScreen#SERIES_DETAILS}.
     * If no series is selected, it sets the current screen to {@link ConsoleAppScreen#MAIN_MENU}.
     */
    private void listMoviesByCategoryScreen() {
        var categories = app.getAllCategories();
        var chosenCategory = ConsoleAppUtils.selectCategoryFromList(categories);
        if (chosenCategory == null) {
            setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
            return;
        }

        var movies = app.getMoviesByCategory(chosenCategory);
        selectedMovie = ConsoleAppUtils.selectMovieFromList(movies);
        setCurrentScreen(selectedMovie == null ? ConsoleAppScreen.MAIN_MENU : ConsoleAppScreen.MOVIE_DETAILS);
    }

    /**
     * Displays a list of series based on the selected category.
     * If no category is selected, returns to the main menu.
     * If a series is selected, navigates to the series details screen.
     */
    private void listSeriesByCategoryScreen() {
        var categories = app.getAllCategories();
        var chosenCategory = ConsoleAppUtils.selectCategoryFromList(categories);
        if (chosenCategory == null) {
            setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
            return;
        }

        var series = app.getSeriesByCategory(chosenCategory);
        selectedSeries = ConsoleAppUtils.selectSeriesFromList(series);
        setCurrentScreen(selectedSeries == null ? ConsoleAppScreen.MAIN_MENU : ConsoleAppScreen.SERIES_DETAILS);
    }

    /**
     * Displays the details of the selected movie and provides options to play or go back.
     *
     * @throws IllegalStateException If no movie is selected before calling this method.
     */
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

    /**
     * Displays the details of the selected series, including its title, release year, description,
     * and a list of available seasons. The user can choose a season to view its details.
     */
    private void seriesDetails() {
        System.out.println("[Series] " + selectedSeries.getTitle() + " (" + selectedSeries.getReleaseYear() + ")");
        System.out.println(selectedSeries.getDescription() + "\n");

        var seasons = selectedSeries.getSeasons().toArray(new SeriesSeason[0]);

        for (int i = 0; i < seasons.length; i++) {
            var season = seasons[i];
            System.out.printf("%d. Season %s\n", i + 1, season.getOrderNumber());
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

    /**
     * Displays the details of a selected series season.
     * Prints the title and release year of the series along with the season number.
     * Lists all episodes of the selected season with their respective numbers and titles.
     * Allows the user to choose an episode to play or go back to the series details.
     * Sets the current screen to {@link ConsoleAppScreen#SERIES_DETAILS} if the user chooses to go back,
     * otherwise sets it to {@link ConsoleAppScreen#PLAY_SERIES_EPISODE} after selecting an episode.
     */
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

    /**
     * Handles the editing of the current user profile's name.
     * Prompts the user to enter a new name, updates the current profile's name,
     * and notifies the user whether the update was successful or not.
     * Sets the current screen to {@link ConsoleAppScreen#MAIN_MENU} after completion.
     */
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

    /**
     * Handles the deletion of the current user profile.
     * Asks for confirmation from the user before deleting the profile.
     * Deletes the profile if confirmed and notifies the user of the deletion.
     * Sets the current screen to {@link ConsoleAppScreen#PROFILE_PICKER} if profile is deleted,
     * otherwise sets it to {@link ConsoleAppScreen#MAIN_MENU}.
     */
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

    /**
     * Displays the list of watched movies for the current profile.
     * If no watched movies are found, prints a message indicating so.
     * Otherwise, prints the titles of all watched movies.
     * After displaying the list, waits for user input to return to the main menu.
     * Sets the current screen to {@link ConsoleAppScreen#MAIN_MENU} after user input.
     */

    public void searchMoviesByYearScreen() {
        var year = ConsoleUtils.getEntry("Enter the year to search for movies");

        var movies = app.getMoviesByYear(Integer.valueOf(year));
        selectedMovie = ConsoleAppUtils.selectMovieFromList(movies);
        setCurrentScreen(selectedMovie == null ? ConsoleAppScreen.MAIN_MENU : ConsoleAppScreen.MOVIE_DETAILS);
    }

    /**
     * Displays the list of watched movies for the current profile.
     * If no watched movies are found, prints a message indicating so.
     * Otherwise, prints the titles of all watched movies.
     * After displaying the list, waits for user input to return to the main menu.
     * Sets the current screen to {@link ConsoleAppScreen#MAIN_MENU} after user input.
     */
    public void searchSeriesByYearScreen() {
        var year = ConsoleUtils.getEntry("Enter the year to search for series");

        var series = app.getSeriesByYear(Integer.valueOf(year));
        selectedSeries = ConsoleAppUtils.selectSeriesFromList(series);
        setCurrentScreen(selectedSeries == null ? ConsoleAppScreen.MAIN_MENU : ConsoleAppScreen.SERIES_DETAILS);
    }

    /**
     * Displays the list of watched movies for the current profile.
     * If no watched movies are found, prints a message indicating so.
     * Otherwise, prints the titles of all watched movies.
     * After displaying the list, waits for user input to return to the main menu.
     * Sets the current screen to {@link ConsoleAppScreen#MAIN_MENU} after user input.
     */
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

        System.out.print("\nPress Enter to go back...");
        ConsoleUtils.getEntry();
        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    /**
     * Displays the list of watched series episodes for the current profile.
     * If no watched series episodes are found, prints a message indicating so.
     * Otherwise, prints the series title, season number, episode number, and title
     * of each watched series episode.
     * After displaying the list, waits for user input to return to the main menu.
     * Sets the current screen to {@link ConsoleAppScreen#MAIN_MENU} after user input.
     */
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

        System.out.print("\nPress Enter to go back...");
        ConsoleUtils.getEntry();
        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    /**
     * Adds the selected movie to the watched list and simulates playing it.
     * Prints the movie title and waits for user input to stop playback.
     * After stopping, sets the current screen to the main menu.
     */
    private void playMovie() {
        app.addWatchedMovie(selectedMovie);

        System.out.printf("Playing movie: %s\n", selectedMovie.getTitle());
        System.out.print("Press Enter to stop playing...");
        ConsoleUtils.getEntry();

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }

    /**
     * Adds the selected series episode to the watched list and simulates playing it.
     * Prints the series title, season number, episode number, and waits for user input to stop playback.
     * After stopping, sets the current screen to the main menu.
     */
    private void playSeriesEpisode() {
        app.addWatchedSeriesEpisode(selectedSeriesEpisode);

        System.out.printf("Playing series: %s\n", selectedSeries.getTitle());
        System.out.printf("Season %d, episode %d: %s\n", selectedSeriesSeason.getOrderNumber(), selectedSeriesEpisode.getOrderNumber(), selectedSeriesEpisode.getTitle());
        System.out.print("Press Enter to stop playing...");
        ConsoleUtils.getEntry();

        setCurrentScreen(ConsoleAppScreen.MAIN_MENU);
    }
}
