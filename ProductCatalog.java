package LinkedInventoryManagement.Product;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.PersistentStorage.InventoryOperations;

/**
 * ProductCatalog
 */
public class ProductCatalog {

    private static InventoryLinkedList<Product> productList = new InventoryLinkedList<>();

    // Static value used to determine when to print header for print product methods
    private static int printingProductcounter = 0;

    // TODO Add data fields, constructors and methods as needed. Add a collection to
    // hold Product objects
    public ProductCatalog() {

        // Call method to read inventory.dat file and store products in productList
        InventoryOperations.loadInventory();
        
    }

    /* This method Adds or updates a product if already exists. The method
    checks to see if the product exists in the list already. If so, it 
    sets the passed in element, working as the update function. If the
    product is not in the list already, it sets the passed in product
    as the last element in the list. It returns true if the methods
    it calls to add/update a product indicate a successful addition
    or update. 
    */
    public static boolean AddUpdateProduct(Product product) {

        // Define flag
        boolean successfulOperation;

        // Call method to see if product is already in list
        int productIndexInList = productList.GetIndexOfElement(product);

        try {

            // If the product is not already in the inventory
            if (productIndexInList == -1) {

                // Add the product to the end of the list
                productList.InsertLast(product);
                

            } else // If the product is in the inventory -> update
            {
                // Update product in list
                productList.SetElement(productIndexInList, product);

            }

            // Set return flag
            successfulOperation = true;

            // Increment size of product list
            productList.incrementSizeOfList();

        } catch (Exception e) // Should this be input mismatch exception
        {
            // Set flag
            successfulOperation = false;
        }

        // Return flag
        return successfulOperation;

    }

    
    /*
    This method takes a product object as a parameter, and then
    calls a method to get the index of the product in the list.
    If the product does not exist in the list, the method
    returns false for an unsuccessful attempt to remove. Otherwise,
    the product calls the Remove method on the productList inherited
    from the InventoryLinkedList class, and returns true if the removal
    was successful.
    */
    public static boolean RemoveProduct(Product product) {
        boolean isSuccess = false;

        // Call method to see if product is in list
        int productIndexInList = productList.GetIndexOfElement(product);

        // If the element to remove was not in the list
        if (productIndexInList == -1)
            isSuccess = false;
        else {
            // Call method to remove product
            productList.Remove(productIndexInList);

            // Set flag
            isSuccess = true;

            // Decrement size of products list
            productList.decrementSizeOfList();
        }


        return isSuccess;
    }

    /* This method calls the Contains method on the
    InventoryLinkedList class, inherited by the productList object. 
    If the product is in the list, the method returns true, and 
    false otherwise.
    */
    public static boolean FindProduct(Product product) {

        // Call method to see if product is in list
        boolean isSuccess = productList.Contains(product);

        return isSuccess;
    }

    // Print information about a product including retail price (cost +
    // (margin*cost/100))
    public static String PrintProductInformation(Product product) {
        
        String productInformation = null;

        // Call method to see if product is in list
        int productIndexInList = productList.GetIndexOfElement(product);

        // If the product is in the list
        if(productIndexInList != -1)
        {
            // Get string of product information
            productInformation = product.toString();

            // Output header if first iteration for call to this method
            if (printingProductcounter == 0) {

                System.out.println("\nId Name Cost Quantity Retail");
                System.out.println("----------------------------------------------------");
            }

            // Increment printing product counter
            printingProductcounter++;

            // Print information
            System.out.print(productInformation);

        }
        // If the product is not in the list
        else
        {
            System.out.println("The product is not in the inventory!");
        }

        return productInformation;
    }

    // Print all product information in the inventory
    public static String PrintInventoryList() {
        StringBuilder inventoryInformation = new StringBuilder();

        // Loop through list
        for (int i = 0; i < productList.GetLength(); i++) 
        {   
            // Store element at index
            Product currentProduct = productList.GetElement(i);

            // Append to information string
            inventoryInformation.append(currentProduct.toString());

            // Print current product information
            System.out.print(currentProduct.toString());
        }

        return inventoryInformation.toString();
    }

