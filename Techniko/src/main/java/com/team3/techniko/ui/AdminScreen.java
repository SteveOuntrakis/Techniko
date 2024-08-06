package com.team3.techniko.ui;

import com.team3.techniko.Utils.Finals;
import com.team3.techniko.exceptions.InvalidDateFormatException;
import com.team3.techniko.utils.Finals;
import com.team3.techniko.model.PropertyRepair;
import com.team3.techniko.model.enums.Status;
import com.team3.techniko.repositories.RepositoryImpl;
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

    public AdminScreen() {
        this.scanner = new Scanner(System.in);
        this.entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void homeScreen() {
        int input = -1;
        while (input != 0 & input != 1 & input != 2 & input != 3) {
            System.out.println(Finals.DELIMITER + "Please choose:"
                    + "\n1.Show Pending repairs"
                    + "\n2.Show all Scheduled repairs"
                    + "\n3.Show all Completed repairs"
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
            case 0:
                System.exit(input);
            default:
                break;
        }
    }

    //Case 1: Show All pending Requests.
    public void showPendingRequests() {
        RepositoryImpl<PropertyRepair> propertyRepair = new RepositoryImpl(entityManager, PropertyRepair.class);
        List<PropertyRepair> pendingRepairs = propertyRepair.findPendingRepairs(Status.PENDING);
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
        RepositoryImpl<PropertyRepair> propertyRepair = new RepositoryImpl(entityManager, PropertyRepair.class);
        List<PropertyRepair> pendingRepairs = propertyRepair.findPendingRepairs(Status.IN_PROGRESS);
        if (pendingRepairs.isEmpty()) {
            System.out.println(Finals.DELIMITER + "\nEmpty List");
        } else {
            for (PropertyRepair repair : pendingRepairs) {
                System.out.println(Finals.DELIMITER);
                System.out.println("\n" + repair);
            }
        }
    }

    //Case 3: Show All completed Repairs.
    public void showAllCompletedRepairs() {
        RepositoryImpl<PropertyRepair> propertyRepair = new RepositoryImpl(entityManager, PropertyRepair.class);
        List<PropertyRepair> pendingRepairs = propertyRepair.findPendingRepairs(Status.COMPLETED);
        if (pendingRepairs.isEmpty()) {
            System.out.println(Finals.DELIMITER + "\nEmpty List");
        } else {
            for (PropertyRepair repair : pendingRepairs) {
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
                   // System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                    // TODO Δεν ειμαι σιγουρος αν αντι για sout θελει exception
                    throw new InvalidDateFormatException("Invalid date format. Please enter the date in yyyy-MM-dd format");
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
                   // System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                   throw new InvalidDateFormatException("Invalid date format. Please enter the date in yyyy-MM-dd format");
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
        RepositoryImpl repo = new RepositoryImpl(entityManager, PropertyRepair.class);
        repo.save(chosenPropertyRepair);
    }
}
