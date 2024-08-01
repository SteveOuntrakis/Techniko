package com.team3.techniko.service;

import com.team3.techniko.model.PropertyOwner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PropertyOwnerService {

    private EntityManagerFactory entityManagerFactory;

    public PropertyOwnerService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
    }

    public void savePropertyOwner(PropertyOwner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(owner);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public PropertyOwner getPropertyOwnerById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PropertyOwner owner = entityManager.find(PropertyOwner.class, id);
        entityManager.close();
        return owner;
    }

    public List<PropertyOwner> getAllPropertyOwners() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<PropertyOwner> owners = entityManager.createQuery("from PropertyOwner", PropertyOwner.class).getResultList();
        entityManager.close();
        return owners;
    }

    public void updatePropertyOwner(PropertyOwner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(owner);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deletePropertyOwner(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        PropertyOwner owner = entityManager.find(PropertyOwner.class, id);
        if (owner != null) {
            entityManager.remove(owner);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

