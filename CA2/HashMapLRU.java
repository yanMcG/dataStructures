package CA2;

import java.util.HashMap;

/**
 * Simple LRU cache using a HashMap for key->node lookup and a doubly-linked list for ordering.
 * Supports put, get, delete and automatic eviction when capacity is exceeded.
 */
public class HashMapLRU {
    class Node {
        String key;
        String value;
        Node prev, next;
        Node(String k, String v) { key = k; value = v; }
    }


    // LRU Cache properties
    private final HashMap<String, Node> map;
    private final int capacity;
    private final Node head;
    private final Node tail; 


    // Constructor
    public HashMapLRU(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }


    // Add a node to the front of the list
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }



    // Remove a node from the list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }



    // Move a node to the front (most recently used)
    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }



    // Insert or update a key-value pair
    public void put(String key, String value) {
        if (key == null) return;
        if (map.containsKey(key)) {
            Node n = map.get(key);
            n.value = value;
            moveToFront(n);
            return;
        }
        // New key
        if (map.size() >= capacity) {
            // evict LRU
            Node lru = tail.prev;
            if (lru != head) {
                removeNode(lru);
                map.remove(lru.key);
            }
        }
        // Insert new node
        Node node = new Node(key, value);
        addToFront(node);
        map.put(key, node);
    }


    // Get the value of a key if it exists in the cache
    public String get(String key) {
        Node n = map.get(key);
        if (n == null) return null;
        moveToFront(n);
        return n.value;
    }




    // Delete a key from the cache
    public void delete(String key) {
        Node n = map.remove(key);
        if (n == null) return;
        removeNode(n);
    }




    // Print current cache state from most to least recently used
    public void printCache() {
        Node cur = head.next;
        System.out.print("[MRU] ");
        // Print all key-value pairs
        while (cur != tail) {
            System.out.print(cur.key + ":" + cur.value + " ");
            cur = cur.next;
        }
        System.out.println(" [LRU]");
    }


    /*  
        Implement a hash table that acts as a cache using the least recently used caching strategy. The
        table should store key-value pairs and have a fixed capacity. The LRU element should be
        deleted to make room for any new element.

        4.1. Create a hash table with 20 values.
        4.1.1. A doubly linked list is used to maintain the order of elements
        4.1.2. A hash map is used to store the keys and their nodes
        4.2. If another element is added beyond the cache size, evict the LRU item
        4.3. Create the following operations using the base code provided:
        4.3.1. Insert a key-value pair into the cache
        4.3.2. Search for a key in the table, if it exists mark the pair as the most recently
        used
        4.3.3. Delete a key from the table
        4.3.4. Evict item when the cache is full

*/




    // Demo
    public static void main(String[] args) {
        HashMapLRU cache = new HashMapLRU(20);
        cache.put("1", "ryan");
        cache.put("2", "julia");
        cache.put("3", "shauna");
        cache.put("4", "Liam");
        cache.put("5", "Noah");
        cache.put("6", "Olivia");
        cache.put("7", "Emma");
        cache.put("8", "Ava");
        cache.put("9", "Isabella");
        cache.put("10", "Sophia");
        cache.put("11", "Mia");
        cache.put("12", "Charlotte");
        cache.put("13", "Amelia");
        cache.put("14", "Harper");
        cache.put("15", "Evelyn");
        cache.put("16", "Abigail");
        cache.put("17", "Emily");
        cache.put("18", "Elizabeth");
        cache.put("19", "Sofia");
        cache.put("20", "Avery");
        cache.printCache();


        // Get the value of a key if it exists in the cache
        System.out.println("get 2 -> " + cache.get("2"));
        cache.printCache();

        // Add a new key-value pair, should evict the LRU item
        cache.put("21", "grim reaper"); 
        cache.printCache();


        // Delete a key from the cache
        cache.delete("3");
        cache.printCache();


        // Add more items to see eviction in action
        cache.put("22", "Elderberry");
        cache.put("23", "Fig");
        cache.printCache();
    }
}
