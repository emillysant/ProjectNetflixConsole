package org.example.repository;

import org.example.entity.Category;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * This class represents a repository for managing {@link Category} entities.
 * It provides methods for interacting with the database using Hibernate.
 */
public class CategoryRepository {
    /**
     * The Hibernate SessionFactory used for creating sessions.
     */
    public final SessionFactory sessionFactory;

    /**
     * Constructs a new CategoryRepository with the given SessionFactory.
     *
     * @param sessionFactory the Hibernate SessionFactory
     */
    public CategoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Retrieves all Category entities from the database.
     *
     * @return a list of Category entities
     */
    public List<Category> findAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Category", Category.class)
                    .getResultList();
        }
    }
}
