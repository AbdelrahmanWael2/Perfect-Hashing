import java.io.*;
import java.util.*;

public class ON2 {
    String[] result;
    int H[][]; // Zeros and ones
    boolean[] exist;
    int b;
    int n;
    int size = 0;
    Hashing hashing;

    public ON2(Hashing hash) {
        this.hashing = hash;
        n = hashing.n;
        result = new String[n * n];
        exist = new boolean[n * n];
        b = (int) Math.floor(Math.log(Math.pow(n, 2)) / Math.log(2));
        H = new int[b][Hashing.U];
        // System.out.println("Using Order N^2 method");
        hashFunction();
    }

    public static String[] removeNulls(String[] strings) {
        List<String> filteredList = new ArrayList<>();

        for (String str : strings) {
            if (str != null) {
                filteredList.add(str);
            }
        }

        // Convert the list back to an array
        String[] filteredArray = new String[filteredList.size()];
        filteredList.toArray(filteredArray);

        return filteredArray;
    }

    /*
     * this function generate random h and multiply by the element in array to get
     * index
     * if that index exists before ,so it is collision so will loop again from
     * beginning of array
     * to find hash function suitable for all elements of array without collision
     */
    public void hashFunction() {
        String[] s = hashing.S;
        s = removeNulls(s);
        n = hashing.n;
        H = hashing.randomH(b);
        result = new String[n * n];
        exist = new boolean[n * n];
        boolean hashed = false;
        while (!hashed) {
            hashed = true;
            for (int i = 0; i < s.length; i++) {
                int[] indexBinary = hashing.multiply(H, s[i]);
                int index = hashing.convertToDecimal(indexBinary);
                if (exist[index]) {
                    // System.out.println("alo");
                    H = hashing.randomH(b);
                    hashed = false;
                    Arrays.fill(exist, false);
                    Arrays.fill(result, "");
                    size = 0;

                    break;
                } else {
                    result[index] = s[i];
                    size++;
                    exist[index] = true;

                }
            }

            // System.out.println("Hash result is " + n +"X"+ n);

        }
    }

    void insert(String value) {
        boolean Hashed = false;
        int[] indexBinary = hashing.multiply(H, value);
        int index = hashing.convertToDecimal(indexBinary);
        System.out.println("N= "+hashing.n + " Size= " + size);
        if(hashing.n * hashing.n <= size){
            System.out.println("No more space in table");
            return;
        }
        if (Objects.equals(result[index], value)) {
            System.out.println("Already Exists");
             return;
        }
        if (exist[index]) {
            String[] s = new String[1];
            s[0] = value;
            hashing.batchinsert(s);
            n = hashing.n;
            hashFunction();

            System.out.println("Failed to hash directly");
        } else {
            result[index] = value;
            size++;
            exist[index] = true;
            hashing.insertElement(value);

            System.out.println("Hashed " + value + " Directly");
        }
    }

    int batchInsertion(String[] s) {
        for (int i = 0; i < s.length; i++) {
            int[] indexBinary = hashing.multiply(H, s[i]);
            int index = hashing.convertToDecimal(indexBinary);
            if (Objects.equals(result[index], s[i])) {
                result[index] = null;
            }
        }

        size = size + s.length - hashing.batchinsert(s);
        return size;
    }

    public int noOfHashFuns() {
        return hashing.noCollision;
    }

    // Method to clear the contents of a file
    public static void clearFileContents(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, false);
        fileWriter.close();
    }

    public void print() {

        n = hashing.n;
        String filePath = "src/Output.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            clearFileContents(filePath);
            for (int i = 0; i < n * n; i++) {
                if (exist[i]) {
                    writer.write("String \"" + result[i] + "\"  found at index : " + i);
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("Number to re-build the hash table in the case of collision = " + hashing.noCollision);
        hashing.noCollision = 0;
    }

    // search for element if existed, and it's location using O(1) time complexity
    // worst case complexity is O(n) if all collisions happen
    public String lookUp(String value) {
        int[] indexBinary = hashing.multiply(H, value);
        int index = hashing.convertToDecimal(indexBinary);
        if (exist[index] && Objects.equals(result[index], value)) {
            return "String \"" + value + "\"  found at index " + index;
        } else {
            return "String \"" + value + "\" not found !!";
        }
    }

    public boolean delete(String value) {
        int[] indexBinary = hashing.multiply(H, value);
        int index = hashing.convertToDecimal(indexBinary);
        if (exist[index] && Objects.equals(result[index], value)) {
            exist[index] = false;
            result[index] = null;
            //size--;
            //hashing.n--;
            hashing.deleteElement(value);
            return true;
        } else {
            return false;
        }
    }

}
