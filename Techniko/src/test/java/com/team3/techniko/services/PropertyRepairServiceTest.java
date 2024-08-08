package com.team3.techniko.services;

import com.team3.techniko.model.PropertyRepair;
import com.team3.techniko.model.enums.Status;
import com.team3.techniko.model.enums.RepairType;
import com.team3.techniko.model.Property;
import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.model.enums.PropertyType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyRepairServiceTest {

    @Test
    void testFindPendingRepairs() {
        PropertyOwner owner = new PropertyOwner("34543435", "John", "Doe", "Thessaloniki", "690324324", "john@example.com", "joys", "21213");
        Property property = new Property("123 Main St", PropertyType.APARTMENT, owner);
        PropertyRepair repair1 = new PropertyRepair(RepairType.ELECTRICAL_WORK, "Repair 1", new Date(), Status.PENDING, property, owner);
        PropertyRepair repair2 = new PropertyRepair(RepairType.PLUMBING, "Repair 2", new Date(), Status.COMPLETED, property, owner);

        List<PropertyRepair> repairs = Arrays.asList(repair1, repair2);
        
        List<PropertyRepair> pendingRepairs = repairs.stream()
                .filter(repair -> repair.getStatus() == Status.PENDING)
                .toList();

        assertEquals(1, pendingRepairs.size());
        assertEquals(Status.PENDING, pendingRepairs.get(0).getStatus());
        assertEquals("Repair 1", pendingRepairs.get(0).getShortDescription());
    }

    @Test
    void testFindAllByPropertyId() {
        PropertyOwner owner = new PropertyOwner("34543435", "John", "Doe", "Thessaloniki", "690324324", "john@example.com", "joys", "21213");
        Property property1 = new Property("123 Main St", PropertyType.APARTMENT, owner);
        Property property2 = new Property("126 Main St", PropertyType.DETACHED_HOUSE, owner);

        PropertyRepair repair1 = new PropertyRepair(RepairType.ELECTRICAL_WORK, "Repair 1", new Date(), Status.PENDING, property1, owner);
        PropertyRepair repair2 = new PropertyRepair(RepairType.PLUMBING, "Repair 2", new Date(), Status.COMPLETED, property2, owner);

        List<PropertyRepair> repairs = Arrays.asList(repair1, repair2);

        List<PropertyRepair> repairsForProperty1 = repairs.stream()
                .filter(repair -> repair.getProperty().equals(property1))
                .toList();

        assertEquals(1, repairsForProperty1.size());
        assertEquals(property1, repairsForProperty1.get(0).getProperty());
        assertEquals("Repair 1", repairsForProperty1.get(0).getShortDescription());
    }

    @Test
    void testFindPendingRepairsForID() {
        PropertyOwner owner = new PropertyOwner("34543435", "John", "Doe", "Thessaloniki", "690324324", "john@example.com", "joys", "21213");
        Property property = new Property("123 Main St", PropertyType.APARTMENT, owner);

        PropertyRepair repair1 = new PropertyRepair(RepairType.FRAMES, "Repair 1", new Date(), Status.PENDING, property, owner);
        PropertyRepair repair2 = new PropertyRepair(RepairType.PLUMBING, "Repair 2", new Date(), Status.PENDING, property, owner);

        List<PropertyRepair> repairs = Arrays.asList(repair1, repair2);

        List<PropertyRepair> pendingRepairs = repairs.stream()
                .filter(repair -> repair.getStatus() == Status.PENDING && repair.getProperty().equals(property))
                .toList();

        assertEquals(2, pendingRepairs.size());
        assertEquals(property, pendingRepairs.get(0).getProperty());
        assertEquals(Status.PENDING, pendingRepairs.get(0).getStatus());
    }
}
