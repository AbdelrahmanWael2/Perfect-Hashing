import java.io.IOException;

public interface Dictionary {
    String insert(String insertString);
    String search(String searchString);
    String delete(String deleteString);
    String batchInsert(String path) throws IOException;
    String batchDelete(String path) throws IOException;
}
