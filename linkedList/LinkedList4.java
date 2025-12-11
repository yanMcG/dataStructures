// Import the LinkedList class
import java.util.LinkedList;

// This is a brief recap of linked lists. 
// There were 4 examples duruing class each building on each other 
public class LinkedList4 {
  public static void main(String[] args) {
    var uniLL = new LinkedList<String>();
    
    // example of add
    uniLL.add("ATU");
    uniLL.add("MTU");
    uniLL.add("DKIT");
    uniLL.add("UCD");
    System.out.println(uniLL);

    // examples of addFirst() and addLast()
    uniLL.addFirst("TCD");
    System.out.println(uniLL);
    uniLL.addLast("UCC");
    System.out.println(uniLL);

    // examples of removeFirst() and removeLast()
    uniLL.removeFirst(); //remove TCD
    System.out.println(uniLL);
    uniLL.removeLast(); //remove UCC
    System.out.println(uniLL);

    // getFirst() and getLast()
    // Note that it does not 'remove' the item
    String firstItem = uniLL.getFirst();
    System.out.println("first item: "+firstItem);
    System.out.println(uniLL);

    String lastItem = uniLL.getLast();
    System.out.println("last item: "+lastItem);
    System.out.println(uniLL);
  }
}