package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.PersistentStorage.InventoryOperations;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;
import java.util.*;

/**
 * UpdateProductCommand 
 */
public class UpdateProductCommand extends Command 
{

    // Constructor
    public UpdateProductCommand(ProductCatalog productCatalog, User loggedOnUser) 
    {
        super(productCatalog, loggedOnUser);
      
    }

    @Override
    public void Execute() 
    {
        

        // Prompt for product information and input
        System.out.print("Enter product ID: ");
        int productID = ScannerFactory.GetScannerInstance().nextInt();
        System.out.print("Enter product name: ");
        String productName = ScannerFactory.GetScannerInstance().next();
        System.out.print("Enter product cost: ");
        double productCost = ScannerFactory.GetScannerInstance().nextDouble();
        System.out.print("Enter product quantity: ");
        int productQuantity = ScannerFactory.GetScannerInstance().nextInt();
        System.out.print("Enter product margin: ");
        int productMargin = ScannerFactory.GetScannerInstance().nextInt();

        // Call inventory operation to update product
        InventoryOperations.updateProduct(new Product(productID, productName, productCost, productQuantity, productMargin));
        
    }

   
}
