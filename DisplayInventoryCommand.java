package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;


/**
 * DisplayInventoryCommand 
 */
public class DisplayInventoryCommand extends Command 
{

    
    // Constructor
    public DisplayInventoryCommand(ProductCatalog productCatalog, User loggedOnUser) 
    {
        super(productCatalog, loggedOnUser);
       
    }

    @Override
    public void Execute() 
    {
        // Set static variable to tell method to print header when displaying the whole
        // ivnentory
        ProductCatalog.setPrintingProductCounter(0);

        // Call method to print inventory list
        ProductCatalog.PrintInventoryList();
    }

    
}
