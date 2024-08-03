package com.team3.techniko.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepositoryImpl<T> implements Repository<T, Long> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;

    public RepositoryImpl(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> findById(Long id) {
        try {
            entityManager.getTransaction().begin();
            T t = entityManager.find(entityClass, id);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            log.debug("An exception occured");
        }
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = entityManager.createQuery("from " + entityClass.getName(), entityClass);
        return query.getResultList();
    }

    @Override
    public Optional<T> save(T t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            log.debug("An exception occured");
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        T persistentInstance = entityManager.find(entityClass, id);
        if (persistentInstance != null) {

            try {
                entityManager.getTransaction().begin();
                entityManager.remove(persistentInstance);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                log.debug("An exception occured");
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public List<T> findByUserId(Long userId) {
        TypedQuery<T> query = entityManager.createQuery("from " + entityClass.getName() + " where ownerId = :ownerId", entityClass);
        query.setParameter("ownerId", userId);
        return query.getResultList();
    }

    @Override
    public List<T> findByDateRange(Date startDate, Date endDate) {
        TypedQuery<T> query = entityManager.createQuery("from " + entityClass.getName() + " where date >= :startDate and date <= :endDate", entityClass);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    public List<T> findAllByUsername(String username) {
        TypedQuery<T> query
                = entityManager.createQuery("from " + entityClass.getName()
                        + " where username  like :username ",
                         entityClass)
                        .setParameter("username", username);
        return query.getResultList();
    }
}
