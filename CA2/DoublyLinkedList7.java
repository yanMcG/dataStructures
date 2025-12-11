// Doubly linked list with insertion and deletion 
// Delete at position
// If you are confused by doubly linked lists draw out three boxes and using different coloured pens
// point to the one that you are referring to at any time. That way you will see what is prev, next, current and so on.
// This is most helpful when you see lines such as the following:  current.prev.next = temp;
class node {
    node prev;
    String data;
    node next;

    // node constructor 
    node(String value)
    {
        // previous pointer is pointed to NULL
        prev = null;

        // Value is assigned to the data
        data = value;

        // next pointer is pointed to NULL
        next = null;
    }
}

// This is the 7th example of Doubly linked lists that we worked on. 
// this one uses strings instead of ints just to match the example of linked lists
@SuppressWarnings("unused")
class DoublyLinkedList7 {

    // Declare an empty doubly linked list
    static node head = null;
    static node tail = null;
    static void insertAtBeginning(String data)
    {
        node temp = new node(data);
        if (head == null) {
            head = temp;
            tail = temp;
        }
        else {
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
    }

    static void insertAtEnd(String data)
    {
        node temp = new node(data);
        if (tail == null) {
            head = temp;
            tail = temp;
        }
        else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
    }

    static void insertAtPosition(String data, int position)
    {
        node temp = new node(data);
        if (position == 1) {
            insertAtBeginning(data);
        }
        else {
            node current = head;
            int currPosition = 1;
            while (current != null
                   && currPosition < position) {
                current = current.next;
                currPosition++;
            }
            if (current == null) {
                insertAtEnd(data);
            }
            else {
                temp.next = current;
                temp.prev = current.prev;
                current.prev.next = temp;
                current.prev = temp;
            }
        }
    }

    static void deleteAtBeginning()
    {
        if (head == null) {
            return;
        }

        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        node temp = head;
        head = head.next;
        head.prev = null;
        temp.next = null;
    }

    static void deleteAtEnd()
    {
        if (tail == null) {
            return;
        }

        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        node temp = tail;
        tail = tail.prev;
        tail.next = null;
        temp.prev = null;
    }

    static void deleteAtSpecificPosition(int pos)
    {
        if (head == null) {
            return;           }

        if (pos == 1) {
            deleteAtBeginning();
            return;           }

        node current = head; int count = 1;

        while (current != null && count != pos) {
            current = current.next;
            count++;
        }

        if (current == null) {
            System.out.println("Position wrong");
            return;
        }

        if (current == tail) {
            deleteAtEnd();
            return;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        current.prev = null;
        current.next = null;
    }

    static void display(node head)
    {
        node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " --> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    // main
    public static void main(String[] args)
    {
        insertAtEnd("ATU");
        insertAtEnd("DKIT");
        insertAtEnd("TCD");
        insertAtEnd("UL");
        insertAtEnd("DCU");

        System.out.print("After insertion at tail: ");
        display(head);

        System.out.print("After insertion at head: ");
        insertAtBeginning("MTU");
        display(head);

        insertAtPosition("UCC", 2);
        System.out.print("After insertion at 2nd position: ");
        display(head);

        deleteAtBeginning();
        System.out.print("After deletion at the beginning: ");
        display(head);

        deleteAtEnd();
        System.out.print("After deletion at the end: ");
        display(head);

        deleteAtSpecificPosition(2);
        System.out.print("After deletion at 2nd position: ");
        display(head);
    }
}