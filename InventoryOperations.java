package LinkedInventoryManagement.PersistentStorage;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;

/**
 * InventoryOperations
 */
public class InventoryOperations {

    // Method loads in ventory from file and stores in products linked list
    public static void loadInventory()
    {
        InventoryLinkedList<Product> productList = ProductCatalog.GetProductList();

        // did i already write this in product/productcatalog file?
        
            
            // Get singleton scanner object for inventory file
            Scanner inventoryScanner = ScannerFactory.GetScannerInstance("inventory.txt");

            // Create array to store each line in inventory file
            String[] productInfoArray = new String[5];
            
            String productInfo;

            // Clear products list -> Will be refilled
            for (int i = 0; i < productList.GetLength(); i++) 
            {
                productList.Remove(i);
                productList.decrementSizeOfList();
            }


            // While there is data in the file
            while (inventoryScanner.hasNextLine()) {

                // Input line
                productInfo = inventoryScanner.nextLine();

                // If the line is an empty line
                if (productInfo.isBlank()) {
                    if (inventoryScanner.hasNextLine())
                        productInfo = inventoryScanner.nextLine();

                    continue;
                }

                // Split line into multiple components
                productInfoArray = productInfo.split(", ");

                // Store product values in individual variables
                int productID = Integer.parseInt(productInfoArray[0]);
                String productName = productInfoArray[1];
                Double productCost = Double.parseDouble(productInfoArray[2]);
                int productQuantity = Integer.parseInt(productInfoArray[3]);
                int productMargin = Integer.parseInt(productInfoArray[4]);

                // Call method to add the product to the product list
                ProductCatalog.AddUpdateProduct(new Product(productID, productName, productCost, productQuantity, productMargin));

            }
       
        }
    
    /*
    This method loops through all items in the list of products
    and writes them to the file
    */
    public static void writeProductListToFile() {

        try {

            // Store list of products
            InventoryLinkedList<Product> productList = ProductCatalog.GetProductList();

            // Create fileWriter object
            // FileWriter fw = new FileWriter("Inventory.dat");
            FileWriter fw = new FileWriter("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/inventory.txt");

            // Loop through list of products
            for (int i = 0; i < productList.GetLength(); i++) 
            {
                // Store product at index
                Product currentProduct = productList.GetElement(i);

                // Write element to file
                fw.write(currentProduct.toString());

                // Flush buffer
                fw.flush();
            }

        } catch (Exception e) {

        }
    }

    /*
    This method calls a static method to add a product
    to the product List. If that method returns true, indicating
    a successful addition, the updated product is then written to the file.
    */
    public static void addProduct(Product product)
    {
        // Call method to add product to list
        boolean successfulAddition = ProductCatalog.AddUpdateProduct(product);
       

        // Write the newly updated productList to the file
        if(successfulAddition)
            writeProductListToFile();
    }

     /*
     * This method calls a static method to update a product in the product List. If
     * that method returns true, indicating a successful update, the updated product list is
     * then written to the file.
     */
    public static void updateProduct(Product product)
    {
        boolean successfulUpdate = ProductCatalog.AddUpdateProduct(product);

        // Write the newly updated productList to the file
        if(successfulUpdate)
            writeProductListToFile();
    }



    /*
     * This method calls a static method to remove a product in the product List. If
     * that method returns true, indicating a successful remval, the updated
     * product list is then written to the file (without the removed product).
     */
    public static void removeProduct(Product product)
    {
        boolean successfulRemoval = ProductCatalog.RemoveProduct(product);

        // Call method to write products list to file (without removed product)
        if(successfulRemoval)
            writeProductListToFile();
    }
}
