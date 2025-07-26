package LinkedInventoryManagement.Menu;

/**
 * MenuItem
 */
/* Hosanna Pyles
 * hpp220001
 */

public class MenuItem implements Comparable<MenuItem>
{
    //TODO Add data fields, constructors and methods as needed. Class must implement Comparable to compare two menu items.
    private Command command;
    private int optionNumber;
    private String description;
    private boolean isRestricted;

    public MenuItem(Command command, int optionNumber, String description, Boolean isRestricted)
    {
        this.command = command;
        this.optionNumber = optionNumber;
        this.description = description;
        this.isRestricted = isRestricted;

        System.out.println("Menu item created with command " + command.getClass().getSimpleName());
    }

    //Getters
    public Command getCommand() {
        return command; }
        
    public int getOptionNumber() { 
        return optionNumber; }

    public String getDescription() { 
        return description; }

    public boolean isRestricted() { 
        return isRestricted; }

    public String getCommandName() { 
        return command.getClass().getSimpleName(); }

    public void executeCommand() {
        command.Execute();
    }

    @Override
    public int compareTo(MenuItem menuItemToCompare) {
        // TODO Auto-generated method stub
        MenuItem menuItem = new MenuItem(this.command, this.optionNumber, this.description, this.isRestricted);

        if (menuItem == menuItemToCompare) {
            return 1;
        } else {
            return 0;
        }
    }

}