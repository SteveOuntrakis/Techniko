package com.team3.techniko.ui;

import com.team3.techniko.Utils.Finals;
import com.team3.techniko.exceptions.UserNotFoundException;
import com.team3.techniko.model.Admin;
import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.repositories.RepositoryImpl;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class WelcomeScreen {

    private final Scanner scanner;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public WelcomeScreen() {
        this.scanner = new Scanner(System.in);
        this.entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void login() throws Exception {
        int login = -1;
        while (login != 0 & login != 1 & login != 2) {
            System.out.println(Finals.DELIMITER + "\nPlease choose:\n1.Sign in\n2.Sign up\n0.Exit");
            while (!scanner.hasNextInt()) {
                System.out.println(Finals.DELIMITER + "\nPlease insert a number...");
                scanner.next();
            }
            login = scanner.nextInt();
        }
        switch (login) {
            case 1:
                validateUser();
                break;
            case 2:
                createUser();
                break;
            case 0:
                System.exit(login);
            default:
                break;
        }
    }

    public void validateUser() throws Exception {
        boolean validation = true;
        while (validation) {
            System.out.println(Finals.DELIMITER + "\nPlease insert your username or type 'exit' to leave console:");
            String username = scanner.next();
            PropertyOwner propertyOwner = findOwnerByUsername(username);
            if (username.equals("exit")) {
                System.exit(0);
            }
            if (propertyOwner == null) {
                Admin admin = findAdminByUsername(username);
                System.out.println(Finals.DELIMITER + "\nPlease insert your password:");
                String password = scanner.next();
                if (admin == null) {
                    System.out.println(Finals.DELIMITER + "\nInvalid Password or username, please try again");
                    validation = true;
                } else {
                    validation = validateAdminsPassword(password, admin);
                }

            } else {
                System.out.println(Finals.DELIMITER + "\nPlease insert your password:");
                String password = scanner.next();
                validation = validatePropertyOwnerPassword(password, propertyOwner);
            }

        }

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

    //TODO : put the classes below on Service Class.
    public PropertyOwner findOwnerByUsername(String username) throws Exception {

        RepositoryImpl<PropertyOwner> propertyOwner = new RepositoryImpl(entityManager, PropertyOwner.class);

        if (username == null) {
            //TODO : log System.out.println("Null Username name was given");
        }
        if (!username.chars().allMatch(Character::isLetter)) {
            throw new Exception();
        }
        try {
            List<PropertyOwner> owners = propertyOwner.findAllByUsername(username);
            return owners.getFirst();

        } catch (Exception e) {
            throw new UserNotFoundException("An error occurred wile fetching theproperty owner:  " + e.getMessage() );
           // return null;
        }

    }

    public Admin findAdminByUsername(String username) throws Exception {
        RepositoryImpl<Admin> admin = new RepositoryImpl(entityManager, Admin.class);

        List<Admin> admins = admin.findAllByUsername(username);

        if (admins.isEmpty()) {
            return null;
        }
        return admins.getFirst();
    }

    public boolean validateAdminsPassword(String password, Admin admin) {
        if (password.equals(admin.getPassword())) {
            System.out.println(Finals.DELIMITER + "\nWelcome in Admin");
            new AdminScreen().homeScreen();
            return false;
        } else {
            System.out.println(Finals.DELIMITER + "\nInvalid Password or username, please try again");
            return true;
        }

    }

    public boolean validatePropertyOwnerPassword(String password, PropertyOwner owner) {
        if (password.equals(owner.getPassword())) {
            System.out.println(Finals.DELIMITER + "\nWelcome in Owner");
            new PropertyOwnerScreen().homeScreen(owner);
            return false;
        } else {
            System.out.println(Finals.DELIMITER + "\nInvalid Password or username, please try again");
            return true;
        }
    }
}
