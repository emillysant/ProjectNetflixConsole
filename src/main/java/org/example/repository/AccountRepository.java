package org.example.repository;

import org.example.entity.Account;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

    public void persist(Account account) {
        var session = sessionFactory.openSession();

        Transaction register = session.beginTransaction();
        session.persist(account);
        register.commit();
    }
}
