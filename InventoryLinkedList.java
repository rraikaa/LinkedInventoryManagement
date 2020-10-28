package LinkedInventoryManagement.Common;

/**
 * InventoryLinkedList
 */
public class InventoryLinkedList<E extends Comparable<E>> {

    private Node<E> head;
    private Node<E> tail;

    private int numElementsInList = 0;

    // Creates empty list
    public InventoryLinkedList() {
    }

    /*
    Method returns the first element in the list
    */
    public E GetFirst() 
    {
        // If the list is empty
        if (numElementsInList == 0) {
            // There is no first element
            return null;
        } else // Non-empty list
        {
            return head.element; // Return first element in list
        }
    }

    
    /*
     * Method returns the last element in the list
     */
    public E GetLast() 
    {
        // If the list it empty
        if (numElementsInList == 0) 
        {
            // There is no last element
            return null;
        } 
        else // Non-empty list
        {
            return tail.element; // Return last element in list
        }
    }

    /*
     * Method inserts element at index specified in parameter
     */
    public void Insert(int index, E element) 
    {
        // Inserting at the start of the list
        if (index == 0) 
        {
            InsertFirst(element);
        }
        // Inserting at the end of the list
        else if (index >= numElementsInList)
        {
            InsertLast(element);
        }
        // Inserting in the middle of the list
        else {

            // Create current node and initialize at start of list
            Node<E> current = head;

            // Loop through front of list up until before index
            for (int i = 0; i < index - 1; i++) {
                // Get current to the node at index
                current = current.next;
            }

            // WHAT DOES THIS DO

            if (current.next == null) {

            }


            // Store node AFTER index to temp variable
            Node<E> temp = current.next;

            // Create new node after current node and before temp node
            current.next = new Node<>(element);

            // current.next is the newly inserted element
            (current.next).previous = current;

            // Set node after new node to temp node
            (current.next).next = temp;

            // Set previous node link to the newly inserted node
            temp.previous = current.next;

            // Increment size of list
            numElementsInList++;
        }

    }

    /*
    This method inserts element to the first index in the list
    */
    public void InsertFirst(E element) 
    {
        // Create new node
        Node<E> newFirstNode = new Node<>(element);

        // Set reference to node after new node as previous head of list
        newFirstNode.next = head;

        // First node has no previous
        newFirstNode.previous = null;

        // Set new node to head of list
        head = newFirstNode;

        // Increment number of items in list
        numElementsInList++;

        // If the new element is the only element in the list
        if (tail == null) 
        {
            tail = head;
        }
    }


    /*
     * This method inserts element to the last index in the list
     */
    public void InsertLast(E element) {

        // Create new node
        Node<E> newLastNode = new Node<>(element);

        // If new element is only element in the list
        if (tail == null)
        {
            head = tail = newLastNode;
        } 
        else 
        {
            // Link last node to new last node
            tail.next = newLastNode;

            // Link new last node with element before it
            newLastNode.previous = tail;

            // Set new node as tail of list
            tail = newLastNode;
        }

        // Increment number of elements in list
        numElementsInList++;
    }

    /*
     * This method removes an element at a specified index in the list
     */
    public E Remove(int index) 
    {
        // Check index is within bounds of list
        if (index < 0 || index >= numElementsInList) 
        {
            return null;
        }

        // If removing first element
        else if (index == 0) 
        {
            return RemoveFirst();
        }
        // If removing last index
        else if (index == numElementsInList - 1) 
        {
            return RemoveLast();
        } 
        else 
        {

            // Decleare Node before element to remove
            // Will loop through to move this -> head is just initialization point
            Node<E> nodeBeforeRemoveNode = head;


            // Loop through list until before index
            for (int i = 0; i < index - 1; i++) 
            {
                // Increment node to next
                nodeBeforeRemoveNode = nodeBeforeRemoveNode.next;

            }

            // Set node to remove
            Node<E> nodeToRemove = nodeBeforeRemoveNode.next;
            
            // Link the nodes before and after the node to remove
            nodeBeforeRemoveNode.next = nodeToRemove.next;

            // Link element after removed node and element before it
            (nodeToRemove.next).previous = nodeBeforeRemoveNode;

            // Decrement number of elememts in list
            numElementsInList--;

            // Return the element of removed node
            return nodeToRemove.element;
        }
    }

    /*
     * Remove the head node and return the element in the removed node
     */
    public E RemoveFirst()
    {
        // If the list is empty
        if (numElementsInList == 0) 
        {
            // There is no first element
            return null;
        } 
        else 
        {
            // Create temporary objects
            E temp = head.element;

           

            // There is no other elements in list other than the object to remove
            if (numElementsInList == 1) 
            {
                head = null;
            }

            // Set new head as second element in list
            else 
            {
                head = head.next;

                // Set previous element to null since it is first in list
                head.previous = null;
            }

            // Decrement number of items in list
            numElementsInList--;

            // If the list is empty
            if (head == null) 
            {
                // Set tail as null
                tail = null;
            }

            // Return removed objects
            return temp;
        }
    }

