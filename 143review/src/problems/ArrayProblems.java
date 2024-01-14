package problems;

/**
 * See the spec on the website for example behavior.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - Do not add any additional imports
 * - Do not create new `int[]` objects for `toString` or `rotateRight`
 */
public class ArrayProblems {

    /**
     * Returns a `String` representation of the input array.
     * Always starts with '[' and ends with ']'; elements are separated by ',' and a space.
     */
    public static String toString(int[] array) {
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        String str = "[";
        for (int i = 0; i < array.length; i++) {
            str = str + array[i];
            if (array.length - 1 > i) {
                str += ", ";
            }
        }
        str += "]";

        return str;
    }

    /**
     * Returns a new array containing the input array's elements in reversed order.
     * Does not modify the input array.
     */
    public static int[] reverse(int[] array) {
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - i - 1];
        }

        return reversed;
    }

    /**
     * Rotates the values in the array to the right.
     */
    public static void rotateRight(int[] array) {
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        if (0 == array.length) {
            return;
        }

        int temp = array[array.length - 1];
        for (int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = temp;
    }
}
