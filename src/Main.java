import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static final String ANSI_RESET = "\u001B[0m", BLACK = "\u001B[30m", RED = "\u001B[31m", GREEN = "\u001B[32m",
      YELLOW = "\u001B[33m", BLUE = "\u001B[34m", PURPLE = "\u001B[35m", CYAN = "\u001B[36m";

  public static void main(String[] args) throws IOException {

    // MAX STRING LENGTH IS 10

    /* CLI */

    // Initialize the dictionary
    Dictionary d = null;

    util.clearScreen();

    System.out.println(GREEN + "Select the type of the backend perfect hashing:" + ANSI_RESET);
    System.out.println(BLUE + "\t-> O(N) - Space" + ANSI_RESET + " (Enter 1)");
    System.out.println(RED + "\t-> O(N^2) - Space" + ANSI_RESET + " (Enter 2)");

    Scanner sc = new Scanner(System.in);
    String selection = "0";
    while (!selection.equals("1") && !selection.equals("2")) {
      selection = sc.nextLine();
      System.out.println("Enter n");
      int n = sc.nextInt();
      if (!selection.equals("1") && !selection.equals("2")) {
      } else if (selection.equals("1"))
        d = new ON_Dictionary(n);
      else
        d = new ON2_Dictionary(n);
    }

    // main menu
    while (true) {
      util.clearScreen();
      System.out.println(PURPLE + "1." + CYAN + " Insert" + ANSI_RESET);
      System.out.println(PURPLE + "2." + CYAN + " Delete" + ANSI_RESET);
      System.out.println(PURPLE + "3." + CYAN + " Search" + ANSI_RESET);
      System.out.println(PURPLE + "4." + CYAN + " Batch Insert" + ANSI_RESET);
      System.out.println(PURPLE + "5." + CYAN + " Batch Delete" + ANSI_RESET);
      System.out.println(PURPLE + "6." + CYAN + " Exit" + ANSI_RESET);
      selection = sc.nextLine();
      util.clearScreen();
      switch (selection) {
        case "1":
          System.out.print("Enter the word to insert: ");
          Scanner insertScanner = new Scanner(System.in);
          String insertString = insertScanner.nextLine();
          util.doInsert(d, insertString);
          break;
        case "2":
          System.out.print("Enter the key to delete: ");
          Scanner deleteScanner = new Scanner(System.in);
          String deleteString = deleteScanner.nextLine();
          util.doDelete(d, deleteString);
          break;
        case "3":
          System.out.print("Enter the key to search for: ");
          Scanner searchScanner = new Scanner(System.in);
          String searchString = searchScanner.nextLine();
          util.doSearch(d, searchString);
          break;
        case "4":
          System.out.print("Enter the file path: ");
          Scanner batchInsertScanner = new Scanner(System.in);
          String batchInsertFilePath = batchInsertScanner.nextLine();
          util.doBatchInsert(d, batchInsertFilePath);
          break;
        case "5":
          System.out.print("Enter the file path: ");
          Scanner batchDeleteScanner = new Scanner(System.in);
          String batchDeleteFilePath = batchDeleteScanner.nextLine();
          util.doBatchDelete(d, batchDeleteFilePath);
          break;
        case "6":
          sc.close();
          System.exit(0);
          break;
        default:
          break;
      }
    }
  }
  // try {
  // List<String> lines = Files.readAllLines(Paths.get("src/Test.txt"));
  // String[] array = lines.toArray(new String[0]);
  // array = Arrays.stream(array).distinct().toArray(String[]::new);
  // Hashing hash = new Hashing(array);
  // ON2 h = new ON2(hash);
  // h.print();

  // // insert one element
  // h.insert("raffiq");

  // // search
  // h.lookUp("ahmed");
  // h.lookUp("anything");

  // // batch insert
  // List<String> lines2 = Files.readAllLines(Paths.get("src/Test2.txt"));
  // String[] array2 = lines2.toArray(new String[0]);
  // array2 = Arrays.stream(array2).distinct().toArray(String[]::new);
  // hash.batchinsert(array2);
  // h = null;
  // h = new ON2(hash);
  // h.print();

  // // delete
  // System.out.println(h.delete("ahmed"));
  // System.out.println(h.delete("anything"));
  // ;

  // // batch delete
  // List<String> lines3 = Files.readAllLines(Paths.get("src/Test2.txt"));
  // String[] array3 = lines3.toArray(new String[0]);
  // array3 = Arrays.stream(array3).distinct().toArray(String[]::new);
  // int count = 0;
  // for (int i = 0; i < array3.length; i++) {
  // if (h.delete(array3[i])) {
  // count++;
  // }
  // }
  // h = new ON2(hash);
  // System.out.println("Deleted " + count + " Strings");
  // h.print();

  // } catch (Exception e) {
  // e.printStackTrace();
  // }

  // ////// O(n) start
  // try {
  // List<String> lines = Files.readAllLines(Paths.get("src/Test.txt"));
  // String[] array = lines.toArray(new String[0]);
  // array = Arrays.stream(array).distinct().toArray(String[]::new);
  // Hashing hash = new Hashing(array);

  // ON h = new ON(hash);

  // h.print();

  // // insert one element
  // System.out.println(h.insert("raffiq"));

  // // search
  // System.out.println(h.search("ahmed"));
  // System.out.println(h.search("anything"));

  // // batch insert
  // List<String> lines2 = Files.readAllLines(Paths.get("src/Test2.txt"));
  // String[] array2 = lines2.toArray(new String[0]);
  // array2 = Arrays.stream(array2).distinct().toArray(String[]::new);
  // hash.batchinsert(array2);
  // h = null;
  // h = new ON(hash);
  // h.print();

  // // delete
  // System.out.println(h.delete("ahmed"));
  // System.out.println(h.delete("anything"));
  // ;

  // // batch delete
  // List<String> lines3 = Files.readAllLines(Paths.get("src/Test2.txt"));
  // String[] array3 = lines3.toArray(new String[0]);
  // array3 = Arrays.stream(array3).distinct().toArray(String[]::new);
  // int count = 0;
  // for (int i = 0; i < array3.length; i++) {
  // if (h.delete(array3[i])) {
  // count++;
  // }
  // }
  // hash = new Hashing(array3);
  // h = new ON(hash);
  // System.out.println("Deleted " + count + " Strings");
  // h.print();

  // } catch (Exception e) {
  // e.printStackTrace();
  // }
  // ////// O(n) ends
  // }

  // boolean valid = false;
  // while (!valid) {
  // System.out.println("Choose Method 1 for ON2 2 for ON");
  // Scanner sc = new Scanner(System.in);
  // int input = sc.nextInt();
  // switch (input) {
  // case 1:
  // valid = true;
  // ON2 h = new ON2(hash);
  // h.print();
  // System.out.println("1 for delete, 2 for insert, 3 for batch insert, 4 for
  // search");
  // int fun = sc.nextInt();
  // switch (fun) {
  // case 1:{
  // System.out.println("What do u want to delete?");
  // String in = sc.nextLine();
  // h.delete(in);
  // }
  //
  // case 2:{
  // System.out.println("Enter string to insert");
  // String toInsert = sc.nextLine();
  // h.insert(toInsert);
  // h.print();}
  //
  // case 3:{
  // System.out.println("Give path of file");
  // String path = sc.nextLine();}
  //
  // default:
  //
  // }
  //
  // case 2:
  // System.out.println("Apply ON");
  // valid = true;
  //
  // default:
  // System.out.println("Invalid Choice");
  // valid = false;
  // }
  // }

  // //test case
  // String[] s = {"helloooo", "hello", "malo", "malosh", "zamalek", "eren",
  // "yeager", "zamalek", "ahmed"};
  //
  // //get an array without repeated strings
  // s = Arrays.stream(s).distinct().toArray(String[]::new);
  //
  //
  // Hashing hash = new Hashing(s);
  //// //insert
  //// hash.insert(s);
  // ON2 h = new ON2(hash);
  // h.print();
  // System.out.println("Enter key to search for.. ");
  // Scanner sc = new Scanner(System.in);
  // String input = sc.nextLine();
  // h.lookUp(input);
  // System.out.println("Delete something");
  // String input2 = sc.nextLine();
  // h.delete(input2);
  // System.out.println("insert again");
  // String[] s2 = {"heo", "ello"};
  // hash.batchinsert(s2);
  // //ON2 h2 = new ON2(hash);
  // h = null;
  // h = new ON2(hash);
  // h.print();
}
