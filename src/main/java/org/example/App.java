package org.example;

import org.hibernate.SessionFactory;

public class App {
    private final SessionFactory sessionFactory;

    public App(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
