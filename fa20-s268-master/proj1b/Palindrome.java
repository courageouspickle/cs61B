public class Palindrome {

    /**
     * Turn the word into an arraydeque.
     * @param word word to be turned
     * @return the deque
     */
    public Deque<Character> wordToDeque(String word) {
        Deque deck = new ArrayDeque();
        for (int i = 0; i < word.length(); i++) {
            deck.addLast(word.charAt(i));
        }
        return deck;
    }

    /**
     * Return whether the given word is a palindrome.
     * @param word to be tested
     * @return whether its a palindrome
     */
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        } else if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        Deque wordDeque = wordToDeque(word);
        for (int i = 0; i < word.length() / 2; i++) {
            if (wordDeque.get(i) != wordDeque.get(word.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return whether the given word is a palindrome based on cc.
     * @param word to be tested
     * @param cc criteria for chars being equal
     * @return whether its a palindrome
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        } else if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        Deque wordDeque = wordToDeque(word);
        for (int i = 0; i < word.length() / 2; i++) {
            if (!(cc.equalChars((Character) wordDeque.get(i),
                    (Character) wordDeque.get(word.length()
                            - 1 - i)))) {
                return false;
            }
        }
        return true;
    }

}
