/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // Implement LSD Sort
        
        // get the max length
        int maxLength = 0;
        for (String ascii : asciis) {
            if (ascii.length() > maxLength) {
                maxLength = ascii.length();
            }
        }

        // create a new arr
        String[] newAsciis = new String[asciis.length];
        for (int i = 0; i < asciis.length; i += 1) {
            StringBuilder sb = new StringBuilder(asciis[i]);
            if (asciis[i].length() < maxLength) {
                int diff = maxLength - asciis[i].length();
                for (int j = 0; j < diff; j += 1) {
                    sb.append((char) 0);
                }
            }
            newAsciis[i] = sb.toString();
        }

        // sort new arr
        for (int index = maxLength - 1; index >= 0; index -= 1) {
            newAsciis = sortHelperLSD(newAsciis, index);
        }

        return newAsciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        String[] sorted = new String[asciis.length];
        int[] count = new int[256];
        for (String ascii : asciis) {
            int digit = (int) ascii.charAt(index);
            count[digit] += 1;
        }

        int[] start = new int[256];
        int pos = 0;
        for (int i = 0; i < start.length; i += 1) {
            start[i] = pos;
            pos += count[i];
        }
        
        for (int i = 0; i < asciis.length; i += 1) {
            String item = asciis[i];
            int digit = (int) item.charAt(index);
            int place = start[digit];
            sorted[place] = item;
            start[digit] += 1;
        }
        
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
