package com.team3.techniko.ui;

import com.team3.techniko.exceptions.InvalidDateFormatException;
import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.utils.Finals;
import com.team3.techniko.model.PropertyRepair;
import com.team3.techniko.model.enums.Status;
import com.team3.techniko.repositories.RepositoryImpl;
import com.team3.techniko.services.PropertyOwnerService;
import com.team3.techniko.services.PropertyRepairService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdminScreen {

    private final Scanner scanner;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final PropertyRepairService propertyRepairService;
    private final PropertyOwnerService propertyOwnerService;

    public AdminScreen() {
        this.scanner = new Scanner(System.in);
        this.entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
        this.entityManager = entityManagerFactory.createEntityManager();
        RepositoryImpl<PropertyRepair> propertyRepair = new RepositoryImpl(entityManager, PropertyRepair.class);
        this.propertyRepairService = new PropertyRepairService(propertyRepair);
        RepositoryImpl<PropertyOwner> propertyOwner = new RepositoryImpl(entityManager, PropertyOwner.class);
        this.propertyOwnerService = new PropertyOwnerService(propertyOwner);
    }

    public void homeScreen() {
        int input = -1;
        while (input < 0 || input > 4) {
            System.out.println(Finals.DELIMITER + "\nPlease choose:"
                    + "\n1.Show Pending repairs"
                    + "\n2.Show all Scheduled repairs"
                    + "\n3.Show all Completed repairs"
                    + "\n4.View Property Owners"
                    + "\n0.Exit");
            while (!scanner.hasNextInt()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            input = scanner.nextInt();
        }
        switch (input) {
            case 1:
                showPendingRequests();
                break;
            case 2:
                showAllScheduledRepairs();
                break;
            case 3:
                showAllCompletedRepairs();
                break;
            case 4:
                viewPropertyOwners();
                break;
            case 0:
                System.exit(input);
            default:
                break;
        }
    }

    // 4.4.1 Repair administration
    // 4.5 Reports
    // Case 1: Show All pending Requests.   
    public void showPendingRequests() {
        List<PropertyRepair> pendingRepairs = propertyRepairService.findPendingRepairs(Status.PENDING);
        if (pendingRepairs.isEmpty()) {
            System.out.println(Finals.DELIMITER + "\nEmpty List");
        } else {
            int i = 0;
            System.out.println(Finals.DELIMITER + "\nPlease choose the number of the chosen repair below:");
            for (PropertyRepair repair : pendingRepairs) {
                System.out.println(Finals.DELIMITER);
                i++;
                writeRepairDetails(repair, i);
            }
        }
        System.out.println(Finals.DELIMITER);
        int input = -1;
        while (input <= 0 || input > pendingRepairs.size()) {
            System.out.println("Please insert the number of the Pending request or 0 to Exit the console.");
            while (!scanner.hasNextInt()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            input = scanner.nextInt();
            if (input == 0) {
                System.exit(0);
            }
        }
        updatePendingRequest(pendingRepairs.get(input - 1));
        System.out.println(Finals.DELIMITER + "\n" + input);
    }

    //Case 2: Show All In progress Repairs. 
    public void showAllScheduledRepairs() {
        List<PropertyRepair> repairsInProgress = propertyRepairService.findPendingRepairs(Status.IN_PROGRESS);
        if (repairsInProgress.isEmpty()) {
            System.out.println(Finals.DELIMITER + "\nYou have not pending repairs.");
        } else {
            for (PropertyRepair repair : repairsInProgress) {
                System.out.println(Finals.DELIMITER);
                System.out.println("\n" + repair);
            }
        }
    }

    //Case 3: Show All completed Repairs.
    public void showAllCompletedRepairs() {
        List<PropertyRepair> completedRepairs = propertyRepairService.findPendingRepairs(Status.COMPLETED);
        if (completedRepairs.isEmpty()) {
            System.out.println(Finals.DELIMITER + "\nYou have not completed repairs.");
        } else {
            for (PropertyRepair repair : completedRepairs) {
                System.out.println(Finals.DELIMITER);
                System.out.println("\n" + repair);
            }
        }
    }

    private void writeRepairDetails(PropertyRepair repair, int i) {
        System.out.println("\n" + i + ". " + "repairId=" + repair.getRepairId() + ", repairType="
                + repair.getRepairType() + ", shortDescription=" + repair.getShortDescription()
                + ", dateSubmitted=" + repair.getDateSubmitted() + ", description=" + repair.getDescription());
    }

    // 4.4.2 Repair administration
    private void updatePendingRequest(PropertyRepair chosenPropertyRepair) {
        System.out.println("Please enter the cost: ");
        Double cost = -1.0;
        while (cost <= 0) {
            while (!scanner.hasNextDouble()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            cost = scanner.nextDouble();
        }
        chosenPropertyRepair.setProposedCost(cost);

        boolean wrongDates = true;
        while (wrongDates) {
            System.out.println("Please enter the proposed start date (yyyy-MM-dd): ");
            Date proposedStartDate = null;
            while (proposedStartDate == null) {
                String input = scanner.next();
                try {
                    proposedStartDate = dateFormat.parse(input);
                } catch (ParseException e) {
                    try {
                        throw new InvalidDateFormatException("Invalid date format. Please enter the date in yyyy-MM-dd format");
                    } catch (InvalidDateFormatException ex) {
                        ex.getMessage();
                    }
                }
            }
            chosenPropertyRepair.setProposedStartDate(proposedStartDate);

            System.out.println("Please enter the proposed end date (yyyy-MM-dd): ");
            Date proposedEndDate = null;
            while (proposedEndDate == null) {
                String input = scanner.next();
                try {
                    proposedEndDate = dateFormat.parse(input);
                } catch (ParseException e) {
                    try {
                        throw new InvalidDateFormatException("Invalid date format. Please enter the date in yyyy-MM-dd format:");
                    } catch (InvalidDateFormatException ex) {
                        ex.getMessage();
                    }
                }
            }
            chosenPropertyRepair.setProposedEndDate(proposedEndDate);

            if (proposedStartDate.after(proposedEndDate)) {
                System.out.println("Error: Proposed start date cannot be after proposed end date.");

            } else {
                wrongDates = false;
            }
        }
        chosenPropertyRepair.setOwnerAcceptance(false);
        propertyRepairService.save(chosenPropertyRepair);
    }

    private void viewPropertyOwners() {
        List<PropertyOwner> propertyOwners = propertyOwnerService.getAll();
        System.out.println(Finals.DELIMITER);
        for (PropertyOwner owner : propertyOwners) {
            System.out.println(owner);
        }
        int input = -1;
        while (input != 0 & input != 1) {
            System.out.println(Finals.DELIMITER + "\nPlease choose:"
                    + "\n1.Delete property owner."
                    + "\n0.Exit system.");
            while (!scanner.hasNextInt()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            input = scanner.nextInt();
            if (input == 0) {
                System.exit(0);
            }
        }
        deletePropertyOwner(propertyOwners);

    }

    private void deletePropertyOwner(List<PropertyOwner> propertyOwners) {
        long input = -1;
        while (input < 0) {
            System.out.println(Finals.DELIMITER + "\nPlease give me the id of the owner you want to delete or put 0 to exit");
            while (!scanner.hasNextLong()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            input = scanner.nextLong();
            for (PropertyOwner owner : propertyOwners) {
                if (owner.getOwnerId() == input) {
                    propertyOwnerService.deleteById(input);
                    System.out.println("Succesfully deleted owner.");
                    break;
                } else if (input == 0) {
                    System.exit(0);
                }
            }
        }
    }
}
