package org.example;

import org.example.entity.Movie;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        var sessionFactory = new Configuration().configure().buildSessionFactory();
        var session = sessionFactory.openSession();

        session.beginTransaction();

        var movie = new Movie();
        movie.setTitle("Movie");
        movie.setReleaseDate(LocalDate.now());
        session.persist(movie);

        session.getTransaction().commit();
        System.out.println("Movie ID="+movie.getId());

        session.close();
    }
}
