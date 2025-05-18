InventoryItem.java
public class InventoryItem implements Comparable<InventoryItem> {
    protected String name;
    protected int quantity;
    protected double price;
    
    public InventoryItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    
    @Override
    public String toString() {
        return String.format("%-20s %-10d $%-10.2f", name, quantity, price);
    }
    
    public String toFileString() {
        return name + "," + quantity + "," + price;
    }
    
    @Override
    public int compareTo(InventoryItem other) {
        return this.name.compareTo(other.name);
    }
}
