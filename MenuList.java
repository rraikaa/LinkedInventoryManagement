package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.PersistentStorage.MenuListOperations;
import LinkedInventoryManagement.Security.User;

import java.util.*;

/**
 * MenuList 
 */
public class MenuList {

    // Declare private fields
    private static InventoryLinkedList<MenuItem> menuItemsList = new InventoryLinkedList<>();
    private String menuHeader;

    // Create empty InventoryLinkedList
    public MenuList(String menuHeader)
    {   
        this.menuHeader = menuHeader; 
    }

    /*
     * This method is used to print the menu items in the menuItemsList by looping
     * through the list and calling the printMenuItem method on each individual item
     * in the list. The method does not return anything.
     */
    public void printMenu(User user)
    {

        // Declare variables
        int exitMenuChoice;
        int userMenuSelection;

        // Output menu heading
        System.out.println(menuHeader);
       

        do {
            exitMenuChoice = 1;

            // Loop through all items in menu
            for(int i = 0; i < menuItemsList.GetLength(); i++)
            {

                // Store current item
                MenuItem currentItem = menuItemsList.GetElement(i);

                // If the option is for managers only
                if (currentItem.getBooleanRestrictedToManager() == true) {

                    // If the user is not a manager and the option is restricted
                    if (!user.getIsManager()) {

                        continue;

                    } else { // If the option is not restricted

                        MenuItem.printMenuItem(currentItem);
                        exitMenuChoice++;
                    }
                } else { // If the option is not restricted

                    // If current item is the exit choice
                    if (currentItem.getDescription().equalsIgnoreCase("exit")) {

                        // Print exit choice
                        MenuItem.printMenuItem(currentItem);
                    } else {

                        // Call method to print the menu item
                        MenuItem.printMenuItem(currentItem);
                    }

                    // Increment exit choice
                    exitMenuChoice++;
                }
            } // End of for loop
            
            
            // Prompt user for menu selection

            System.out.printf("Enter your selection: ");

            // Input user menu choice
            userMenuSelection = ScannerFactory.GetScannerInstance().nextInt();

            // Decrement exit choice by one
            exitMenuChoice -= 1;

            // If the user does not choose to exit the program
            if (userMenuSelection != exitMenuChoice) {

                // Call execute method on menu item selected by user
                menuItemsList.GetElement(userMenuSelection - 1).getCommand().Execute();

            } else // Users chooses to exit
            {
                // Exit program
                System.exit(1);
            }
        } while (userMenuSelection != exitMenuChoice); // Print menu while user does not choose to exit
    }

    /*
     * This method takes a menu item as a parameter and first verifies that it
     * is not already a menu item in the list (duplicates are not added). If the menu
     * item is not a duplicate, it appends the information of
     * the menu item to the menuList.dat file and adds the item to the
     * menuItemsList array list. The method does not return anything. 
     */
    public void AddMenuItem(MenuItem keyMenuItem)
    {
    
        // First check there doesnt already exist a menu item with the same command

        // Get and store value of menu item command to compare to others in file
        String keyMenuItemCommandAsString = getMenuItemCommandAsString(keyMenuItem);

        // Set flag
        boolean duplicateMenuOption = false;

        // If the menu item has a command
        if(keyMenuItemCommandAsString!= null)
        {
            // Loop through menu items in list
            for (int i = 0; i < menuItemsList.GetLength(); i++) {

                // Store current item
                MenuItem currentItem = menuItemsList.GetElement(i);

                // Get representation of command as string
                String currentMenuItemCommandAsString = getMenuItemCommandAsString(currentItem);

                // Check equality
                if(keyMenuItemCommandAsString.equalsIgnoreCase(currentMenuItemCommandAsString))
                {
                    // If equal, set Flag
                    duplicateMenuOption = true;
                    break;
                }

            }
        }

        // Increment size of list
        menuItemsList.incrementSizeOfList();
        
        // If the menu option is not already in the menulist by command
        if(!duplicateMenuOption)
        {

            // Add menu item to list
            menuItemsList.InsertLast(keyMenuItem);

            // Call method to write the new menu item to file
            MenuListOperations.writeMenuToFile(menuItemsList, "add");

        }
        // Menu item is a duplicate
        else
        {   
            System.out.println("Menu item unable to be added! It is a duplicate!");
        }
    }

    

