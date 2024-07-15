package org.example.ui.tui;

import org.example.entity.Category;
import org.example.entity.Movie;
import org.example.entity.Series;

import java.util.List;

public class ConsoleAppUtils {
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
