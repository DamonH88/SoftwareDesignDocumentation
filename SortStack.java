import java.util.Scanner;
import java.util.Stack;

/**
 * <p><i>
 * Created on February 12, 2024<br>
 * Software Design and Documentation<br>
 * CSC6301 - Week 5<br>
 * Module 5: Project 5 - Planning and Executing a Maintenance Task -<br>
 * Main Class "StackProject."<br>
 * </i></p>
 *
 * <p style="color:blue;">This is the Main class to sort a Stack using an auxiliary stack.<br>
 * It requires the user to input the length of the stack and<br>
 * input the values for each element.  The program then <br>
 * sorts the stack from smallest to largest.<br>
 * </p>
 * <br>
 *
 * @author Damon Huber
 */

public class SortStack {

    /**
     * This is the Constructor for SortStack.
     */
    public SortStack(){
    }
    /**
     * This function returns the sorted stack.
     * @param input This takes the input of the main method and enters it into the stack.
     * @return This returns the values in the stack.
     */
    protected static Stack<Integer> sortstack(Stack<Integer> input){
        Stack<Integer> tmpStack = new Stack<Integer>();
        while(!input.isEmpty()) {

            // pop out the first element
            int tmp = input.pop();

            // While the temporary stack is not empty and top of stack is lesser than temp.
            while(!tmpStack.isEmpty() && tmpStack.peek() < tmp) {

                //pop from temporary stack and push it to the input stack.
                input.push(tmpStack.pop());
            }
            // push temp in temporary of stack.
            tmpStack.push(tmp);
        }
        return tmpStack;
    }
    

    /** Declares the Scanner name "in".
     * @param promptMsg This parameter requests the user enter an integer
     * @param errorMsg This parameter gives an error message if entry is NOT an integer.
     * @return Returns the integer when it has been verified as an integer.
     */
    public static int readInteger(String promptMsg, String errorMsg){
        Scanner in = new Scanner(System.in);
        int number = 0;
        String strInput;
        boolean valid = false;
        // Keep looking until valid input is given.
        while(valid == false){
            // Prompt the user.
            System.out.println(promptMsg);
            // Grab input from keyboard.
            strInput = in.nextLine();
            // Try to convert String to int.
            try{
                number = Integer.parseInt(strInput);
                valid = true;
                
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
        return number;
    }

    /** The main method takes input from the user, adds each input into a<br>
     * stack and then uses sortstack to organize the integers from<br>
     * smallest to largest.<br>
     * @param args The argument in the main method is user input inserted into a Stack.
     */
    public static void main(String args[]){
        Stack<Integer> input = new Stack<Integer>();
        Scanner sc = new Scanner(System.in);
        int num = -1;

        while (num < 0){
            System.out.println("---------------------------------------");
            System.out.println("STACK SORT IMPLEMENTED ON A STACK");
            System.out.println("---------------------------------------");
            System.out.println("Enter the total number of elements: ");
            String validInt = sc.nextLine();

            try{
                num = Integer.valueOf(validInt);
                System.out.println("You specified " + num + " elements.");
            } catch (Exception ex) {
                num = -1;
                System.out.println("Incorrect entry.  Please try again. ");
            }
        }
        while(num > 0) {
            // Test case 1: readInteger.
            int intInput;
            String promptMsg1 = "Please enter the integer for element #" + num;
            String errorMsg1 = "Invalid entry.";
            // Call to readInteger.
            intInput = readInteger(promptMsg1, errorMsg1);

            input.add(intInput);
            System.out.println("---------------------------------------");
            System.out.print("Stack: " + input + "\n");
            System.out.println("---------------------------------------");
            num--; //decrement until the index becomes 0.
        }
        sc.close();
        Stack<Integer> tmpStack = sortstack(input);
        System.out.println("Sorted numbers are: ");
        System.out.print("[ ");
        while (!tmpStack.empty()){
            System.out.print(tmpStack.pop() + " ");
        }
        System.out.println("]");
    }
}
