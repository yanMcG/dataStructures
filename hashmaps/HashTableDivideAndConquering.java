

import java.util.Arrays;

public class HashTableDivideAndConquering {

    // Entry class to hold key-value pairs
    private static class Entry {
        String key;
        String value;
        Entry(String k, String v) { key = k; value = v; }
        public String toString() { return key + "=" + value; }
    }




    // Hash table properties
    private Entry[] table;
    private int capacity;
    private int size;
    private static final double LOAD_FACTOR = 0.75;




    // Constructor to initialize the hash table with a given capacity
    public HashTableDivideAndConquering(int capacity) {
        this.capacity = Math.max(4, capacity);
        this.size = 0;
        this.table = new Entry[this.capacity];
    }





    // Simple hash function to map a string key to an index
    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }





    // Insert or update a key->value pair
    public void insert(String key, String value) {
        if (key == null) return;
        // resize if load factor would be exceeded
        if ((double)(size + 1) / capacity > LOAD_FACTOR) {
            resize();
        }
        // Find the next available slot using linear probing
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                // update existing
                table[index].value = value;
                return;
            }
            // Move to the next index because of collision
            index = (index + 1) % capacity;
        }
        // Insert the new entry
        table[index] = new Entry(key, value);
        size++;
    }





    // Get value for a key (null if not found)
    public String get(String key) {
        if (key == null) return null;
        int index = hash(key);
        int start = index;
        // Linear probing to find the key
        while (table[index] != null) {
            // Key found then return value
            if (table[index].key.equals(key)) return table[index].value;
            
            // Move to the next index because of collision
            index = (index + 1) % capacity;

            // Full loop check
            if (index == start) break; // full loop
        }
        return null;
    }




    // Check existence of a key
    public boolean contains(String key) {
        return get(key) != null;
    }




    // Delete a key and reinsert only the following cluster
    public void delete(String key) {
        // Check for null key
        if (key == null) return;
        int index = hash(key);
        int start = index;

        // Linear probing to find the key
        while (table[index] != null) {
            // Key found then remove
            if (table[index].key.equals(key)) {
                // remove
                table[index] = null;
                size--;
               
                // reinsert contiguous cluster after this slot
                int next = (index + 1) % capacity;
                while (table[next] != null) {
                    // reinsert entry
                    Entry e = table[next];
                    table[next] = null;
                    size--;
                    insert(e.key, e.value);
                    next = (next + 1) % capacity;
                }
               
                return;
            }

            // Move to the next index because of collision
            index = (index + 1) % capacity;
            if (index == start) break;
        }
    }






    // Resize with divide-and-conquer rehashing
    private void resize() {
        Entry[] old = table;
        capacity *= 2;
        table = new Entry[capacity];
        size = 0;
        // Rehash entries using divide-and-conquer order to reduce clustering behavior
        rehashRange(old, 0, old.length - 1);
    }





    // Rehash entries using divide-and-conquer order to reduce clustering behavior
    private void rehashRange(Entry[] old, int lo, int hi) {
        // if base case then return
        if (lo > hi) return;
        int mid = (lo + hi) >>> 1;

        // Rehash the middle entry first because it helps reduce clustering
        if (old[mid] != null) {
            // reinsert into new table by 
            insert(old[mid].key, old[mid].value);
        }

        // Recur on left and right halves
        rehashRange(old, lo, mid - 1);
        rehashRange(old, mid + 1, hi);
    }




    // Print the hash table for debugging
    public void printTable() {
        String[] out = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            out[i] = (table[i] == null) ? "null" : table[i].toString();
        }
        System.out.println(Arrays.toString(out));
    }

    // Demo main (uses key==value for simplicity)
    public static void main(String[] args) {
        HashTableDivideAndConquering t = new HashTableDivideAndConquering(20);
        t.insert("ATU Letterkenny", "ATU Letterkenny");
        t.insert("ATU Killybegs", "ATU Killybegs");
        t.insert("ATU Sligo", "ATU Sligo");
        t.insert("ATU Galway Mayo", "ATU Galway Mayo");
        t.insert("ATU Killybegs", "ATU Killybegs"); // update/duplicate
        for (int i = 1; i <= 15; i++) t.insert("UNI " + i, "UNI " + i);

        t.printTable();
        System.out.println("Is 'ATU Sligo' in the table? " + t.contains("ATU Sligo"));
        System.out.println("Is 'ATU Dundalk' in the table? " + t.contains("ATU Dundalk"));

        t.delete("ATU Galway Mayo");
        t.printTable();
    }
}