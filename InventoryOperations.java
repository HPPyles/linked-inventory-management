package LinkedInventoryManagement.PersistentStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Product.Product;

/**
 * InventoryOperations: This class should contain all the Inventory.dat file read, write 
 * and update operations. 
 */
/* Hosanna Pyles
 * hpp220001
 */
public class InventoryOperations {

    public InventoryLinkedList<Product> loadInventory(String fileName) {
        Path filePath = Paths.get(fileName);

        InventoryLinkedList<Product> list = new InventoryLinkedList<>(new Product[0]);
        try {
            if (!Files.exists(filePath))
            {
                Files.createFile(filePath);
            }

            
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines)
            {
                String[] parts = line.split(",");
                if (parts.length == 5)
                {
                    // Set variables using inventory info
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double cost = Double.parseDouble(parts[2].trim());
                    int quantity = Integer.parseInt(parts[3].trim());
                    int margin = Integer.parseInt(parts[4].trim());

                    //Make product object
                    Product product = new Product(id, name, cost, quantity, margin);

                    //Put product object in collection
                    list.Add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory from file:");
            e.printStackTrace();
        }

        return list;
    }
    
    public void saveInventory(InventoryLinkedList<Product> overwriteList, String fileName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < overwriteList.GetLength(); i++)
        {
            // Format: id, name, cost, quantity, margin
            sb.append(overwriteList.GetElement(i).getId()).append(", ")
            .append(overwriteList.GetElement(i).getProductName()).append(", ")
            .append(overwriteList.GetElement(i).getCost()).append(", ")
            .append(overwriteList.GetElement(i).getQuantity()).append(", ")
            .append(overwriteList.GetElement(i).getMargin()).append(System.lineSeparator());

            try {
                Files.write(Paths.get(fileName), sb.toString().getBytes());
            } catch (IOException e) {
                System.out.println("Error saving inventory to file:");
                e.printStackTrace();
            }
        }
    }
}