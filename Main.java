package LinkedInventoryManagement;

import LinkedInventoryManagement.Menu.*;
import LinkedInventoryManagement.PersistentStorage.MenuListOperations;
import LinkedInventoryManagement.PersistentStorage.UsersOperations;

import java.util.InputMismatchException;
import java.util.Scanner;

import LinkedInventoryManagement.Common.*;
import LinkedInventoryManagement.Product.*;
import LinkedInventoryManagement.Security.*;

/**
 * Name: Roope Raikaa 
 */
public class Main 
{

    
    public static void main( String[] args )
    {
    
        //Authenticate the user and get a user object back
        User user = SecurityOperations.AuthenticateUser("admin", "admin"); 
        
        // This automatically loadds inventory from file when constructor is called and stores in linked list
        ProductCatalog  productCatalog = new ProductCatalog(); 
        
        // Read users from file 
        UsersOperations.readUsers();

        // Store list of users
        InventoryLinkedList<User> userList = UsersOperations.getUsersList();

        // Define variables
        String inputtedUsername;
        String inputtedUnHashedPassword;
        Boolean userAuthenticated = false;
        User systemUser;

        do
        {
            // Prompt user for inputtedUsername and and input into variable
            System.out.print("Enter username: ");
            inputtedUsername = ScannerFactory.GetScannerInstance().next();

            // Prompt user for password and and input into variable
            System.out.print("Enter password: ");
            inputtedUnHashedPassword = ScannerFactory.GetScannerInstance().next();

            // Call authenticate user method on inputted data
            systemUser = SecurityOperations.AuthenticateUser(inputtedUsername, inputtedUnHashedPassword);
            
            // If the user is authenticated correctly
            if (systemUser != null) {

                // Set flag
                userAuthenticated = true;

                // Print welcome message
                System.out.println("Welcome " + systemUser.getFirstName() + " " + systemUser.getLastName());
            }
            else
            {
                // Error message
                System.out.println("Invalid username or password! User could not be authenticated!");

                // Prompt to continue or exit program
                System.out.print("Press enter to continue or “Exit” to exit: ");

                // Clear buffer
                ScannerFactory.GetScannerInstance().nextLine();

                // Input user decision to continue or exit
                String finalInput = ScannerFactory.GetScannerInstance().nextLine();

                // If user chooses to exit program
                if (finalInput.equalsIgnoreCase("exit")) {
                    System.exit(0);
                } else {
                    continue;
                }
            }

        } while(!userAuthenticated);

        // Create option counter for menuList
        int optionNumber = 1;

        MenuList menuList = new MenuList("Main Menu");

        // Load menu items from inventory
        MenuListOperations.loadMenu(productCatalog, systemUser, optionNumber);
         
        
        // Create Flag
        Boolean loopAgain = true;

        // Call method to display start menu
        menuList.StartMenu(systemUser);

        
        // This loop is to continue displaying the menu until the user chooses to exit
        if (loopAgain) 
        {
            // Set flag
            loopAgain = false;

            do {
                try {

                    // Print menu
                    menuList.printMenu(systemUser);

                } catch (InputMismatchException e) {

                    loopAgain = true;
                    ScannerFactory.GetScannerInstance().next();

                }
            } while (loopAgain);
        }

        //Close the Scanner at the end of program
        ScannerFactory.CloseScannerInstance();

        // Close all file scanners at the end of program
        ScannerFactory.CloseAllFileScanners();
    }
}
