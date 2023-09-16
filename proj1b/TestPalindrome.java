import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offbyone = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("aaaab"));
        assertFalse(palindrome.isPalindrome("rancor"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
    }

    @Test
    public void testIsPalindromeOfByOne() {
        assertTrue(palindrome.isPalindrome("flake", offbyone));
        assertTrue(palindrome.isPalindrome("abwab", offbyone));
        assertTrue(palindrome.isPalindrome("climb", offbyone));
        assertFalse(palindrome.isPalindrome("abase", offbyone));
        assertFalse(palindrome.isPalindrome("pneumocele", offbyone));
        assertFalse(palindrome.isPalindrome("monocarp", offbyone));
    }
}
