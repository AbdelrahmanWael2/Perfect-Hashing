import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class ON2 {
    String[] result;
    int H[][]; // Zeros and ones
    boolean[] exist;
    int b;
    int n;
    Hashing hashing;

    public ON2(Hashing hash) {
        this.hashing = hash;
        n = hashing.n;
        result = new String[n * n];
        exist = new boolean[n * n];
        b = (int) Math.floor(Math.log(Math.pow(n, 2)) / Math.log(2));
        H = new int[b][Hashing.U];
        System.out.println("Using Order N^2 method");
        hashFunction();
    }


    /*this function generate random h and multiply by the element in array to get index
    if that index exists before ,so it is collision so will loop again from beginning of array
    to find hash function suitable for all elements of array without collision
    * */
    public void hashFunction() {
        String[] s = hashing.S;
        n = hashing.n;
        H = hashing.randomH(b);
        result = new String[n * n];
        exist = new boolean[n * n];
        boolean hashed = false;
        while (!hashed) {
            hashed = true;
            for (int i = 0; i < n; i++) {
                int[] indexBinary = hashing.multiply(H, s[i]);
                int index = hashing.convertToDecimal(indexBinary);
                if (exist[index]) {
//                    System.out.println("alo");
                    H = hashing.randomH(b);
                    hashed = false;
                    Arrays.fill(exist, false);
                    Arrays.fill(result, "");

                    break;
                } else {
                    result[index] = s[i];
                    exist[index] = true;

                }
            }

            System.out.println("Hash result is " + n +"X"+ n);

        }
    }

    void insert(String value){
        boolean Hashed = false;
        int[] indexBinary = hashing.multiply(H, value);
        int index = hashing.convertToDecimal(indexBinary);
        if(exist[index]){
            String[] s = new String[1];
            s[0] = value;
            hashing.batchinsert(s);
            n = hashing.n;
            hashFunction();
            print();
            System.out.println("Failed to hash directly");
        }else{
            result[index] = value;
            exist[index] = true;
            print();
            System.out.println("Hashed Directly");
        }
    }


    public int noOfHashFuns() {
        return hashing.noCollision;
    }

    public void print() {
        int j = 0;
//        if(n < hashing.n){
            n = hashing.n;
//        }
        for (int i = 0; i < n * n; i++) {
            if (exist[i]) {
                System.out.println("String \"" + result[i] + "\"  found at index : " + i);
                j++;
            }
        }
        System.out.println("Number to re-build the hash table in the case of collision = " + hashing.noCollision);
    }
    //search for element if existed, and it's location using O(1) time complexity
    //worst case complexity is O(n) if all collisions happen
    public String lookUp(String value) {
        int[] indexBinary = hashing.multiply(H, value);
        int index = hashing.convertToDecimal(indexBinary);
        if (exist[index] && Objects.equals(result[index], value)) {
           return "String \"" + value + "\"  found at index " + index;
        } else {
            return "String \"" + value + "\" not found !!";
        }
    }

    public boolean delete(String value){
        int[] indexBinary = hashing.multiply(H, value);
        int index = hashing.convertToDecimal(indexBinary);
        if (exist[index] && Objects.equals(result[index], value)) {
            exist[index] = false;
            result[index] = null;
            hashing.n --;
            hashing.deleteElement(value);
            return true;
        }else{
            return false;
        }
    }

}
