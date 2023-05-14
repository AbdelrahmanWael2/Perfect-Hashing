import java.util.ArrayList;

public class ON {
    public int[][] result;
    private int[][] h1fun;
    private int[][][] h2funs;
    private int n;
    private int notFound = 0;
    private int alreadyExists = 0;
    private Hashing hash;

    public ON(Hashing h) {
        hash = h;
        this.n = hash.n;
        h1fun = hash.randomH(this.n);
        ArrayList<String>[] chain = constructLevel1(); // linked list is needed due to collision of level1
        constructLevel2(chain);
    }

    // make the level 1 hashing by putting each element in its place with collsion
    private ArrayList<String>[] constructLevel1() {
        ArrayList<String>[] level1 = new ArrayList[this.n];
        for (int i = 0; i < this.n; i++) {
            int[] hashBin = hash.multiply(h1fun, hash.S[i]);
            int index = hash.convertToDecimal(hashBin);
            if (level1[index].contains(hash.S[i]))
                alreadyExists++;
            else
                level1[index].add(hash.S[i]);
        }
        return level1;
    }

    // make the level 2 hashing by making O(n^2) hashing for each cell
    private void constructLevel2(ArrayList<String>[] level1) {
        for (int i = 0; i < this.n; i++) {
            if (level1[i].size() != 0) {
                Hashing Hi = new Hashing(toArray(level1[i]));
                ON2 sec = new ON2(Hi);
                h2funs[i] = sec.H;
                result[i] = sec.result;
            }
        }
    }

    // search
    public boolean search(String s) {
        int i = hash.hashCode(s, h1fun);
        if (h2funs[i].length != 0) {
            int j = hash.hashCode(s, h2funs[i]);
            if (s.equals(result[i][j]))
                return true;
        }
        notFound++;
        return false;
    }

    // delete
    ////////////////////////////// need edit
    public boolean delete(String s) {
        int i = hash.hashCode(s, h1fun);
        if (h2funs[i].length != 0) {
            int j = hash.hashCode(s, h2funs[i]);
            if (s.equals(result[i][j])) {
                ////////////// change 0 to ""
                result[i][j] = 0;
                return true;
            }
        }
        return false;
    }

    // insert
    public void insert(String s) {

    }

    // statistics
    public int getNotFound() {
        return this.notFound;
    }

    public int getAlreadyExists() {
        return this.alreadyExists;
    }

    // auxilary functions
    // function to convert linked list to array
    private String[] toArray(ArrayList<String> level1Elems) {
        String[] ans = new String[level1Elems.size()];
        for (int i = 0; i < level1Elems.size(); i++)
            ans[i] = level1Elems.get(i);
        return ans;
    }
}
