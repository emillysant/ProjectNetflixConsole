package org.example.repository;

import org.example.entity.WatchedMovie;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

/**
 * This class provides methods for interacting with the WatchedMovie entity in the database.
 */
public class WatchedMoviesRepository {

    private final SessionFactory sessionFactory;

    /**
     * Constructs a new instance of WatchedMoviesRepository.
     *
     * @param sessionFactory The Hibernate SessionFactory for database operations.
     */
    public WatchedMoviesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Creates a new WatchedMovie record in the database.
     *
     * @param watchedMovie The WatchedMovie object to be created.
     * @return True if the record is successfully created, false if a database constraint violation occurs.
     */
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

    /**
     * Retrieves a list of WatchedMovie records associated with a specific profile ID.
     *
     * @param profileId The ID of the profile for which to retrieve WatchedMovie records.
     * @return A list of WatchedMovie records associated with the specified profile ID, sorted by creation date in descending order.
     */
    public List<WatchedMovie> findByProfileId(int profileId) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from WatchedMovie wm join wm.profile p join wm.movie m where p.id = :profileId order by wm.createdAt desc", WatchedMovie.class)
                    .setParameter("profileId", profileId)
                    .list();
        }
    }
}
