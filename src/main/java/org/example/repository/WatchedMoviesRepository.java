package org.example.repository;

import org.example.entity.WatchedMovie;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class WatchedMoviesRepository {

    private final SessionFactory sessionFactory;

    public WatchedMoviesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean create(WatchedMovie watchedMovie) {
        try (var session = sessionFactory.openSession()) {
            var tx = session.beginTransaction();
            try {
                session.persist(watchedMovie);
                tx.commit();
                return true;
            } catch (ConstraintViolationException e) {
                return false;
            }
        }
    }

    public List<WatchedMovie> findByProfileId(int profileId) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from WatchedMovie wm join wm.profile p join wm.movie m where p.id = :profileId order by wm.createdAt desc", WatchedMovie.class)
                    .setParameter("profileId", profileId)
                    .list();
        }
    }
}
