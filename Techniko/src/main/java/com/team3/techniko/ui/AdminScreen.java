package com.team3.techniko.ui;

import com.team3.techniko.Utils.Finals;
import com.team3.techniko.model.PropertyRepair;
import java.util.Scanner;

public class AdminScreen {
    private final Scanner scanner;

    public AdminScreen() {
        this.scanner = new Scanner(System.in);
    }
    
    public void homeScreen(){
        int input = -1;
        while (input != 0 & input != 1 & input != 2) {
            System.out.println(Finals.DELIMITER + "Please choose:\n1.Show Pending repairs\n2.Show all scheduled repairs\n0. Exit");
            while (!scanner.hasNextInt()) {
                System.out.println("Please insert a number...");
                scanner.next();
            }
            input = scanner.nextInt();
        }
        switch (input) {
            case 1:
                showPendingRequests();
            case 2:
                showAllScheduledRepairs();
            case 0:
                System.exit(input);
            default:
                break;
        }
    }
    
    public void showPendingRequests(){
        PropertyRepair propertyRepair = new PropertyRepair();
        //TODO Find All Properties where repair status = Pending
    }
    public void showAllScheduledRepairs(){
        
    }

}