    /*
     * This method prints the start menu. It takes as a parameter the current user
     * that is logged on to the system. This is used to determine which menu items
     * to print, contingent upon if the user is a manager or not.
     */
    public void StartMenu(User user)
    {
        
        // Define variables
        int exitMenuChoice;
        int userMenuSelection;
        int numRestrictedItems = 0;


        // Print menu header
        System.out.println(menuHeader);

   
        // Set starting exit menu choice value
        exitMenuChoice = 1;

        // Loop through items in menuItemsList
        for (int i = 0; i < menuItemsList.GetLength(); i++) 
        {
            // Store current item
            MenuItem currentMenuItem = menuItemsList.GetElement(i);

            // If the current option is for managers only
            if (currentMenuItem.getBooleanRestrictedToManager() == true) 
            {

                // If the user is not a manager and the item is restricted
                if (!user.getIsManager()) 
                {
                    continue;
                } 
                // If the user is a manager
                else 
                { 

                    // Print menu item
                    MenuItem.printMenuItem(currentMenuItem);

                    // Increment exit menu choice
                    exitMenuChoice++;
                }
            } 
            // If the option is not restricted
            else 
            {

                // Print menu item
                MenuItem.printMenuItem(currentMenuItem);

                // Increment exit menu choice
                exitMenuChoice++;
            }
        }

        
        // Add the exit menu choice to end of list -> null is the command
        menuItemsList.InsertLast(new MenuItem(null, exitMenuChoice, "Exit", false));
        
        // Print the exit menu option
        MenuItem.printMenuItem(menuItemsList.GetElement(exitMenuChoice - 1));

        // Prompt user for menu selection
        System.out.printf("Enter your selection: ");

        // Input menu selection
        userMenuSelection = ScannerFactory.GetScannerInstance().nextInt();

        // If the user does not choose to exit
        if (userMenuSelection != exitMenuChoice) {

            // Call execute method on menu item selected by user
            menuItemsList.GetElement(numRestrictedItems + userMenuSelection - 1).getCommand().Execute();
        } 
        // If user chooses to exit
        else 
        {
            // Exit program
            System.exit(0);
        }
    
    }

    /*
    This method takes a menu item as parameter and returns
    the command for the item, as a string.This method is used
    to validate no duplicate menu items are added to the menuItemsList
    or menuItemsList.txt file
    */
    public String getMenuItemCommandAsString(MenuItem keyMenuItem)
    {

        // Store command for menu item
        Command keyMenuItemCommand = keyMenuItem.getCommand();

        // Declare String variable
        String commandAsString;

        // Check if menuItem is any of the possible commands
        if (keyMenuItemCommand instanceof AddProductCommand) 
        {
            commandAsString = "AddProductCommand";
        } 
        else if (keyMenuItemCommand instanceof AddUserCommand) 
        {
            commandAsString = "AddUserCommand";
        } 
        else if (keyMenuItemCommand instanceof ChangePasswordCommand) 
        {
            commandAsString = "ChangePasswordCommand";
        } 
        else if (keyMenuItemCommand instanceof DeleteUserCommand) 
        {
            commandAsString = "DeleteUserCommand";
        } 
        else if (keyMenuItemCommand instanceof DisplayInventoryCommand) 
        {
            commandAsString = "DisplayInventoryCommand";
        } 
        else if (keyMenuItemCommand instanceof DisplayProductCommand) 
        {
            commandAsString = "DisplayProductCommand";
        } 
        else if (keyMenuItemCommand instanceof RemoveProductCommand) 
        {
            commandAsString = "RemoveProductCommand";
        } 
        else if (keyMenuItemCommand instanceof UpdateProductCommand) 
        {
            commandAsString = "UpdateProductCommand";
        } 
        else // If command is not a valid type (Should never occur)
        {
            commandAsString = null;
        }

        // Return the string representation of command
        return commandAsString;

    }

    // This method returns the linked list of menu items
    public static InventoryLinkedList<MenuItem> getMenuList()
    {
        return menuItemsList;
    }
}
