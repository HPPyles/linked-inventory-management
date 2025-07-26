package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/* Hosanna Pyles
 * hpp220001
 */
public class UpdateProductCommand extends Command {
    public UpdateProductCommand(ProductCatalog produCatalog, User loggedonUser)
    {
        super(produCatalog, loggedonUser);
    }

    @Override
    public void Execute()
    {
        System.out.println("=== Update Product ===");

        try {
            // Prompt for the product ID to update
            System.out.print("Enter product ID to update: ");
            int id = Integer.parseInt(ScannerFactory.GetScannerInstance().nextLine().trim());
            Product productExists = productCatalog.FindProductFromID(id);

            // Check if the product exists
            if (!productCatalog.FindProduct(productExists)) {
                System.out.println("Product not found.");
                return;
            }
            
            // Get new details for the product
            System.out.print("Enter new product name: ");
            String name = ScannerFactory.GetScannerInstance().nextLine().trim();
            
            System.out.print("Enter new product cost (decimal): ");
            double cost = Double.parseDouble(ScannerFactory.GetScannerInstance().nextLine().trim());
            
            System.out.print("Enter new product quantity (integer): ");
            int quantity = Integer.parseInt(ScannerFactory.GetScannerInstance().nextLine().trim());
            
            System.out.print("Enter new product margin (as a percentage, integer): ");
            int margin = Integer.parseInt(ScannerFactory.GetScannerInstance().nextLine().trim());
            
            // Create an updated Product object
            Product updatedProduct = new Product(id, name, cost, quantity, margin);
            
            // Update the product in the catalog
            productCatalog.AddUpdateProduct(updatedProduct);
            
            System.out.println("Product updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please ensure numbers are entered where required.");
        }
    }
}
