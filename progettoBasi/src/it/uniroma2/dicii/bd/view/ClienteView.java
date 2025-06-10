package it.uniroma2.dicii.bd.view;

import java.io.IOException;
import java.util.Scanner;

public class ClienteView {

    public static int showMenu() throws IOException {
        System.out.println("*********************************");
        System.out.println("*    AUCTIONS AGENCY DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** Seleziona un comando ***\n");
        System.out.println("1) Vedi aste aperte");
        System.out.println("2) Vedi aste in corso");
        System.out.println("3) Vedi oggetti acquistati");
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
