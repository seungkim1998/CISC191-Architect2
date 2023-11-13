package edu.sdccd.cisc191.template;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GroceryStoreTest {
    @Test
    public void testGroceryStore() {
        GroceryStore store = new GroceryStore(10);

        // Test the initial size and elements
        assertEquals(10, store.items.length);
        assertEquals(0, store.size);

        // Test adding and getting items
        GroceryItem item1 = new GroceryItem("Item1", 1.99);
        store.setAtIndex(0, item1);
        assertEquals(store.getAtIndex(0), item1);

        // Test finding the index of an item
        int foundIndex = store.findIndexOf(item1);
        assertEquals(0, foundIndex);

        // Test deleting an item
        store.deleteAtIndex(0);
        assertNull(store.getAtIndex(0));

        // Test expanding and shrinking the array
        store.expand(20);
        assertEquals(20, store.items.length);
        store.shrink(5);
        assertEquals(5, store.items.length);

    }

    @Test
    public void testGroceryStore2D() {
        GroceryStore2D store2D = new GroceryStore2D(5, 10);

        // Test the initial size and elements
        assertEquals(5, store2D.numRows);
        assertEquals(10, store2D.numCols);

        // Test adding and getting items
        GroceryItem item1 = new GroceryItem("Item1", 1.99);
        store2D.setAtIndex(0, 0, item1);
        assertEquals(item1, store2D.getAtIndex(0, 0));

        // Test finding the index of an item
        int foundIndex = store2D.findIndexOf(item1);
        assertEquals(0, foundIndex);

        // Test deleting an item
        store2D.deleteAtIndex(0, 0);
        assertNull(store2D.getAtIndex(0, 0));

        // Test expanding and shrinking the array
        store2D.expand(10, 20);
        assertEquals(10, store2D.numRows);
        assertEquals(20, store2D.numCols);
        store2D.shrink(3, 5);
        assertEquals(3, store2D.numRows);
        assertEquals(5, store2D.numCols);


    }

    @Test
    public void testGroceryResponse() {
        GroceryRequest request = new GroceryRequest("Dairy", "Milk");
        GroceryResponse response = new GroceryResponse(request, 5.99, 100);

        // Test getting request, price, and calories
        assertEquals(request, response.getRequest());
        assertEquals(5.99, response.getPrice(), 0.01);
        assertEquals(100, response.getCalories());
    }

    @Test
    void testPriceReadResultsExists() {
        String results = GroceryIOLab.readTestResults("Prices.csv");
        System.out.println("File Content:\n" + results); // Add this line for debugging

        assertTrue(results.startsWith("Iceberg Lettuce, $1.99, Not Organic"));
        assertTrue(results.contains("Medium Hass Avocado, $1.00, Not Organic"));
        assertTrue(results.endsWith("Blood Orange, $2.99, Not Organic"));
    }

    @Test
    void testReadTestResultsDoesNotExist() {
        String results = GroceryIOLab.readTestResults("IDoNotExist.csv");
        assertEquals("", results);
    }

    @Test
    void testAppendTestResults() {
        // Note: With each run, a new set of data may be appended to the file
        // Note: If multiples times are run, ensure you go back into Prices.csv and remove duplicates
        GroceryIOLab.appendTestResult("Prices.csv", "Zucchini Squash, $0.77, Not Organic");
        String results = GroceryIOLab.readTestResults("Prices.csv");
        assertTrue(results.endsWith("Zucchini Squash, $0.77, Not Organic"));
        GroceryIOLab.appendTestResult("Prices.csv", "Baby Bella Mushroom, $2.99, Organic");
        results = GroceryIOLab.readTestResults("Prices.csv");
        assertTrue(results.contains("Zucchini Squash, $0.77, Not Organic"));
        assertTrue(results.endsWith("Baby Bella Mushroom, $2.99, Organic"));
    }

    @Test
    public void testBinarySearch() {
        GroceryStore store = new GroceryStore(10);

        // Add items to the store
        GroceryItem item1 = new GroceryItem("Item1", 1.99);
        GroceryItem item2 = new GroceryItem("Item2", 2.99);
        GroceryItem item3 = new GroceryItem("Item3", 0.99);
        store.setAtIndex(0, item1);
        store.setAtIndex(1, item2);
        store.setAtIndex(2, item3);

        // Sort the items in the store
        store.sortItems();

        // Create a sorted array for searching
        GroceryItem[] sortedItems = {item1, item2, item3};

        // Search for item2
        int foundIndex = store.binarySearch(item2);

        // Assert that item2 is found at index 1
        assertEquals(1, foundIndex);
    }

    @Test
    public void testSortItems() {
        GroceryStore store = new GroceryStore(10);

        // Add items to the store in a random order
        GroceryItem item3 = new GroceryItem("Item3", 0.99);
        GroceryItem item1 = new GroceryItem("Item1", 1.99);
        GroceryItem item2 = new GroceryItem("Item2", 2.99);
        store.setAtIndex(0, item3);
        store.setAtIndex(1, item1);
        store.setAtIndex(2, item2);

        // Sort the items in the store
        store.sortItems();

        // Check if the items are sorted in ascending order
        assertEquals(item1, store.getAtIndex(0));
        assertEquals(item2, store.getAtIndex(1));
        assertEquals(item3, store.getAtIndex(2));
    }

}
