package com.team3.techniko.ui;

import com.team3.techniko.model.Property;
import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.model.PropertyRepair;
import com.team3.techniko.model.enums.PropertyType;
import com.team3.techniko.model.enums.RepairType;
import com.team3.techniko.model.enums.Status;
import com.team3.techniko.repositories.RepositoryImpl;
import com.team3.techniko.utils.Finals;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The PropertyOwnerScreen class provides a user interface for property owners
 * to manage their properties and repairs.
 */
public class PropertyOwnerScreen {

    private final Scanner scanner;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    /**
     * Constructor for PropertyOwnerScreen. Initializes the scanner,
     * entityManagerFactory, and entityManager.
     */
    public PropertyOwnerScreen() {
        this.scanner = new Scanner(System.in);
        this.entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Displays the home screen for the property owner.
     *
     * @param owner The property owner.
     */
    public void homeScreen(PropertyOwner owner) {
        int input = -1;
        while (input != 0) {
            System.out.println(Finals.DELIMITER + "Please choose:"
                    + "\n1. Show My Properties"
                    + "\n0. Exit");
            while (!scanner.hasNextInt()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    showProperties(owner);
                    break;
                case 0:
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    /**
     * Displays the properties owned by the property owner.
     *
     * @param owner The property owner.
     */
    private void showProperties(PropertyOwner owner) {
        RepositoryImpl<Property> propertyRepository = new RepositoryImpl<>(entityManager, Property.class);
        List<Property> properties = propertyRepository.findPropertiesByUserID(owner.getOwnerId());
        if (properties.isEmpty()) {
            System.out.println("No properties found.");
        } else {
            properties.forEach(property -> System.out.println("Property ID: " + property.getPropertyId() + ", Address: " + property.getAddress()));
        }

        int input = -1;
        while (input != 0) {
            System.out.println(Finals.DELIMITER + "Please choose:"
                    + "\n1. Add New Property"
                    + "\n2. View Property Details"
                    + "\n0. Exit");
            while (!scanner.hasNextInt()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    addNewProperty(owner);
                    break;
                case 2:
                    viewPropertyDetails(owner);
                    break;
                case 0:
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    /**
     * Adds a new property for the property owner.
     *
     * @param owner The property owner.
     */
    private void addNewProperty(PropertyOwner owner) {
        System.out.println("Enter Property Address:");
        scanner.nextLine(); // consume newline
        String address = scanner.nextLine();
        System.out.println("Enter Year of Construction:");
        int yearOfConstruction = scanner.nextInt();
        System.out.println("Choose Property Type: \n1. HOUSE \n2. APARTMENT");
        int propertyTypeInput = scanner.nextInt();
        PropertyType propertyType = PropertyType.values()[propertyTypeInput - 1];

        Property newProperty = new Property();
        newProperty.setAddress(address);
        newProperty.setYearOfConstruction(yearOfConstruction);
        newProperty.setPropertyType(propertyType);
        newProperty.setOwner(owner);  // Set the property owner

        RepositoryImpl<Property> propertyRepository = new RepositoryImpl<>(entityManager, Property.class);
        propertyRepository.save(newProperty);
        System.out.println("New property added successfully.");
    }

    /**
     * Displays the details of a property.
     */
    private void viewPropertyDetails(PropertyOwner owner) {
        RepositoryImpl<Property> propertyRepository = new RepositoryImpl<>(entityManager, Property.class);
        List<Property> properties = propertyRepository.findPropertiesByUserID(owner.getOwnerId());
        if (properties.isEmpty()) {
            System.out.println("No properties found.");
            return;
        } else {
            properties.forEach(property -> System.out.println("Property ID: " + property.getPropertyId() + ", Address: " + property.getAddress()));
        }
        int input = -1;
        while (input <= 0 || input > properties.size()) {
            System.out.println("Enter property ID to view details or 0 to Exit:");
            while (!scanner.hasNextInt()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            input = scanner.nextInt();

            if (input == 0) {
                System.exit(0);
            }
        }
        showRepairsForProperty(properties.get(input - 1));
    }

    /**
     * Displays the repairs for a property and provides options to request a new
     * repair, accept/refuse a repair, or update property details.
     *
     * @param propertyId The ID of the property.
     */
    private void showRepairsForProperty(Property chosenProperty) {
        RepositoryImpl<PropertyRepair> repairRepository = new RepositoryImpl<>(entityManager, PropertyRepair.class);
        List<PropertyRepair> repairs = repairRepository.findAllByPropertyId(chosenProperty.getPropertyId());
        if (repairs.isEmpty()) {
            System.out.println("No repairs found for this property.");
        } else {
            repairs.forEach(repair -> System.out.println(repair.toString()));
        }

        int input = -1;
        while (input != 0 & input != 1 & input != 2 & input != 3) {
            System.out.println(Finals.DELIMITER + "\nPlease choose:"
                    + "\n1. Request a New Repair"
                    + "\n2. Accept/Refuse Repair"
                    + "\n3. Update Property Details"
                    + "\n0. Exit");
            while (!scanner.hasNextInt()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            input = scanner.nextInt();
        }

        switch (input) {
            case 1:
                requestNewRepair(chosenProperty.getPropertyId());
                break;
            case 2:
                acceptOrRefuseRepair(chosenProperty.getPropertyId());
                break;
            case 3:
                updatePropertyDetails(chosenProperty.getPropertyId());
                break;
            case 0:
                System.exit(input);
            default:
                break;
        }
    }

    /**
     * Requests a new repair for a property.
     *
     * @param propertyId The ID of the property.
     */
    private void requestNewRepair(long propertyId) {
        System.out.println(Finals.DELIMITER + "\nChoose type of repair: \n1. PAINTING \n2. INSULATION \n3. FRAMES \n4. PLUMBING \n5. ELECTRICAL_WORK");
        int repairTypeInput = scanner.nextInt();
        RepairType repairType = RepairType.values()[repairTypeInput - 1];
        System.out.println("Enter short description of the repair:");
        scanner.nextLine(); // consume newline
        String description = scanner.nextLine();

        PropertyRepair newRepair = new PropertyRepair();
        newRepair.setProperty(entityManager.find(Property.class, propertyId));
        newRepair.setRepairType(repairType);
        newRepair.setDescription(description);
        newRepair.setDateSubmitted(new Date());
        newRepair.setStatus(Status.PENDING); // Set the status to PENDING
        newRepair.setOwnerAcceptance(false); // Initial acceptance state

        RepositoryImpl<PropertyRepair> repairRepository = new RepositoryImpl<>(entityManager, PropertyRepair.class);
        repairRepository.save(newRepair);
        System.out.println("Repair request submitted successfully.");
    }

    /**
     * Allows the property owner to accept or refuse a repair.
     *
     * @param propertyId The ID of the property.
     */
    private void acceptOrRefuseRepair(long propertyId) {
        RepositoryImpl<PropertyRepair> repairRepository = new RepositoryImpl<>(entityManager, PropertyRepair.class);
        List<PropertyRepair> repairs = repairRepository.findPendingRepairsForID(Status.PENDING, propertyId);
        if (repairs.isEmpty()) {
            int input = -1;
            while (input != 0) {
                System.out.println(Finals.DELIMITER + "\nNo repairs found for this property."
                        + "\ntype 1 for going back or 0 for exit:");
                while (!scanner.hasNextInt()) {
                    System.out.println("Please insert a number...");
                    scanner.next();
                }
                input = scanner.nextInt();

            }
            if (input == 0) {
                System.exit(input);
            } else {
                return;
            }
        } else {

            System.out.println(Finals.DELIMITER);
            repairs.forEach(repair -> {

                System.out.println("Repair ID: " + repair.getRepairId()
                        + ", Description: " + repair.getDescription()
                        + ", Cost: " + repair.getProposedCost()
                        + ", Status: " + repair.getStatus()
                        + ", Date start :" + repair.getProposedStartDate()
                        + ", Date end :" + repair.getProposedEndDate());
            });
        }

        System.out.println(Finals.DELIMITER + "\nEnter Repair ID to accept/refuse:");
        long repairId = scanner.nextLong();
        PropertyRepair repair = entityManager.find(PropertyRepair.class, repairId);
        if (repair != null) {
            System.out.println(Finals.DELIMITER + "\nDo you accept this repair? (yes/no):");
            String decision = scanner.next();
            if ("yes".equalsIgnoreCase(decision)) {
                repair.setOwnerAcceptance(true);
                repair.setStatus(Status.IN_PROGRESS);
                System.out.println("Repair accepted.");
            } else {
                repair.setOwnerAcceptance(false);
                repair.setStatus(Status.CANCELLED);
                System.out.println("Repair refused.");
            }
            entityManager.getTransaction().begin();
            entityManager.merge(repair);
            entityManager.getTransaction().commit();
        } else {
            System.out.println("Repair not found.");
        }
    }

    /**
     * Updates the details of a property.
     *
     * @param propertyId The ID of the property.
     */
    private void updatePropertyDetails(long propertyId) {
        Property property = entityManager.find(Property.class, propertyId);
        if (property != null) {
            scanner.nextLine(); // consume newline
            System.out.println("Do you want to update the address? (yes/no):");
            String decision = scanner.nextLine();
            if ("yes".equalsIgnoreCase(decision)) {
                System.out.println("Enter new address:");
                String newAddress = scanner.nextLine();
                property.setAddress(newAddress);
            }

            System.out.println("Do you want to update the year of construction? (yes/no):");
            decision = scanner.nextLine();
            if ("yes".equalsIgnoreCase(decision)) {
                System.out.println("Enter new year of construction:");
                int newYearOfConstruction = scanner.nextInt();
                property.setYearOfConstruction(newYearOfConstruction);
                scanner.nextLine(); // consume newline
            }

            System.out.println("Do you want to update the property type? (yes/no):");
            decision = scanner.nextLine();
            if ("yes".equalsIgnoreCase(decision)) {
                System.out.println("Choose new property type: \n1. HOUSE \n2. APARTMENT");
                int propertyTypeInput = scanner.nextInt();
                PropertyType newPropertyType = PropertyType.values()[propertyTypeInput - 1];
                property.setPropertyType(newPropertyType);
                scanner.nextLine(); // consume newline
            }

            entityManager.getTransaction().begin();
            entityManager.merge(property);
            entityManager.getTransaction().commit();
            System.out.println("Property details updated successfully.");
        } else {
            System.out.println("Property not found.");
        }
    }
}
