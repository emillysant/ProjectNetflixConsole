package org.example.repository;

import org.hibernate.SessionFactory;

public class CategoryRepository {

    public final SessionFactory sessionFactory;


    public CategoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
