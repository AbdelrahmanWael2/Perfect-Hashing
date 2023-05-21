import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomWordGenerator {
    public static void main(String[] args) {
        int wordLength = 10;
        Random rand = new Random();
        try (FileWriter writer = new FileWriter("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test2.txt")) {
            for (int i = 0; i < 1000000; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < wordLength; j++) {
                    char c = (char) (rand.nextInt(26) + 'a');
                    sb.append(c);
                }
                String randomWord = sb.toString();
                writer.write(randomWord + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//import java.io.FileWriter;
//        import java.io.IOException;
//        import java.util.Random;
//
//public class RandomWordGenerator {
//    public static void main(String[] args) {
//        String[] words = {"apple", "banana", "orange", "strawberry", "grape", "pineapple", "mango", "cherry"};
//        Random rand = new Random();
//        try (FileWriter writer = new FileWriter("D:/CSED2_2/DS/labs/hashing/Perfect-Hashing/src/Test.txt")) {
//            for (int i = 0; i < 1000000; i++) {
//                String randomWord = words[rand.nextInt(words.length)];
//                writer.write(randomWord + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}