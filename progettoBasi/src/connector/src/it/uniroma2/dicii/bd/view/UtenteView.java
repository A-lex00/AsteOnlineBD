package it.uniroma2.dicii.bd.view;

import java.io.IOException;
import java.util.Scanner;

public class UtenteView {

    public static int showMenu() throws IOException {
        System.out.println("*********************************");
        System.out.println("*     AGENCY AUCTIONS DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** What should I do for you? ***\n");
        System.out.println("1) Book flight");
        System.out.println("2) Booking list");
        System.out.println("3) List flights");
        System.out.println("4) Quit");


        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 4) {
                break;
            }
            System.out.println("Invalid option");
        }

        return choice;
    }
}
