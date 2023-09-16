public class Palindrome {

    /** put characters of a string into a Deque */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));

        }
        return deque;
    }
    
    /** check if a String is a palindrome using double-side pointers*/
    public boolean isPalindrome(String word) {
        Deque<Character> deque = this.wordToDeque(word);
        for (int i = 0, j = deque.size() - 1; i < j; i++, j--) {
            if (deque.get(i) != deque.get(j)) {
                return false;
            }
        }
        return true;
    }
    
    /** check if a String is a Palindrome using recursion */
    /* private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.removeFirst() != deque.removeLast() && deque.size() != 0) {
            return false;
        } else if (deque.size() == 0) {
            return true;
        }
        return isPalindromeHelper(deque);
    } */

    /**  */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = this.wordToDeque(word);
        for (int i = 0, j = deque.size() - 1; i < j; i++, j--) {
            if (!cc.equalChars(deque.get(i), deque.get(j))) {
                return false;
            }
        }
        return true;
    }
}

