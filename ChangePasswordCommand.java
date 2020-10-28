package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;
import java.util.*;

/**
 * ChangePasswordCommand 
 */
public class ChangePasswordCommand extends Command 
{
    

    // Constructor
    public ChangePasswordCommand(ProductCatalog productCatalog, User loggedOnUser) 
    {
        super(productCatalog, loggedOnUser);
     
    }

    @Override
    public void Execute() 
    {

       

        // PROMPT FOR USER INFORMATION AND INPUT
        System.out.print("Enter your username: ");
        String userInputUsername = ScannerFactory.GetScannerInstance().nextLine().trim();
        System.out.print("Enter your password: ");
        String userInputPassword = ScannerFactory.GetScannerInstance().nextLine().trim();

        // Get hashed value of inputted password
        String hashedInputPassword = SecurityOperations.GetPasswordHash(userInputPassword).trim();

        // Prompt user for new password & input new password
        System.out.print("Enter new password: ");
        String newUnhashedPassword = ScannerFactory.GetScannerInstance().next();

        // Call method to change password with inputted information(if inputted current password matches what is on file for the user)
        SecurityOperations.ChangePassword(userInputUsername, hashedInputPassword, newUnhashedPassword);
    }

   
}
