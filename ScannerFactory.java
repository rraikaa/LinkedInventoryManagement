package LinkedInventoryManagement.Common;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * ScannerFactory 
 */
public abstract class ScannerFactory {

    private static Scanner _scannerObject; 
    private static String[] fileNames = new String[3];
    private static Scanner[] fileScanners = new Scanner[3];
    
    // Create file objects for the files and initialize file Scanners 
    public ScannerFactory()
    {
        // Try to open the users file and create singleton scanner object
        try
        {
            File usersFile = new File("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/Users.txt");

            // Create new Scanner instance for users file
            fileScanners[0] = new Scanner(usersFile);

        } catch(Exception e) // Exception opening file
        {
            try {
                // File usersFile = new File("Users.dat");
                // FileWriter fw = new FileWriter("Users.dat");

                // Create new Users file
                File usersFile = new File("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/Users.txt");
                FileWriter fw = new FileWriter("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/Users.txt");

                // Create new Scanner instance for users file
                fileScanners[0] = new Scanner(usersFile);

                // Write admin information to Users file
                fw.write("admin, admin, admin, 58c536ed8facc2c2a293a18a48e3e120, true\n");
                fw.flush();
                
            } catch (Exception e2) {
                System.out.println("The Users.dat File did not exist and could not be created");
                fileScanners[0] = null;
            }

            try { // Try and create new file

                File usersFile = new File("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/users.txt");

                // Create new Scanner instance for users file
                fileScanners[0] = new Scanner(usersFile);
               
            } catch (Exception e2) { 
               
                System.out.println("The Users.txt File did not exist and could not be created");
            }
        }

        // Try to open the inventory file and create singleton scanner object
        try
        {
            File inventoryFile = new File("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/inventory.txt");
            fileScanners[1] = new Scanner(inventoryFile);

        } catch (Exception e)  // Exception opening file
        {
            try { // Try and create new file

                File inventoryFile = new File("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/inventory.txt");

                // Create new Scanner instance for users file
                fileScanners[1] = new Scanner(inventoryFile);

            } catch (Exception e2) {

                System.out.println("The inventory.txt File did not exist and could not be created");
            }
        }


        // Try to open the menuList file and create singleton scanner object
        try
        {
             File menuFile = new File("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/menuList.txt");

             // Create new Scanner instance for users file
             fileScanners[2] = new Scanner(menuFile);
        } 
        catch (Exception e) // Exception opening file
        {
            try { // Try and create new file

                File menuFile = new File("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/menuList.txt");

                // Create new Scanner instance for users file
                fileScanners[2] = new Scanner(menuFile);

            } catch (Exception e2) {

                System.out.println("The fileList.txt File did not exist and could not be created");
            }
        }

    }

    // Returns general singleton scanner for user input
    public static Scanner GetScannerInstance()
    {
        if(_scannerObject == null)
        {
            _scannerObject = new Scanner(System.in); 
        }

        return _scannerObject;
    }   

    

    /*
    Gets scanner instance for specific file
    */
    public static Scanner GetScannerInstance(String fileName)
    {
        if(fileName.equalsIgnoreCase("users.txt"))
        {
            return fileScanners[0];
        }
        else if(fileName.equalsIgnoreCase("inventory.txt"))
        {
            return fileScanners[1];
        }
        else if(fileName.equalsIgnoreCase("menuList.txt"))
        {
            return fileScanners[2];
        }
        else
        {
            return null;
        }
        
    }

    
    
    public static void CloseScannerInstance()
    {
        _scannerObject.close();
        _scannerObject = null; 
    } 

    public static void CloseAllFileScanners()
    {
        for(int i = 0; i < fileScanners.length; i++)
        {
            fileScanners[i].close();
            fileScanners[i] = null;
        }
    }
}
