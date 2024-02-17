import static org.junit.Assert.fail;

/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        // make counting sort work with arrays containing negative numbers.

        // find min and max
        int min = arr[0];
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }

        // count 0 and positive numbers
        int[] countP = new int[max + 1];
        // count negative nunbers
        int[] countN = new int[-min + 1];

        for (int i : arr) {
            if (i < 0) {
                countN[-i] += 1;
            } else {
                countP[i] += 1;
            }
        }

        // approach1: copy ints
        int[] sorted1 = new int[arr.length];
        int k = 0;
        if (min < 0) {
            for (int i = countN.length - 1; i >= 1; i -= 1) {
                for (int j = 0; j < countN[i]; j += 1, k += 1) {
                    sorted1[k] = -i;
                }
            }
        }
        
        for (int i = 0; i < countP.length; i += 1) {
            for (int j = 0; j < countP[i]; j += 1, k += 1) {
                sorted1[k] = i;
            }
        }
        
        // approach2: create general start arr
        int[] sorted2 = new int[arr.length];
        int[] startN = new int[-min + 1];
        int[] startP = new int[max + 1];
        
        int pos = 0;
        if (min < 0) {
            for (int i = countN.length - 1; i >= 1; i -= 1) {
                startN[i] = pos;
                pos += countN[i];
            }
        }
        for (int i = 0; i < countP.length; i += 1) {
            startP[i] = pos;
            pos += countP[i];
        }
        
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            if (item < 0) {
                int place = startN[-item];
                sorted2[place] = item;
                startN[-item] += 1;
            } else {
                int place = startP[item];
                sorted2[place] = item;
                startP[item] += 1;
            }
        }

        return sorted1;
    }
}
