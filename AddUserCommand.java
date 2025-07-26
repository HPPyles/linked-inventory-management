package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;

/* Hosanna Pyles
 * hpp220001
 */

public class AddUserCommand extends Command {
    public AddUserCommand(ProductCatalog productCatalog, User loggedOnUser) {
    super(productCatalog, loggedOnUser);
}

@Override
public void Execute() {
    System.out.println("=== Add New User ===");
    
    //Prompt for user info
    System.out.print("Enter first name: ");
    String firstName = ScannerFactory.GetScannerInstance().nextLine().trim();
    while (firstName == "")
    {
        System.out.println("Please input a first name:");
        firstName = ScannerFactory.GetScannerInstance().nextLine().trim();
    }

    System.out.print("Enter last name: ");
    String lastName = ScannerFactory.GetScannerInstance().nextLine().trim();
    while (lastName == "")
    {
        System.out.println("Please input a last name:");
        lastName = ScannerFactory.GetScannerInstance().nextLine().trim();
    }
    
    System.out.print("Enter username: ");
    String username = ScannerFactory.GetScannerInstance().nextLine().trim();
    while (username == "")
    {
        System.out.println("Please input a username:");
        username = ScannerFactory.GetScannerInstance().nextLine().trim();
    }
    
    System.out.print("Enter password: ");
    String password = ScannerFactory.GetScannerInstance().nextLine().trim();
    while (password == "")
    {
        System.out.println("Please input a password:");
        password = ScannerFactory.GetScannerInstance().nextLine().trim();
    }
    
    System.out.print("Is this user a manager? (true/false): ");
    String input = ScannerFactory.GetScannerInstance().nextLine().toLowerCase();
    System.out.println(input);
    while (input == "")
    {
        System.out.println("Please input true or false: ");
        input = ScannerFactory.GetScannerInstance().nextLine().trim().toLowerCase();
    }
    boolean isManager = input.equals("true") || input.equals("t");
    
    // Create new user and hash password
    String hashedPassword = SecurityOperations.GetPasswordHash(password);
    User newUser = new User(firstName, lastName, username, hashedPassword, isManager);
    
    // Call the method to add the new user
    SecurityOperations.AddNewUser(newUser);
    
    System.out.println("User addition process completed.");
}
}
