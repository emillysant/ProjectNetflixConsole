package org.example;

import org.example.factory.ConsoleAppFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        var sessionFactory = new Configuration().configure().buildSessionFactory();

        var app = new App(sessionFactory);

        ConsoleAppFactory.run(app);
    }
}
