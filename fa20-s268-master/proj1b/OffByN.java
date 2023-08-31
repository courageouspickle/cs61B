
public class OffByN implements CharacterComparator {
    /**
     * N which they are off by.
     */
    private int number;

    /**
     * Construct an OffByN Compaarator.
     * @param N the num they should be off by
     */
    public OffByN(int N) {
        this.number = N;
    }
    /**
     * Return whether the two chars are off by N.
     * @param x first char
     * @param y second char
     * @return whether they're equal
     */
    @Override
    public boolean equalChars(char x, char y) {
        int xint = x;
        int yint = y;

        if (Math.abs(xint - yint) == number) {
            return true;
        }
        return false;
    }
}
