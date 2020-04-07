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


    public static String storeContact() {
        System.out.print("Enter First Name: ");
        String contactFirstName = sc.next();
        System.out.print("Enter Last Name: ");
        String contactLastName = sc.next();
        System.out.print("Enter A Phone Number: ");
        String contactNumber = sc.next();
        String[] splitNumber;
        splitNumber = contactNumber.split("");
        if (contactNumber.contains("0123456789")) {
            System.out.println(splitNumber);
        }
        String number = contactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        String formattedNumber = String.format("%-10s %-13s| %-18s|", contactFirstName, contactLastName, number);
        System.out.println(formattedNumber);
        return formattedNumber;
    }


    private static void removeContact(String nan) throws IOException {
        System.out.println("Enter a first name");
        String firstName = sc.next();
        System.out.println("You Deleted: " + firstName);//assign what user enters to new String in uppercase letters.  Will be used to compare first name in contacts.txt file
        String firstNameUpperCase = firstName.toUpperCase();//declare string which will store uppercase version of contacts in contacts.txt file - will be used to compare with first name that user enters.
        String contactUpperCase;
        List<String> lines = Files.readAllLines(dataFile);
        List<String> newList = new ArrayList<>();
        for (String line : lines) {
            contactUpperCase = line.toUpperCase();
            if (contactUpperCase.contains(firstNameUpperCase)){ //compares if first name entered by user is found in contacts.txt file.
                int indexContact = lines.indexOf(line);
                lines.remove(indexContact);
                break;
            }
            newList.add(line);
        }
        Files.write(dataFile, lines);
    }


    public static void searchByName() throws IOException {
        System.out.println("Enter a first name");
        String firstName = sc.next();
        //assign what user enters to new String in uppercase letters.  Will be used to compare first name in contacts.txt file
        String firstNameUpperCase = firstName.toUpperCase();
        //declare string which will store uppercase version of contacts in contacts.txt file - will be used to compare with first name that user enters.
        String contactUpperCase;
        List<String> readList = Files.readAllLines(dataFile);
        for (String contact : readList) {
            contactUpperCase = contact.toUpperCase();
            if (contactUpperCase.contains(firstNameUpperCase)) {
                //compares if first name entered by user is found in contacts.txt file.
                System.out.println("\n");
                System.out.println(contact);
                break;
            }
        }
    }

    public static void editContact() throws IOException {
        removeContact(name);
        System.out.println("Enter The Updated Contact Information: ");
        String line = storeContact();
        Files.write(dataFile, Arrays.asList(line), StandardOpenOption.APPEND);
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
            System.out.println("Would you like to:\n" +
                    "Show All Contacts: ALL \n" +
                    "Search a number: SEARCH\n" +
                    "Enter a new number: NEW\n" +
                    "Edit an existing contact: EDIT\n" +
                    "Or delete a number: DELETE\n" +
                    "Please Enter\n" +
                    "All: Search: New: Edit: Delete:");
            String confirm = sc.next().toUpperCase();
            if (confirm.contains("NEW")) {
                String line = storeContact();
                String topLine = "\t\tName            | Phone Number \n" +
                                " --------------------------------------------";
                Files.write(contactFilePath, Arrays.asList(topLine), StandardOpenOption.CREATE);
                Files.write(contactFilePath, Arrays.asList(line), StandardOpenOption.APPEND);
            }
            if (confirm.contains("ALL")) {
                // readList needs to only print name or not exist at all...
                List<String> readList = Files.readAllLines(dataFile);
                for (var i = 0; i < readList.size(); i++){
                    //for loop iterates and prints all contacts
                    System.out.println(readList.get(i));
                }
            }if (confirm.contains("DELETE")) {
                removeContact(name);
            }if (confirm.contains("SEARCH")) {
                searchByName();
            }if (confirm.contains("EDIT")) {
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