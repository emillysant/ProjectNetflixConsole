package org.example.repository;

import org.example.entity.Account;
import org.hibernate.SessionFactory;

public class AccountRepository {
    public final SessionFactory sessionFactory;

    public AccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Account findByEmail(String email) {
        var session = sessionFactory.openSession();
        return session.createQuery("from Account where email=:email", Account.class)
                .setParameter("email", email)
                .uniqueResult();
    }
}
