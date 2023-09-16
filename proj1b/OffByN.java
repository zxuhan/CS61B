public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int N) {
        this.N = N;
    }
    
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) != N) {
            return false;
        }
        return true;
    }
}
