package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;

/**
 * AddCommand
 * Hosanna Pyles
 * hpp220001
 
 */
public class ChangePasswordCommand extends Command
{
    //TODO: add necessary fields/constructors to this class

    public ChangePasswordCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);

    }

    @Override
    public void Execute() {
        System.out.println("=== Change Password ===");

        // Prompt the user for their username, current password, and new password
        System.out.print("Enter your username: ");
        String username = ScannerFactory.GetScannerInstance().nextLine().trim();

        System.out.print("Enter your current password: ");
        String currentPassword = ScannerFactory.GetScannerInstance().nextLine().trim();

        System.out.print("Enter your new password: ");
        String newPassword = ScannerFactory.GetScannerInstance().nextLine().trim();

        // Call the security method to change the password
        SecurityOperations.ChangePassword(username, currentPassword, newPassword);
    }
}