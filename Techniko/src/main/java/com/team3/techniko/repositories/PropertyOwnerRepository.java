package com.team3.techniko.repositories;

import com.team3.techniko.model.PropertyOwner;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertyOwnerRepository implements Repository<PropertyOwner, Long>{
    private final EntityManager entityManager;

    public PropertyOwnerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<PropertyOwner> findById(Long id) {
        try {
            entityManager.getTransaction().begin();
            PropertyOwner t = entityManager.find(getEntityClass(), id);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            log.debug("An exception occured");
        }
        return Optional.empty();  }

    @Override
    public List<PropertyOwner> findAll() {
       TypedQuery<PropertyOwner> query = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
        return query.getResultList();    }

    @Override
    public Optional<PropertyOwner> save(PropertyOwner t) {
          try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            log.debug("An exception occured");
        }
        return Optional.empty();    }

    @Override
    public boolean deleteById(Long id) {
        PropertyOwner persistentInstance = entityManager.find(getEntityClass(), id);
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
    
    
    private Class<PropertyOwner> getEntityClass() {
        return PropertyOwner.class;
    }

    private String getEntityClassName() {
        return PropertyOwner.class.getName();
        
    }
    
     @Override
    public List<PropertyOwner> findByUserId(Long userId) {
        TypedQuery<PropertyOwner> query = entityManager.createQuery("from " + getEntityClassName() + " where ownerId = :ownerId", getEntityClass());
        query.setParameter("ownerId", userId);
        return query.getResultList();
    }

    @Override
    public List<PropertyOwner> findByDateRange(Date startDate, Date endDate) {
        TypedQuery<PropertyOwner> query = entityManager.createQuery("from " + getEntityClassName() + " where date >= :startDate and date <= :endDate", getEntityClass());
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
