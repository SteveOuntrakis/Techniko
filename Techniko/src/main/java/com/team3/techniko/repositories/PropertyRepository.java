/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.techniko.repositories;

import com.team3.techniko.model.Property;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static javax.persistence.Persistence.createEntityManagerFactory;

/**
 *
 * @author Panagiotis
 */
public class PropertyRepository {
    private EntityManagerFactory entityManagerFactory;
    
    public PropertyRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory =  entityManagerFactory;
    }
    
    public void saveProperty(Property property) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(property);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public Property getPropertyById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Property property = entityManager.find(Property.class, id);
        entityManager.close();
        return property;
    }

    public List<Property> getAllProperties() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Property> properties = entityManager.createQuery("from Property", Property.class).getResultList();
        entityManager.close();
        return properties;
    }

    public void updateProperty(Property property) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(property);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteProperty(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Property property = entityManager.find(Property.class, id);
        if (property != null) {
            entityManager.remove(property);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    } 


}
