package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;
import java.util.*;

/**
 * AddUserCommand 
 */
public class AddUserCommand extends Command 
{

   

    // Constructor
    public AddUserCommand(ProductCatalog productCatalog, User loggedOnUser) 
    {
        super(productCatalog, loggedOnUser);
        
    }

    @Override
    public void Execute() 
    {
        

        // PROMPT FOR NEW USER INFORMATION AND STORE IN TEMPORARY VARIABLES
        System.out.printf("Enter first name: ");
        String firstName = ScannerFactory.GetScannerInstance().next();
        System.out.printf("Enter last name: ");
        String lastName = ScannerFactory.GetScannerInstance().next();
        System.out.printf("Enter username: ");
        String userName = ScannerFactory.GetScannerInstance().next();
        System.out.printf("Enter password: ");
        String unHashedPassword = ScannerFactory.GetScannerInstance().next();
        System.out.printf("True or False: the User is a manager: ");
        String isManager = ScannerFactory.GetScannerInstance().next();

        // Flag 
        boolean informationAddedCorrectly;

        // Input validation
        if (firstName.isBlank() || lastName.isBlank() || userName.isBlank() || unHashedPassword.isEmpty()
                || isManager.isEmpty()) 
        {
            informationAddedCorrectly = false;
        } 
        else // Valid input
        {
            informationAddedCorrectly = true;

            // Store as boolean
            boolean isManagerBool = Boolean.parseBoolean(isManager);

            if(informationAddedCorrectly)
            {
                // Create new user object with inputted values
                User newUser = new User(firstName, lastName, userName, unHashedPassword, isManagerBool);
                
                // Call method to add new user
                SecurityOperations.AddNewUser(newUser);
               
                // Print success message
                System.out.println("New User added!");
            }
            else // Invalid input
            {
                // Print error message
                System.out.println("Unable to add user");
            }
        }

    }

}
