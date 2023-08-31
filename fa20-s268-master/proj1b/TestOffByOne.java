import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testOne() {
        assertTrue(palindrome.isPalindrome("abab", offByOne));
        assertFalse(palindrome.isPalindrome("aaaaaaa", offByOne));
    }
    @Test
    public void testTwo() {
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertTrue(palindrome.isPalindrome(" ", offByOne));
        assertFalse(palindrome.isPalindrome(null, offByOne));
    }

    @Test
    public void testThree() {
        assertFalse(palindrome.isPalindrome("Aabb", offByOne));
        assertFalse(palindrome.isPalindrome("aaB", offByOne));
        assertFalse(palindrome.isPalindrome("aBcB", offByOne));
        assertTrue(palindrome.isPalindrome("%&", offByOne));
    }


}
