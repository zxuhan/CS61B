package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        List<Oomage>[] bucket = new List[M];
        int N = oomages.size();

        for (Oomage o : oomages) {
            int bucketIndex = (o.hashCode() & 0x7FFFFFFF) % M;
            if (bucket[bucketIndex] == null) {
                bucket[bucketIndex] = new ArrayList<Oomage>();
                bucket[bucketIndex].add(o);
            } else if (!bucket[bucketIndex].contains(o)) {
                bucket[bucketIndex].add(o);
            }
        }

        for (List<Oomage> l : bucket) {
            if (l == null) {
                return false;
            }
            int size = l.size();
            if (size < (N / 50) || size > (N / 2.5)) {
                return false;
            }
        }
        return true;
    }
}
