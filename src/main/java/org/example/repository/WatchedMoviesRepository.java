package org.example.repository;

import org.example.entity.WatchedMovie;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class WatchedMoviesRepository {

    private final SessionFactory sessionFactory;

    public WatchedMoviesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addWatchedMovie(int profileId, int filmId){
        var session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.createNativeQuery(
                        "INSERT INTO watched_films (profile, film) VALUES (:profileId, :filmId) ON CONFLICT DO NOTHING")
                .setParameter("profileId", profileId)
                .setParameter("filmId", filmId)
                .executeUpdate();
        tx.commit();
    }

    public List<WatchedMovie> getWatchedMoviesForCurrentProfile(int profileId){
        var session = sessionFactory.openSession();

        return session.createQuery(
                        "SELECT wm FROM WatchedMovie wm JOIN wm.profile p JOIN wm.movie m WHERE p.id = :profileId", WatchedMovie.class)
                .setParameter("profileId", profileId)
                .list();
    }

}
