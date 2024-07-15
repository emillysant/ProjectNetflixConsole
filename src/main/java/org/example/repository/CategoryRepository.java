package org.example.repository;

import org.example.entity.Category;
import org.hibernate.SessionFactory;

import java.util.List;

public class CategoryRepository {
    public final SessionFactory sessionFactory;

    public CategoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Category> findAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Category", Category.class)
                    .getResultList();
        }
    }
}
