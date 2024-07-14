package org.example;

import org.example.entity.Account;
import org.example.entity.Movie;
import org.example.factory.ConsoleAppFactory;
import org.example.ui.tui.ConsoleApp;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var sessionFactory = new Configuration().configure().buildSessionFactory();

        var app = new App(sessionFactory);

        ConsoleAppFactory.run(app);
    }
}
