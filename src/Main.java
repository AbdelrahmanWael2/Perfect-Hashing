import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //test case
        String[] s = {"helloooo", "hello", "malo", "malosh", "zamalek", "eren", "yeager", "zamalek", "ahmed"};
        
        //get an array without repeated strings
        s = Arrays.stream(s).distinct().toArray(String[]::new);


        Hashing hash = new Hashing(s);
        ON2 h = new ON2(hash);
        h.print();
        System.out.println("Enter key to search for.. ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        h.lookUp(input);

    }
}