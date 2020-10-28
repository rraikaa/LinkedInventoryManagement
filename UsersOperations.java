package LinkedInventoryManagement.PersistentStorage;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Security.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * UsersOperations:
 */
public class UsersOperations {

    
    // Create static list of users
    private static InventoryLinkedList<User> usersList = new InventoryLinkedList<>();

    /*
    This method returns a null linkedlist if users could not be read
    from file and a filled linkedlist if could be read from file. 
    */
    public static InventoryLinkedList<User> readUsers()
    { 
        boolean isSuccess = false;

        // Remove all elements from linked list
        for(int i = 0; i < usersList.GetLength(); i++)
        {
            usersList.Remove(i);
            usersList.decrementSizeOfList();
        }

       

            

            // Get scanner instance
            Scanner file = ScannerFactory.GetScannerInstance("users.txt");

            // Temporary variables
            String userInfo;
            String tempFirstName;
            String tempLastName;
            String tempUsername;
            String tempPassword;
            Boolean tempIsManager;

            // Loop through users.dat file and try to search for user
            while (file.hasNextLine()) 
            {
                // Store each line into array
                String[] userInfoArray = new String[5];

                // Read line
                userInfo = file.nextLine();

                // If the line in file is blank
                if (userInfo.isBlank()) {
                    if (file.hasNextLine())
                        userInfo = file.nextLine();
                    continue;
                }

                // Split array
                userInfoArray = userInfo.split(", ");

                // Store values in temporary variables
                tempFirstName = userInfoArray[0].trim();
                tempLastName = userInfoArray[1].trim();
                tempUsername = userInfoArray[2].trim();
                tempPassword = userInfoArray[3].trim();
                tempIsManager = Boolean.parseBoolean((userInfoArray[4]).trim());

                // Create new user object
                User newUser = new User(tempFirstName, tempLastName, tempUsername, tempPassword, tempIsManager);

                // Add new user to list of users
                usersList.InsertLast(newUser);

            }

            // Set flag
            isSuccess = true;

            if(file == null)
            {
                isSuccess = false;
            }
            
        if(isSuccess)
            return usersList;
        else
            return null;
        }


    /*
    
     This method is called after it is checked that the user is not already
     in the file. If the user is not already in the file, 
    the method writes the new user to the existing file and then adds the new user
    to the list of users. It returns true if the new user was successfully written
    to the file, and false otherwise.
    */
    
    public static boolean addNewUser(User userToAdd)
    {
        boolean successfulAddition = false;
        // need to search through and see if user list contains user already
        // Get hash value for new user password
        

        try {

            // FileWriter fileWriter = new FileWriter("Users.dat", true);
            FileWriter fileWriter = new FileWriter("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/Users.txt",
                    true);

            // Write information to file
            fileWriter.write(userToAdd.getFirstName() + ", " + userToAdd.getLastName() + ", " + userToAdd.getUsername()
                    + ", " + userToAdd.getPassword() + ", " + userToAdd.getIsManager() + "\n");

            fileWriter.flush();

            // Update userList
            usersList = readUsers();

            // Set flag
            successfulAddition = true;

            // Increment size of users list
            usersList.incrementSizeOfList();

        } catch (IOException e) 
        {
            // Set flag
            successfulAddition = false;
        }
        

        return successfulAddition;
    }

    /*
    This method searches through the list of users
    and checks if the user passed in as the parameter
    is in the list. If the user is in the list, it returns
    true, and false otherwise. 
    */
    public static boolean usersListContains(User user)
    {
        // Set flag
        boolean userFound = false;

        // Loop through users list
        for(int i = 0; i < usersList.GetLength(); i++)
        {

            // Store current user
            User currentUser = usersList.GetElement(i);

            // This is used for creating temporary user in changepassword method
            if(user.getFirstName().isBlank())
            {
                // Check if username and password match by overloaded compareTo method with only username and password
                if(currentUser.compareTo(user.getUsername(), user.getPassword()) == 0)
                {
                    // Set flag
                    userFound = true;
                    break;
                }

            }
            else // For normal comparison
            {
                // If the users match
                if (currentUser.compareTo(user) == 0) 
                {
                    // Set flag
                    userFound = true;
                    break;
                }
            }
            
        }

        // Return boolean if the user found
        return userFound;
    }



    /*
    This method takes a username as parameter and returns the
    User object of the user with the specific username, if one
    exists in the list. If a user with this username does not exist, 
    it returns a null User object. 
    */
    public static User getUserObjectByUsername(String username)
    {
        // Initialize key user as null
        User keyUser = null;

        for(int i = 0; i < usersList.GetLength(); i++)
        {

            // Store current user
            User currentUser = usersList.GetElement(i);

            // Check to see is usernames match
            if (currentUser.getUsername().equalsIgnoreCase(username))
                {
                // Set key User as the user at the current index
                keyUser = currentUser;
            
                break;
            }
        }

        // Return the key user 
        return keyUser;
    }

    // Returns linked user list
    public static InventoryLinkedList<User> getUsersList()
    {
        return usersList;
    }


    /*
    This method write the users from the user linked list to the
    users.txt file. If the users were successfully written to the file,
    it returns true. The method returns false otherwise. 
    */
    public static boolean writeUsersToFile()
    {
        boolean isSuccess = false;
        try
        {   
            FileWriter fileWriter = new FileWriter("/Users/rooperaikaa91/Desktop/LinkedInventoryManagement/Users.txt", false);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Loop through all valid users
            for (int i = 0; i < usersList.GetLength(); i++) {

                // Print valid user to Users.dat file
                bufferedWriter.write(usersList.GetElement(i).toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            isSuccess = true;

        }
        catch(IOException e)
        {
            isSuccess = false;
        }

        return isSuccess;
    }

}
