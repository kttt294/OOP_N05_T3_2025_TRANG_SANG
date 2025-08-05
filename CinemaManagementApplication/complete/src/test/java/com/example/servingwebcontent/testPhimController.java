package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Phim;
import java.util.ArrayList;

/**
 * Test class for PhimController functionality
 * This test uses the static methods from Phim class for simplicity
 */
public class testPhimController {

    /**
     * Mock PhimController that uses static Phim methods instead of database
     */
    public static class MockPhimController {
        
        public static boolean taoPhim(Phim phim) {
            try {
                if (phim == null) {
                    System.out.println("Lỗi: Phim không được null!");
                    return false;
                }
                if (phim.getMaPhim() == null || phim.getMaPhim().trim().isEmpty()) {
                    System.out.println("Lỗi: Mã phim không được để trống!");
                    return false;
                }
                if (phim.getTenPhim() == null || phim.getTenPhim().trim().isEmpty()) {
                    System.out.println("Lỗi: Tên phim không được để trống!");
                    return false;
                }
                if (phim.getThoiLuong() <= 0) {
                    System.out.println("Lỗi: Thời lượng phim phải lớn hơn 0!");
                    return false;
                }
                if (phim.getGioiHanTuoi() < 0 || phim.getGioiHanTuoi() > 25) {
                    System.out.println("Lỗi: Giới hạn tuổi không hợp lệ!");
                    return false;
                }
                
                Phim.Create(phim);
                return true;
            } catch (Exception e) {
                System.out.println("Lỗi hệ thống: " + e.getMessage());
                return false;
            }
        }

        public static boolean capNhatPhim(String maPhim, Phim phimMoi) {
            try {
                if (maPhim == null || maPhim.trim().isEmpty()) {
                    System.out.println("Lỗi: Mã phim không được để trống!");
                    return false;
                }
                if (phimMoi == null) {
                    System.out.println("Lỗi: Thông tin phim mới không được null!");
                    return false;
                }
                
                Phim phimCu = Phim.getPhimById(maPhim);
                if (phimCu == null) {
                    System.out.println("Không tìm thấy phim với mã: " + maPhim);
                    return false;
                }
                
                Phim.Update(maPhim, phimMoi);
                return true;
            } catch (Exception e) {
                System.out.println("Lỗi hệ thống: " + e.getMessage());
                return false;
            }
        }

        public static boolean xoaPhim(String maPhim) {
            try {
                if (maPhim == null || maPhim.trim().isEmpty()) {
                    System.out.println("Lỗi: Mã phim không được để trống!");
                    return false;
                }
                
                Phim phim = Phim.getPhimById(maPhim);
                if (phim == null) {
                    System.out.println("Không tìm thấy phim với mã: " + maPhim);
                    return false;
                }
                
                Phim.Delete(maPhim);
                return true;
            } catch (Exception e) {
                System.out.println("Lỗi hệ thống: " + e.getMessage());
                return false;
            }
        }

