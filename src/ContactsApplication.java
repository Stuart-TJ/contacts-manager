import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ContactsApplication {

    public static String directory = "data";
    public static String filename = "contacts.txt";

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

    public static void optionChooser (Scanner sc) {

        boolean userContinues = true;

        System.out.printf("%n%nWelcome to the Contacts Manager!%n");
        System.out.println("=========================");

        do {
            showMenu();
            int userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("You chose 1");
                    viewContacts();
                    break;
                // something;
                case 2:
                    System.out.println("Adding a contact...");
                    break;
                // something else;
                case 3:
                    System.out.println("Searching...");
                    break;
                // same
                case 4:
                    System.out.println("Deleting contact!");
                    break;
                // same
                case 5:
                    userContinues = false;
                    break;
            }

        } while (userContinues);

        System.out.println("Goodbye!");

        // overwrite txt file here

    }

    public static void viewContacts(){
        Path p = Paths.get("data","contacts.txt");
        List<String> allContacts = null;
        try {
            allContacts = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Name | Phone number");
        System.out.println("-------------------");
        for(int i=0; i < allContacts.size(); i += 1){
            String[] line = allContacts.get(i).split(",");
            System.out.println(line[0]+ " | " +line[1]);
        }
    }

    public static void main(String[] args) {

        createFileAndDirectory(directory, filename);

        Scanner sc = new Scanner(System.in);

        optionChooser(sc);
    }
}