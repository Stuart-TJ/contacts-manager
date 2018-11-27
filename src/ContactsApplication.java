import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsApplication {

    public static String directory = "data";
    public static String filename = "contacts.txt";
    public static Path filePath = Paths.get(directory, filename);

    public static void createFileAndDirectory(String location,String fileTitle){
        Path dataDirectory = Paths.get(location);
        Path dataFile = Paths.get(location, fileTitle);

        try {
            if(Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }

            if(Files.notExists(dataFile)){
                Files.createFile(dataFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void showMenu() {
        System.out.println("\n\nWhat would you like to do?");
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.printf("%nEnter an option (1, 2, 3, 4, or 5): ");
    }

    public static List<String> contactsArrayInit (Path filePath) {

        List<String> allContacts = null;
        try {
            allContacts = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allContacts;
    }

    public static void optionChooser (Scanner sc) {

        boolean userContinues = true;
        List<String> contacts = contactsArrayInit(filePath);

        System.out.printf("%n%nWelcome to the Contacts Manager!%n");
        System.out.println("=========================");

        do {
            showMenu();
            int userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    viewContacts(filePath, contacts);
                    break;
                // something;
                case 2:
                    addContact(sc, contacts);
                    break;
                // something else;
                case 3:
                    searching(sc, contacts);
                    break;
                // same
                case 4:
                    deleting(sc, contacts);
                    break;
                // same
                case 5:
                    saveAndExit(filePath, contacts);
                    userContinues = false;
                    break;
            }

        } while (userContinues);

        System.out.println("Goodbye!");

        // overwrite txt file here

    }

    public static void viewContacts(Path p, List<String> contacts){

        System.out.println("Name | Phone number");
        System.out.println("-------------------");
        for(int i=0; i < contacts.size(); i += 1){
            String[] line = contacts.get(i).split(",");
            System.out.println(line[0]+ " | " +line[1]);
        }
    }

    public static void addContact (Scanner sc, List<String> contacts) {

        System.out.printf("%nName: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.printf("%nPhone Number: ");
        int phoneNo = sc.nextInt();

        String newContact = name + "," + phoneNo;
        contacts.add(newContact);

    }

    public static void searching(Scanner sc, List<String> contacts){
        sc.nextLine();
        System.out.println("Who would you like to search for?");
        String userInput = sc.nextLine();
        for(String contact: contacts){
            if(contact.contains(userInput)){
                String[] line = contact.split(",");
                System.out.println(line[0]+ " | " +line[1]);
            }
        }
    }

    public static void deleting(Scanner sc, List<String> contacts){
        sc.nextLine();
        System.out.println("Type a contacts full name you want to delete");
        String userInput = sc.nextLine();
        String toBeDeleted = "";
        for(String contact: contacts){
            String[] line = contact.split(",");
            if(line[0].equalsIgnoreCase(userInput)){
                toBeDeleted = contact;
                break;
            }
        }
        if(!toBeDeleted.equals("")){
            System.out.println("Are you sure you want to delete this contact");
            String userDeleteConfirmation = sc.nextLine();
            if(userDeleteConfirmation.equalsIgnoreCase("y")){
                contacts.remove(contacts.indexOf(toBeDeleted));
                System.out.println("Contact deleted");
            } else {
                System.out.println("Deletion canceled");
            }
        } else {
            System.out.println("No contact found");
        }

    }

    public static void saveAndExit(Path p, List<String> contacts){
        try {
            Files.write(p,contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        createFileAndDirectory(directory, filename);

        Scanner sc = new Scanner(System.in);

        optionChooser(sc);
    }
}