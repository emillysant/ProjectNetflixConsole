package org.example;

import org.example.entity.Account;
import org.example.entity.Profile;
import org.example.repository.AccountRepository;
import org.example.repository.ProfileRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class App {
    private final SessionFactory sessionFactory;

    private final AccountRepository accountRepository;
    private final ProfileRepository profileRepository;

    private Account loggedInAccount = null;
    private Profile currentProfile = null;

    public App(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.accountRepository = new AccountRepository(sessionFactory);
        this.profileRepository = new ProfileRepository(sessionFactory);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }

    public boolean login(String email, String password) {
        var account = accountRepository.findByEmail(email);
        if (account == null) return false;
        else if (!account.getPasswordHash().equals(password))
            return false;

        loggedInAccount = account;
        return true;
    }

    public boolean logout() {
        if (loggedInAccount == null) return false;
        loggedInAccount = null;
        return true;
    }

    public boolean register(String email, String password) {
        var existingAccount = accountRepository.findByEmail(email);
        if (existingAccount != null) return false;

        var newAccount = new Account();
        newAccount.setEmail(email);
        newAccount.setPasswordHash(password);

        accountRepository.persist(newAccount);
        loggedInAccount = newAccount;
        return true;
    }

    public List<Profile> getProfiles() {
        return profileRepository.findByAccount(loggedInAccount);
    }
}
