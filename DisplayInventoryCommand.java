package LinkedInventoryManagement.Menu;

import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;

/* Hosanna Pyles
 * hpp220001
 */

public class DisplayInventoryCommand extends Command {
    //TODO: add necessary fields/constructors to this class
    public DisplayInventoryCommand(ProductCatalog produCatalog, User loggedonUser)
    {
        super(produCatalog, loggedonUser);
    }

    @Override
    public void Execute()
    {
        System.out.println("=== Display Inventory ===");
        // Call PrintInventoryList to get a formatted string for the inventory
        String inventoryList = productCatalog.PrintInventoryList();
        System.out.println(inventoryList);
    }
}
