package org.example;

import org.example.entity.Movie;
import org.example.ui.tui.ConsoleApp;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        var sessionFactory = new Configuration().configure().buildSessionFactory();
        var app = new App(sessionFactory);

        var consoleApp = new ConsoleApp(app);
        consoleApp.run();
    }
}
