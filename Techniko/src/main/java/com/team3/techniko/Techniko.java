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
        //Repair Testing
//        Repository<PropertyRepair, Long> propertyRepairRepository = new PropertyRepairRepository(entityManager);
//        PropertyOwner owner = new PropertyOwner();
//        owner.setName("John");
//        owner.setAddress("456 Elm Street");  // Ensure address is set
//        owner.setEmail("john.doe@example.com"); // Ensure email is set
//        owner.setPassword("password123"); // Ensure password is set
//        owner.setPhoneNumber("6912313211");
//        owner.setSurname("Doe");
//        owner.setUsername("134789");
//        owner.setVatNumber("ghkdfaj12");
//
//        Property property = new Property();
//        property.setAddress("123 Main St");
//        property.setOwner(owner);
//        property.setPropertyType("lmao");
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(owner);
//        entityManager.persist(property);
//        entityManager.getTransaction().commit();
//
//        PropertyRepair repair = new PropertyRepair();
//        repair.setRepairType("Plumbing");
//        repair.setShortDescription("Fixing a leaking pipe");
//        repair.setDateSubmitted(new Date());
//        repair.setDescription("The pipe under the kitchen sink is leaking and needs to be fixed.");
//        repair.setProposedStartDate(new Date());
//        repair.setProposedEndDate(new Date());
//        repair.setProposedCost(200.0);
//        repair.setOwnerAcceptance(true);
//        repair.setRepairStatus(Status.PENDING);
//        repair.setProperty(property);
//        repair.setOwner(owner);
//
//        // Save the PropertyRepair
//        Optional<PropertyRepair> savedRepair = propertyRepairRepository.save(repair);
//        System.out.println("Saved Repair: " + savedRepair);
//
//        // Find the PropertyRepair by ID
//        Long repairId = savedRepair.map(PropertyRepair::getRepairId).orElse(null);
//        Optional<PropertyRepair> foundRepair = propertyRepairRepository.findById(repairId);
//        System.out.println("Found Repair: " + foundRepair);
//
//        // Update the PropertyRepair
//        foundRepair.ifPresent(r -> {
//            r.setRepairStatus(Status.IN_PROGRESS);
//            propertyRepairRepository.save(r);
//        });
//        Optional<PropertyRepair> updatedRepair = propertyRepairRepository.findById(repairId);
//        System.out.println("Updated Repair: " + updatedRepair);
//
//        // Delete the PropertyRepair
//        if (repairId != null) {
//            boolean isDeleted = propertyRepairRepository.deleteById(repairId);
//            System.out.println("Repair Deleted: " + isDeleted);
//        }
//
//        entityManager.close();
//        entityManagerFactory.close();
    }
}
