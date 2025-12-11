import java.util.Arrays;

interface PriorityQueue<T> {
    void add(T value);
    boolean isEmpty();
    T remove();
    T peek();
}

public class BinaryHeap_DH4<T extends Comparable<T>> implements PriorityQueue<T> {
    
    private static final int DEFAULT_CAPACITY = 10;
    protected T[] array;
    protected int size;

    /**
     * Constructs a new BinaryHeap.
     */
    @SuppressWarnings("unchecked")
    public BinaryHeap_DH4() {
        array = (T[])new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }




    /**
     * Adds a value to the min-heap.
     */
    @Override
    public void add(T value) {
        //grow array if needed
        if (size >= array.length) {
            array = resize();
        }
        array[size] =  value;
        size++;
        bubbleUp();
    }




    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //new peek
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return array[0];
    }

    //new remove
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T result = peek();

        array[0] = array[size - 1];
        array[size - 1] = null;
        size--;

        bubbleDown();
        return result;
    }


    @SuppressWarnings("unchecked")
    public T[] returnSorted(){
        T[] sortedArray = (T[]) new Comparable[this.size];
        int originalSize = size;

        for (int i = 0; i < originalSize; i++) {
            sortedArray[i] = remove();
        }

        return sortedArray;
    }


    public String toString(){
        return Arrays.toString(Arrays.copyOf(array, size));
    }

    private T[] resize(){
        return Arrays.copyOf(array, array.length * 2);  
    }

    private void bubbleUp(){
        int index = size - 1;
       while(hasParent(index) && parent(index).compareTo(array[index]) > 0){
           swap(index, parentIndex(index));
           index = parentIndex(index);
        }
    }

    public void bubbleDown(){
       
        int index = 0;
        
        while(hasLeftChild(index)){

            int smallerChildIndex = LeftIndex(index);
            
            if(hasRightChild(index) && array[LeftIndex(index)].compareTo(leftChild(index)) < 0){
                smallerChildIndex = rightIndex(index);
            }

            if(array[index].compareTo(array[smallerChildIndex]) > 0){
                swap(index, smallerChildIndex);
            } else {
                break;
            }
            index = smallerChildIndex;
        }
    }

    private T leftChild(int i){
        return array[LeftIndex(i)];
    }
    private boolean hasParent(int i){
        return i >= 0;
    }

    private boolean hasLeftChild(int i){
        return LeftIndex(i) < size;
    }

    private boolean hasRightChild(int i){
        return rightIndex(i) < size;
    }

    private int parentIndex(int i){
        return (i - 1) / 2;
    }

    private int LeftIndex(int i){
        return 2 * i + 1;
    }

    private int rightIndex(int i){
        return 2 * i + 2;
    }

    private T parent(int i){
        return array[parentIndex(i)];
    }

    private void swap(int index1, int index2){
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    public static void main(String[] args) {
        BinaryHeap_DH4<Integer> heap = new BinaryHeap_DH4<>();

        heap.add(10);
        heap.add(12);
        heap.add(1);
        heap.add(3);
        heap.add(5);

        System.out.println("Heap after adding elements: " + heap);
    
        System.out.println("Peek: " + heap.peek());

        //remove all elements
        System.out.println("Removing elements:");
        while (!heap.isEmpty()) {
            System.out.println("Removed: " + heap.remove());
        }

        //print empty heap
        System.out.println("Heap after removing all elements: " + heap);
    
    
    
    }



}