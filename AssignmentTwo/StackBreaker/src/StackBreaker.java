import java.util.Arrays;

/**
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