package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/**
 * AddCommand
 * Hosanna Pyles
 * hpp220001
 
 */
public class AddProductCommand extends Command
{
    //TODO: add necessary fields/constructors to this class

    public AddProductCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);

    }

    @Override
    public void Execute() {
        // TODO Add the code that will execute this command
        
        System.out.println("=== Add New Product ===");

        try {
            // Prompt for product details
            System.out.print("Enter product ID (integer): ");
            int id = Integer.parseInt(ScannerFactory.GetScannerInstance().nextLine().trim());

            System.out.print("Enter product name: ");
            String name = ScannerFactory.GetScannerInstance().nextLine().trim();

            System.out.print("Enter product cost (decimal): ");
            double cost = Double.parseDouble(ScannerFactory.GetScannerInstance().nextLine().trim());

            System.out.print("Enter product quantity (integer): ");
            int quantity = Integer.parseInt(ScannerFactory.GetScannerInstance().nextLine().trim());

            System.out.print("Enter product margin (as a percentage, integer): ");
            int margin = Integer.parseInt(ScannerFactory.GetScannerInstance().nextLine().trim());

            // Create new product
            Product newProduct = new Product(id, name, cost, quantity, margin);

            // Add or update the product in the catalog
            productCatalog.AddUpdateProduct(newProduct);

            System.out.println("Product added/updated successfully.");
        } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please try again.");
        }
    }
}