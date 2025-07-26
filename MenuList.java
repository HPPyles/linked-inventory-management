package LinkedInventoryManagement.Menu;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Common.ScannerFactory;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.User;
import LinkedInventoryManagement.PersistentStorage.MenuListOperations;

/**
 * MenuList
 */
/* Hosanna Pyles
 * hpp220001
 */
public class MenuList {
    private String menuHeader;    
    private final String fileName = "MenuList.dat";
    private ProductCatalog productCatalog;
    InventoryLinkedList<String> menuList;

    public MenuList(String menuHeader)
    {
        this.menuHeader = menuHeader;
        MenuListOperations menuListOperations = new MenuListOperations();
        menuList = menuListOperations.loadMenuFromFile(fileName);
    }

    public void AddMenuItem(MenuItem menuItem)
    {        
            // Create the file if it doesn't exist
            String newItemLine = menuItem.getDescription() + "," + menuItem.isRestricted() + "," + menuItem.getCommandName();
            
            // Check if this menu item is already in the file (using description as key)
            boolean duplicateFound = false;
            for (int i = 0; i < menuList.GetLength(); i++)
            {
                if (menuList.GetElement(i).contains(menuItem.getDescription()))
                {
                    duplicateFound = true;
                    break;
                }
            }
            
            if (!duplicateFound)
            {
                // Append the new menu item record with a newline
                menuList.Add(newItemLine);
            }
    }

    public void StartMenu(User user)
    {
        //TODO Display menu items based on user type, prompt user for command, execute selected command.
        Path filePath = Paths.get(fileName);
        
        // Ensure the file exists and has menu items
        if (!Files.exists(filePath))
        {
            System.out.println("No menu items found. Please contact an administrator.");
            return;
        }

        while (true) {
                // Read menu items from file
                InventoryLinkedList<String> availableMenuRecords = new InventoryLinkedList<>(new String[0]);
                int count = 0;
                
                System.out.println("\n" + menuHeader);
                
                // Display each menu item that the user has access to
                for (int i = 0; i < menuList.GetLength(); i++) {
                    String[] parts = menuList.GetElement(i).split(",");
                    if (parts.length < 3) {
                        continue; // Skip malformed lines
                    }
                    
                    boolean adminLock = Boolean.parseBoolean(parts[1].trim());
                    if (!user.isManager() && adminLock) {
                        continue; // Skip restricted items for non-managers
                    }
                    count++;
                    System.out.println(count + "- " + parts[0].trim());
                    // Save the record so we can use it later for command creation
                    availableMenuRecords.Add(menuList.GetElement(i));
                }
                
                // Always add the Exit option as the last item
                System.out.println((count + 1) + "- Exit");
                System.out.print("Enter your selection: ");
                
                // Get and validate user input
                String input = ScannerFactory.GetScannerInstance().nextLine();
                int selection;
                try {
                    selection = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue; // Redisplay the menu
                }
                
                // Check if user wants to exit
                if (selection == count + 1) {
                    System.out.println("Exiting...");
                break;
            }
                
            // Validate the selection range
            if (selection < 1 || selection > count) {
                System.out.println("Invalid selection. Please try again.");
                continue;
            }
                
            // Get the corresponding menu record
            //String[] selectedRecord = availableMenuRecords.get(selection - 1);
            String menuRecord = availableMenuRecords.GetElement(selection - 1);
            String[] selectedRecord = menuRecord.split(",");
            String description = selectedRecord[0].trim();
            String commandName = selectedRecord[2].trim();
                
            // Dynamically create and execute the command
            Command command = Command.CreateCommandDynamically(productCatalog, user, commandName);
            if (command != null) {
                command.Execute();
            } else {
                System.out.println("Error: Could not create command for " + description);
            }
                
            System.out.println("Press Enter to continue...");
            ScannerFactory.GetScannerInstance().nextLine();
        }
    }

    public void setProductCatalog(ProductCatalog productCatalog)
    {
        if (productCatalog == null) {
            System.err.println("Error: Attempted to set ProductCatalog to null.");
            return;
        }
        this.productCatalog = productCatalog;
    }
}