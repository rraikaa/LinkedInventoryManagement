package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.PersistentStorage.InventoryOperations;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;
import java.util.*;

/**
 * RemoveProductCommand 
 */
public class RemoveProductCommand extends Command 
{

   
    // Constructor
    public RemoveProductCommand(ProductCatalog productCatalog, User loggedOnUser) 
    {
        super(productCatalog, loggedOnUser);
        
    }

    @Override
    public void Execute() 
    {
       
        // Prompt for product ID and input
        System.out.print("Enter product ID: ");
        int productID = ScannerFactory.GetScannerInstance().nextInt();


        // Get index
        int indexOfProductToRemove = ProductCatalog.getProductByID(productID);

        // Store product list
        InventoryLinkedList<Product> productList = ProductCatalog.GetProductList();

        // Get Product object to Remove
        Product productToRemove = productList.GetElement(indexOfProductToRemove);
        
        // Call inventory operation to remove product
        InventoryOperations.removeProduct(productToRemove);
        

    }

   
}
