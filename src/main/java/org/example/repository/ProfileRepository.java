package org.example.repository;

import jakarta.persistence.EntityExistsException;
import org.example.entity.Account;
import org.example.entity.Profile;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * This class provides methods for interacting with the Profile entity in the database.
 * It uses Hibernate for database operations.
 */
public class ProfileRepository {
    /**
     * The Hibernate SessionFactory for creating sessions.
     */
    public final SessionFactory sessionFactory;

    /**
     * Constructs a new ProfileRepository with the given SessionFactory.
     *
     * @param sessionFactory The Hibernate SessionFactory for creating sessions.
     */
    public ProfileRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Finds all Profiles associated with the given Account.
     *
     * @param account The Account to find Profiles for.
     * @return A List of Profiles associated with the given Account.
     */
    public List<Profile> findByAccount(Account account) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Profile where account = :account", Profile.class)
                    .setParameter("account", account.getId())
                    .getResultList();
        }
    }

    /**
     * Creates a new Profile in the database.
     *
     * @param profile The Profile to create.
     * @return True if the Profile was created successfully, false if an EntityExistsException was thrown.
     */
    public boolean create(Profile profile) {
        try (var session = sessionFactory.openSession()) {
            var tx = session.beginTransaction();
            session.persist(profile);
            try {
                tx.commit();
                return true;
            } catch (EntityExistsException e) {
                return false;
            }
        }
    }

    /**
     * Updates an existing Profile in the database.
     *
     * <p>Note: This method uses Session.merge() which may not be necessary.
     *
     * @param profile The Profile to update.
     */
    public void edit(Profile profile) {
        try (var session = sessionFactory.openSession()) {
            var tx = session.beginTransaction();
            session.merge(profile);
            tx.commit();
        }
    }

    /**
     * Deletes a Profile from the database.
     *
     * @param profile The Profile to delete.
     */
    public void delete(Profile profile) {
        try (var session = sessionFactory.openSession()) {
            var tx = session.beginTransaction();
            session.remove(profile);
            tx.commit();
        }
    }
}
