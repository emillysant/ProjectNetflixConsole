package org.example.repository;

import org.example.entity.WatchedMovie;
import org.hibernate.SessionFactory;

import java.util.List;

public class WatchedSeriesRepository {
    private final SessionFactory sessionFactory;

    public List<WatchedMovie> getWatchedMovies(int profileId) {
        var session = sessionFactory.openSession();

        return session.createQuery("FROM WatchedSeries ws JOIN ws.profile p JOIN ws.seriesEpisode e WHERE p.id = :profileId order by ws.createdAt desc", WatchedMovie.class)
                .setParameter("profileId", profileId)
                .list();
    }
}
