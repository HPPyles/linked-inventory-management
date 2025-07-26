package LinkedInventoryManagement.PersistentStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * UsersOperations: This class should contain all the Users.dat file read, write 
 * and update operations.
 */
/* Hosanna Pyles
 * hpp220001
 */
public class UsersOperations {

    //TODO: Add methods to read, write and update Users.dat
    //open file
    //get data
    //close file
    //return data to caller.
    
    public List<String> loadInventory(String fileName) {
        Path filePath = Paths.get(fileName);
        //List<User> inventory = null;
        List<String> lines;

        try {
            if (!Files.exists(filePath))
            {
                System.out.println("No users found. Please ask admin to create accounts.");
                return null;
            }

            lines = Files.readAllLines(filePath);
            return lines;

        } catch (IOException e) {
            System.out.println("Error loading inventory from file:");
            e.printStackTrace();
        }

        return null;
    }
}