package LinkedInventoryManagement.Product;

/**
 * This class represent a line in Inventory.dat file
 */
/* Hosanna Pyles
 * hpp220001
 */
public class Product implements Comparable<Product> {

    private int id;
    private String name;
    private double cost;
    private int quantity;
    private int margin;

    public Product(int id, String name, double cost, int quantity, int margin)
    {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.margin = margin;
    }

    public int getId()
    {
        return id;
    }

    public String getProductName()
    {
        return name;
    }

    public double getCost()
    {
        return cost;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getMargin()
    {
        return margin;
    }

    @Override
    public int compareTo(Product productToCompare) {
        // TODO compare the parameter object to the current instance of product
        
        return Integer.compare(this.id,productToCompare.id);
    }

    
}