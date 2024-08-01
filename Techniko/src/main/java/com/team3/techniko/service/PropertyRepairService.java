package com.team3.techniko.service;

import com.team3.techniko.model.PropertyRepair;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PropertyRepairService {

    private EntityManagerFactory entityManagerFactory;

    // Default constructor
    public PropertyRepairService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
    }

    // Constructor that accepts an EntityManagerFactory
    public PropertyRepairService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    // Save (either add or update) a property repair
    public void savePropertyRepair(PropertyRepair propertyRepair) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        if (propertyRepair.getRepairId() == null) {
            entityManager.persist(propertyRepair);
        } else {
            entityManager.merge(propertyRepair);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void addPropertyRepair(PropertyRepair propertyRepair) {
        savePropertyRepair(propertyRepair);
    }

    public void updatePropertyRepair(PropertyRepair propertyRepair) {
        savePropertyRepair(propertyRepair);
    }

    public void deletePropertyRepair(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        PropertyRepair propertyRepair = entityManager.find(PropertyRepair.class, id);
        if (propertyRepair != null) {
            entityManager.remove(propertyRepair);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public PropertyRepair getPropertyRepair(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PropertyRepair propertyRepair = entityManager.find(PropertyRepair.class, id);
        entityManager.close();
        return propertyRepair;
    }

    public List<PropertyRepair> getAllPropertyRepairs() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<PropertyRepair> query = entityManager.createQuery("from PropertyRepair", PropertyRepair.class);
        List<PropertyRepair> repairs = query.getResultList();
        entityManager.close();
        return repairs;
    }

    // Search repairs by date range
    public List<PropertyRepair> searchRepairsByDateRange(Date startDate, Date endDate) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<PropertyRepair> query = entityManager.createQuery(
                "from PropertyRepair where dateSubmitted between :startDate and :endDate", PropertyRepair.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<PropertyRepair> repairs = query.getResultList();
        entityManager.close();
        return repairs;
    }

    // Search repairs by owner ID
    public List<PropertyRepair> searchRepairsByOwnerId(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<PropertyRepair> query = entityManager.createQuery(
                "from PropertyRepair where owner.ownerId = :ownerId", PropertyRepair.class);
        query.setParameter("ownerId", ownerId);
        List<PropertyRepair> repairs = query.getResultList();
        entityManager.close();
        return repairs;
    }
}