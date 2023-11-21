package edu.sdccd.cisc191.template;

import java.util.Arrays;
import java.util.Scanner;

//Sean Standen - Peer Review
//Abstracted out the main, groceryStoreMenu, and groceryStore2DMenu methods into a new class
//called GroceryStoreMain to clean up the GroceryStore class.
public class GroceryStore {
    private GroceryItem[] items;
    private int size;

    public GroceryStore(int initialSize) {
        this.items = new GroceryItem[initialSize];
        this.size = 0;
    }

    public void sortItems() {
        GroceryStoreSorter.quickSort(items, 0, size - 1); // Provide the array and the range to sort
    }

    public int findItem(GroceryItem item) {
        return GroceryStoreSearcher.binarySearch(items, size, item);
    }

    public void setAtIndex(int index, GroceryItem item) {
//        if (index >= 0 && index < items.length) {
//            items[index] = item;
//        }

        //Sean Standen - Peer Review
        //The code above was not working as intended.  To place an item at
        //an index of a normal array, you can just use array[index] = item.
        this.items[index] = item;
    }

    public GroceryItem getAtIndex(int index) {
        if (index >= 0 && index < size) {
            return items[index];
        }
        return null;
        // Return null if the index is out of bounds
    }


    public int findIndexOf(GroceryItem item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(items[i]);
        }
        System.out.println("They're no items to list");
    }

    public void deleteAtIndex(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                items[i] = items[i + 1];
            }
            items[size - 1] = null; // Remove the last item
            size--;
        }
    }

    //Sean Standen - Peer Review
    //Getter method for getting size.
    public int getSize() {
        return this.size;
    }

    //Sean Standen - Peer Review
    //Getter method for getting items array.
    public GroceryItem[] getItems() {
        return this.items;
    }

    public void expand(int newSize) {
        items = Arrays.copyOf(items, newSize);
    }

    public void shrink(int newSize) {
        if (newSize >= 0 && newSize < size) {
            items = Arrays.copyOf(items, newSize);
            size = newSize;
        }
    }
    public int binarySearch(GroceryItem item) {
        // First, sort the items array
        sortItems();

        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (items[mid].equals(item)) {
                return mid; // Item found, return its index
            }

            if (items[mid].compareTo(item) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return 1; // Item not found
    }
}



