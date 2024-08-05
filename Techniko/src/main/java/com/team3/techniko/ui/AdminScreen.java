package com.team3.techniko.ui;

import com.team3.techniko.Utils.Finals;
import com.team3.techniko.model.PropertyRepair;
import com.team3.techniko.model.enums.Status;
import com.team3.techniko.repositories.RepositoryImpl;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdminScreen {

    private final Scanner scanner;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public AdminScreen() {
        this.scanner = new Scanner(System.in);
        this.entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void homeScreen() {
        int input = -1;
        while (input != 0 & input != 1 & input != 2) {
            System.out.println(Finals.DELIMITER + "Please choose:\n1.Show Pending repairs\n2.Show all scheduled repairs\n0.Exit");
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
            case 0:
                System.exit(input);
            default:
                break;
        }
    }

    public void showPendingRequests() {
        RepositoryImpl<PropertyRepair> propertyRepair = new RepositoryImpl(entityManager, PropertyRepair.class);
        List<PropertyRepair> pendingRepairs = propertyRepair.findPendingRepairs(Status.PENDING);
        if (pendingRepairs.isEmpty()) {
            System.out.println(Finals.DELIMITER + "\nEmpty List");
        } else {
            pendingRepairs.forEach(pr -> System.out.println(pr));
        }

    }

    public void showAllScheduledRepairs() {

    }

}
