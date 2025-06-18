package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankDB db = new BankDB();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 :
                {
                    System.out.print("Enter Account ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    db.createAccount(id, name);
                }
                case 2 :
                {
                    System.out.print("Enter Account ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Amount: ");
                    double amt = sc.nextDouble();
                    db.deposit(id, amt);
                }
                case 3 :
                {
                    System.out.print("Enter Account ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Amount: ");
                    double amt = sc.nextDouble();
                    db.withdraw(id, amt);
                }
                case 4 :
                {
                    System.out.print("Enter Account ID: ");
                    String id = sc.nextLine();
                    db.checkBalance(id);
                }
                case 5 :
                {
                    System.out.println("Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }
}