import java.util.ArrayList;
import java.util.List;

public class testLibrary {
    public static void test() {
        // Tạo các đối tượng Book
        Book book1 = new Book("24100012", "Doraemon");
        Book book2 = new Book("24100011", "Dragon Ball");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        // Kiểm tra constructor có tham số
        Library libraryWithBooks = new Library(bookList);
        System.out.println("Test Library with books:");
        for (Book b : libraryWithBooks.getList()) {
            System.out.println("Book ID: " + b.getBookID() + ", Name: " + b.getBookName());
        }

        // Kiểm tra constructor mặc định
        Library emptyLibrary = new Library();
        System.out.println("\nTest empty Library:");
        System.out.println("Number of books: " + emptyLibrary.getList().size());
    }
}