    /*
     * This method takes a product ID as a parameter, and then prints the
     * information for that product. Depending on if the product is the first
     * product being sequentially printed, it will print a header. It does not print
     * this header whenever the method to print all inventory items calls this
     * method for each item. This method returns the string of the information for
     * the given product, and the return string does not include the header.
     */
    public String PrintProductInformation(int productId) {

        String productInformation = null;

        
        // Get index of product in product list by ID
        int index = getProductByID(productId);

        DecimalFormat df = new DecimalFormat("#.##");

        // Get values of product information individually
        String ID = (Integer.toString(productId)).trim();
        String name = productList.GetElement(index).getName();
        Double costAsDouble = productList.GetElement(index).getCost();
        String cost = String.format("%.2f", costAsDouble);
        String quantity = Integer.toString((productList.GetElement(index).getQuantity()));
        int margin = productList.GetElement(index).getMargin();

        // Calculate retail price
        Double retailPriceAsDouble = costAsDouble + ((margin * costAsDouble) / 100);
        String retailPrice = String.format("%.2f", retailPriceAsDouble);

        // Concatenate string
        productInformation = ID + " " + name + " $" + cost + " " + quantity + " $" + retailPrice + "\n";

        // Output header if necessary
        if (printingProductcounter == 0) {
            System.out.println("\nId Name Cost Quantity Retail");
            System.out.println("----------------------------------------------------");
        }

        printingProductcounter++; // Increment counter so header is not printed more than once each time this
                                  // method is called (It is static variable)

        // Output product information
        System.out.println(productInformation);

        return productInformation;
    }

    // Returns the product linked list
    public static InventoryLinkedList<Product> GetProductList()
    {
        return productList;
    }
    
    // Used to set value to control when to print header in product printing methods
    public static void setPrintingProductCounter(int counter)
    {
        printingProductcounter = counter;
    }   

    /*
     * Overloaded method that takes a Product object as a parameter and loops
     * through the list of product and calls the compareTo method to compare
     * the two products. If the compareTo method returns 0, the products match,
     * so it returns the index of the
     * product in the list. Otherwise it returns -1.
     */
    public static int searchForProductInList(Product keyProduct) {

        // Define variables
        int productIndex = -1;
        int counter = 0;

        // Loop through each product in list
        for(int i = 0; i < productList.GetLength(); i++)
        {
            Product currentProduct = productList.GetElement(i);

            // If the products match
            if(currentProduct.compareTo(keyProduct) == 0)
            {
                // Store index of product
                productIndex = counter;
                break;
            }

            // Increment counter
            counter++;
        }
        
        // Rreturn the index of the product, or -1 if not in list
        return productIndex;
    }

    /*
     * Overloaded method that takes a product name as a parameter and loops through
     * the list of product and checks if any of the products in the list's names
     * match the passed in product name. If found, it returns the index of the
     * product in the list. Otherwise it returns -1.
     */
    public static int getProductByName(String productName) {

        // Define variables
        int productIndex = -1;
        int counter = 0;

        // Loop through each product in list
        for (int i = 0; i < productList.GetLength(); i++) {
            Product currentProduct = productList.GetElement(i);

            // If the names match
            if (currentProduct.getName().trim().equalsIgnoreCase(productName)) 
            {
                // Store index of product
                productIndex = counter;
                break;
            }

            // Increment counter
            counter++;
        }

        // Rreturn the index of the product, or -1 if not in list
        return productIndex;
    }
    
    /*
     * Overloaded method that takes a Product ID as a parameter and loops through
     * the list of product and checks if any of the products in the list's IDs match
     * the passed in productID. If found, it returns the index of the product in the
     * list. Otherwise it returns -1.
     */
    public static int getProductByID(int ID) {

        // Define variables
        int productIndex = -1;
        int counter = 0;

        // Loop through each product in list
        for (int i = 0; i < productList.GetLength(); i++) {
            Product currentProduct = productList.GetElement(i);

            // If the IDs match
            if (currentProduct.getID() == ID) 
            {
                // Store index of product
                productIndex = counter;
                break;
            }

            // Increment counter
            counter++;
        }

        // Rreturn the index of the product, or -1 if not in list
        return productIndex;
    }
}
