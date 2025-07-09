package btvn.ReusingClasses.ex1;

public class LazyInitialization {
    private Simple simple; // Khai báo tham chiếu nhưng chưa khởi tạo

    // Phương thức này chỉ khởi tạo khi thật sự cần
    public void useSimple() {
        if (simple == null) {
            // Chỉ tạo đối tượng khi cần dùng -> lazy initialization
            simple = new Simple();
        }
        simple.greet();
    }

    public static void main(String[] args) {
        LazyInitialization li = new LazyInitialization(); // Tạo đối tượng của lớp hiện tại
        System.out.println("Calling useSimple() the first time:");
        li.useSimple(); // Gọi lần đầu -> sẽ tạo Simple

        System.out.println("Calling useSimple() again:");
        li.useSimple(); // Gọi lần sau -> không tạo nữa, dùng lại
    }
}
