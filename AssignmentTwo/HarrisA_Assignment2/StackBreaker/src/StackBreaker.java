import java.util.Arrays;

/**
 * This is an attempt to break the stack by creating an array of doubles (primitive) and then assigning
 * am enormous value to a local variable in a method. However, this breaks the heap rather than the
 * stack. Why? Aren't either [a] arrays or primitives (that aren't an element of an object) stored on the stack,
 * or [b] temp/local variables stored on the stack?
 *
 * Author: Alex
 * Version: 4/20/2014.
 */
public class StackBreaker {

    public static void main(String[] args) {

        double[] d = new double[999999999];
        double dbl = Double.MAX_VALUE;

        fillArray(d, dbl);
    }

    public static void fillArray(double[] d, double dbl) {
        Arrays.fill(d, dbl);
    }


}