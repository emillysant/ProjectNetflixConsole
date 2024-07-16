package org.example.repository;

import org.example.entity.Account;
import org.hibernate.SessionFactory;

/**
 * This class represents a repository for managing {@link Account} entities.
 * It provides methods for finding accounts by email and persisting new accounts.
 */
public class AccountRepository {
    /**
     * The Hibernate SessionFactory used to create sessions for database operations.
     */
    public final SessionFactory sessionFactory;

    /**
     * Constructs a new AccountRepository with the given SessionFactory.
     *
     * @param sessionFactory the SessionFactory to use for database operations
     */
    public AccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Finds an account by its email.
     *
     * @param email the email of the account to find
     * @return the found account, or null if no account with the given email exists
     */
    public Account findByEmail(String email) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Account where email=:email", Account.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

    /**
     * Persists a new account in the database.
     *
     * @param account the account to persist
     */
    public void persist(Account account) {
        try (var session = sessionFactory.openSession()) {
            var register = session.beginTransaction();
            session.persist(account);
            register.commit();
        }
    }
}
