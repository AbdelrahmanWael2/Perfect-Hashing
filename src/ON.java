import java.util.ArrayList;

public class ON {
    public String[][] result;
    private int[][] h1fun;
    private int[][][] h2funs;
    private Hashing hash;
    private ArrayList<String>[] level1;
    public int order;

    public ON(Hashing h) {
        hash = h;
        h1fun = hash.randomH((int) Math.floor(Math.log(hash.n) / Math.log(2)));
        sequance();
    }

    private void sequance() {
        constructLevel1();
        while (!acceptable()) {
            h1fun = hash.randomH((int) Math.floor(Math.log(hash.n) / Math.log(2)));
            constructLevel1();
        }
        constructLevel2();
    }

    // make the level 1 hashing by putting each element in its place with collsion
    private ArrayList<String>[] constructLevel1() {
        level1 = new ArrayList[hash.n];
        for (int i = 0; i < hash.S.length; i++) {
            int index = hash.hashCode(hash.S[i], h1fun);
            if (level1[index] == null) {
                {
                    level1[index] = new ArrayList<String>();
                    level1[index].add(hash.S[i]);
                }
            } else
                level1[index].add(hash.S[i]);
        }
        return level1;
    }

    // check that level 1 is acceptable
    private boolean acceptable() {
        System.out.println("check acceptance");
        order = hash.S.length;
        int count = 0;
        for (int i = 0; i < level1.length; i++) {
            if (level1[i] != null) {
                count += (int) Math.pow(level1[i].size(), 2);
                order += level1[i].size();
            }

        }
        if (count > 5 * hash.S.length) {
            return false;
        }

        else {
            return true;
        }
    }

    // make the level 2 hashing by making O(n^2) hashing for each cell
    private void constructLevel2() {
        h2funs = new int[hash.n][][];
        result = new String[hash.n][];
        for (int i = 0; i < hash.n; i++) {
            if (level1[i] != null) {
                Hashing Hi = new Hashing(level1[i].size());
                Hi.batchinsert(toArray(level1[i]));
                ON2 sec = new ON2(Hi);
                h2funs[i] = sec.H;
                for (int j = 0; j < sec.result.length; j++) {
                    if (sec.result[j] == "")
                        sec.result[j] = null;
                }
                result[i] = sec.result;
            }
        }
    }

    // search
    public boolean search(String s) {
        int i = hash.hashCode(s, h1fun);
        if (h2funs[i] != null) {
            int j = hash.hashCode(s, h2funs[i]);
            if (s.equals(result[i][j]))
                return true;
        }
        return false;
    }

    // delete
    public boolean delete(String s) {
        if (!search(s))
            return false;
        hash.S = remove(hash.S, s);
        sequance();
        return true;
    }

    // insert
    public boolean insert(String s) {
        if (search(s))
            return false;
        hash.insertElement(s);
        sequance();
        return true;
    }

    // batch delete
    public String batchDelete(String[] s) {
        int deleted = 0;
        int notFound = 0;
        for (String it : s) {
            // System.out.println("check existance");
            if (searchArray(hash.S, it)) {
                deleted++;
                hash.S = remove(hash.S, it);
            } else {
                notFound++;
            }
        }
        if (deleted != 0)
            sequance();
        return deleted + " items have been deleted successfully and " + notFound + " items not found";
    }

    // batch insert
    public String batchInsert(String[] s) {
        int alreadyExists = hash.batchinsert(s);
        int added = s.length - alreadyExists;
        if (added != 0)
            sequance();
        return added + " items have been added successfully and " + alreadyExists + " already exists";

    }

    // auxilary functions
    // function to convert linked list to array
    private String[] toArray(ArrayList<String> level1Elems) {
        String[] ans = new String[level1Elems.size()];
        for (int i = 0; i < level1Elems.size(); i++)
            ans[i] = level1Elems.get(i);
        return ans;
    }

    // private String[] concat(String[] old, String s) {
    // String[] newArr = new String[old.length + 1];
    // for (int i = 0; i < old.length; i++) {
    // newArr[i] = old[i];
    // }
    // newArr[old.length] = s;
    // return newArr;
    // }

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
        for (int i = 0; i < hash.n; i++) {
            if (result[i] != null) {
                for (int j = 0; j < result[i].length; j++) {
                    if (result[i][j] != null)
                        System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }

        }
        System.out.println("order is " + order + " size is " + hash.n);
    }

    private boolean searchArray(String[] arr, String s) {
        for (String it : arr) {
            if (it.equals(s))
                return true;
        }
        return false;
    }
}
