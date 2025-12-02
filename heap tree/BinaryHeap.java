import java.util.Arrays;


//This is a Min-Heap
public class BinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {
 private static final int DEFAULT_CAPACITY = 10;

 protected T[] array;

 //array representation of heap 
 private int[] heap;

 //number of elements in heap
 protected int size;

 //capacity of heap max size
 private int capacity;

 /**
 * Constructs a new BinaryHeap.
 */
 @SuppressWarnings("unchecked")
public BinaryHeap () {
 // Java doesn't allow construction of arrays of placeholder data types
 array = (T[])new Comparable[DEFAULT_CAPACITY];
 size = 0;
 }

 //constructor to initilize heap with given capacity
 public BinaryHeap (int capacity){
    this.capacity = capacity;
    this.heap = new int[capacity];
    this.size = 0;
 }


 /**
 * Adds a value to the min-heap.
 */
 @Override
 public void add(T value) {
 // grow array if needed
 if (size >= array.length - 1) {
 array = this.resize();
 }

 // place element into heap at bottom
 size++;
 int index = size;
 array[index] = value;

 bubbleUp();
 }


 /**
 * Returns true if the heap has no elements; false otherwise.
 */
 @Override
 public boolean isEmpty() {
 return size == 0;
 }

 /**
 * Returns (but does not remove) the minimum element in the heap.
 */
 @Override
 public T peek() {
 if (this.isEmpty()) {
 throw new IllegalStateException();
 }

 return array[1];
 }


 /* Removes and returns the minimum element in the heap */
 @Override
 public T remove() {
 // what do want return?
 T result = peek();

 // get rid of the last leaf/decrement
 array[1] = array[size];
 array[size] = null;
 size--;

 bubbleDown();

 return result;
 }

 public T[] returnSorted()
 {
 T[] sortedArray = (T[])new Comparable[this.size];
 for(int i = 0; i < sortedArray.length; i++)
 {
 sortedArray[i] = remove();
 }
 return sortedArray;
 }

 /**
 * Returns a String representation of BinaryHeap with values stored with
 * heap structure and order properties.
 */
 public String toString() {
 return Arrays.toString(array);
 }

 /**
 * Performs the "bubble down" operation to place the element that is at the
 * root of the heap in its correct place so that the heap maintains the
 * min-heap order property.
 */
 protected void bubbleDown() {
 int index = 1;

 // bubble down
 while (hasLeftChild(index)) {
 // which of my children is smaller?
 int smallerChild = leftIndex(index);

 // bubble with the smaller child, if I have a smaller child
 if (hasRightChild(index)
 && array[leftIndex(index)].compareTo(array[rightIndex(index)]) > 0) {
 smallerChild = rightIndex(index);
 }

 if (array[index].compareTo(array[smallerChild]) > 0) {
 swap(index, smallerChild);
 } else {
 // otherwise, get outta here!
 break;
 }

 // make sure to update loop counter/index of where last el is put
 index = smallerChild;
 }
 }


 /**
 * Performs the "bubble up" operation to place a newly inserted element
 * (i.e. the element that is at the size index) in its correct place so
 * that the heap maintains the min-heap order property.
 */
 protected void bubbleUp() {
 int index = this.size;

 while (hasParent(index)
 && (parent(index).compareTo(array[index]) > 0)) {
 // parent/child are out of order; swap them
 swap(index, parentIndex(index));
 index = parentIndex(index);
 }
 }


 protected boolean hasParent(int i) {
 return i > 1;
 }


 protected int leftIndex(int i) {
 return i * 2;
 }


 protected int rightIndex(int i) {
 return i * 2 + 1;
 }


 protected boolean hasLeftChild(int i) {
 return leftIndex(i) <= size;
 }


 protected boolean hasRightChild(int i) {
 return rightIndex(i) <= size;
 }


 protected T parent(int i) {
 return array[parentIndex(i)];
 }


 protected int parentIndex(int i) {
 return i / 2;
 }


 protected T[] resize() {
 return Arrays.copyOf(array, array.length * 2);
 }


 protected void swap(int index1, int index2) {
 T tmp = array[index1];
 array[index1] = array[index2];
 array[index2] = tmp;
 }

// edits in class 
 private int parent(int i){
    return (i - 1) / 2;
 }

 private int leftChild(int i)  {
    return 2 * i + 1;
 }

 private rightChild(int i) {
    return 2 * i + 2;
 }


 private void swap(int i, int j){
    int temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
 }

 private void resize(){
    capacity *= 2;

    heap = Arrays.copyOf(heap, capacity);
 


 public void insert(int value){
    if(size >= capacity)
{
         resize();
} 
heap[size] = value;

int current = size;
size++;
while(current > 0 && heap[current] < heap[parent(current)]){
    swap(current, parent(current));
    current = parent(current);
}
 }

}

//sorting down through the heap
private void sortDown(int i){
    //find smallest among node and children
    int smallest = i;

    //get left and right child indices
    int left = leftChild(i);

    //right child index
    int right = rightChild(i);

    //compare left child and node and if left child is smaller update smallest
    if(left < size && heap[left] < heap[smallest]){
        smallest = left;
    }


    if(right < size && heap[right] < heap[smallest]){
        smallest = right;
    }
    if(smallest != i){
        swap(i, smallest);
        sortDown(smallest);
    }
}



public int extractMin(){
    if(size == 0){
        throw new IllegalStateException("Heap is empty");
    }

    int min = heap[0];
    heap[0] = heap[size - 1];
    size--;
    sortDown(0);
    return min;
}


//state size of the heap
public void printHeap(){
    System.out.println(Arrays.toString(Arrays.copyOf(heap, size)));
}

//main method
public static void main(String[] args){
    BinaryHeap bh = new BinaryHeap(10);
    bh.insert(10);
    bh.insert(5);
    bh.insert(30);
    bh.insert(3);
    bh.insert(8);

    System.out.println("Heap after inserting elements:");
    bh.printHeap();
    System.out.println("Extracted min: " + bh.extractMin());
    System.out.println("Heap after extracting min:");
    bh.printHeap();

}




}