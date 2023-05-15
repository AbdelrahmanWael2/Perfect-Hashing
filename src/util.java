import java.io.IOException;

public class util {

    public static void prompt(){
        System.out.println("Press any key to go back");
        try
        {
        System.in.read();
        }
        catch(Exception e)
        {}
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void doBatchDelete(Dictionary dict, String batchDeleteFilePath) throws IOException {
        dict.batchDelete(batchDeleteFilePath);
        prompt();
    }
    public static void doBatchInsert(Dictionary dict, String batchInsertFilePath) throws IOException {
        System.out.println(dict.batchInsert(batchInsertFilePath));
        prompt();
    }
    public static void doSearch(Dictionary dict, String searchString) {
        System.out.println(dict.search(searchString));
        prompt();
    }
    public static void doDelete(Dictionary dict, String deleteString) {
        System.out.println(dict.delete(deleteString));
        prompt();
    }
    public static void doInsert(Dictionary dict, String inputString) {
        System.out.println(dict.insert(inputString));
        prompt();
    }

}