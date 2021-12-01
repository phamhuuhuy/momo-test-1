package rmit;

import java.io.*;
import java.text.*;
import java.util.*;

public class MenuLead extends Menu{
    @Override
    public void menu() throws FileNotFoundException, ParseException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        while(true) {
            boolean run =true;
            List<Lead> leads = getLead();
            System.out.println("------------------------");
            System.out.println("What do you want to do: ");
            System.out.println("1.View \n2.Adding new lead \n3.Delete lead \n4.Update lead\n5.Return to Main Menu");
            System.out.println("6.Exit");
            System.out.println("------------------------");

            while (true) {
                System.out.print("Your choice: ");
                String choice = sc.next();
                if (choice.equals("1")) {
                    showLeads(leads);
                    break;
                } else if (choice.equals("2")) {
                    addLeads(leads);
                    break;
                } else if (choice.equals("3")) {
                    deleteLeads(leads);
                    break;
                } else if (choice.equals("4")) {
                    updateLeads(leads);
                    break;
                } else if (choice.equals("5")) {
                    Menu mainMenu = new MainMenu();
                    mainMenu.menu();
                    return;
                } else if(choice.equals("6")){
                    return;
                } else {
                    System.out.println("Wrong input");
                }
            }
            while (true) {
                System.out.print("Do you want to continue managing? (y/n): ");
                String cont = sc.next();
                if (cont.equals("y") || cont.equals("Y")) {
                    run = false;
                    break;
                } else if (cont.equals("n") || cont.equals("N")) {
                    break;
                } else {
                    System.out.println("Wrong input");
                }
            }
            while (run) {
                System.out.print("Do you want to go back to the main menu? (y/n): ");
                String cont = sc.next();
                if (cont.equals("y") || cont.equals("Y")) {
                    Menu mainMenu = new MainMenu();
                    mainMenu.menu();
                    return;
                } else if (cont.equals("n") || cont.equals("N")) {
                    return;
                } else {
                    System.out.println("Wrong input");
                }
            }
        }
    }

    public static List<Lead> getLead() throws FileNotFoundException, ParseException {
        File fileCSV = new File("manageLeads.csv");
        Scanner input = new Scanner(fileCSV);
        List<Lead> arrLead = new ArrayList<Lead>();
        while (input.hasNext()){
            String line = input.nextLine();
            String[] token = line.split(",");

            boolean gender = token[2].equals("true");

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = df.parse(token[3]);

            Lead lead = new Lead(token[0],token[1],gender,dob,token[4],token[5],token[6]);

            arrLead.add(lead);
        }
        input.close();
        return arrLead;
    }

    public static void showLeads(List<Lead> arrLead){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for(Lead lead : arrLead){
            System.out.println("ID: "+lead.getId());
            System.out.println("Name: "+lead.getName());
            System.out.println("Gender: "+ ((lead.isGender())?"male":"female"));
            System.out.println("Born: "+df.format(lead.getDob()));
            System.out.println("Phone: "+lead.getPhoneNumber());
            System.out.println("Email: "+lead.getEmail());
            System.out.println("Address: "+lead.getAddress()+"\n");
        }
    }
    public static void addLeads(List<Lead> arrLead) throws FileNotFoundException, ParseException {

        //count number lines of leads
        Scanner sc = new Scanner(new File("manageLeads.csv"));
        String id;
        if (arrLead.size()==0){
            id = "lead_001";
        }else{
            String lastID = arrLead.get(arrLead.size()-1).getId().substring(5);
            int nextID = Integer.parseInt(lastID)+1;
            if ((nextID)<10){
                id = "lead_00"+ String.valueOf(nextID);
            }else if ((nextID)<100){
                id = "lead_0"+String.valueOf(nextID);
            }else{
                id = "lead_"+String.valueOf(nextID);
            }
        }


        Scanner input = new Scanner(System.in);

        String name;
        while(true){
            System.out.print("Name: ");
            name = input.nextLine();
            if(name.matches("^[\\p{L} .'-]+$")){
                break;
            }else{
                System.out.println("Characters only");
            }
        }

        String genderString;
        while(true){
            System.out.print("Gender (f/m): ");
            genderString = input.nextLine();
            if(genderString.length() == 1 && (genderString.charAt(0) == 'f' || genderString.charAt(0) == 'm' || genderString.charAt(0) == 'F' || genderString.charAt(0) == 'M')){
                break;
            }else{
                System.out.println("Only choose F or M");
            }
        }
        boolean gender = (genderString.charAt(0) == 'm' || genderString.charAt(0) == 'M');

        Date dob;
        while(true){
            System.out.print("Born (yyyy-MM-dd): ");
            String date = input.nextLine();
            if(date.matches("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$")){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                dob = df.parse(date);
                break;
            }else{
                System.out.println("Wrong Date Format.");
            }
        }


        String phoneNumber;
        while(true){
            System.out.print("Phone Number: ");
            phoneNumber = input.nextLine();
            if(phoneNumber.matches("[\\d]{10}")){
                break;
            }else{
                System.out.println("Must be digits and have 10 digits only");
            }
        }

        String email;
        while(true){
            System.out.print("Email: ");
            email = input.nextLine();
            if(email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
                break;
            }else{
                System.out.println("Wrong email format (...)@(...)(dot)(...)");
            }
        }

        System.out.print("Address: ");
        String address = input.nextLine();
        System.out.println("Adding successfully");

        //adding in list of leads
        Lead lead = new Lead(id,name,gender,dob,phoneNumber,email,address);
        arrLead.add(lead);

        //testing if list works

        //writing into file
        File file = new File("manageLeads.csv");
        PrintWriter pw = new PrintWriter(file);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for(Lead ld : arrLead){
            pw.println(ld.getId()+","+ld.getName()+","+ld.isGender()+","+df.format(ld.getDob())+","+ld.getPhoneNumber()+","+ld.getEmail()+","+ld.getAddress());
        }
        pw.close();



    }



    public static void deleteLeads(List<Lead> arrLead) throws FileNotFoundException, ParseException, ClassNotFoundException {
        List<Interaction> arrInteraction = MenuInteraction.getInteraction();
        // Show all the id number
        StringBuilder leadId = new StringBuilder();
        System.out.println("Available Leads: ");
        for (Lead l: arrLead){
            leadId.append(l.getId()+"\t");
        }
        System.out.println(leadId);

        // Delete id in the arrLead

        Scanner input = new Scanner(System.in);
        String idS;
        while(true) {
            System.out.print("Choose id number you want to delete: ");
            String id = input.nextLine();
            idS = "lead_" + id;

            //Check if the id is exist
            if (!String.valueOf(leadId).contains(idS)) {
                System.out.println("Wrong id");
            } else {
                break;
            }
        }

        while (true){
            System.out.print("Delete this lead  will remove all the related interactions and leads? (y/n): ");
            String choice = input.nextLine();
            if (choice.equals("y") || choice.equals("Y")) {
                System.out.println("Successful delete. ");
                // Remove id in arrLead
                Iterator<Lead> itrLead = arrLead.iterator();
                Iterator<Interaction> itrInter = arrInteraction.iterator();
                while (itrLead.hasNext()) {
                    Lead lead = itrLead.next();
                    if (lead.getId().equals(idS)) {
                        itrLead.remove();
                    }
                }
                //Remove id in arrInteraction
                while (itrInter.hasNext()) {
                    Interaction inter = itrInter.next();
                    if (inter.getLeadID().getId().equals(idS)) {
                        itrInter.remove();
                    }
                }
                break;
            } else if (choice.equals("n") || choice.equals("N")) {
                break;
            } else {
                System.out.println("Only y or n");
            }
        }

        //writing lead into file
        File file = new File("manageLeads.csv");
        PrintWriter pw = new PrintWriter(file);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for(Lead _lead: arrLead){
            pw.println(_lead.getId()+","+_lead.getName()+","+_lead.isGender()+","+df.format(_lead.getDob())+","+_lead.getPhoneNumber()+","+_lead.getEmail()+","+_lead.getAddress());
        }
        pw.close();
        // Writing interaction into file
        File file1 = new File("manageInteractions.csv");
        PrintWriter pw1 = new PrintWriter(file1);
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        for(Interaction interact :arrInteraction){
            pw1.println(interact.getId()+","+interact.getLeadID().getId()+","+df1.format(interact.getDoI()) +","+interact.getCommunicate()+","+interact.getPotential());
        }
        pw1.close();


    }

    public static void updateLeads(List<Lead> arrLead) throws FileNotFoundException, ParseException {
        // Show all the id number
        System.out.println("Available Leads: ");
        for (Lead l: arrLead){
            System.out.print(l.getId()+"\t");
        }

        // Update the array lead
        boolean run = true;
        while(run) {
            Scanner input = new Scanner(System.in);
            System.out.print("\nChoose id number you want to update: ");
            String id = input.nextLine();
            String idS = "lead_" + id;
            boolean exitId = false; // declare exitId variable
            for (Lead l : arrLead) {
                if (l.getId().equals(idS)) { // Check if exit the id
                    exitId = true;
                    run = false;
                    boolean start = true;
                    while (start) {
                        System.out.println("What do you want to update:");
                        System.out.println("1.Name");
                        System.out.println("2.Gender");
                        System.out.println("3.Day of Birth");
                        System.out.println("4.Phone number");
                        System.out.println("5.Email");
                        System.out.println("6.Address");
                        String choice;
                        while (true) {
                            System.out.print("Your choice: ");
                            choice = input.nextLine();
                            if (choice.matches("[1-6]")) {
                                break;
                            } else {
                                System.out.println("Must be between 1 and 6");
                            }
                        }
                        //Set name
                        if (choice.equals("1")) {
                            String name;
                            while (true) {
                                System.out.print("Name: ");
                                name = input.nextLine();
                                if (name.matches("^[\\p{L} .'-]+$")) {
                                    System.out.println("Successful update");
                                    break;
                                } else {
                                    System.out.println("Should be characters only");
                                }
                            }
                            l.setName(name);

                        }
                        // Set gender
                        else if (choice.equals("2")) {
                            String genderS;
                            while (true) {
                                System.out.print("Gender (f/m): ");
                                genderS = input.nextLine();
                                if (genderS.length() == 1 && (genderS.charAt(0) == 'f' || genderS.charAt(0) == 'm' || genderS.charAt(0) == 'F' || genderS.charAt(0) == 'M')) {
                                    System.out.println("Successful update");
                                    break;
                                } else {
                                    System.out.println("Must be F or M");
                                }
                            }
                            boolean gender = (genderS.charAt(0) == 'm' || genderS.charAt(0) == 'M');
                            l.setGender(gender);

                        }
                        //Set BirthDate
                        else if (choice.equals("3")) {
                            Date dob = null;
                            input.nextLine();
                            while(true){
                                System.out.print("Born (yyyy-MM-dd): ");
                                String date = input.nextLine();
                                if(date.matches("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$\" \n" +
                                        "      + \"|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$\"\n" +
                                        "      + \"|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$\" \n" +
                                        "      + \"|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$")){
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                    dob = df.parse(date);
                                    System.out.println("Successful update");
                                    break;
                                }else{
                                    System.out.println("Wrong Date Format.");
                                }
                            }
                            l.setDob(dob);
                        }
                        //Set PhoneNumber
                        else if (choice.equals("4")) {
                            String phoneNumber;
                            while (true) {
                                System.out.print("Phone Number: ");
                                phoneNumber = input.nextLine();
                                if (phoneNumber.matches("[\\d]{10}")) {
                                    System.out.println("Successful update");
                                    break;
                                } else {
                                    System.out.println("Must be digits and have 10 digits only");
                                }
                            }
                            l.setPhoneNumber(phoneNumber);
                        }
                        //Set email
                        else if (choice.equals("5")) {
                            String email;
                            input.nextLine();
                            while (true) {
                                System.out.print("Email: ");
                                email = input.nextLine();
                                if (email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                                    System.out.println("Successful update");
                                    break;
                                } else {
                                    System.out.println("Wrong email format (...)@(...)(dot)(...)");
                                }
                            }
                            l.setEmail(email);
                        }
                        //Set Address
                        else {
                            System.out.print("Address: ");
                            String address = input.nextLine();
                            System.out.println("Successful update");
                            l.setAddress(address);
                        }
                        //Writing into file
                        File file = new File("manageLeads.csv");
                        PrintWriter pw = new PrintWriter(file);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        for(Lead _lead:arrLead){
                            pw.println(_lead.getId()+","+_lead.getName()+","+_lead.isGender()+","+df.format(_lead.getDob()) +","+_lead.getPhoneNumber()+","+_lead.getEmail()+","+_lead.getAddress());
                        }
                        pw.close();
                        //Ask if users want to keep updating
                        while (true) {
                            System.out.print("Do you want to continue updating? (y/n): ");
                            String cont = input.nextLine();
                            if (cont.equals("y") || cont.equals("Y")) {
                                break;
                            } else if (cont.equals("n") || cont.equals("N")) {
                                start = false;
                                break;
                            } else {
                                System.out.println("Wrong input");
                            }
                        }
                    }
                }
            }
            if(exitId == false){
                System.out.println("Wrong id");
            }
        }


    }
}
