import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.print.FlavorException;

public class ON_Dictionary implements Dictionary {
    private ON On_dictionary = null;
    private Hashing h = null;

    public ON_Dictionary(int n) {
        // String[] arr = new String[2];
        // arr[0] = "dummy1";
        // arr[1] = "dummy2";

        h = new Hashing(n);
        On_dictionary = new ON(h);
    }

    @Override
    public String insert(String insertString) {
        if (On_dictionary.insert(insertString))
            return insertString + " inserted successfully";
        // return insertString + " inserted successfully" + ", num of cells in level2 =
        // " + On_dictionary.order;
        else
            return insertString + " already exists";
    }

    @Override
    public String search(String searchString) {
        return On_dictionary.search(searchString);
    }

    @Override
    public boolean delete(String deleteString) {
        if (On_dictionary.delete(deleteString))
            return true;
        else
            return false;
    }

    @Override
    public String batchInsert(String path) throws IOException {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (Exception e) {
            return "File not found";
        }
        String[] array = lines.toArray(new String[0]);
        // for(int i = 0; i < array.length; i++) {
        // On_dictionary.insert(array[i]);
        // }
        return On_dictionary.batchInsert(array);
    }

    @Override
    public String batchDelete(String path) throws IOException {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (Exception e) {
            return "File not found";
        }
        String[] array = lines.toArray(new String[0]);
        // for(int i = 0; i < array.length; i++) {
        // On_dictionary.delete(array[i]);
        // }
        return On_dictionary.batchDelete(array);
    }
}
