ElectronicsItem.java
public class ElectronicsItem extends InventoryItem {
    private String warranty;
    
    public ElectronicsItem(String name, int quantity, double price, String warranty) {
        super(name, quantity, price);
        this.warranty = warranty;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Warranty: " + warranty;
    }
    
    @Override
    public String toFileString() {
        return super.toFileString() + ",Electronics," + warranty;
    }
}
