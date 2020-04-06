import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main {

    public static String datapath = "data";
    public static String contactFileName = "contacts.txt";
    public static Path dataDirectory = Paths.get(datapath);
    public static Path dataFile = Paths.get(datapath, contactFileName);
    public static Scanner sc = new Scanner(System.in);
    public static String name;
    public static String phoneNumber;
    public static ArrayList<String> ContactList = new ArrayList<String>();


    public static String storeContact() {
        System.out.print("Enter First Name: ");
        String contactFirstName = sc.next();
        System.out.print("Enter Last Name: ");
        String contactLastName = sc.next();
        //enter if statement to check if name exists
//        if(contactFirstName == findContact()){
//
//        }
        System.out.print("Enter A Phone Number: ");
        String contactNumber = sc.next();
        String[] splitNumber;
        splitNumber = contactNumber.split("");
        if(contactNumber.contains("0123456789")) {
            System.out.println(splitNumber);
        }
        System.out.println(contactFirstName +" " + contactLastName + " | " + contactNumber);
        return contactFirstName +" " + contactLastName + " | " + contactNumber;
    }


    private static void removeContact(String nan) throws IOException {
        System.out.println("Enter a first name");
        String firstName = sc.next();
        System.out.println("You Deleted: " + firstName);
        //assign what user enters to new String in uppercase letters.  Will be used to compare first name in contacts.txt file
        String firstNameUpperCase = firstName.toUpperCase();
        //declare string which will store uppercase version of contacts in contacts.txt file - will be used to compare with first name that user enters.
        String contactUpperCase;
//        Path contactFilePath = Paths.get(datapath, contactFileName);
        List<String> lines = Files.readAllLines(dataFile);
        List<String> newList = new ArrayList<>();
        for(String line : lines) {
            contactUpperCase = line.toUpperCase();
            if (contactUpperCase.contains(firstNameUpperCase)) { //compares if first name entered by user is found in contacts.txt file.
                int indexContact = lines.indexOf(line);
                lines.remove(indexContact);
                break;
            }
            newList.add(line);
        }
        Files.write(dataFile, lines);
        }


    public static void searchByName() throws IOException{
        System.out.println("Enter a first name");
        String firstName = sc.next();
        //assign what user enters to new String in uppercase letters.  Will be used to compare first name in contacts.txt file
        String firstNameUpperCase = firstName.toUpperCase();
        //declare string which will store uppercase version of contacts in contacts.txt file - will be used to compare with first name that user enters.
        String contactUpperCase;
        List<String> readList = Files.readAllLines(dataFile);
        for(String contact : readList) {
            contactUpperCase = contact.toUpperCase();

            if(contactUpperCase.contains(firstNameUpperCase)) { //compares if first name entered by user is found in contacts.txt file.
                System.out.println("\n");
                System.out.println(contact);
                break;
            } else {
                continue;
            }
        }
    }

    public static void editContact() throws IOException {
        System.out.println("Enter a first name");
        String firstName = sc.next();
        String firstNameUpperCase = firstName.toUpperCase();
        String contactUpperCase;

//        Path contactFilePath = Paths.get(datapath, contactFileName);
        List<String> lines = Files.readAllLines(dataFile);
        List<String> newList = new ArrayList<>();
        for(String line : lines) {
            contactUpperCase = line.toUpperCase();
            if (contactUpperCase.contains(firstNameUpperCase)) { //compares if first name entered by user is found in contacts.txt file.
                int indexContact = lines.indexOf(line);
//                System.out.println(indexContact);
                System.out.println("It seems you wish to edit the following: \n" + lines.get(indexContact)); //prints out element at specified index.
                System.out.println("Is this the contact you wish to edit? [y/n] ");

                String userResponse = sc.next().toLowerCase();
                if (userResponse.equalsIgnoreCase("y")) {
                    System.out.println("Go ahead and re-enter the first name of that contact you wish to edit: ");
                    removeContact(name);
                    System.out.println("Go ahead and follow the prompts below to enter the edited contact information: ");
                    storeContact();
                }
                break;
            }
            newList.add(line);
        }
        Files.write(dataFile, lines);
    }

    public static void main(String[] args) throws IOException {
        Path data = Paths.get(datapath);
        if (!Files.exists(data)) {
            Files.createDirectory(data);
        }
        //create a file inside directory
        Path contactFilePath = Paths.get(datapath, contactFileName);
        if (!Files.exists(contactFilePath)) {
            Files.createFile(contactFilePath);
        }

        boolean userConfirm = true;
        do {
            System.out.println("Would you like to:\nShow All Contacts \nSearch a number: SEARCH\nEnter a new number: NEW\nEdit an existing contact: EDIT\nOr delete a number: DELETE\nPlease Enter, All: Search: New: Edit: Delete");
            String confirm = sc.next().toUpperCase();
//            System.out.println(confirm);
            if (confirm.contains("NEW")) {
                String line = storeContact();
                String topLine = "\t\tName | Phone Number \n -----------------------";
//                Contacts.add(contact);
                Files.write(contactFilePath, Arrays.asList(topLine), StandardOpenOption.CREATE);

                Files.write(contactFilePath, Arrays.asList(line), StandardOpenOption.APPEND);
            }if (confirm.contains("ALL")) {
                // readList needs to only print name or not exist at all...
                List<String> readList = Files.readAllLines(dataFile);
                //for loop iterates and prints all contacts
                for (var i = 0; i < readList.size();i++) {
                    System.out.println("\n" + readList.get(i));
                }
                // nameFromList needs to iterate through the contacts and return contact info.
            } else if (confirm.contains("DELETE")) {
                removeContact(name);
//                searchByName();
            } else if (confirm.contains("SEARCH")) {
                searchByName();
            } else if (confirm.contains("EDIT")) {
                editContact();
            }
            System.out.println("Do you wish to continue? [y/n]");
            String userResponse = sc.next().toLowerCase();
            if (!userResponse.equalsIgnoreCase("y")) {
                userConfirm = false;
            }
        } while (userConfirm);
    }

}