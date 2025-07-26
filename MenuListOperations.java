package LinkedInventoryManagement.PersistentStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import LinkedInventoryManagement.Common.InventoryLinkedList;

/**
 * MenuListOperations: This class should contain all the MenuList.dat file read, write 
 * and update operations.
 */
/* Hosanna Pyles
 * hpp220001
 */
public class MenuListOperations {

    InventoryLinkedList<String> temp = new InventoryLinkedList<>(new String[0]);

    // TODO Add methods to read, write and update MenuList.dat
    public InventoryLinkedList<String> loadMenuFromFile(String fileName) {
        Path filePath = Paths.get(fileName);

        if (!Files.exists(filePath))
        {
            System.out.println("No menu items found. Please ask for adminstrative assistance.");
            return null;
        }

        try {
            //Get MenuList.dat contents
            List<String> lines = Files.readAllLines(filePath);
            
            for (String line : lines)
            {
                String[] parts = line.split(",");
                if (parts.length == 3)
                {
                    temp.Add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading menu from file:");
            e.printStackTrace();
        }

        return temp;
    }

    public void saveMenuToFile(InventoryLinkedList<String> newList, String fileName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newList.GetLength(); i++)
        {
            String[] parts = newList.GetElement(i).split(",");
            for (int j = 0; j < 3; j++)
            {
                String description = parts[0].trim();
                String isRestricted = parts[1].trim();
                String commandName = parts[2].trim();

                sb.append(description).append(", ")
                .append(isRestricted).append(", ")
                .append(commandName).append(System.lineSeparator());
            }
        }

        try {
            Files.write(Paths.get(fileName), sb.toString().getBytes());
        } catch (IOException e) {
            System.out.println("Error saving inventory to file:");
            e.printStackTrace();
        }
    }
}