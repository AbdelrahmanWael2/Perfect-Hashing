import java.util.ArrayList;

public class ON {
    public String[][] result;
    private int[][] h1fun;
    private int[][][] h2funs;
    private Hashing hash;
    private ArrayList<String>[] level1;
    public int order;
    public int rehashnum = 0;

    public ON(Hashing h) {
        hash = h;
        h1fun = hash.randomH((int) Math.floor(Math.log(hash.n) / Math.log(2)));
        h2funs = new int[hash.n][][];
        result = new String[hash.n][];
        level1 = new ArrayList[hash.n];
    }

    // search
    private String getIndex(String s) {
        int i = hash.hashCode(s, h1fun);
        int j = -1;
        if (h2funs[i] != null) {
            j = hash.hashCode(s, h2funs[i]);
        }
        return i + "$" + j;
    }

    public String search(String s) {
        String[] arr = getIndex(s).split("\\$");
        int i = Integer.parseInt(arr[0]);
        int j = Integer.parseInt(arr[1]);
        if (j == -1)
            return s + " not found";
        else {
            if (result[i][j] == null)
                return s + " not found";
            else
                return s + " found successfully";
            // return s + " found at level1 index = " + arr[0] + " level2 index = " +
            // arr[1];
        }

    }

    private void rehash(int i) {
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

    // insert
    public boolean insert(String myStr) {
        String[] arr = getIndex(myStr).split("\\$");
        int i = Integer.parseInt(arr[0]);
        int j = Integer.parseInt(arr[1]);
        if (j != -1) // already exists or collision needs rehash or ready to accept
        {
            if (result[i][j] == null) {
                level1[i].add(myStr);
                result[i][j] = myStr;
                return true;
            } else if (!result[i][j].equals(myStr)) // collision
            {
                order -= (level1[i].size() * level1[i].size());
                level1[i].add(myStr);
                rehash(i);
                order += (level1[i].size() * level1[i].size());
                rehashnum++;
                return true;
            } else {
                return false;
            }
        } else {
            level1[i] = new ArrayList<String>();
            level1[i].add(myStr);
            order += (level1[i].size() * level1[i].size());
            rehash(i);
            return true;
        }
    }

    // delete
    public boolean delete(String myStr) {
        String[] arr = getIndex(myStr).split("\\$");
        int i = Integer.parseInt(arr[0]);
        int j = Integer.parseInt(arr[1]);
        if (j == -1)
            return false;
        else {
            if (result[i][j] == null)
                return false;
            else {
                result[i][j] = null;
                return true;
            }

        }
    }

    // batch insert and delete
    public String batchDelete(String[] arr) {
        int deleteed = 0;
        for (String it : arr)
            if (delete(it))
                deleteed++;
        return deleteed + " items deleted " + (arr.length - deleteed) + " items not exist";
    }

    public String batchInsert(String[] arr) {
        int added = 0;
        for (String it : arr)
            if (insert(it))
                added++;
        return added + " items added and " + (arr.length - added) + " not exist";
        // return added + " items added and " + (arr.length - added) + " not exist" + ",
        // num of cells in level2 = "
        // + order;
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

    // private String[] remove(String[] old, String s) {
    // String[] newArr = new String[old.length - 1];
    // int count = 0;
    // for (int i = 0; i < old.length; i++) {
    // if (!old[i].equals(s))
    // newArr[count++] = old[i];
    // }
    // return newArr;
    // }

    // public void print() {
    // for (int i = 0; i < hash.n; i++) {
    // if (result[i] != null) {
    // for (int j = 0; j < result[i].length; j++) {
    // if (result[i][j] != null)
    // System.out.print(result[i][j] + " ");
    // }
    // System.out.println();
    // }

    // }
    // System.out.println("order is " + order + " size is " + hash.n);
    // }

    // private boolean searchArray(String[] arr, String s) {
    // for (String it : arr) {
    // if (it.equals(s))
    // return true;
    // }
    // return false;
    // }
}
