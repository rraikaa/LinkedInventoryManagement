package LinkedInventoryManagement.Menu;

/**
 * MenuItem
 */
public class MenuItem implements Comparable<MenuItem>
{
    //TODO Add data fields, constructors and methods as needed. Class must implement Comparable to compare two menu items.
    // Define variables
    private Command menuItemCommand;
    private String description;
    private int optionNumber;
    private Boolean isRestrictedToManagersOnly;
    private int numberTimesInFile = 1;

    // MenuItem constructor
    public MenuItem(Command command, 
                    int optionNumber, 
                    String description, 
                    Boolean isRestricted)
    {

        this.menuItemCommand = command;
        this.optionNumber = optionNumber;
        this.description = description;
        this.isRestrictedToManagersOnly = isRestricted;

        // For exit menu option command = null
        if(command != null)
            System.out.println("Menu item created with command " + command.getClass().getSimpleName());
    }

    // NEED TO DO THIS
    @Override
    public int compareTo(MenuItem menuItemToCompare) {
        // TODO Auto-generated method stub
        
        return 0;
    }

    public static void printMenuItem(MenuItem menuItem) {
        System.out.println(menuItem.getOptionNumber() + "- " + menuItem.getDescription());
    }

    // Returns option number of specific menu item object
    public int getOptionNumber() {
        return this.optionNumber;
    }

    // Returns description of specific menu item object
    public String getDescription() {
        return this.description;
    }

    // Returns if the pecific menu item object is restricted to be viewed by
    // managers only
    public Boolean getBooleanRestrictedToManager() {
        return this.isRestrictedToManagersOnly;
    }

    // Returns the command for this menu item
    public Command getCommand() {
        return this.menuItemCommand;
    }

    /*
     * This is an overloaded toString method for the menuItem object which creates a
     * string that includes certain data fields for the menu item. This string is
     * then returned.
     */
    public String toString(MenuItem menuItem) {
        StringBuilder menuItemString = new StringBuilder();

        menuItemString.append(menuItem.description + ", " + menuItem.isRestrictedToManagersOnly + ", "
                + menuItem.menuItemCommand + "\n");

        return menuItemString.toString();
    }

    // Returns the number of times a menuItem is stored in the file
    public int getNumTimesInFile()
    {
        return this.numberTimesInFile;
    }

    // Increments the number of times a menuItem is stored in the file
    public void incrementNumTimesInFile()
    {
        this.numberTimesInFile++;
    }
}
