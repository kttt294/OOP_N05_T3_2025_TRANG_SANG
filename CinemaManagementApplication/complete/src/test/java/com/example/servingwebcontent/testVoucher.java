package com.example.servingwebcontent;
import com.example.servingwebcontent.model.Voucher;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class testVoucher {
    public static void testCreate() {
        System.out.println("=== TEST CREATE ===");
        Voucher v1 = new Voucher("VC001", "Giảm 10%", 10,
                LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(3),
                "5", "HoatDong");

        Voucher.Create(v1);

        assert Voucher.getVoucherById("VC001") != null;
        System.out.println("✓ Tạo voucher thành công");
    }

    public static void testRead() {
        System.out.println("=== TEST READ ===");
        ArrayList<Voucher> ds = Voucher.Read();
        assert ds.size() >= 1;
        Voucher.Read("VC001"); // Hiển thị chi tiết
        System.out.println("✓ Đọc danh sách voucher OK");
    }

    public static void testUpdate() {
        System.out.println("=== TEST UPDATE ===");
        Voucher v2 = new Voucher("VC001", "Giảm 15%", 15,
                LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(5),
                "10", "HoatDong");
        Voucher.Update("VC001", v2);

        Voucher capNhat = Voucher.getVoucherById("VC001");
        assert capNhat.getPhanTramGiamGia() == 15;
        assert "10".equals(capNhat.getSoLuongConLai());
        System.out.println("✓ Cập nhật voucher OK");
    }

    public static void testDelete() {
        System.out.println("=== TEST DELETE ===");
        Voucher v3 = new Voucher("VC002", "Test xoá", 5,
                LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "1", "HoatDong");
        Voucher.Create(v3);
        Voucher.Delete("VC002");

        assert Voucher.getVoucherById("VC002") == null;
        System.out.println("✓ Xoá voucher OK");
    }

    public static void testKiemTraHopLe() {
        System.out.println("=== TEST KIỂM TRA VOUCHER HỢP LỆ ===");
        Voucher v4 = new Voucher("VC003", "Giảm 20%", 20,
                LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1),
                "3", "HoatDong");
        Voucher.Create(v4);

        boolean hopLe = Voucher.kiemTraVoucherHopLe("VC003");
        assert hopLe;
        System.out.println("✓ Voucher hợp lệ OK");
    }

    public static void testSuDungVoucher() {
        System.out.println("=== TEST SỬ DỤNG VOUCHER ===");
        Voucher v5 = new Voucher("VC004", "Giảm 30%", 30,
                LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1),
                "1", "HoatDong");
        Voucher.Create(v5);

        boolean dung1 = Voucher.suDungVoucher("VC004");
        boolean dung2 = Voucher.suDungVoucher("VC004");

        assert dung1 == true;
        assert dung2 == false;

        Voucher daDung = Voucher.getVoucherById("VC004");
        assert "HetHang".equalsIgnoreCase(daDung.getTrangThai());

        System.out.println("✓ Sử dụng voucher và chuyển trạng thái OK");
    }

    public static void testThongKe() {
        System.out.println("=== TEST THỐNG KÊ ===");
        Voucher.thongKeVoucher();
        System.out.println("✓ Thống kê voucher OK");
    }

    public static void test() {
        testCreate();
        testRead();
        testUpdate();
        testDelete();
        testKiemTraHopLe();
        testSuDungVoucher();
        testThongKe();
    }
}
