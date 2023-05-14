import java.util.ArrayList;

public class ON {
    public String[][] result;
    private int[][] h1fun;
    private int[][][] h2funs;
    private int n;
    private int notFound = 0;
    private int alreadyExists = 0;
    private Hashing hash;
    private ArrayList<String>[] level1;
    public int order;

    public ON(Hashing h) {
        hash = h;
        this.n = hash.n;
        sequance();
    }

    private void sequance() {
        hashFunction();
        while (!acceptable())
            hashFunction();
        constructLevel2();
    }

    private void hashFunction() {
        h1fun = hash.randomH((int) Math.floor(Math.log(n) / Math.log(2)));
        constructLevel1(); // linked list is needed due to collision of level1
    }

    // make the level 1 hashing by putting each element in its place with collsion
    private ArrayList<String>[] constructLevel1() {
        level1 = new ArrayList[this.n];
        for (int i = 0; i < this.n; i++) {
            int index = hash.hashCode(hash.S[i], h1fun);
            if (level1[index] == null) {
                level1[index] = new ArrayList<String>();
            }
            if (level1[index].contains(hash.S[i]))
                alreadyExists++;
            else
                level1[index].add(hash.S[i]);
        }
        return level1;
    }

    // check that level 1 is acceptable
    private boolean acceptable() {
        order = n;
        int count = 0;
        for (int i = 0; i < level1.length; i++) {
            if (level1[i] != null) {
                count += (int) Math.pow(level1[i].size(), 2);
                order += level1[i].size();
            }

        }
        if (count >= 2 * n)
            return false;
        else {
            return true;
        }
    }

    // make the level 2 hashing by making O(n^2) hashing for each cell
    private void constructLevel2() {
        h2funs = new int[n][][];
        result = new String[n][];
        for (int i = 0; i < this.n; i++) {
            if (level1[i] != null) {
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
    public boolean delete(String s) {
        int i = hash.hashCode(s, h1fun);
        if (h2funs[i] != null && h2funs[i].length != 0) {
            int j = hash.hashCode(s, h2funs[i]);
            if (s.equals(result[i][j])) {
                result[i][j] = null;
                hash.S = remove(hash.S, s);
                this.n--;
                return true;
            }
        }
        return false;
    }

    // insert
    public boolean insert(String s) {
        int i = hash.hashCode(s, h1fun);
        // there is a collision in level 1
        if (h2funs[i] != null && h2funs[i].length != 0) {
            int j = hash.hashCode(s, h2funs[i]);
            // level 2 is ready just insert
            if (result[i][j] == null) {
                hash.S = concat(hash.S, s);
                level1[i].add(s);
                if (!acceptable()) {
                    sequance();
                } else
                    result[i][j] = s;
                return true;
            }
            // already exists
            else if (result[i][j].equals(s))
                return false;
            // need rehash the same cell has another word
            else {
                hash.S = concat(hash.S, s);
                sequance();
                return true;
            }
        } else {
            hash.S = concat(hash.S, s);
            String[] temp = new String[1];
            temp[0] = s;
            Hashing Hi = new Hashing(temp);
            ON2 sec = new ON2(Hi);
            h2funs[i] = sec.H;
            result[i] = sec.result;
            return true;
        }

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

    private String[] concat(String[] old, String s) {
        String[] newArr = new String[old.length + 1];
        for (int i = 0; i < old.length; i++) {
            newArr[i] = old[i];
        }
        newArr[old.length] = s;
        return newArr;
    }

    private String[] remove(String[] old, String s) {
        String[] newArr = new String[old.length - 1];
        int count = 0;
        for (int i = 0; i < old.length; i++) {
            if (!old[i].equals(s))
                newArr[count++] = old[i];
        }
        return newArr;
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            if (result[i] != null) {
                for (int j = 0; j < result[i].length; j++) {
                    if (result[i][j] != null)
                        System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }

        }
        System.out.println("order is " + order + " size is " + n);
    }

}
