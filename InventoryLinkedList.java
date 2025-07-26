package LinkedInventoryManagement.Common;

/**
 * InventoryLinkedList
 */
public class InventoryLinkedList<E extends Comparable<E>> {
    
    //Implement a generic linkedlist to support different data types.
    //Use this class instead of using ArrayList.
    E[] elements;
    private Node<E> head, tail;
    private int size = 0; // List size

    public InventoryLinkedList(E[] elements) //Constructor
    {
        if (elements.length == 0) //empty list
        {
            this.head = null; 
            this.tail = null;
            return;
        }
        this.head = new Node<>(elements[0],null,null);
        Node<E> prev = head;
        size++;
        if (elements.length == 1) //one element
        {
            this.tail = head;
        }
        else { //more than 1 element
            for (int i = 1; i < elements.length; i++) {
                Node<E> temp = new Node<>(elements[i],prev,null);
                prev.next = temp;
                prev = temp;

                size++;
            }
            this.tail = prev;
        }
    }

    public E GetFirst() //Get the first element in the list
    {
        if (size == 0)
        {
            return null;
        } else {
            return head.element;
        }
    }
    
    public E GetLast() //Get the last element in the list
    {
        if (size == 0)
        {
            return null;
        } else {
            return tail.element;
        }
    }
    
    public void Add (E element) //Insert at end
    {
        Node<E> newNode = new Node<>(element,tail,null); 

        if (tail == null) { // If list is empty
            head = tail = newNode; 
        }
        else {
            tail.next = newNode; //Adjust tail pointers
            tail = tail.next; 
        }
                
        size++; // Increase size
    }
    
    public void Insert(int index, E element) //Inserts element e at the specified index
    {
        if (index > size || index < 0) //Index inputted out of range
        {
            System.out.println("[Debug message] Value beyond range of list");
            return;
        }
        
        if (index == 0) //Insert at beginning
        {
            Node<E> newNode = new Node<>(element,null,head);
            newNode.next = head;
            head = newNode; // head points to the new node
            size++; // Increase list size
            
            if (tail == null) // If list is empty
            {
                tail = head;
            }
        }
        else if (index == size) // Insert at end
        {
            Add(element);
        }
        else //Insert in middle
        {
            Node<E> previous = head;
            
            for (int i = 1; i < index; i++)
            {
                previous = previous.next;
            }

            Node<E> next = previous.next;
            Node<E> current = new Node<E>(element,previous,previous.next);
            previous.next = current;
            if (next != null) {
                next.prev = current;
            }

            size++; // Increase size
        }
    }
    
    public E Remove(int index) //Remove the element at the specified index
    {
        if (index >= size || index < 0) //Index inputted out of range
        {
            System.out.println("[Debug message] Value beyond range of list");
            return null;
        }
        else if (size == 0) //If list is empty
        {
            System.out.println("Nothing to remove.");
            return null;
        }
        else if (size == 1) { // Only one element in the list
            Node<E> temp = head;
            head = tail = null; // list becomes empty
            size = 0;
            return temp.element;
        }

        if (index == 0) //Remove head
        {
            Node<E> temp = head;
            head = head.next;
            size--;
            return temp.element;
        }
        else if (index == size - 1) //Remove tail
        {
            Node<E> current = head;
            
            for (int i = 0; i < size - 2; i++)
            {
                current = current.next;
            }

            Node<E> temp = tail;
            tail = current;
            tail.next = null;

            size--;
            return temp.element;
        }
        else //Remove middle
        {
            Node<E> previous = head;
            
            for (int i = 1; i < index; i++)
            {
                previous = previous.next;
            }
            
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element; 
        }
    }
    
    public String toString() //Return formatted elements information
    {
        Node<E> temp = head;
        String elementInfo = temp.element.toString();

        for (int i = 1; i < size; i++)
        {
            temp = temp.next;
            elementInfo = elementInfo + "\n" + temp.element.toString();
        }

        return elementInfo;
    }
    
    public boolean Contains(E element) //Check if list contains the element
    {
        Node<E> temp = head;
        boolean contains = false;

        for (int i = 0; i < size; i++)
        {
            if (temp.element == element)
            {
                contains = true;
            }
            temp = temp.next;
        }

        return contains;
    }

    public E getNext(E element)
    {
        Node<E> temp = head;

        for (int i = 1; i < size; i++)
        {
            if (temp.element == element)
            {
                return temp.next.element;
            }
            temp = temp.next;
        }

        return null;
    }

    public E getPrev(E element)
    {
        Node<E> temp = tail;

        for (int i = 1; i < size; i++)
        {
            if (temp.element == element)
            {
                return temp.prev.element;
            }
            temp = temp.prev;
        }

        return null;
    }
    
    public E SetElement(int index, E element) //Set the element at the specified index
    {
        if (index >= size || index < 0) //Index inputted out of range
        {
            System.out.println("[Debug message] Value beyond range of list");
            return null;
        }
        else if (size == 0) //If list is empty
        {
            System.out.println("Nothing to edit.");
            return null;
        }

        Node<E> current = null;

        if (index == 0)
        {
            current = head;
            current.element = element;
        }
        else
        {
            Node<E> previous = head;
                
            for (int i = 1; i < index; i++)
            {
                previous = previous.next;
            }
            
            current = previous.next;
            current.element = element;
        }

        return current.element;
    }
    
    public E GetElement(int index) //Get the element at the specified index
    {
        if (index >= size || index < 0) //Index inputted out of range
        {
            System.out.println("[Debug message] Value beyond range of list");
            return null;
        }
        else if (size == 0) //If list is empty
        {
            System.out.println("Nothing to edit.");
            return null;
        }

        Node<E> current;
        
        if (index == 0)
        {
            current = head;
        }
        else
        {
            Node<E> previous = head;
                
            for (int i = 1; i < index; i++)
            {
                previous = previous.next;
            }
            
            current = previous.next;
        }

        return current.element;
    }
    
    public Integer GetLength() //Returns the number of elements in the list
    {
        return size;
    }
}

//Create another class that represents the node of a linkedlist.
class Node<E> {
    E element;
    Node<E> prev;
    Node<E> next;

    public Node(E element, Node<E> prev, Node<E> next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }
}
