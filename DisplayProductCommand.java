package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Security.User;

/* Hosanna Pyles
 * hpp220001
 */

public class DisplayProductCommand extends Command {
    //TODO: add necessary fields/constructors to this class
    public DisplayProductCommand(ProductCatalog produCatalog, User loggedonUser)
    {
        super(produCatalog, loggedonUser);
    }

    @Override
    public void Execute()
    {
        System.out.println("=== Display Product Information ===");
        // Prompt for product id
        System.out.print("Enter product ID: ");
        try {
            int productId = Integer.parseInt(ScannerFactory.GetScannerInstance().nextLine().trim());

            Product currentProduct = productCatalog.FindProductFromID(productId);
            if (currentProduct == null){
                System.out.println("Product does not exist.");
                return;
            }

            // Run method to retrieve formatted product info
            String info = productCatalog.PrintProductInformation(currentProduct);

            // More formatting to go w formatted info
            System.out.println("Product Information:");
            System.out.println(String.format("%-5s %-12s %-8s %-12s %-8s", "Id", "Name", "Cost", "Quantity", "Retail"));
            System.out.println("------------------------------------------------");
            System.out.println(info);

            boolean go = true;
            Product adjacent = currentProduct;
            while (go) {
                System.out.println("Type 'Next' or 'Previous' to display next/previous product, press enter to return: ");
                String move = ScannerFactory.GetScannerInstance().nextLine().trim().toLowerCase();
                
                if (move.equals("next"))
                {
                    if (productCatalog.nextProduct(adjacent) == null)
                    {
                        System.out.println("End of products list.");
                        continue;
                    }
                    
                    adjacent = productCatalog.nextProduct(adjacent);
                    System.out.println("=== Display Product Information ===");
                    String infoNext = productCatalog.PrintProductInformation(adjacent);

                    // More formatting to go w formatted info
                    System.out.println("Product Information:");
                    System.out.println(String.format("%-5s %-12s %-8s %-12s %-8s", "Id", "Name", "Cost", "Quantity", "Retail"));
                    System.out.println("------------------------------------------------");
                    System.out.println(infoNext);
                }
                else if (move.equals("previous") || move.equals("prev"))
                {
                    if (productCatalog.prevProduct(adjacent) == null)
                    {
                        System.out.println("Top of products list.");
                        continue;
                    }
                    adjacent = productCatalog.prevProduct(adjacent);

                    System.out.println("=== Display Product Information ===");
                    String infoNext = productCatalog.PrintProductInformation(adjacent);

                    // More formatting to go w formatted info
                    System.out.println("Product Information:");
                    System.out.println(String.format("%-5s %-12s %-8s %-12s %-8s", "Id", "Name", "Cost", "Quantity", "Retail"));
                    System.out.println("------------------------------------------------");
                    System.out.println(infoNext);
                }
                else {go = false;}
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid product ID.");
        }
    }
}
