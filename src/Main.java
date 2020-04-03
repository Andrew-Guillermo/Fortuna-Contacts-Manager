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


        do {
            String confirm = sc.nextLine().toUpperCase();
            System.out.println("Would you like to find a number or enter a new number? Find/New");
            if (confirm.contains("New")) {
                String topLine = "Name | Phone Number \n -----------------------";
                Files.write(contactFilePath, Arrays.asList(topLine), StandardOpenOption.CREATE);
            }
            if (confirm.contains("New")) {

                String line = getContact();
                Files.write(contactFilePath, Arrays.asList(line), StandardOpenOption.APPEND);
            }
            while (true) ;

            //begin terminal interactive

            public static String getContact () {
                System.out.print("Enter A Name: ");
                String contactName = sc.next();
                System.out.print("Enter A Phone Number: ");
                String contactNumber = sc.next();
                String[] splitNumber;
                splitNumber = contactNumber.split("");
                contactNumber.contains("0123456789");
                System.out.println(splitNumber);
                return contactName + " | " + contactNumber + "\n";
            }


        }





