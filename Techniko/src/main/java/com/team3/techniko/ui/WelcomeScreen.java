package com.team3.techniko.ui;

import com.team3.techniko.Utils.Finals;
import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.repositories.RepositoryImpl;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class WelcomeScreen {

    private final Scanner scanner;
    private final EntityManagerFactory entityManagerFactory ;
    private final EntityManager entityManager ;

    public WelcomeScreen() {
        this.scanner = new Scanner(System.in);
        this.entityManagerFactory= Persistence.createEntityManagerFactory("Technikon");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    
    public void login() {

        int login = -1;
        while (login != 0 & login != 1 & login != 2) {
            System.out.println(Finals.DELIMITER + "Please choose:\n1.Sign in\n2.Sign up\n0. Exit");
            while (!scanner.hasNextInt()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            login = scanner.nextInt();
        }
        switch (login) {
            case 1:
                validateUser();
            case 2:
                createUser();
            case 0:
                System.exit(login);
            default:
                break;
        }
    }

    public void validateUser() {
        System.out.println(Finals.DELIMITER + "\nPlease insert your username:");
        String username = scanner.next();
        System.out.println("Please insert your password:");
        String password = scanner.next();

        //TODO : find PropertyOwner or admin by username -> Id. New query on Repo?
    }

    public void createUser() {
        PropertyOwner propertyOwner = new PropertyOwner();

        System.out.println(Finals.DELIMITER + "\nPlease insert your Name:");
        propertyOwner.setName(scanner.next());
        System.out.println(Finals.DELIMITER + "\nPlease insert your Surname:");
        propertyOwner.setSurname(scanner.next());
        System.out.println(Finals.DELIMITER + "\nPlease insert your Email:");
        propertyOwner.setEmail(scanner.next());
        System.out.println(Finals.DELIMITER + "\nPlease insert your Username:");
        propertyOwner.setUsername(scanner.next());
        System.out.println(Finals.DELIMITER + "\nPlease insert your Password:");
        propertyOwner.setPassword(scanner.next());
        System.out.println(Finals.DELIMITER + "\nPlease insert your Vat number:");
        propertyOwner.setVatNumber(scanner.next());
        System.out.println(Finals.DELIMITER + "\nPlease insert your Address:");
        propertyOwner.setAddress(scanner.next());
        System.out.println(Finals.DELIMITER + "\nPlease insert your Phone number:");
        propertyOwner.setPhoneNumber(scanner.next());
        
        //TODO : save PropertyOwner using Services.
        RepositoryImpl repo = new RepositoryImpl(entityManager, PropertyOwner.class);
        repo.save(propertyOwner);

    }
}
