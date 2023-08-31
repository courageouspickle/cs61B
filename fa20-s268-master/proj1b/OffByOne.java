public class OffByOne implements CharacterComparator {
    public boolean equalChars(char x, char y) {
        int xint = x;
        int yint = y;

        if (xint == yint + 1 || xint + 1 == yint) {
            return true;
        }
        return false;
    }
}
