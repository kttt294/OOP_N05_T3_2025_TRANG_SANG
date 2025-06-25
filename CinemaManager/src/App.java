import java.util.Scanner;
import test.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
            testKhachHang.test(scanner);
            System.out.println();
            testPhim.test(scanner);
            System.out.println();
            testSuatChieu.test(scanner);
    }
}
