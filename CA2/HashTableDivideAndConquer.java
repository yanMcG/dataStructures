import java.util.Arrays;
public class HashTableDivideAndConquer {
 private String[] table;
 private int capacity;
 private int size;
 // Constructor to initialize the hash table with a given capacity
 public HashTableDivideAndConquer(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.table = new String[capacity];
 
 }



 // Simple hash function to map a string key to an index
 private int hash(String key) {
     return Math.abs(key.hashCode()) % capacity;
 }



 // Insert a key into the hash table
 public void insert(String key) {
    // Check load factor and resize if necessary
     if (size >= capacity) {
         resize();
     }
     // Find the next available slot using linear probing
     int index = hash(key);
     while (table[index] != null) {
         index = (index + 1) % capacity;
     }
     // Insert the key
     table[index] = key;
     size++;
 }





 // Search for a key in the hash table
 public boolean search(String key) {
     int index = hash(key);
     // Linear probing to find the key
     while (table[index] != null) {
        // Key found then return true 
         if (table[index].equals(key)) {
             return true;
         }
         // Move to the next index because of collision 
         index = (index + 1) % capacity;
     }
     return false;
 }



 // Delete a key from the hash table
 public void delete(String key) {
     int index = hash(key);
     // Linear probing to find the key
     while (table[index] != null) {
        //if key found then delete it and rehash the table
         if (table[index].equals(key)) {
             table[index] = null;
             size--;
             rehash();
             return;
         }
         index = (index + 1) % capacity;
     }
 }



 // Divide and Conquer Resize: Rehash the table by dividing the task into smaller chunks
 private void resize() {
    //double the capacity
     String[] oldTable = table;
     capacity *= 2;
     table = new String[capacity];
     size = 0;
     // Reinsert all existing keys
     for (String key : oldTable) {
         if (key != null) {
             insert(key);
         }
     }
 }



 // Rehash remaining elements to fill gaps after a deletion (Divide and Conquer Approach)
 private void rehash() {
    // Reinsert all existing keys
     String[] oldTable = table;
     table = new String[capacity];
     size = 0;
     for (String key : oldTable) {
         if (key != null) {
             insert(key);
         }
     }
 }




 // Print the hash table for debugging
 public void printTable() {
     System.out.println(Arrays.toString(table));
 }



 public static void main(String[] args) {
    HashTableDivideAndConquer unihashTable = new HashTableDivideAndConquer(20);
    // Insert keys
    unihashTable.insert("ATU Letterkenny");
    unihashTable.insert("ATU Killybegs");
    unihashTable.insert("ATU Sligo");
    unihashTable.insert("ATU Galway Mayo");
    unihashTable.insert("ATU Killybegs"); //add 15 more sample university campuses
    unihashTable.insert("UNI 1");
    unihashTable.insert("UNI 1");
    unihashTable.insert("UNI 2");
    unihashTable.insert("UNI 3");  
    unihashTable.insert("UNI 4");
    unihashTable.insert("UNI 5");
    unihashTable.insert("UNI 6");
    unihashTable.insert("UNI 7");
    unihashTable.insert("UNI 8");
    unihashTable.insert("UNI 9");
    unihashTable.insert("UNI 10");
    unihashTable.insert("UNI 11");
    unihashTable.insert("UNI 12");
    unihashTable.insert("UNI 13");
    unihashTable.insert("UNI 14");
    unihashTable.insert("UNI 15");

    // Print the hash table
    unihashTable.printTable();
    // Search for a key
    System.out.println("Is 'ATU Sligo' in the table? " + unihashTable.search("ATU Sligo")); // true
    System.out.println("Is 'ATU Dundalk' in the table? " + unihashTable.search("ATU Dundalk"));
    // false
    // Delete a key
    unihashTable.delete("ATU Galway Mayo");
    unihashTable.printTable();
    }
}