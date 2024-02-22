import java.util.Scanner;

/**
 * <p><i>
 * Created on February 7, 2024<br>
 * Software Design and Documentation<br>
 * CSC6301 - Week 4<br>
 * Module 4: Project 4 -  Linked List and Collections Framework -<br>
 * Main Class "mergeSortLinkedList."<br>
 * </i></p>
 *
 * <p style="color:blue;">This is the Main class to sort a Linked List using Merge Sort.<br>
 * It requires the user to input the length of the linked list and<br>
 * input the values for each element.  The program then uses Merge Sort<br>
 * to sort the linked list from smallest to largest.<br>
 * </p>
 * <br>
 *
 * @author Damon Huber
 */

public class mergeSortLinkedList {
    /**
     * This is the Constructor for mergeSortLinkedList.
     */
    public mergeSortLinkedList() {
    }

    /** This method defines the head of each half<br>
     * of the linked list.
     */
    protected node head = null;

    /**
     * A subclass that separates each half of the linked list by
     * defining node a, b;
    */
    protected static class node {
        int val;
        node next;

        /** This node inserts the integer into an element.
         *
         * @param val This is the value of the integer added to an element.
         */
        public node(int val) { this.val = val; }
    }

    /** This method splits the linked list into two lists.<br>
     * It also contains the mechanism for sorting elements if<br>
     * the values are out of order.<br>
     *
     * @param a This parameter defines the left half of the linked list.
     * @param b This parameter defines the right half of the linked list.
     * @return Returns the result of Merge Sort on the linked list.
     */
    protected node sortedMerge(node a, node b)
    {
        node result = null;

        // Base cases
        if (a == null)
            return b;
        if (b == null)
            return a;

        // Pick either a or b, and recur
        if (a.val < b.val) {
            result = a;
            result.next = sortedMerge(a.next, b);
        }
        else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }

        return result;
    }

    /** This method is a series of conditionals used to sort through the
     * linked list using Merge Sort.
     *
     * @param h     This parameter defines the head of the linked list
     * @return      Returns the sorted list
     */
    protected node mergeSort(node h)
    {
        // Base case : if head is null
        if (h == null || h.next == null) {
            return h;
        }

        // get the middle of the list
        node middle = getMiddle(h);
        node nextofmiddle = middle.next;

        // set the next of middle node to null
        middle.next = null;

        // Apply mergeSort on left list
        node left = mergeSort(h);

        // Apply mergeSort on right list
        node right = mergeSort(nextofmiddle);

        // Merge the left and right lists
        node sortedlist = sortedMerge(left, right);

        return sortedlist;
    }

    /** This is the utility function to get the middle of the linked list,<br>
     *  the head and then compares the slow element(one step from the head)<br>
     * with the fast element (two steps from the head).<br>
     *
     * @param head  The head of the method defines the middle of the linked list.
     * @return      Returns the slow element in the linked list.
     */
    protected static node getMiddle(node head)
    {
        if (head == null)
            return head;

        node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /** This method allocates the node,
     * links the old list to the new node
     * and moves the head to point to the new node.
     *
     * @param new_data  This parameter rejoins the split linked list.
     */
    protected void push(int new_data)
    {
        node new_node = new node(new_data); // allocate node
        new_node.next = head; // link the old list of the new node
        head = new_node; // move the head to point to the new node
    }

    /** This method prints the nodes spaced out so that each node is
     * properly spaced.
     *
     * @param headref   This parameter ensures each value in the linked list includes spacing.
     */
    protected void printList(node headref)
    {
        while (headref != null) {
            System.out.print(headref.val + " ");
            headref = headref.next;
        }
    }

    /**
     * The main method takes input from the user, adds each input into a<br>
     * linked list and then uses MergeSort to organize the integers from<br>
     * smallest to largest.<br>
     *
     * @param args The argument in the main method is user input inserted into a linked list.
     */
    public static void main(String[] args)
    {

        mergeSortLinkedList list = new mergeSortLinkedList();

        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------");
        System.out.println("Merge Sort implemented on a Linked List");
        System.out.println("---------------------------------------");
        System.out.println("Enter the total number of elements: ");
        int num = sc.nextInt(); // user will enter total elements.
        System.out.println("Please enter the first integer...");

        while(num > 0) {
            System.out.println("Please enter the integer for element " + num);
            list.push(sc.nextInt());
            num--; //decrement until the index becomes 0.
        }
        sc.close();
        System.out.println("---------------------------------------");
        System.out.print("\nOriginal List: \n");
        list.printList(list.head);

        // Apply merge Sort
        list.head = list.mergeSort(list.head);

        System.out.print("\nSorted List: \n");
        list.printList(list.head);
    }
}