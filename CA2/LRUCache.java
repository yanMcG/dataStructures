import java.util.HashMap;
class LRUCache {
 // Node class for doubly linked list
 class Node {
 int key, value;
 Node prev, next;

 Node(int key, int value) {
 this.key = key;
 this.value = value;
 }
 }
 private HashMap<Integer, Node> cache;
 private int capacity, size;
 private Node head, tail;
 // Constructor
 public LRUCache(int capacity) {
 <<fill in this method>>
 }
 // Move a node to the front (most recently used)
 private void moveToFront(Node node) {
 <<fill in this method>>
 }
 // Add a node to the front of the list
 private void addToFront(Node node) {
 <<fill in this method>>
 }
 // Remove a node from the list
 private void remove(Node node) {
 <<fill in this method>>
 }
 // Get the value of a key if it exists in the cache
 public int get(int key) {
 <<fill in this method>>
 return -1; // Key not found
 }
 // Insert a key-value pair into the cache
 public void put(int key, int value) {
 <<fill in this method>>
 }
 // Print the cache for debugging
 public void printCache() {
 <<fill in this method>>
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