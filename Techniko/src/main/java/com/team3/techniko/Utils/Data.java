package com.team3.techniko.utils;

import com.team3.techniko.model.Admin;
import com.team3.techniko.model.Property;
import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.model.PropertyRepair;
import com.team3.techniko.model.enums.PropertyType;
import com.team3.techniko.model.enums.RepairType;
import com.team3.techniko.model.enums.Status;
import com.team3.techniko.repositories.RepositoryImpl;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Data {

    public void insertDummyData() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Save PropertyOwners.
        RepositoryImpl repo = new RepositoryImpl(entityManager, PropertyOwner.class);
        PropertyOwner panagiotis = new PropertyOwner("23534543", "Panagiotis",
                "Kostopoulos", "Paris", "69349234", "panagiotis@gmail.com", "Pankos", "2222");
        PropertyOwner spiros = new PropertyOwner("235345", "Spiros",
                "Katiforis", "London", "69234324324", "spiros@gmail.com", "Spikat", "3333");
        PropertyOwner stefanos = new PropertyOwner("324123", "Stefanos",
                "Ountrakis", "New York", "69324234234", "stefanos@gmail.com", "Stevoun", "1234");
        repo.deleteById(1L);
        repo.save(panagiotis);
        repo.save(spiros);
        repo.save(stefanos);

        //Save Admins.
        RepositoryImpl repo2 = new RepositoryImpl(entityManager, Admin.class);
        repo2.save(new Admin("Katif", "katiforis@gmail.com", "1212"));
        repo2.save(new Admin("Pankost", "kostop@gmail.com", "1111"));
        repo2.save(new Admin("Stephen", "stef6754@gmail.com", "1234"));

        //Save Properties.
        RepositoryImpl repo4 = new RepositoryImpl(entityManager, Property.class);
        Property stef = new Property("Chania", PropertyType.HOUSE, stefanos);
        repo4.save(stef);
        repo4.save(new Property("Chania", PropertyType.APARTMENT, stefanos));
        Property spir = new Property("Athens", PropertyType.HOUSE, spiros);
        repo4.save(spir);
        repo4.save(new Property("Athens", PropertyType.APARTMENT, spiros));
        Property panos = new Property("Patra",PropertyType.HOUSE, panagiotis);
        repo4.save(panos);
        repo4.save(new Property("Patra", PropertyType.APARTMENT, panagiotis));

        //Save Property Repair.
        RepositoryImpl repo3 = new RepositoryImpl(entityManager, PropertyRepair.class);
        repo3.save(new PropertyRepair(RepairType.PLUMBING, "Losing water from toilet", new Date(), Status.PENDING, stef, stefanos));
        repo3.save(new PropertyRepair(RepairType.ELECTRICAL_WORK, "No lights in reception", new Date(), Status.PENDING, spir, spiros));
        repo3.save(new PropertyRepair(RepairType.INSULATION, "Need to do some reworking..", new Date(), Status.PENDING, panos, panagiotis));
        entityManager.close();
        entityManagerFactory.close();
    }
}
