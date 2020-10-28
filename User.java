package LinkedInventoryManagement.Security;


/**
 * User Class
 */
public class User implements Comparable<User>
{
    //TODO: Add necessary fields, constructor and methods. Consider implementing Comparable for this class. 

    // Define variables
    private String username;
    private String firstName;
    private String lastName;
    private String hashedPassword;
    private Boolean isManager;

    // Constructor
    public User(String username, String hashedPassword, boolean isManager) 
    {
        this.firstName = "";
        this.lastName = "";
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.isManager = isManager;
    }

    // Overloaded constructor
    public User(String firstName, String lastName, String username, String hashedPassword, boolean isManager) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.isManager = isManager;
    }

    // Needed to write this to be able to implement comparable
    public int compareTo(User user)
    {
        boolean usersEqual = true;

        if(!this.firstName.equalsIgnoreCase(user.username))
            usersEqual = false;
        else if(!this.lastName.equalsIgnoreCase(user.lastName))
            usersEqual = false;
        else if(!this.username.equalsIgnoreCase(user.username))
            usersEqual = false;
        else if(!this.hashedPassword.equalsIgnoreCase(user.hashedPassword))
            usersEqual = false;
        else if(!this.isManager.equals(user.isManager))
            usersEqual = false;

        if(usersEqual)
            return 0;
        else
            return -1;
        
    }

    /*
    This method is used to compare two users. It checks the equality
    of the username and password passed in, and the username and password
    store for the user object this (non-static) method was called on.
    */
    public int compareTo(String username, String password)
    {
        
        // User user = UsersOperations.getUserObjectByUsername(username);

        boolean userFound = false;

        // If the usernames and password match
        if (this.getUsername().equalsIgnoreCase(username)
                && this.getPassword().equalsIgnoreCase(password))
        {
            userFound = true;
        }
        
        if(userFound)
            return 0;
        else
            return -1;


    }

    // Returns first name of user
    public String getFirstName() 
    {
        return this.firstName;
    }

    // Returns last name of user
    public String getLastName() 
    {
        return this.lastName;
    }

    // Returns if the user is a manager or not
    public boolean getIsManager() 
    {
        return this.isManager;
    }

    // Returns password of user
    public String getPassword() 
    {
        return this.hashedPassword;
    }

    // Returns username of user
    public String getUsername() 
    {
        return this.username;
    }

    // Setter method to set hashed password of user
    public void setHashedPassword(String password)
    {
        this.hashedPassword = password;
    }

    /*
     * Overloaded toString method for User object. Stores user information in a
     * string and returns this string.
     */
    public String toString() 
    {

        String stringValue = this.firstName + ", " + this.lastName + ", " + this.username + ", " + this.hashedPassword
                + ", " + this.isManager;

        return stringValue;

    }
}
