Main.java
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.io.*;

public class Main extends Application {
    private InventoryManager<InventoryItem> inventory = new InventoryManager<>();
    private TextArea displayArea;
    private TextField nameField, quantityField, priceField, categoryField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inventory Management System");
        
        // Create UI components
        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        
        Label quantityLabel = new Label("Quantity:");
        quantityField = new TextField();
        
        Label priceLabel = new Label("Price:");
        priceField = new TextField();
        
        Label categoryLabel = new Label("Category:");
        categoryField = new TextField();
        
        Button addButton = new Button("Add Item");
        Button displayButton = new Button("Display All");
        Button saveButton = new Button("Save to File");
        Button loadButton = new Button("Load from File");
        Button sortByNameButton = new Button("Sort by Name");
        Button sortByQuantityButton = new Button("Sort by Quantity");
        Button sortByPriceButton = new Button("Sort by Price");
        
        displayArea = new TextArea();
        displayArea.setEditable(false);
        
        // Layout
        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(10));
        inputGrid.setHgap(10);
        inputGrid.setVgap(5);
        
        inputGrid.add(nameLabel, 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(quantityLabel, 0, 1);
        inputGrid.add(quantityField, 1, 1);
        inputGrid.add(priceLabel, 0, 2);
        inputGrid.add(priceField, 1, 2);
        inputGrid.add(categoryLabel, 0, 3);
        inputGrid.add(categoryField, 1, 3);
        
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, displayButton, saveButton, loadButton, 
                                      sortByNameButton, sortByQuantityButton, sortByPriceButton);
        
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));
        mainLayout.getChildren().addAll(inputGrid, buttonBox, displayArea);
        
        // Event handlers
        addButton.setOnAction(e -> addItem());
        displayButton.setOnAction(e -> displayItems());
        saveButton.setOnAction(e -> saveToFile());
        loadButton.setOnAction(e -> loadFromFile());
        sortByNameButton.setOnAction(e -> {
            inventory.quickSort(InventoryManager.SortBy.NAME);
            displayItems();
        });
        sortByQuantityButton.setOnAction(e -> {
            inventory.quickSort(InventoryManager.SortBy.QUANTITY);
            displayItems();
        });
        sortByPriceButton.setOnAction(e -> {
            inventory.quickSort(InventoryManager.SortBy.PRICE);
            displayItems();
        });
        
        primaryStage.setScene(new Scene(mainLayout, 800, 600));
        primaryStage.show();
    }
    
    private void addItem() {
        try {
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            String category = categoryField.getText();
            
            InventoryItem item;
            if (category.equalsIgnoreCase("electronics")) {
                item = new ElectronicsItem(name, quantity, price, "N/A");
            } else if (category.equalsIgnoreCase("grocery")) {
                item = new GroceryItem(name, quantity, price, "N/A");
            } else {
                item = new InventoryItem(name, quantity, price);
            }
            
            inventory.addItem(item);
            clearFields();
        } catch (NumberFormatException e) {
            displayArea.setText("Please enter valid numbers for quantity and price.");
        }
    }
    
    private void displayItems() {
        displayArea.setText(inventory.toString());
    }
    
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("inventory.txt"))) {
            for (InventoryItem item : inventory.getItems()) {
                writer.println(item.toFileString());
            }
            displayArea.setText("Inventory saved to file.");
        } catch (IOException e) {
            displayArea.setText("Error saving to file: " + e.getMessage());
        }
    }
    
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            inventory.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String name = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    double price = Double.parseDouble(parts[2]);
                    
                    InventoryItem item;
                    if (parts.length > 3 && parts[3].equals("Electronics")) {
                        item = new ElectronicsItem(name, quantity, price, parts[4]);
                    } else if (parts.length > 3 && parts[3].equals("Grocery")) {
                        item = new GroceryItem(name, quantity, price, parts[4]);
                    } else {
                        item = new InventoryItem(name, quantity, price);
                    }
                    
                    inventory.addItem(item);
                }
            }
            displayArea.setText("Inventory loaded from file.\n" + inventory);
        } catch (IOException e) {
            displayArea.setText("Error loading from file: " + e.getMessage());
        }
    }
    
    private void clearFields() {
        nameField.clear();
        quantityField.clear();
        priceField.clear();
        categoryField.clear();
    }
}
