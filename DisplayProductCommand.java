package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * DisplayProductCommand 
 */
public class DisplayProductCommand extends Command 
{

    
    // Constructor
    public DisplayProductCommand(ProductCatalog productCatalog, User loggedOnUser) 
    {
        super(productCatalog, loggedOnUser);
        
    }

    @Override
    public void Execute() 
    {
        // Set static variable to tell method to print header
        ProductCatalog.setPrintingProductCounter(0);

        // Call method to display product
        Product.displayProduct();
    }

   
}
