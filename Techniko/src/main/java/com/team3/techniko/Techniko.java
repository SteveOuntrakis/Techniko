package com.team3.techniko;

import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.service.AdminService;
import com.team3.techniko.service.PropertyOwnerService;
import javax.persistence.*;

public class Techniko {

    public static void main(String[] args) {
        
//        
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        entityManager.getTransaction().begin();
//        entityManager.getTransaction().commit();
//
//        entityManager.close();
//        entityManagerFactory.close();
        PropertyOwnerService propertyOwnerService = new PropertyOwnerService();
//        propertyOwnerService.savePropertyOwner(new PropertyOwner("324123","Stefanos",
//                "Ountrakis","Kolimvari","69324234234","sountrakis@gmail.com","Stevoun","1234"));
        System.out.println(propertyOwnerService.getAllPropertyOwners());
    }}
