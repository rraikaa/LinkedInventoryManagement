package LinkedInventoryManagement.Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.PersistentStorage.UsersOperations;

/**
 * InventoryManagementSecurity
 */
public class SecurityOperations {

    /*
    This method takes a username and password as parameters. It reads the
    users from the users.txt file and stores them in the list. Then, it loops through the
    list and checks if there exists a user with the passed in parameters. If so,
    the method returns an authenticated user object with the user whose credentials were passed in.
    If matching credentials are not found, it returns a null user object. 
    */
    public static User AuthenticateUser(String username, String password) {
        User authenticatedUser = null;
        User unauthenticatedUser = null;
        boolean userFound = false;

        if ((username.compareToIgnoreCase("admin") == 0)
                && (GetPasswordHash(password).compareToIgnoreCase("58c536ed8facc2c2a293a18a48e3e120") == 0)) {
            authenticatedUser = new User(username, GetPasswordHash(password), true);
            userFound = true;
        } else {

            // Store list of users
            InventoryLinkedList<User> usersList = UsersOperations.readUsers();

            // Loop through list of users
            for (int i = 0; i < usersList.GetLength(); i++) {

                // If the list of users contains this element
                if (UsersOperations.usersListContains(usersList.GetElement(i))) 
                {
                    // Set flag
                    userFound = true;

                    // Retrieve authenticated user from list
                    authenticatedUser = usersList.GetElement(i);
                    break;
                }
            }
        }

        // If user found return the user pbject
        if (userFound)
            return authenticatedUser;
        else
            return unauthenticatedUser; // This will be null if user not found
    }

    /*
    This method is called by the AddUserCommand, before the password is hashed for the new user. 
    This method hashes the password of the new user and sets this password. If the
    list contains this user already, an error messageis printed. Otherwise, it calles a method to write
    the user to the file and adds them to the list. 
    */
    public static void AddNewUser(User newUser) {

        // Get hashed password
        String hashedPassword = SecurityOperations.GetPasswordHash(newUser.getPassword());
        // Set hashed password for new user
        newUser.setHashedPassword(hashedPassword);

        // If a username already exists in the list with the same username and password
        if (UsersOperations.usersListContains(newUser)) {
            System.out.println("Can not add duplicate user!");
        } else {
            // Call method to write user to Users.dat file
            UsersOperations.addNewUser(newUser);
        }

    }

    /*
    This method gets the User object to remove, and then gets the index
    of this object. Then, it calls the Remove method inherited from the
    InventoryLinkedList class, and passes in the index of this user object
    in the list, and removes them from the list. Then, the method
    writes the updates list of users to the file.
    */
    public static void RemoveUser(String userName) {
        // TODO remove username from Users.dat
        // Check to see the username is in the list
        User userToRemove = UsersOperations.getUserObjectByUsername(userName);

        // If the user is not in the list
        if (userToRemove == null) {
            System.out.println("The user with this username does not exist in the list, can't remove!");
        } else // If the user is in list
        {
            InventoryLinkedList<User> usersList = UsersOperations.getUsersList();

            int indexOfUser = usersList.GetIndexOfElement(userToRemove);

            usersList.Remove(indexOfUser);

            // Decrement size of usersList
            usersList.decrementSizeOfList();

            // Rewrite to file without removed user
            UsersOperations.writeUsersToFile();

        }

    }

    /*
     * This method takes as parameters the username of the user whose password is to
     * be changed, their current password, and their new password. The method first
     * searches through the list of users to check if the username that is passed in
     * is a username of a user in the system. If the username is found, the method
     * gets the user object and then validates that the password passed in matches
     * the password on file. If so, the method updated the data field on the user
     * object and writes the user information from the list to the file again, with
     * the new password; the method returns true. If the credentials are not
     * validated correctly, the password is not changed and the method returns
     * false.
     */
    public static void ChangePassword(String username, String currentPassword, String newPassword) {
        // TODO change the password only if current password match what is on records
        // Create user objects with only password and username

        // TODO: is current password hashed?

        User tempUser = new User("", "", username, currentPassword, false);

        // contains method checks if username and password match
        boolean validInfo = UsersOperations.usersListContains(tempUser);

        // If info not valid
        if (!validInfo) {
            System.out.println("The username and password combination is not correct! Password not changed");
        } else // Username and password match
        {

            // Get user object of user to change password
            User userToModify = UsersOperations.getUserObjectByUsername(username);

            // Hash new password and update user password -> will automatically change it in
            // usersList
            userToModify.setHashedPassword(GetPasswordHash(newPassword.trim()));

            // Write users to file
            UsersOperations.writeUsersToFile();

        }

    }

    // Returns hashed version of password passed in
    public static String GetPasswordHash(String password) {
        String generatedPassword = null;

        try {
            byte[] salt = new byte[] { 12, -12, 65, 61, 2, -6, -90, 12, 4, -7, -87, 2, 34, -102, 3, 115 };

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(salt);
            // Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;
    }
}
