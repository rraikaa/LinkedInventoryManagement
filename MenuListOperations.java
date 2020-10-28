package LinkedInventoryManagement.PersistentStorage;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Menu.Command;
import LinkedInventoryManagement.Menu.MenuItem;
import LinkedInventoryManagement.Menu.MenuList;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * MenuListOperations: 
 */
public class MenuListOperations {

    /*
    This method reads the menu from the file and stores the
    menu items in a menu items list, via the passed in productCatalog object.
    The method ensures that no duplicate menu items are added to the list. A menu
    item is considered a duplicate if a menu item already exists in the list with
    the same command. The method returns a linked list of type Menu Item. 

    */
    public static InventoryLinkedList<MenuItem> loadMenu(ProductCatalog productCatalog, User systemUser,
            int optionNumber) {
 
        
        // Declare menu Items linked list
        InventoryLinkedList<MenuItem> menuItemsList = MenuList.getMenuList();
            
        // Read menu from file



        // CAN REMOVE THIS TRY



            Scanner menuScanner = ScannerFactory.GetScannerInstance("menuList.txt");

            // todo check not getting double commands for menu items
            // String [] commandsAsString = new String[10];

            // While there is data in the file
            while (menuScanner.hasNextLine()) {

                // Define variables
                String menuOption;
                String[] menuOptionArray = new String[3];

                // Input menu data
                menuOption = menuScanner.nextLine();
                // Split components
                menuOptionArray = menuOption.split(", ");

                // STORE INDIVIDIAL VALUES FOR MENU ITEM
                String menuItemDescription = menuOptionArray[0];
                boolean menuItemOnlyForManagers = Boolean.parseBoolean(menuOptionArray[1]);
                String commandClassName = menuOptionArray[2].trim();

                // Set flag
                boolean duplicateCommand = false;

                // Store size of list
                int menuListSize = MenuList.getMenuList().GetLength();
                
                
                // Loop through menu list
                for(int i = 0; i < menuListSize; i++)
                {
                    
                    // Store current item
                    MenuItem currentItem = MenuList.getMenuList().GetElement(i);

                    // Store name of command
                    String currentCommandName = currentItem.getCommand().getClass().getSimpleName();

                    // Check if command name matches any of the existing command names
                    if(currentCommandName.equalsIgnoreCase(commandClassName))
                    {
                        // Increment the number of times the command is in the file
                        currentItem.incrementNumTimesInFile();

                        // If an item with the same command is already in the file
                        if(currentItem.getNumTimesInFile() > 1)
                        {
                            // Set flag
                            duplicateCommand = true;
                        }
                        
                    }
                }
                
                // Do not add a duplicate item to list
                if(duplicateCommand)
                {
                    continue;
                }

                // Dynamically create command object for specific command
                Command dynamicCommand = Command.CreateCommandDynamically(productCatalog, systemUser, commandClassName);

                // Create new menu item object
                MenuItem menuItem = new MenuItem(dynamicCommand, optionNumber, menuItemDescription,
                        menuItemOnlyForManagers);

                // Only add to list if user is manager (can see ever menu item)
                // Or if the item is not restricted
                if (systemUser.getIsManager() || !menuItemOnlyForManagers) {

                    // Add menu item to menuList
                    menuItemsList.InsertLast(menuItem);

                    // Increment size of menu items list
                    menuItemsList.incrementSizeOfList();

                    optionNumber++; // increment option number
                }

            }

        return menuItemsList; // Return the menu list object
    }

    /*
    This method takes a menuITemsList as a parameter and the operation intended, and write
    the menuItemsList to the file. The method may be called after adding a new item, where the
    operation type will be "add", and it will only append the last item in the list to the file.
    If the type is "update", it writes the whole list to the file. 
    */
    public static boolean writeMenuToFile(InventoryLinkedList<MenuItem> menuItemsList, String operationType)
    {

        boolean isSuccess = false;

        int menuSize = menuItemsList.GetLength();

        // If new product was added, append last element in list to file
        if(operationType.equalsIgnoreCase("add"))
        {
            try {

                // Get last element in list
                MenuItem itemToAdd = menuItemsList.GetElement(menuSize - 1);

                // Create file writer instance
                // FileWriter fw = new FileWriter("MenuList.dat", true);
                FileWriter fw = new FileWriter("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/menuList.txt", true);

                // Write item to file
                fw.write(itemToAdd.toString());
                fw.flush();

                // Set flag
                isSuccess = true;

            } catch (Exception e) {
                System.out.println("The menuList.dat file could not be found");
                isSuccess = false;
            }
        }
        // If product was updated, write entire menu list to file
        else
        {   
            try
            
            { // New file writer object
                // FileWriter fw = new FileWriter("MenuList.dat", true);
                FileWriter fw = new FileWriter("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/menuList.txt", true);
                
                // Loop through items in menu
                for(int i = 0; i < menuSize; i++)
                {
                
                    // Store current item
                    MenuItem currentItem = menuItemsList.GetElement(i);
                
                    fw.write(currentItem.toString());
                    fw.flush();

                    // setFlag
                    isSuccess = true;
                } 
            }
            catch(Exception e)
            {
                System.out.println("The menuList.dat file could not be found");
                isSuccess = false;
            }
            
        }
        
        return isSuccess;
    
    }

    

}
