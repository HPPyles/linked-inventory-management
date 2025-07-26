package LinkedInventoryManagement.Menu;

import java.lang.reflect.Constructor;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * Command
 */
/* Hosanna Pyles
 * hpp220001
 */
public abstract class Command 
{
    //TODO: add necessary fields/constructors to this class
    protected ProductCatalog productCatalog;
    protected User loggedOnUser;
   
    public Command(ProductCatalog productCatalog, User loggedOnUser)
    {
        this.productCatalog = productCatalog;
        this.loggedOnUser = loggedOnUser;
    }

    public static Command CreateCommandDynamically(ProductCatalog productCatalog, User user, String commandClassName)
    {
        Class<?>    concreteCommandClass    = null;
        Command     command                 = null;
        String      packageName             = "LinkedInventoryManagement.Menu"; 

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

    //An abstract method that must be overriden in subclasses of class Command
    public abstract void Execute(); 


}