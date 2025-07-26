package LinkedInventoryManagement.Product;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.PersistentStorage.InventoryOperations;

/**
 * ProductCatalog
 */
/* Hosanna Pyles
 * hpp220001
 */
public class ProductCatalog {
    
    InventoryLinkedList<Product> productList; //Collection to hold Product objects
    private final String fileName = "Inventory.dat";

    // Constructor
    public ProductCatalog()
    {
        InventoryOperations inventoryOperations = new InventoryOperations();
        productList = inventoryOperations.loadInventory(fileName);
    }

    //Add or update a product if already exists
    public void AddUpdateProduct(Product product)
    {
        // Flag for success
        boolean updated = false;
        // Search for an existing product w same id
        for (int i = 0; i < productList.GetLength(); i++)
        {
            Product p = productList.GetElement(i);
            if (p.compareTo(product) == 0)
            {
                // Update existing product
                productList.SetElement(i, product);
                updated = true;
                break;
            }
        }
        // If flag for success not triggered
        if (!updated)
        {
            // Then add the product to the list instead
            productList.Add(product);
        }
        
        // Save list to file
        InventoryOperations inventoryOperations = new InventoryOperations();
        inventoryOperations.saveInventory(productList,fileName);
    }

    public boolean RemoveProduct(Product product)
    {
        for (int i = 0; i < productList.GetLength(); i++)
        {
            if (productList.GetElement(i).compareTo(product) == 0)
            {
                productList.Remove(i);
                // Save changes after removal
                InventoryOperations inventoryOperations = new InventoryOperations();
                inventoryOperations.saveInventory(productList,fileName);
                return true;
            }
        }
        return false;
    }

    public boolean FindProduct(Product product)
    {
        for (int i = 0; i < productList.GetLength(); i++)
        {
            if (productList.GetElement(i).compareTo(product) == 0)
            {
                return true;
            }
        }
        return false; 
    }

    public Product FindProductFromID(int Id)
    {
        for (int i = 0; i < productList.GetLength(); i++)
        {
            if (productList.GetElement(i).getId() == Id)
            {
                return productList.GetElement(i);
            }
        }
        return null; 
    }

    public Product nextProduct(Product product)
    {
        return productList.getNext(product);
    }

    public Product prevProduct(Product product)
    {
        return productList.getPrev(product);
    }

    //Print information about a product including retail price (cost + (margin*cost/100))
    public String PrintProductInformation(Product product)
    {
        for (int i = 0; i < productList.GetLength(); i++)
        {
            if (productList.GetElement(i).compareTo(product) == 0)
            {
                double retailPrice = productList.GetElement(i).getCost() + ((productList.GetElement(i).getMargin() * productList.GetElement(i).getCost()) / 100.0);

                // Return a formatted string w product details
                return String.format("%-5d %-12s $%-7.2f %-12d $%-7.2f", productList.GetElement(i).getId(), productList.GetElement(i).getProductName(),
                    productList.GetElement(i).getCost(), productList.GetElement(i).getQuantity(), retailPrice);
            }
        }
        return "Product not found.";
    }

    //Print all product information in the inventory
    public String PrintInventoryList()
    {
        StringBuilder inventoryInformation = new StringBuilder(); 

        inventoryInformation.append(String.format("%-5s %-12s %-8s %-12s %-8s\n", "Id", "Name", "Cost", "Quantity", "Retail"));
        inventoryInformation.append("------------------------------------------------\n");
        for (int i = 0; i < productList.GetLength(); i++)
        {
            double retailPrice = productList.GetElement(i).getCost() + ((productList.GetElement(i).getMargin() * productList.GetElement(i).getCost()) / 100.0);
            inventoryInformation.append(String.format("%-5d %-12s $%-7.2f %-12d $%-7.2f\n", productList.GetElement(i).getId(),
                productList.GetElement(i).getProductName(), productList.GetElement(i).getCost(), productList.GetElement(i).getQuantity(), retailPrice));
        }

        return inventoryInformation.toString(); 
    }
}