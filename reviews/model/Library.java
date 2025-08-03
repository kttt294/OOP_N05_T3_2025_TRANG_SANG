import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;
    public Library() { 
        this.books = new ArrayList<>();
    };
    Library(List<Book> b) {
        this.books = b;
    }
    public List<Book> getList() {
        return books;
    }
}
