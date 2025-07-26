package LinkedInventoryManagement;

import LinkedInventoryManagement.Menu.*;
import LinkedInventoryManagement.Common.*;
import LinkedInventoryManagement.Product.*;
import LinkedInventoryManagement.Security.*;

/**
 * Hello world!
 */
/* Hosanna Pyles
 * hpp220001
 */
public class Main 
{
    public static void main( String[] args )
    {
        User loggedOnUser = null;

        // Keep prompting until user logs in or exits
        while (loggedOnUser == null)
        {
            // Prompt for user name and password
            System.out.print("Enter username: ");
            String username = ScannerFactory.GetScannerInstance().nextLine();
            System.out.print("Enter password: ");
            String password = ScannerFactory.GetScannerInstance().nextLine();

            // Make sure credentials are valid
            loggedOnUser = SecurityOperations.AuthenticateUser(username, password);
            if (loggedOnUser == null) // AuthenticateUser returns null if authentication could not find credentials
            {
                System.out.println("Try again or type 'exit' to quit.");
                if (ScannerFactory.GetScannerInstance().nextLine().equalsIgnoreCase("exit")) // Typing exit will end the program
                {
                    System.out.println("Exiting program.");
                    ScannerFactory.GetScannerInstance().close();
                    return;
                }
            }
        }

        // --- MAIN MENU ---
        //If login is successful, welcome user by username
        System.out.println("Welcome " + loggedOnUser.getFirstName() + " " + loggedOnUser.getLastName() + "!");

        // Catalog
        ProductCatalog productCatalog = new ProductCatalog();

        // Load the menu
        MenuList menuList = new MenuList("Inventory Management System Menu");
        menuList.setProductCatalog(productCatalog);
        

        menuList.StartMenu(loggedOnUser);

        //Close the Scanner at the end of your program as follows. 
        ScannerFactory.CloseScannerInstance();
    }
}
