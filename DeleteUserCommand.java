package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;

/* Hosanna Pyles
 * hpp220001
 */

public class DeleteUserCommand extends Command {
    public DeleteUserCommand(ProductCatalog productCatalog, User loggedOnUser) {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute() {
        // Prompt for username to find user
        System.out.println("=== Delete User ===");
        System.out.print("Enter username to delete: ");
        String username = ScannerFactory.GetScannerInstance().nextLine().trim();
        
        // Remove user
        SecurityOperations.RemoveUser(username);
    }
}
