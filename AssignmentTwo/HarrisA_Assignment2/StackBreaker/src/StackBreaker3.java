/**
 * Working from a tip from another student, this is an attempt to overwhelm the stack
 * by creating lots and lots of local variables. An infinite amount, actually.
 *
 * Author: Alex
 * Version: 4/23/2014.
 */
public class StackBreaker3 {

    public static void main(String[] args) {
        int i = 1;
        recursiveMethod(i);
    }

    private static void recursiveMethod(int i) {
        while (1 > 0) {
            System.out.println(i);
            i++;
            recursiveMethod(i);
        }
    }
}