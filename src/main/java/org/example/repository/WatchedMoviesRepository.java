package org.example.repository;

import jakarta.persistence.EntityExistsException;
import org.example.entity.WatchedMovie;
import org.hibernate.SessionFactory;

import java.util.List;

public class WatchedMoviesRepository {

    private final SessionFactory sessionFactory;

    public WatchedMoviesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean create(WatchedMovie watchedMovie) {
        var session = sessionFactory.openSession();

        var tx = session.beginTransaction();
        session.persist(watchedMovie);
        try {
            tx.commit();
            return true;
        } catch (EntityExistsException e) {
            return false;
        }
    }

    public List<WatchedMovie> findByProfileId(int profileId) {
        var session = sessionFactory.openSession();

        return session.createQuery("from WatchedMovie wm join wm.profile p join wm.movie m where p.id = :profileId order by wm.createdAt desc", WatchedMovie.class)
                .setParameter("profileId", profileId)
                .list();
    }
}
