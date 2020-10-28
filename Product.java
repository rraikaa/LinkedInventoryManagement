package LinkedInventoryManagement.Product;

import java.util.Scanner;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;

/**
 * This class represent a line in Inventory.dat file

 */
public class Product implements Comparable<Product>
{
    //TODO Add data fields, constructors and methods as needed. Class must implement Comparable to compare two products

    // Define variables
    private int ID;
    private String name;
    private Double cost;
    private int quantity;
    private int margin; // Stored as percentage

    // Constructor
    public Product(int id, String name, double cost, int quantity, int margin)
    {
        this.ID = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.margin = margin;
    }

    /*
    Overridden compareTo method. This method copares if the
    ID's and names of a product passed in match the object
    which this (non-static) method is called on. If the
    two product's IDs and names match, it returns 0. Otherwise,
    it returns -1.  
    */
    @Override
    public int compareTo(Product productToCompare) 
    {
        boolean productsMatch = true;

        // Compare the ID and name of the products to verify they match

        if(this.ID != productToCompare.ID)
            productsMatch = false;

        else if(!this.name.trim().equalsIgnoreCase(productToCompare.name.trim()))
            productsMatch = false;

        if(productsMatch)
            return 0;
        else
            return -1;
      
    }

    // // Returns ID of this product
    public int getID() 
    {
        return this.ID;
    }

    // Returns name of this product
    public String getName()
    {
        return this.name;
    }

    // Returns cost of this product
    public Double getCost() 
    {
        return this.cost;
    }

    // Returns quantity of this product
    public int getQuantity() 
    {
        return this.quantity;
    }

    // Returns margin of this product
    public int getMargin() 
    {
        return this.margin;
    }

    // Setter method for product quantity
    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }

    // Setter method for product name
    public void setName(String name) 
    { 
        this.name = name;
    }

    // Setter method for product cost
    public void setCost(double cost) 
    {
        this.cost = cost;
    }

    // Setter method for product margin
    public void setMargin(int margin) 
    {
        this.margin = margin;
    }

    /*
     * Overloaded toString method that stores the information for the specific
     * product in a string and returns this string.
     */
    public String toString() {

        StringBuilder productString = new StringBuilder();

        productString.append(
                this.ID + ", " + this.name + ", " + this.cost + ", " + this.quantity + ", " + this.margin + "\n");

        return productString.toString();
    }

    /*
     * This method is used to display a product from the inventory. The method first
     * prompts for the product's name, and then attemps to find this product in the
     * list. If the product can not be found, and error message is displayed. If it
     * can be found in the file, the information for the given product is displayed
     * to the user.
     */
    public static void displayProduct() {

        boolean productExists = true; // Flag

        // Prompt and input product name
        System.out.print("Enter product name: ");
        String productName = ScannerFactory.GetScannerInstance().nextLine();

        // Clear whitespace
        productName = productName.trim();

        // Set index to negative index
        int productIndex = -1;

        // Search for product in list based on name and store index of product
        productIndex = ProductCatalog.getProductByName(productName);

        // If the product is not found in the list
        if (productIndex == -1) {
            // Print error message
            System.out.println("The product could not be found in the inventory");

            // Set flag
            productExists = false;
        }

        // If the product is found
        if (productExists) {

            // Get product list
            InventoryLinkedList<Product> productList = ProductCatalog.GetProductList();
            
            // Get Product object of product to display
            Product productToDisplay = productList.GetElement(productIndex);
            
           
            // Print information for specific product
            ProductCatalog.PrintProductInformation(productToDisplay);

        }

    }

}
