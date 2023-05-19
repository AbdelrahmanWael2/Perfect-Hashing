import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ON2_Dictionary implements Dictionary {
    private ON2 On2_dictionary;
    private Hashing h;

    public ON2_Dictionary(int n) {
        String[] arr = new String[2];
        arr[0] = "dummy1";
        arr[1] = "dummy2";
        h = new Hashing(n);
        On2_dictionary = new ON2(h);
    }

    @Override
    public String insert(String insertString) {
        On2_dictionary.insert(insertString);
        On2_dictionary = new ON2(h);
        On2_dictionary.print();
        return insertString + " inserted successfully";
    }

    @Override
    public String search(String searchString) {
        return On2_dictionary.lookUp(searchString);
    }

    @Override
    public String delete(String deleteString) {
        if (On2_dictionary.delete(deleteString)) {
            On2_dictionary = new ON2(h);
            On2_dictionary.print();
            return deleteString + " was found and deleted";
        } else
            return deleteString + " was not found";
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
        On2_dictionary.batchInsertion(array);
        On2_dictionary = new ON2(h);
        On2_dictionary.print();
        return "Batch insertion finished";
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
        for (int i = 0; i < array.length; i++) {
            On2_dictionary.delete(array[i]);
        }
        On2_dictionary = new ON2(h);
        On2_dictionary.print();
        return "Batch deletion finished";
    }
}
