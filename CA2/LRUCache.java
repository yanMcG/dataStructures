package CA2;

import java.util.*;

// LRU Cache implementation from
//https://codefarm0.medium.com/lets-implement-an-lru-cache-in-java-step-by-step-e0ef505db9f9

class LRUCache {
    // Node class for doubly linked list
    class Node {
        int key, value;
        Node prev, next;

        // Constructor
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    // LRU Cache properties
    private HashMap<Integer, Node> cache;
    private int capacity, size;
    private Node head, tail;



    // Constructor
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        // Dummy nodes to avoid null checks
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }


    // Move a node to the front (most recently used)
    private void moveToFront(Node node) {
        remove(node);
        addToFront(node);
    }



    // Add a node to the front of the list
    private void addToFront(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }



    // Remove a node from the list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }



    // Get the value of a key if it exists in the cache
    public int get(int key) {
        // if key is not found then return false
        if (!cache.containsKey(key)) return -1; 
        
        
        // Move the accessed node to the front
        Node node = cache.get(key);
        moveToFront(node);
        return node.value;
    }


    //extra method from https://codefarm0.medium.com/lets-implement-an-lru-cache-in-java-step-by-step-e0ef505db9f9 its called medium just to let ya know
    // Insert a key-value pair into the cache
    public void put(int key, int value) {
        // if key exists, update value and move to front
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToFront(node);

        // if key does not exist, insert new node    
        } else {
            if (cache.size() >= capacity) {
                Node lru = tail.prev;
                remove(lru);
                cache.remove(lru.key);
            }


            // Create a new node then add to front for cache
            Node newNode = new Node(key, value);
            addToFront(newNode);
            cache.put(key, newNode);
        }
    }




    // Print the cache for debugging
    public void printCache() {
        Node curr = head.next;
        // Traverse from head to tail
        while (curr != tail) {
            System.out.print(curr.key + ":" + curr.value + " ");

            // Move to the next node
            curr = curr.next;
        }
        System.out.println();
    }



    // Main method to test the LRU Cache implementation
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3); // Cache capacity of 3

        // Insert items
        lruCache.put(1, 102345);
        lruCache.put(2, 102342);
        lruCache.put(3, 102303);
        lruCache.printCache();

        // Access key 2 (this will make key 2 the most recently used)
        System.out.println("Get 2: " + lruCache.get(2)); // Should return 2
        lruCache.printCache();


        // Insert a new key, which will evict key 1 (the LRU)
        lruCache.put(4, 105444);
        lruCache.printCache();


        // Access key 3
        System.out.println("Get 3: " + lruCache.get(3));
        lruCache.printCache();

        
        // Insert another new key, which will evict key 2 (the LRU)
        lruCache.put(5, 103455);
        lruCache.printCache();
        }
}