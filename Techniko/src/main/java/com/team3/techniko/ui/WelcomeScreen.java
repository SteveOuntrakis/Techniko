package com.team3.techniko.ui;

import com.team3.techniko.util.Finals;
import com.team3.techniko.model.Admin;
import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.model.PropertyRepair;
import com.team3.techniko.repositories.RepositoryImpl;
import com.team3.techniko.services.AdminService;
import com.team3.techniko.services.PropertyOwnerService;
import java.util.Optional;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class WelcomeScreen {

    private final Scanner scanner;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final PropertyOwnerService propertyOwnerService;
    private final AdminService adminService;

    public WelcomeScreen() {
        this.scanner = new Scanner(System.in);
        this.entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
        this.entityManager = entityManagerFactory.createEntityManager();

        RepositoryImpl<PropertyOwner> propertyOwnerRepo = new RepositoryImpl<>(entityManager, PropertyOwner.class);
        RepositoryImpl<Admin> adminRepo = new RepositoryImpl<>(entityManager, Admin.class);
        RepositoryImpl<PropertyRepair> propertyRepairRepo = new RepositoryImpl<>(entityManager, PropertyRepair.class);
        this.propertyOwnerService = new PropertyOwnerService(propertyOwnerRepo);
        this.adminService = new AdminService(adminRepo, propertyRepairRepo);
    }

    public void login() throws Exception {
        int login = -1;
        while (login != 0 && login != 1 && login != 2) {
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
            if (username.equals("exit")) {
                System.exit(0);
            }

            Optional<PropertyOwner> propertyOwner = propertyOwnerService.findOwnerByUsername(username);
            if (!propertyOwner.isPresent()) {
                Optional<Admin> admin = adminService.findAdminByUsername(username);
                System.out.println(Finals.DELIMITER + "\nPlease insert your password:");
                String password = scanner.next();
                if (!admin.isPresent()) {
                    System.out.println(Finals.DELIMITER + "\nInvalid Password or username, please try again");
                    validation = true;
                } else {
                    validation = !adminService.validateAdminsPassword(password, admin.get());
                    if (!validation) {
                        System.out.println(Finals.DELIMITER + "\nWelcome in Admin");
                        new AdminScreen().homeScreen();
                    }
                }
            } else {
                System.out.println(Finals.DELIMITER + "\nPlease insert your password:");
                String password = scanner.next();
                validation = !propertyOwnerService.validatePropertyOwnerPassword(password, propertyOwner.get());
                if (!validation) {
                    new PropertyOwnerScreen().homeScreen(propertyOwner.get());
                }
            }
        }
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
        propertyOwnerService.save(propertyOwner);
    }
}
