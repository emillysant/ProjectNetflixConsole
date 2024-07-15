package org.example.repository;

import jakarta.persistence.EntityExistsException;
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
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Profile where account = :account", Profile.class)
                    .setParameter("account", account.getId())
                    .getResultList();
        }
    }

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

    // FIXME: This use of `Session.merge()` appears to not be necessary
    public void edit(Profile profile) {
        try (var session = sessionFactory.openSession()) {
            var tx = session.beginTransaction();
            session.merge(profile);
            tx.commit();
        }
    }

    public void delete(Profile profile) {
        try (var session = sessionFactory.openSession()) {
            var tx = session.beginTransaction();
            session.remove(profile);
            tx.commit();
        }
    }
}
