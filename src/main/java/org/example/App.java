package org.example;

import org.example.entity.Account;
import org.example.repository.AccountRepository;
import org.hibernate.SessionFactory;

public class App {
    private final SessionFactory sessionFactory;

    private final AccountRepository accountRepository;

    private Account loggedInAccount = null;

    public App(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.accountRepository = new AccountRepository(sessionFactory);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public boolean login(String email, String password) {
        var account = accountRepository.findByEmail(email);
        if (account == null) return false;
        else if (!account.getPasswordHash().equals(password))
            return false;

        loggedInAccount = account;
        return true;
    }
}