    /*
     * Remove the tail node and return the element in the removed node. Essentially
     * just removes links to last element.
     */
    public E RemoveLast() // Remove the last element
    {

        // Define temporary objects
        E temp;

        // If the list is empty
        if (numElementsInList == 0) 
        {
            return null;

        } else if (numElementsInList == 1) // Only element in list is object to remive
        {
            // Set temp variable
            temp = head.element;

            // List is now empty
            head = tail = null;

            numElementsInList = 0;

            
        } else // If there is greater than 1 element in the list
        {
            // Declare current node and start at beginning of list
            Node<E> current = head;

            // Loop until current is at last element
            /*
             * Ex: numElementsInList = 10 -> Index of tail is index 9 -> Loop until current
             * = index 7 -> Set current to index 8 -> Set tail to current (index 8)
             */
            for (int i = 0; i < numElementsInList - 2; i++) 
            {
                current = current.next;
            }

            // Store value of last element
            temp = tail.element;

            // Set new last element
            tail = current;

            // No next element
            tail.next = null;

            // Decrement number of items in list
            numElementsInList--;

        }
        
        // Return object that was removed
        return temp;

    }


    /*
    Overridden toString method for custom linked list
    */
    @Override
    public String toString() // Return formatted elements information
    {

        // Stringbuilder object to hold list elements
        StringBuilder listString = new StringBuilder();

        // Openening bracket
        listString.append("[");

        // Create node to loop through list with and initialize at start of list
        Node<E> current = head;

        // Loop through all elements in list
        for (int i = 0; i < numElementsInList; i++) {
            // TODO: Need to make toString method on the elements themself
            listString.append(current.element);

            // Increment current to next item in list
            current = current.next;

            // If there is more items in the list (not last item)
            if (current != null) 
            {
                listString.append(", ");
            } 
            else // For last item in the list
            {
                listString.append("]");
            }
        }

        // Return list as string
        return listString.toString();

    }

    /*
    This method verifies that the list contains a certain element
    */
    public boolean Contains(E keyElement) 
    {
        // Declare flag
        boolean itemInList = false;

        // Create node to loop through list with and initialize at start of list
        Node<E> current = head;

        // Loop through all elements in list
        for (int i = 0; i < numElementsInList; i++) 
        {
            // If the current element equals element to search for
            if (current.element.equals(keyElement)) {
                // Set flag
                itemInList = true;

                // Break loop
                break;
            }

            // Increment current to next item in list
            current = current.next;

        }

        // Return if the item was found in the list
        return itemInList;
    }


    
    /*
    This method removes the element at the specifid index, and then adds
    the element passed in as a parameter to the same index. It returns the
    element that was removed from the specified index. 
    */
    public E SetElement(int index, E element) // Set the element at the specified index
    {


        // STEP 1: Call method to remove element from index
        E replacedElement = Remove(index);

        // STEP 2: Call method to add new element to list at index
        Insert(index, element);

        // Return the element that was removed from index position
        return replacedElement;

    }

    /*
    This method returns an element at a specified index in the list
    */
    public E GetElement(int index)
    {

        // Create node to loop through list with and initialize at start of list
        Node<E> current = head;

        // Loop through elements in list until before current
        
        for (int i = 0; i < index; i++) {
            // Increment current to next item in list
            current = current.next;
        }

        // NOTE: After loop current is the element at the specified index

        // Return element at index
        return current.element;

    }

    /*
    This method takes an element as parameter and searches through
    the list. If the element is found in the list, it returns the index
    of the element in the list. Otherwise, it returns -1. 
    */
    public int GetIndexOfElement(E element) 
    {

        // Create node to loop through list with and initialize at start of list
        Node<E> current = head;

        // Intiialize index of element
        int indexOfElement = -1;

        // Loop through list
        for (int i = 0; i < numElementsInList; i++) {

            // If the element is found
            if (current.element.equals(element)) {
                indexOfElement = i;
                break;
            }
            // Increment current to next item in list
            current = current.next;
        }

        // Return index of element or -1 if not in list
        return indexOfElement;

    }

    // Return the number of elements in the list
    public Integer GetLength() 
    {
        return numElementsInList;
    }

    // Increment static variable for number of objects in list
    public void incrementSizeOfList() 
    {
        numElementsInList++;
    }

    // Decrement static variable for number of objects in list
    public void decrementSizeOfList() 
    {
        numElementsInList--;
    }

    // Node class used for tracking relative positions of elements in linked list
    private static class Node<E> 
    {
        E element;
        Node<E> next;
        Node<E> previous;

        // Node constructor
        public Node(E element) 
        {
            this.element = element;
        }
    }

}
