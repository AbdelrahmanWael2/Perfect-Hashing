import java.util.*;

public class Hashing {
    static final int U = 80;
    int n;
    String[] S;
    int noCollision;




    Hashing(String[] s){
        this.n = s.length;
        this.S = s;
        this.noCollision = -1;
    }

    //batch insert
    void batchinsert(String[] s){
        String[] newS = new String[this.n+s.length];
        for(int i = 0; i < this.n; i++){
            newS[i] = S[i];
        }
        for(int j = this.n; j < this.n + s.length; j++){
            newS[j] = s[j- this.n];
        }
        n=n+s.length;
        S = newS;
        S = removeDuplicates(S);
        n = S.length;
        if(s.length > 1){
        this.noCollision = -1;
            System.out.println("Performed Batch insert to " + s.length + " elements");
        }
        else{
            this.noCollision++;
            System.out.println("Inserted " + s[0]);
        }

    }


    public static String[] removeDuplicates(String[] array) {
        Set<String> uniqueSet = new HashSet<>(Arrays.asList(array));

        String[] arrayWithoutDuplicates = new String[uniqueSet.size()];
        uniqueSet.toArray(arrayWithoutDuplicates);

        return arrayWithoutDuplicates;
    }

    void insertElement(String s){
        String[] newS = new String[this.n+ 1];
        for(int i = 0; i < this.n; i++){
            newS[i] = S[i];
        }

        newS[this.n] = s;
        S = newS;
        this.noCollision = -1;
    }



    void deleteElement(String value) {
        int indexToDelete = -1;
        for (int i = 0; i < S.length; i++) {
            if (Objects.equals(S[i], value)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            return; // value not found
        }

        for (int i = indexToDelete; i < S.length - 1; i++) {
            S[i] = S[i + 1]; // shift elements to the left
        }

        S[S.length - 1] = null; // remove last element
    }


    // generate random H
    public int[][] randomH(int b) {
        int[][] H = new int[b][U];
        Random random = new Random();
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < U; j++) {
                H[i][j] = random.nextInt(2);
            }
        }
        noCollision++;
        return H;
    }

    // convert the result from the multiplication to decimal
    public int convertToDecimal(int[] bin){
        int val=0;
        for(int i=0;i < bin.length;i++)
            val+=bin[i]*Math.pow(2,i);
        return val;
    }

    // convert the value from the input string to binary to be multiplied
    public int[] convertToBinary(String str) {
        int[] bin = new int[U];
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            for (int j = 0; j < 8; j++) {
                bin[i * 8 + j] = (b & 0x80) == 0 ? 0 : 1;
                b <<= 1;
            }
        }
        if (bytes.length < U/8) {
            for (int i = bytes.length * 8; i < U; i++) {
                bin[i] = 0;
            }
        }
        return bin;
    }




    // multiply the input string with the 2D array of integers H
    public int[] multiply(int[][] H, String str) {
        int[] x = convertToBinary(str);
        int[] multiply = new int[H.length];
        int sum = 0;
        for (int i = 0; i < H.length; i++) {
            sum = 0;
            for (int j = 0; j < H[0].length; j++) {
                sum += H[i][j] * x[j];
            }
            if (sum % 2 == 0)
                multiply[i] = 0;
            else
                multiply[i] = 1;

        }
        return multiply;
    }

    // compute the hash code for a given string using the multiplication method
    //leave this for now probably won't use it
    public int hashCode(String str, int[][] H) {
        int[] result = multiply(H, str);
        return convertToDecimal(result);
    }
}

