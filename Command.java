package LinkedInventoryManagement.Menu;

import java.lang.reflect.Constructor;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * Command 
 */
public abstract class Command 
{
    //TODO: add necessary fields/constructors to this class
   
    private static ProductCatalog productCatalog;
    private User loggedOnUser;
    private static String commandsAsString;
    
    

    public Command(ProductCatalog productCatalog, User loggedOnUser)
    {
        Command.productCatalog = productCatalog;
        this.loggedOnUser = loggedOnUser;
    }

    public static Command CreateCommandDynamically(ProductCatalog productCatalog, User user, String commandClassName)
    {
        Class<?>    concreteCommandClass    = null;
        Command     command                 = null;
        String      packageName             = "LinkedInventoryManagement.Menu"; 

        commandsAsString += commandClassName;
        

        try 
        {
            concreteCommandClass = Class.forName(packageName + "." + commandClassName);
            Constructor<?> con = concreteCommandClass.getConstructor(ProductCatalog.class, User.class);
            command = (Command)con.newInstance(productCatalog, user);
        } 
        catch (final Exception e) 
        {
            e.printStackTrace();
        }

        return command;
    }

    // Returns the user logged onto the system
    public User getLoggedOnUser() {
        return this.loggedOnUser;
    }

    // Returns the product catalog object
    public static ProductCatalog getProductCatalog() {
        return productCatalog;
    }
   
    //An abstract method that must be overriden in subclasses of class Command
    public abstract void Execute(); 


}