        public static boolean xemThongTinPhim(String maPhim) {
            try {
                if (maPhim == null || maPhim.trim().isEmpty()) {
                    System.out.println("Lỗi: Mã phim không được để trống!");
                    return false;
                }
                
                Phim phim = Phim.getPhimById(maPhim);
                if (phim != null) {
                    phim.hienThiThongTin();
                    return true;
                } else {
                    System.out.println("Không tìm thấy phim với mã: " + maPhim);
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Lỗi hệ thống: " + e.getMessage());
                return false;
            }
        }

        public static boolean xemTatCaPhim() {
            try {
                ArrayList<Phim> danhSachPhim = Phim.Read();
                if (danhSachPhim.isEmpty()) {
                    System.out.println("Danh sách phim trống.");
                } else {
                    System.out.println("Tổng số phim: " + danhSachPhim.size());
                    for (Phim phim : danhSachPhim) {
                        phim.hienThiThongTin();
                    }
                }
                return true;
            } catch (Exception e) {
                System.out.println("Lỗi hệ thống: " + e.getMessage());
                return false;
            }
        }

        public static Phim timPhimTheoMa(String maPhim) {
            try {
                if (maPhim == null || maPhim.trim().isEmpty()) {
                    System.out.println("Lỗi: Mã phim không được để trống!");
                    return null;
                }
                return Phim.getPhimById(maPhim);
            } catch (Exception e) {
                System.out.println("Lỗi hệ thống: " + e.getMessage());
                return null;
            }
        }

        public static ArrayList<Phim> timPhimTheoTen(String tenPhim) {
            try {
                if (tenPhim == null || tenPhim.trim().isEmpty()) {
                    System.out.println("Lỗi: Tên phim không được để trống!");
                    return new ArrayList<>();
                }
                return Phim.timKiemTheoTen(tenPhim);
            } catch (Exception e) {
                System.out.println("Lỗi hệ thống: " + e.getMessage());
                return new ArrayList<>();
            }
        }

        public static ArrayList<Phim> timPhimTheoTheLoai(String theLoai) {
            try {
                if (theLoai == null || theLoai.trim().isEmpty()) {
                    System.out.println("Lỗi: Thể loại không được để trống!");
                    return new ArrayList<>();
                }
                return Phim.timKiemTheoTheLoai(theLoai);
            } catch (Exception e) {
                System.out.println("Lỗi hệ thống: " + e.getMessage());
                return new ArrayList<>();
            }
        }

        public static boolean thongKePhim() {
            try {
                Phim.thongKePhim();
                return true;
            } catch (Exception e) {
                System.out.println("Lỗi hệ thống: " + e.getMessage());
                return false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== BẮT ĐẦU TEST PHIM CONTROLLER ===");
        test();
        System.out.println("=== KẾT THÚC TEST PHIM CONTROLLER ===");
    }

    public static void test() {
        testTaoPhim();
        testCapNhatPhim();
        testXoaPhim();
        testXemThongTinPhim();
        testXemTatCaPhim();
        testTimPhimTheoMa();
        testTimPhimTheoTen();
        testTimPhimTheoTheLoai();
        testThongKePhim();
    }

    public static void testTaoPhim() {
        System.out.println("\n=== TEST taoPhim ===");
        try {
            Phim phim = new Phim("PHIM001", "Inception", "Sci-Fi", 148, "English", 13, "Dream in a dream");
            boolean result = MockPhimController.taoPhim(phim);
            assert result : "Tạo phim thất bại";
            assert Phim.getPhimById("PHIM001") != null : "Không tìm thấy phim sau khi tạo";
            System.out.println("✓ Tạo phim OK");
        } catch (Exception e) {
            System.out.println("✗ Lỗi test tạo phim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testCapNhatPhim() {
        System.out.println("\n=== TEST capNhatPhim ===");
        try {
            Phim phimMoi = new Phim("PHIM001", "Inception 2", "Action", 150, "English", 16, "New sequel");
            boolean result = MockPhimController.capNhatPhim("PHIM001", phimMoi);
            assert result : "Cập nhật phim thất bại";
            
            Phim phim = Phim.getPhimById("PHIM001");
            assert phim != null : "Không tìm thấy phim sau khi cập nhật";
            assert "Inception 2".equals(phim.getTenPhim()) : "Tên phim không được cập nhật";
            assert phim.getThoiLuong() == 150 : "Thời lượng không được cập nhật";
            assert "Action".equals(phim.getTheLoai()) : "Thể loại không được cập nhật";
            System.out.println("✓ Cập nhật phim OK");
        } catch (Exception e) {
            System.out.println("✗ Lỗi test cập nhật phim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testXoaPhim() {
        System.out.println("\n=== TEST xoaPhim ===");
        try {
            boolean result = MockPhimController.xoaPhim("PHIM001");
            assert result : "Xóa phim thất bại";
            assert Phim.getPhimById("PHIM001") == null : "Phim vẫn tồn tại sau khi xóa";
            System.out.println("✓ Xóa phim OK");
        } catch (Exception e) {
            System.out.println("✗ Lỗi test xóa phim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testXemThongTinPhim() {
        System.out.println("\n=== TEST xemThongTinPhim ===");
        try {
            Phim phim = new Phim("PHIM002", "Avatar", "Fantasy", 162, "English", 10, "Blue people on Pandora");
            Phim.Create(phim);
            boolean result = MockPhimController.xemThongTinPhim("PHIM002");
            assert result : "Xem thông tin phim thất bại";
            System.out.println("✓ Xem thông tin phim OK");
        } catch (Exception e) {
            System.out.println("✗ Lỗi test xem thông tin phim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testXemTatCaPhim() {
        System.out.println("\n=== TEST xemTatCaPhim ===");
        try {
            Phim.Create(new Phim("PHIM003", "Titanic", "Romance", 195, "English", 12, "Ship sinks"));
            Phim.Create(new Phim("PHIM004", "Up", "Animation", 96, "English", 6, "Old man flies house"));
            boolean result = MockPhimController.xemTatCaPhim();
            assert result : "Xem tất cả phim thất bại";
            System.out.println("✓ Xem tất cả phim OK");
        } catch (Exception e) {
            System.out.println("✗ Lỗi test xem tất cả phim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testTimPhimTheoMa() {
        System.out.println("\n=== TEST timPhimTheoMa ===");
        try {
            Phim phim = new Phim("PHIM005", "The Matrix", "Sci-Fi", 136, "English", 16, "Simulation world");
            Phim.Create(phim);
            Phim found = MockPhimController.timPhimTheoMa("PHIM005");
            assert found != null : "Không tìm thấy phim theo mã";
            assert "The Matrix".equals(found.getTenPhim()) : "Tên phim không khớp";
            System.out.println("✓ Tìm phim theo mã OK");
        } catch (Exception e) {
            System.out.println("✗ Lỗi test tìm phim theo mã: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testTimPhimTheoTen() {
        System.out.println("\n=== TEST timPhimTheoTen ===");
        try {
            Phim.Create(new Phim("PHIM006", "Avengers", "Action", 143, "English", 13, "Superhero team"));
            ArrayList<Phim> list = MockPhimController.timPhimTheoTen("Avengers");
            assert list != null : "Kết quả tìm kiếm null";
            assert !list.isEmpty() : "Không tìm thấy phim theo tên";
            System.out.println("✓ Tìm phim theo tên OK");
        } catch (Exception e) {
            System.out.println("✗ Lỗi test tìm phim theo tên: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testTimPhimTheoTheLoai() {
        System.out.println("\n=== TEST timPhimTheoTheLoai ===");
        try {
            Phim.Create(new Phim("PHIM007", "Iron Man", "Action", 126, "English", 13, "Billionaire superhero"));
            ArrayList<Phim> list = MockPhimController.timPhimTheoTheLoai("Action");
            assert list != null : "Kết quả tìm kiếm null";
            assert list.size() > 0 : "Không tìm thấy phim theo thể loại";
            System.out.println("✓ Tìm phim theo thể loại OK");
        } catch (Exception e) {
            System.out.println("✗ Lỗi test tìm phim theo thể loại: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testThongKePhim() {
        System.out.println("\n=== TEST thongKePhim ===");
        try {
            boolean result = MockPhimController.thongKePhim();
            assert result : "Thống kê phim thất bại";
            System.out.println("✓ Thống kê phim OK");
        } catch (Exception e) {
            System.out.println("✗ Lỗi test thống kê phim: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
