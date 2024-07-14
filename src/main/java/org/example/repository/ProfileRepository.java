package org.example.repository;

import org.example.entity.Account;
import org.example.entity.Profile;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProfileRepository {
    public final SessionFactory sessionFactory;

    public ProfileRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Profile> findByAccount(Account account) {
        var session = sessionFactory.openSession();
        return session.createQuery("from Profile where account=:account", Profile.class)
                .setParameter("account", account.getId())
                .getResultList();
    }
}
