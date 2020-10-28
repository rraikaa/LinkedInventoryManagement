package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.PersistentStorage.InventoryOperations;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;
import java.util.*;

/**
 * AddProductCommand 
 */
public class AddProductCommand extends Command
{
    

    public AddProductCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);
       

    }

    @Override
    public void Execute() 
    {
        

        // PROMPT USER FOR INFORMATION AND INPUT INTO VARIABLES
        System.out.print("Enter the product ID: ");
        int productID = ScannerFactory.GetScannerInstance().nextInt();

       
        System.out.printf("Enter the product name: ");
        String productName = ScannerFactory.GetScannerInstance().nextLine();

        System.out.print("Enter product cost: ");
        double productCost = ScannerFactory.GetScannerInstance().nextDouble();

        System.out.print("Enter product quantity: ");
        int productQuantity = ScannerFactory.GetScannerInstance().nextInt();

        System.out.print("Enter product margin: ");
        int productMargin = ScannerFactory.GetScannerInstance().nextInt();

        // Create new product object with inputted information
        Product newProduct = new Product(productID, productName, productCost, productQuantity, productMargin);

        // Call inventory operation to add product
        InventoryOperations.addProduct(newProduct);

        
    }

    
}
