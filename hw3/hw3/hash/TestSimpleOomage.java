package hw3.hash;

import org.junit.Test;


import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        SimpleOomage so1 = new SimpleOomage(5, 10, 20);
        SimpleOomage so2 = new SimpleOomage(5, 20, 10);
        SimpleOomage so3 = new SimpleOomage(20, 10, 5);
        SimpleOomage so4 = new SimpleOomage(20, 5, 10);
        SimpleOomage so5 = new SimpleOomage(10, 5, 20);
        SimpleOomage so6 = new SimpleOomage(10, 20, 5);
        SimpleOomage so7 = new SimpleOomage(5, 10, 20);
        assertFalse(so1.hashCode() == so2.hashCode());
        assertFalse(so1.hashCode() == so3.hashCode());
        assertFalse(so1.hashCode() == so4.hashCode());
        assertFalse(so1.hashCode() == so5.hashCode());
        assertFalse(so1.hashCode() == so6.hashCode());
        assertTrue(so1.hashCode() == so7.hashCode());
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /**
     * Calls tests for SimpleOomage.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
