import java.util.Scanner;
import model.KhachHang;
import model.GioiTinh;
import java.util.ArrayList;

public class testKhachHang{
    
    public void nhapThongTin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập mã khách hàng: ");
        this.maKH = scanner.nextLine();

        System.out.print("Nhập tên khách hàng: ");
        this.tenKH = scanner.nextLine();

        System.out.print("Nhập tuổi: ");
        this.tuoi = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập số điện thoại: ");
        this.sdt = scanner.nextLine();

        System.out.print("Nhập email: ");
        this.email = scanner.nextLine();

        System.out.print("Nhập giới tính (NAM/NU): ");
        String gt = scanner.nextLine().toUpperCase();
        if (gt.equals("NAM")) {
            this.gioiTinh = GioiTinh.NAM;
        } else {
            this.gioiTinh = GioiTinh.NU;
        }

        this.lichSuDatVe = new ArrayList<>();
    }

    public void inThongTin() {
    System.out.println("===== Thông Tin Khách Hàng =====");
    System.out.println("Mã KH: " + maKH);
    System.out.println("Tên KH: " + tenKH);
    System.out.println("Tuổi: " + tuoi);
    System.out.println("Số điện thoại: " + sdt);
    System.out.println("Email: " + email);
    System.out.println("Giới tính: " + gioiTinh);

    System.out.println("----- Danh sách vé đã đặt -----");
    if (lichSuDatVe == null || lichSuDatVe.isEmpty()) {
        System.out.println("Chưa đặt vé nào.");
    } else {
        for (int i = 0; i < lichSuDatVe.size(); i++) {
            System.out.println("Vé " + (i + 1) + ": " + lichSuDatVe.get(i));
        }
    }
}

}