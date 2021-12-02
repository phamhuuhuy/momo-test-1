package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    //define variables
    private static int limitedBudget = 50000;
    private static double winRateIncrease = 0.5;
    private static double percentageWinRate = 0.1;

    //define formatter for format Date
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static Date date = new Date();
    private static String stringDate = formatter.format(date);
    private static Date timeMachineRun = null;


    public static void main(String[] args) {
        //set the date when machine start
        try {
            timeMachineRun = formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //call menu function
        menu();
    }

    public static void menu(){
        // create Scanner to user input
        Scanner sc = new Scanner(System.in);

        // total notes
        int total = 0;
        //loop until they exit program
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

            //loop until choose exit or do not continue input notes (go to choose product)
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
                }else if (choice.equals("6")) { // Exit program
                    return;
                }else {
                    //Wrong input, input again
                    System.out.println("Wrong input");
                }
                while(true){
                    //Ask user to continue adding notes or go to next step (choose product)
                    System.out.println("------------------------");
                    System.out.println("Do you wanna continue input notes or go to choose product (y/n): ");
                    System.out.println("------------------------");
                    System.out.print("Your choice: ");
                    String asking = sc.next();
                    if (asking.equals("y") || asking.equals("Y")) {
                        // if yes, run = false to stop choice and run loop out of it
                        run = false;
                        break;
                    } else if (asking.equals("n") || asking.equals("N")) {
                        // if no, go to choose product
                        chooseProduct(total);
                        return;
                    } else {
                        //if wrong, user can input again
                        System.out.println("Wrong input");
                    }
                }
            }
        }
    }

    public static void chooseProduct(int currentUserBudget){
        Scanner sc = new Scanner(System.in);
        //Create hashmap to store cart of user
        HashMap<String, Integer> cart = new HashMap<>();
        // total payment
        int total = 0;
        //initialize values
        cart.put("Coke", 0);
        cart.put("Pepsi", 0);
        cart.put("Soda", 0);
        System.out.println("------------------------");

        while(true) {
            //show current budget and cart of user
            System.out.println("------------------------");
            System.out.println("Your total budget: " + currentUserBudget);
            System.out.println("------------------------");
            System.out.println("Your Cart:");
            for (String key: cart.keySet()) {
                if (cart.get(key) > 0){
                    System.out.println("    " + key + ": " + cart.get(key));
                }
            }
            System.out.println("------------------------");
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
                    //check budget is enough or not
                    if (currentUserBudget >= 10000){
                        // increase the quantity
                        cart.put("Coke", cart.get("Coke") + 1);
                        // minus current budget of user
                        currentUserBudget -= 10000;
                        // increase payment
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
                    //exit program
                    return;
                }else {
                    System.out.println("Wrong input");
                }


                if (currentUserBudget < 10000){
                    // check if budget is not enough to continue buy, call payment method
                    System.out.println("Your money do not enough for buy other products");

                    paymentMethod(cart, total, currentUserBudget);
                    return;
                }else{
                    //if enough, user can choose another product
                    System.out.println("------------------------");
                    System.out.println("Do you wanna continue buy product (y/n): ");
                    System.out.println("------------------------");
                    System.out.print("Your choice: ");
                    String asking = sc.next();
                    if (asking.equals("y") || asking.equals("Y")) {
                        run = false;
                        break;
                    } else if (asking.equals("n") || asking.equals("N")) {
                        //if no, call payment method
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
            //show all cart, user's budget, total payment
            System.out.println("------------------------");
            System.out.println("Your Cart:");
            for (String key: cart.keySet()) {
                if (cart.get(key) > 0){
                    System.out.println("    " + key + ": " + cart.get(key));
                }
            }
            System.out.println("Your total payment: " + total);
            System.out.println("Your budget: " + currentUserBudget);
            System.out.println("------------------------");
            System.out.println("Do you wanna buy or refund: ");
            System.out.println("1. Yes");
            System.out.println("2. No, Exit and Refund");
            System.out.println("------------------------");
            while (true) {
                System.out.print("Your choice: ");
                String choice = sc.next();
                if (choice.equals("1")) {
                    // take date of user buy pay the bill
                    Date date2 = new Date();
                    // format Date to String to get dd/mm/yyyy (do not get time)
                    String stringDate1 = formatter.format(date2);
                    Date timeOfPayment = null;
                    try {
                        // parse String to Date
                        timeOfPayment = formatter.parse(stringDate1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Your Cart:");
                    for (String key: cart.keySet()) {
                        if (cart.get(key) > 0){
                            System.out.println("    " + key + ": " + cart.get(key));
                        }
                    }
                    System.out.println("Your total payment: " + total);
                    System.out.println("Your budget: " + currentUserBudget);

                    //if difference of two dates is more than 0
                    if (differenceOfTwoDates(timeOfPayment, timeMachineRun) > 0){
                        if (limitedBudget > 0){
                            // if limited budget of bonus is more than 0, do not reach so increase the chance
                            // the increase chance is calculating by using formula : percent of win rate * (1 + win rate increase)
                            percentageWinRate = percentageWinRate * Math.pow((1+ winRateIncrease), differenceOfTwoDates(timeOfPayment, timeMachineRun));
                        }else{
                            percentageWinRate = 0.1;
                        }
                        limitedBudget = 50000;
                        timeMachineRun = timeOfPayment;
                    }


                    boolean checkBonus = false;
                    for (String key: cart.keySet()){
                        int timesBonus = cart.get(key);
                        for(int i  = 0 ; i < timesBonus/3 ; i++) {
                            // check if successfully get a free product
                            if (promotion(percentageWinRate)) {
                                if (limitedBudget - (key.equals("Soda") ? 20000 : 10000) >= 0){
                                    checkBonus = true;
                                    cart.put(key, cart.get(key) + 1); // add 1 free product
                                    // decrease limitedBudget if user get a free product
                                    limitedBudget -= key.equals("Soda") ? 20000 : 10000;
                                    System.out.println("Congratulation! You receive 1 free " + key);

                                }
                            }
                        }
                    }
                    if (checkBonus){
                        System.out.println("Now! Your Cart:");
                        for (String key: cart.keySet()) {
                            if (cart.get(key) > 0){
                                System.out.println("    " + key + ": " + cart.get(key));
                            }
                        }
                        System.out.println("Your total payment: " + total);
                        System.out.println("Your budget: " + currentUserBudget);
                    }else{
                        System.out.println("Sorry you do not have any bonus");
                    }

                    return;
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

    public static long differenceOfTwoDates(Date date1, Date date2) {

        // Strip out the time part of each date.
        long julianDayNumber1 = date1.getTime();
        long julianDayNumber2 = date2.getTime();
        long distanceOfTwoDate = julianDayNumber1 - julianDayNumber2;
        // If they now are equal then it is the same day.
        return distanceOfTwoDate/(60*60*24*1000);
    }

    public static boolean promotion(double percentageWinRate){
        return Math.random() <= percentageWinRate;
    }
}
