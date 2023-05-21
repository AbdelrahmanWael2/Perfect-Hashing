import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateChecker {
    public static int countWord(String word, FileInputStream fis) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        String readLine = "";
        int count = 0;
        while ((readLine = in.readLine()) != null) {
            String[] words = readLine.split(" ");
            for (String s : words) {
                if (s.equals(word)) count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int count = 0 ;
//        String fileName = "D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test2.txt";
        String fileName = "D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test.txt";

        try {

//                to detect whether the file contains duplicates or not
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            Set<String> uniqueWords = new HashSet<>(lines);
            if (uniqueWords.size() == lines.size()) {
                System.out.println("The file does not contain any duplicate words.");
            } else {
                System.out.println("The file contains duplicate words.");
                FileInputStream fis = new FileInputStream(fileName);
                System.out.println( countWord("mango", fis));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}