package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;
import java.util.*;

/**
 * DeleteUserCommand 
 */
public class DeleteUserCommand extends Command 
{
    

    // Constructor
    public DeleteUserCommand(ProductCatalog productCatalog, User loggedOnUser) 
    {
        super(productCatalog, loggedOnUser);
        
    }

    @Override
    public void Execute() 
    {
        

        // Prompt for & Input username of user to remove
        System.out.printf("Enter the username of the user to remove: ");
        String username = (ScannerFactory.GetScannerInstance().next()).trim();

        // Call method to remove user if valid username
        SecurityOperations.RemoveUser(username);

    }

    
}
