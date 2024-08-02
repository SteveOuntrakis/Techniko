package com.team3.techniko;

import com.team3.techniko.model.PropertyOwner;
import javax.persistence.*;
import com.team3.techniko.repositories.PropertyOwnerRepository;
import com.team3.techniko.repositories.PropertyRepository;

public class Techniko {

    public static void main(String[] args) {
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
                EntityManager entityManager = entityManagerFactory.createEntityManager();
        //
        //        entityManager.getTransaction().begin();
        //        entityManager.getTransaction().commit();
        //
        //        entityManager.close();
        //        entityManagerFactory.close();
        PropertyOwnerRepository ownerRepository = new PropertyOwnerRepository(entityManager);
        ownerRepository.save(new PropertyOwner("324123","Stefanos",
                      "Ountrakis","Kolimvari","69324234234","sountrakis@gmail.com","Stevoun","1234"));
        System.out.println(ownerRepository.findAll());
      
        PropertyRepository propertyRepository = new PropertyRepository(entityManagerFactory);
    
    }
}
