package com.team3.techniko.repositories;

import com.team3.techniko.model.PropertyRepair;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertyRepairRepository implements Repository<PropertyRepair, Long> {
    private final EntityManager entityManager;

    public PropertyRepairRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Find a PropertyRepair by its ID
    @Override
    public Optional<PropertyRepair> findById(Long id) {
        try {
            entityManager.getTransaction().begin();
            PropertyRepair t = entityManager.find(getEntityClass(), id);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            log.debug("An exception occurred");
        }
        return Optional.empty();
    }

    // Find all PropertyRepairs
    @Override
    public List<PropertyRepair> findAll() {
        TypedQuery<PropertyRepair> query = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
        return query.getResultList();
    }

    // Save a new PropertyRepair
    @Override
    public Optional<PropertyRepair> save(PropertyRepair t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            log.debug("An exception occurred");
        }
        return Optional.empty();
    }

    // Delete a PropertyRepair by its ID
    @Override
    public boolean deleteById(Long id) {
        PropertyRepair persistentInstance = entityManager.find(getEntityClass(), id);
        if (persistentInstance != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(persistentInstance);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                log.debug("An exception occurred");
                return false;
            }
            return true;
        }
        return false;
    }

    // Find PropertyRepairs by user ID
    @Override
    public List<PropertyRepair> findByUserId(Long userId) {
        TypedQuery<PropertyRepair> query = entityManager.createQuery("from " + getEntityClassName() + " where ownerId = :ownerId", getEntityClass());
        query.setParameter("ownerId", userId);
        return query.getResultList();
    }

    // Find PropertyRepairs by date range
    @Override
    public List<PropertyRepair> findByDateRange(Date startDate, Date endDate) {
        TypedQuery<PropertyRepair> query = entityManager.createQuery("from " + getEntityClassName() + " where date >= :startDate and date <= :endDate", getEntityClass());
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    // Get the class of the entity
    private Class<PropertyRepair> getEntityClass() {
        return PropertyRepair.class;
    }

    // Get the class name of the entity
    private String getEntityClassName() {
        return PropertyRepair.class.getName();
    }
}