package LinkedInventoryManagement.Security;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

import LinkedInventoryManagement.PersistentStorage.UsersOperations;

/**
 * InventoryManagementSecurity
 */
/* Hosanna Pyles
 * hpp220001
 */
public class SecurityOperations 
{
    // Verify user is in database, and if they are return user object storing user info
    public static User AuthenticateUser(String username, String password)
    {
        String filePath = "Users.dat";
        User authenticatedUser = null;
        String hashedPassword = GetPasswordHash(password);
        UsersOperations usersOperations = new UsersOperations();
        
        // Special case: Allow "admin" login with unhashed password "admin"
        if (username.equalsIgnoreCase("admin") && hashedPassword.equals("58c536ed8facc2c2a293a18a48e3e120"))
        {
            return new User("Admin","Adminer",username, hashedPassword, true); // Admin is always a manager
        }

        List<String> userList = usersOperations.loadInventory(filePath);
        
        //Check each line for inputted username and password
        for (String line : userList)
        {
            String[] parts = line.split(",");
            if (parts.length >= 5)
            {
                String firstName = parts[0].trim();
                String lastName = parts[1].trim();
                String storedUsername = parts[2].toLowerCase().trim();
                String storedPassword = parts[3].trim();
                boolean isManager = Boolean.parseBoolean(parts[4].trim());
                //Make sure username and password are in database and correct
                if (storedUsername.equals(username.toLowerCase()) && storedPassword.equals(hashedPassword)) 
                {
                    authenticatedUser = new User(firstName, lastName, username, storedPassword, isManager);
                    break;
                }
            }
        }

        if (authenticatedUser == null)
        {
            System.out.println("Invalid username or password. Please try again.");
        }

        return authenticatedUser; //Change the return value based on authentication result
    }

    public static void AddNewUser(User newUser)
    {
        String pathName = "Users.dat";
        Path userFile = Paths.get(pathName);
        UsersOperations usersOperations = new UsersOperations();

        List<String> users = usersOperations.loadInventory(pathName);
            for (String line : users) {
                String[] parts = line.split(",");
                // Check that there's a third field (username) and compare case-insensitively
                if (parts.length >= 3 && parts[2].trim().equalsIgnoreCase(newUser.getUsername()))
                {
                    System.out.println("User already exists. Please use a different username.");
                    return;
                }
            }

            String userRecord = newUser.getFirstName() + "," + newUser.getLastName() + "," + newUser.getUsername()
            + "," + newUser.getHashedPassword() + "," + newUser.isManager();

        try {                
            // Check if file ends with a newline, if not and file isn't empty, add newline
            byte[] fileBytes = Files.readAllBytes(userFile);
            String fileContent = new String(fileBytes);
            if (!fileContent.isEmpty() && !fileContent.endsWith(System.lineSeparator()))
            {
                userRecord = System.lineSeparator() + userRecord;
            }
            else
            {
                userRecord = userRecord + System.lineSeparator();
            }
            
            // Append new user record
            Files.write(userFile, userRecord.getBytes(), StandardOpenOption.APPEND);
            System.out.println("New user added successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while adding the new user.");
            e.printStackTrace();
        }
    }

    public static void RemoveUser(String userName)
    {
        String pathName = "Users.dat";
        UsersOperations usersOperations = new UsersOperations();
        List<String> userList = usersOperations.loadInventory(pathName);

        // Clear the file
        try (FileWriter fw = new FileWriter(pathName, false)) {
            // immediately closes, leaving an empty file of the same name to append to
        } catch (IOException e) { 
            e.printStackTrace();
        }
            // Removal flag
            boolean removed = false;

            for (String line : userList)
            {
                String[] parts = line.split(",");
                if (parts.length >= 3)
                {
                    String storedUsername = parts[2].trim();

                    // If line has inputted username, omit from updated list
                    if (storedUsername.equalsIgnoreCase(userName))
                    {
                        removed = true;
                        continue;
                    }
                }
                // Otherwise, put in list
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathName, true))) {
                    writer.write(line);
                    writer.newLine();  // add system-dependent line separator
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            

            //Output whether or not removal was successful
            if (removed)
            {
                System.out.println("User '" + userName + "' removed successfully.");
            }
            else
            {
                System.out.println("User '" + userName + "' not found.");
            }
    }

    public static void ChangePassword(String username, String currentPassword, String newPassword)
    {
        String filePath = "Users.dat";
        Path userFile = Paths.get(filePath);
        UsersOperations usersOperations = new UsersOperations();
        
        try {
            // Read all lines from file
            List<String> lines = usersOperations.loadInventory(filePath);
            int length = lines.size();
            String[] users = new String[length];
            boolean updated = false;
            
            // Process each line
            int i = 0; //counter
            for (String line : lines) {
                String[] parts = line.split(",");
                // Expected format: FirstName, LastName, Username, HashedPassword, isManager
                if (parts.length >= 5) {
                    String storedUsername = parts[2].trim();
                    if (storedUsername.equalsIgnoreCase(username)) {
                        String storedPassword = parts[3].trim();

                        // Only update if currentPassword matches stored hashed password
                        String hashedCurrentPassword = GetPasswordHash(currentPassword);

                        if (storedPassword.equals(hashedCurrentPassword))
                        {
                            // Build the updated record with the new password
                            String hashedNewPassword = GetPasswordHash(newPassword);
                            String updatedLine = parts[0].trim() + "," + parts[1].trim() + "," +parts[2].trim() + "," + hashedNewPassword + "," + parts[4].trim();
                            users[i] = updatedLine;
                            updated = true;
                            i++;
                            continue; // Skip adding the old record
                        }
                    }
                }
                // Add the record unchanged if no update was made
                users[i] = line;
                i++;
            }
            List<String> updatedLines = Arrays.asList(users);
            // Write the updated lines back to the file
            Files.write(userFile, updatedLines);
            
            if (updated)
            {
                System.out.println("Password updated successfully for user: " + username);
            } 
            else
            {
                System.out.println("Password update failed. Either username not found or current password is incorrect.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while changing the password.");
            e.printStackTrace();
        }
    }

    public static String GetPasswordHash(String password) 
    {        
        String generatedPassword = null;
        
        try 
        {
            byte[] salt = new byte[] {12, -12, 65, 61, 
                                      2, -6, -90, 12, 
                                      4, -7, -87, 2, 
                                      34, -102, 3, 115};

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(salt);
            // Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        } 

        return generatedPassword;
    }
}