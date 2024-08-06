package com.team3.techniko;

import com.team3.techniko.model.Admin;
import com.team3.techniko.model.Property;
import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.model.PropertyRepair;
import com.team3.techniko.model.enums.Status;
import javax.persistence.*;
import com.team3.techniko.repositories.RepositoryImpl;
import com.team3.techniko.ui.WelcomeScreen;
import java.util.Date;

public class Techniko {

    public static void main(String[] args) throws Exception {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//                entityManager.getTransaction().begin();
//                entityManager.getTransaction().commit();
//        
//                entityManager.close();
//                entityManagerFactory.close();

//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        RepositoryImpl repo = new RepositoryImpl(entityManager, PropertyOwner.class);
//        PropertyOwner propertyOwner = new PropertyOwner("324123", "Stefanos",
//                "Ountrakis", "Kolimvari", "69324234234", "sountrakis@gmail.com", "Stevoun", "1234");
//        repo.save(propertyOwner);
//
//        RepositoryImpl repo2 = new RepositoryImpl(entityManager, Admin.class);
//        Admin admin = new Admin("Stephen", "stef6754@gmail.com", "1234");
//        repo2.save(admin);
//        
//        RepositoryImpl repo4 = new RepositoryImpl(entityManager, Property.class);
//        Property property = new Property("Chania","Hotel",propertyOwner);
//        repo4.save(property);
//        
//        RepositoryImpl repo3 = new RepositoryImpl(entityManager, Property.class);
//        repo3.save(new PropertyRepair("dffw", "fwf", new Date(), Status.PENDING, property, propertyOwner));
//        entityManager.close();
//        entityManagerFactory.close();
    new WelcomeScreen().login();
    }
}
