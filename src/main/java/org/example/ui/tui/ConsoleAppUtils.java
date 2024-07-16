package org.example.ui.tui;

import org.example.entity.Category;
import org.example.entity.Movie;
import org.example.entity.Series;

import java.util.List;

/**
 * This utility class provides methods for selecting a category, movie, or series from a list.
 */
public class ConsoleAppUtils {

    /**
     * Selects a category from a list of categories.
     * Displays the categories with their respective indices and prompts the user to choose one.
     *
     * @param categories The list of categories to choose from.
     * @return The selected category, or null if the user chooses to go back.
     */

    public static Category selectCategoryFromList(List<Category> categories) {
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, categories.get(i).getName());
        }
        System.out.println("0. Back");

        var choice = ConsoleUtils.getChoice(categories.size());
        if (choice == 0) {
            return null;
        }

        return categories.get(choice - 1);
    }

    /**
     * Selects a movie from a list of movies.
     * Displays the movies with their respective indices and prompts the user to choose one.
     *
     * @param movies The list of movies to choose from.
     * @return The selected movie, or null if no movies are found or the user chooses to go back.
     */
    public static Movie selectMovieFromList(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
            return null;
        }

        for (int i = 0; i < movies.size(); i++) {
            var movie = movies.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, movie.getTitle(), movie.getReleaseYear());
        }
        System.out.println("0. Back");

        var choice = ConsoleUtils.getChoice(movies.size());
        if (choice == 0) {
            return null;
        }

        return movies.get(choice - 1);
    }

    /**
     * Selects a series from a list of series.
     * Displays the series with their respective indices and prompts the user to choose one.
     *
     * @param series The list of series to choose from.
     * @return The selected series, or null if no series are found or the user chooses to go back.
     */
    public static Series selectSeriesFromList(List<Series> series) {
        if (series.isEmpty()) {
            System.out.println("No series found.");
            return null;
        }

        for (int i = 0; i < series.size(); i++) {
            var series1 = series.get(i);
            System.out.printf("%d. %s (%d)\n", i + 1, series1.getTitle(), series1.getReleaseYear());
        }
        System.out.println("0. Back");

        var choice = ConsoleUtils.getChoice(series.size());
        if (choice == 0) {
            return null;
        }

        return series.get(choice - 1);
    }
}
