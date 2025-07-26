package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/* Hosanna Pyles
 * hpp220001
 */

public class DeleteProductCommand extends Command {
    //TODO: add necessary fields/constructors to this class
    public DeleteProductCommand(ProductCatalog productCatalog, User loggedonUser)
    {
        super(productCatalog, loggedonUser);
    }

    @Override
    public void Execute()
    {
        System.out.println("=== Delete Product ===");

        try {
            // Prompt for the product ID to delete
            System.out.print("Enter product ID to delete: ");
            int id = Integer.parseInt(ScannerFactory.GetScannerInstance().nextLine().trim());

            Product deleteProduct = productCatalog.FindProductFromID(id);

            // Attempt to remove the product from the catalog, flags whether successful
            boolean removed = productCatalog.RemoveProduct(deleteProduct);
            
            //Display whether or not removal was successful
            if (removed)
            {
                System.out.println("Product deleted successfully.");
            }
            else
            {
                System.out.println("Product not found.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid product ID.");
        }
    }
}
