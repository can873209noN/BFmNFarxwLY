// 代码生成时间: 2025-09-22 14:40:32
package inventory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * InventoryManagedBean class to manage inventory operations.
 */
@ManagedBean(name = "inventoryBean")
@SessionScoped
public class InventoryManagedBean {

    // List to store inventory items
    private List<Item> inventoryItems;

    // Constructor
    public InventoryManagedBean() {
        inventoryItems = new ArrayList<>();
        // Initialize with some sample data
        inventoryItems.add(new Item("001\, "Apple", 100));
        inventoryItems.add(new Item("002", "Banana", 150));
        inventoryItems.add(new Item("003", "Cherry", 200));
    }

    // Getter and setter for inventoryItems
    public List<Item> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(List<Item> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    // Method to add an item to the inventory
    public void addItem(String id, String name, int quantity) {
        try {
            // Validate input
            if (id == null || name == null || quantity <= 0) {
                throw new IllegalArgumentException("Invalid item details");
            }
            inventoryItems.add(new Item(id, name, quantity));
        } catch (IllegalArgumentException e) {
            // Handle error
            System.out.println(e.getMessage());
        }
    }

    // Method to remove an item from the inventory
    public void removeItem(String id) {
        inventoryItems.removeIf(item -> item.getId().equals(id));
    }

    // Inner class representing an inventory item
    private static class Item {
        private String id;
        private String name;
        private int quantity;

        public Item(String id, String name, int quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
