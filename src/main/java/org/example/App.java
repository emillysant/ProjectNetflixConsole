package org.example;

import org.example.entity.*;
import org.example.repository.*;
import org.hibernate.SessionFactory;

import java.util.List;

public class App {
    private final SessionFactory sessionFactory;

    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final ProfileRepository profileRepository;
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;
    private final WatchedMoviesRepository watchedMoviesRepository;
    private final WatchedSeriesRepository watchedSeriesRepository;

    private Account loggedInAccount = null;
    private Profile currentProfile = null;

    public App(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.accountRepository = new AccountRepository(sessionFactory);
        this.categoryRepository = new CategoryRepository(sessionFactory);
        this.profileRepository = new ProfileRepository(sessionFactory);
        this.movieRepository = new MovieRepository(sessionFactory);
        this.seriesRepository = new SeriesRepository(sessionFactory);
        this.watchedMoviesRepository = new WatchedMoviesRepository(sessionFactory);
        this.watchedSeriesRepository = new WatchedSeriesRepository(sessionFactory);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }

    public boolean login(String email, String password) {
        var account = accountRepository.findByEmail(email);
        if (account == null) return false;
        else if (!account.getPasswordHash().equals(password))
            return false;

        loggedInAccount = account;
        return true;
    }

    public boolean logout() {
        if (loggedInAccount == null) return false;
        loggedInAccount = null;
        return true;
    }

    public boolean register(String email, String password) {
        var existingAccount = accountRepository.findByEmail(email);
        if (existingAccount != null) return false;

        var newAccount = new Account();
        newAccount.setEmail(email);
        newAccount.setPasswordHash(password);

        accountRepository.persist(newAccount);
        loggedInAccount = newAccount;
        return true;
    }

    public List<Profile> getProfiles() {
        return profileRepository.findByAccount(loggedInAccount);
    }

    public boolean createProfile(Profile profile) {
        if (profile.getAccount() == 0)
            profile.setAccount(loggedInAccount.getId());
        return profileRepository.create(profile);
    }

    public boolean updateProfile(Profile profile) {
        profileRepository.edit(profile);
        return true;
    }

    public boolean deleteProfile(Profile profile) {
        profileRepository.delete(profile);
        return true;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAllMovies();
    }

    public List<Series> getAllSeries() {
        return seriesRepository.findAllSeries();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Movie> searchMovie(String query) {
        return movieRepository.searchByTitle(query);
    }

    public List<Series> searchSeries(String query) {
        return seriesRepository.searchByTitle(query);
    }

    public List<Movie> getMoviesByCategory(Category category) {
        return movieRepository.findByCategory(category.getName());
    }

    public List<Series> getSeriesByCategory(Category category) {
        return seriesRepository.findByCategory(category.getName());
    }

    public List<Movie> getMoviesByYear(Integer year) {
        return movieRepository.findMoviesByYear(year);
    }

    public List<Series> getSeriesByYear(Integer year) {
        return seriesRepository.findSeriesByYear(year);
    }

    public List<WatchedMovie> getWatchedMovies() {
        return watchedMoviesRepository.findByProfileId(currentProfile.getId());
    }

    public List<WatchedSeries> getWatchedSeries() {
        return watchedSeriesRepository.findByProfileId(currentProfile.getId());
    }

    public boolean addWatchedMovie(Movie movie) {
        var watchedMovie = new WatchedMovie();
        watchedMovie.setMovie(movie);
        watchedMovie.setProfile(getCurrentProfile());
        return watchedMoviesRepository.create(watchedMovie);
    }

    public boolean addWatchedSeriesEpisode(SeriesEpisode seriesEpisode) {
        var watchedSeries = new WatchedSeries();
        watchedSeries.setSeriesEpisode(seriesEpisode);
        watchedSeries.setProfile(getCurrentProfile());
        return watchedSeriesRepository.create(watchedSeries);
    }
}
