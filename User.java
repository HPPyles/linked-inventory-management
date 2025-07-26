package LinkedInventoryManagement.Security;

/**
 * User
 */
/* Hosanna Pyles
 * hpp220001
 */
public class User implements Comparable<User> {

    private String firstName;
    private String lastName;
    private String username;
    private String hashedPassword;
    private boolean isManager;

    public User(String firstName, String lastName, String username, String hashedPassword, boolean isManager)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username.toLowerCase(); // Username is not case sensitive
        this.hashedPassword = hashedPassword;
        this.isManager = isManager;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getHashedPassword() {
        return hashedPassword;
    }
    
    public boolean isManager() {
        return isManager;
    }

    @Override
    public int compareTo(User userToCompare)
    {
        return username.compareToIgnoreCase(userToCompare.username);
    }
}