import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main {

    public static List<String> ContactList = new ArrayList<>();
    public static String datapath = "data";
    public static String contactFileName = "contacts.txt";
    public static Scanner sc = new Scanner(System.in);
    public static String name;
    public static String phoneNumber;

    public void Contacts(String name, String phoneNumber){
        Main.name = name;
        Main.phoneNumber = phoneNumber;
    }

    public static String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

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



    //find contact position

    public static int findContact(String contactName){
        System.out.println("contact index: "+ContactList.indexOf(contactName));
        ContactList.indexOf(contactName);
        for (int i = 0; i < ContactList.size(); i++){
            String contacts = ContactList.get(i);
            if(contacts.equals(contactName)){
                System.out.println(i);
                return i;
            }
        }
        return 1;
    }

    private static void removeContact(String nan) {
        System.out.println("Enter name of contact you want to delete: ");
        String contactName = sc.next();
        int findName = findContact(contactName);
        System.out.println("PSV You entered: " + findName );

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
            System.out.println("Would you like to: \nFind a number: FIND\nEnter a new number: NEW\nOr delete a number: DELETE\nPlease Enter, Find: New: Delete");
            String confirm = sc.next().toUpperCase();
//            System.out.println(confirm);
            if (confirm.contains("NEW")) {
                String line = storeContact();
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
                removeContact(name);
                System.out.println("Main: You entered: " + name );

            }
//            begin terminal interactive

            System.out.println("Do you wish to continue? [y/n]");
            String userResponse = sc.next().toLowerCase();
            if (!userResponse.equalsIgnoreCase("y")) {
                userConfirm = false;
            }
        } while (userConfirm);
    }








}





