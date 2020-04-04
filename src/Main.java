import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    List<String> ContactList = new ArrayList<>();
    public static String datapath = "data";
    public static String contactFileName = "contacts.txt";
    public static Scanner sc = new Scanner(System.in);


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
            System.out.println("Would you like to: \nFind a number: FIND\nEnter a new number: NEW\nOr delete a number: DELETE\nPlease Enter, Find: New: Delete");
            String confirm = sc.next().toUpperCase();
//            System.out.println(confirm);
            if (confirm.contains("NEW")) {
                String line = getContact();
                String topLine = "\t\tName | Phone Number \n -----------------------";
                Files.write(contactFilePath, Arrays.asList(topLine), StandardOpenOption.CREATE);
                Files.write(contactFilePath, Arrays.asList(line), StandardOpenOption.APPEND);
            }if (confirm.contains("FIND")) {

                // readList needs to only print name or not exist at all...
                List<String> readList = Files.readAllLines(contactFilePath);

                //for loop iterates and prints all contacts
                for (var i = 0; i < readList.size();i++) {
                    System.out.println("\n" + readList.get(i));
                }
                System.out.println("Please Enter a Name From The List: ");
                String nameFromList = sc.next().toUpperCase();
                // nameFromList needs to iterate through the contacts and return contact info.
            } else if (confirm.contains("DELETE")) {
                removeContact();
            }
//            begin terminal interactive

            System.out.println("Do you wish to continue? [y/n]");
            String userResponse = sc.next().toLowerCase();
            if (!userResponse.equalsIgnoreCase("y")) {
                userConfirm = false;
            }
        } while (userConfirm);
    }

    public static String getContact() {
        System.out.print("Enter First Name: ");
        String contactFirstName = sc.next();
        System.out.print("Enter Last Name: ");
        String contactLastName = sc.next();
        System.out.print("Enter A Phone Number: ");
        String contactNumber = sc.next();
//        String[] splitNumber;
//        splitNumber = contactNumber.split("");
        contactNumber.contains("0123456789");
//        System.out.println(splitNumber);
        return contactFirstName +" " + contactLastName + " | " + contactNumber ;
    }

    public static void removeContact() {
        System.out.println("Enter name of contact you want to delete: ");
        String name = sc.next();

        System.out.println("You entered: " + name);
    }




}





