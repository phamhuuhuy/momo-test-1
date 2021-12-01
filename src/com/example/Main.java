package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String qh = "01/12/2021";
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(qh);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date2 = new Date();
        String huy = formatter.format(date2);
        Date date3 = null;
        try {
            date3 = new SimpleDateFormat("dd/MM/yyyy").parse(huy);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(isSameDay(date1, date3));
        menu();
    }

    public static void menu(){
        Scanner sc = new Scanner(System.in);

        int total = 0;
        while(true) {
            boolean run = true;
            System.out.println("------------------------");
            System.out.println("Our machine has 3 products: Coke(10000) Pepsi(10000) Soda(20000)");
            System.out.println("Your budget: " + total);
            System.out.println("Please input your notes: ");
            System.out.println("1. 10.000");
            System.out.println("2. 20.000");
            System.out.println("3. 50.000");
            System.out.println("4. 100.000");
            System.out.println("5. 200.000");
            System.out.println("6. Exit");
            System.out.println("------------------------");
            while (run) {
                System.out.print("Your choice: ");
                String choice = sc.next();
                if (choice.equals("1")) {
                    total += 10000;
                } else if (choice.equals("2")) {
                    total += 20000;
                } else if (choice.equals("3")) {
                    total += 50000;
                } else if (choice.equals("4")) {
                    total += 100000;
                } else if (choice.equals("5")) {
                    total += 200000;
                }else if (choice.equals("6")) {
                    return;
                }else {
                    System.out.println("Wrong input");
                }
                while(true){
                    System.out.println("------------------------");
                    System.out.println("Do you wanna continue input notes (y/n): ");
                    System.out.println("------------------------");
                    System.out.print("Your choice: ");
                    String asking = sc.next();
                    if (asking.equals("y") || asking.equals("Y")) {
                        run = false;
                        break;
                    } else if (asking.equals("n") || asking.equals("N")) {

                        chooseProduct(total);
                        return;
                    } else {
                        System.out.println("Wrong input");
                    }
                }

            }
        }
    }

    public static void chooseProduct(int currentUserBudget){
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> cart = new HashMap<>();
        int total = 0;
        cart.put("Coke", 0);
        cart.put("Pepsi", 0);
        cart.put("Soda", 0);
        System.out.println("------------------------");

        while(true) {
            System.out.println("------------------------");
            System.out.println("Your total budget: " + currentUserBudget);
            System.out.println("Please input your product: ");
            System.out.println("1. Coke (10.000)");
            System.out.println("2. Pepsi (10.000)");
            System.out.println("3. Soda (20.000)");
            System.out.println("4. Exit");
            System.out.println("------------------------");
            boolean run = true;
            while (run) {
                System.out.print("Your choice: ");
                String choice = sc.next();
                if (choice.equals("1")) {
                    if (currentUserBudget >= 10000){
                        cart.put("Coke", cart.get("Coke") + 1);
                        currentUserBudget -= 10000;
                        total += 10000;
                    }else{
                        System.out.println("------------------------");
                        System.out.println("YOU DON'T HAVE ENOUGH MONEY");
                        System.out.println("------------------------");
                        break;
                    }
                } else if (choice.equals("2")) {
                    if (currentUserBudget >= 10000){
                        cart.put("Pepsi", cart.get("Pepsi") + 1);
                        currentUserBudget -= 10000;
                        total += 10000;
                    }else{
                        System.out.println("------------------------");
                        System.out.println("YOU DON'T HAVE ENOUGH MONEY");
                        System.out.println("------------------------");
                        break;
                    }

                } else if (choice.equals("3")) {
                    if (currentUserBudget >= 20000){
                        cart.put("Soda", cart.get("Soda") + 1);
                        currentUserBudget -= 20000;
                        total += 20000;
                    }else{
                        System.out.println("------------------------");
                        System.out.println("YOU DON'T HAVE ENOUGH MONEY");
                        System.out.println("------------------------");
                        break;
                    }
                }else if (choice.equals("4")) {
                    return;
                }else {
                    System.out.println("Wrong input");
                }
                if (currentUserBudget < 10000){
                    System.out.println("Your money do not enough for buy other products");

                    System.out.println(cart + " "+ total +" " + currentUserBudget);
                    paymentMethod(cart, total, currentUserBudget);
                    return;
                }else{
                    System.out.println("------------------------");
                    System.out.println("Do you wanna continue buy product (y/n): ");
                    System.out.println("------------------------");
                    System.out.print("Your choice: ");
                    String asking = sc.next();
                    if (asking.equals("y") || asking.equals("Y")) {
                        run = false;
                        break;
                    } else if (asking.equals("n") || asking.equals("N")) {
                        System.out.println(cart + " "+ total +" " + currentUserBudget);
                        paymentMethod(cart, total, currentUserBudget);
                        return;
                    } else {
                        System.out.println("Wrong input");
                    }
                }

            }
        }
    }

    public static void paymentMethod(HashMap<String, Integer> cart, int total, int currentUserBudget){
        Scanner sc = new Scanner(System.in);

        while(true) {
            boolean run = true;
            System.out.println("------------------------");
            System.out.println("Do you wanna buy or refund: ");
            System.out.println("1. Yes");
            System.out.println("2. No, Exit and Refund");
            System.out.println("------------------------");
            while (true) {
                System.out.print("Your choice: ");
                String choice = sc.next();
                if (choice.equals("1")) {

                }else if (choice.equals("2")) {
                    int refund = total + currentUserBudget;
                    System.out.println("Your refund is " + refund);
                    return;
                }else {
                    System.out.println("Wrong input");
                }
            }
        }
    }

    public static boolean isSameDay(Date date1, Date date2) {

        // Strip out the time part of each date.
        long julianDayNumber1 = date1.getTime();
        long julianDayNumber2 = date2.getTime();
        System.out.println(julianDayNumber1);
        System.out.println(julianDayNumber2);
        // If they now are equal then it is the same day.
        return julianDayNumber1 == julianDayNumber2;
    }
}
