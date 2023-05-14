import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //MAX STRING LENGTH IS 10

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/Test.txt"));
            String[] array = lines.toArray(new String[0]);
            array = Arrays.stream(array).distinct().toArray(String[]::new);
            Hashing hash = new Hashing(array);
            ON2 h = new ON2(hash);
            h.print();

            //insert one element
            h.insert("raffiq");


            //search
            h.lookUp("ahmed");
            h.lookUp("anything");

            //batch insert
            List<String> lines2 = Files.readAllLines(Paths.get("src/Test2.txt"));
            String[] array2 = lines2.toArray(new String[0]);
            array2 = Arrays.stream(array2).distinct().toArray(String[]::new);
            hash.batchinsert(array2);
            h = null;
            h = new ON2(hash);
            h.print();

            //delete
            System.out.println(h.delete("ahmed"));
            System.out.println(h.delete("anything"));;

            //batch delete
            List<String> lines3 = Files.readAllLines(Paths.get("src/Test2.txt"));
            String[] array3 = lines3.toArray(new String[0]);
            array3 = Arrays.stream(array3).distinct().toArray(String[]::new);
            int count = 0;
            for(int i = 0; i < array3.length; i++){
                if(h.delete(array3[i])){
                    count++;
                }
            }
            h = new ON2(hash);
            System.out.println("Deleted " + count + " Strings");
            h.print();




        } catch (Exception e) {
            e.printStackTrace();
        }


    }


//           boolean valid = false;
//            while (!valid) {
//                System.out.println("Choose Method 1 for ON2 2 for ON");
//                Scanner sc = new Scanner(System.in);
//                int input = sc.nextInt();
//                switch (input) {
//                    case 1:
//                        valid = true;
//                        ON2 h = new ON2(hash);
//                        h.print();
//                        System.out.println("1 for delete, 2 for insert, 3 for batch insert, 4 for search");
//                        int fun = sc.nextInt();
//                        switch (fun) {
//                            case 1:{
//                                System.out.println("What do u want to delete?");
//                                String in = sc.nextLine();
//                                h.delete(in);
//                               }
//
//                            case 2:{
//                                System.out.println("Enter string to insert");
//                                String toInsert = sc.nextLine();
//                                h.insert(toInsert);
//                                h.print();}
//
//                            case 3:{
//                                System.out.println("Give path of file");
//                                String path = sc.nextLine();}
//
//                            default:
//
//                        }
//
//                    case 2:
//                        System.out.println("Apply ON");
//                        valid = true;
//
//                    default:
//                        System.out.println("Invalid Choice");
//                        valid = false;
//                }
//            }




//        //test case
//        String[] s = {"helloooo", "hello", "malo", "malosh", "zamalek", "eren", "yeager", "zamalek", "ahmed"};
//
//        //get an array without repeated strings
//        s = Arrays.stream(s).distinct().toArray(String[]::new);
//
//
//        Hashing hash = new Hashing(s);
////        //insert
////        hash.insert(s);
//        ON2 h = new ON2(hash);
//        h.print();
//        System.out.println("Enter key to search for.. ");
//        Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine();
//        h.lookUp(input);
//        System.out.println("Delete something");
//        String input2 = sc.nextLine();
//        h.delete(input2);
//        System.out.println("insert again");
//        String[] s2 = {"heo", "ello"};
//        hash.batchinsert(s2);
//        //ON2 h2 = new ON2(hash);
//        h = null;
//        h = new ON2(hash);
//        h.print();
    }
