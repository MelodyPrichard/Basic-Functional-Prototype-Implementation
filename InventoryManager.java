InventoryManager.java
import java.util.ArrayList;

public class InventoryManager<T extends InventoryItem> {
    private ArrayList<T> items = new ArrayList<>();
    
    public enum SortBy {
        NAME, QUANTITY, PRICE
    }
    
    public void addItem(T item) {
        items.add(item);
    }
    
    public ArrayList<T> getItems() {
        return items;
    }
    
    public void clear() {
        items.clear();
    }
    
    // Quicksort implementation with O(n log n) average case complexity
    public void quickSort(SortBy sortBy) {
        quickSort(0, items.size() - 1, sortBy);
    }
    
    private void quickSort(int low, int high, SortBy sortBy) {
        if (low < high) {
            int pi = partition(low, high, sortBy);
            quickSort(low, pi - 1, sortBy);
            quickSort(pi + 1, high, sortBy);
        }
    }
    
    private int partition(int low, int high, SortBy sortBy) {
        T pivot = items.get(high);
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            boolean shouldSwap = false;
            switch (sortBy) {
                case NAME:
                    shouldSwap = items.get(j).getName().compareTo(pivot.getName()) < 0;
                    break;
                case QUANTITY:
                    shouldSwap = items.get(j).getQuantity() < pivot.getQuantity();
                    break;
                case PRICE:
                    shouldSwap = items.get(j).getPrice() < pivot.getPrice();
                    break;
            }
            
            if (shouldSwap) {
                i++;
                swap(i, j);
            }
        }
        
        swap(i + 1, high);
        return i + 1;
    }
    
    private void swap(int i, int j) {
        T temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-10s %-10s\n", "Name", "Quantity", "Price"));
        sb.append("----------------------------------------\n");
        for (T item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}
