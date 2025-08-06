package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.servingwebcontent.model.Ve;
import com.example.servingwebcontent.util.DateTimeUtils;
import com.example.servingwebcontent.database.veAiven;

@Controller
public class VeController {

    @Autowired
    private veAiven veDB;
    
    // Web Controller Methods
    @GetMapping("/ve")
    public String vePage(Model model) {
        try {
            List<Ve> dsVe = veDB.getAllVe();
            model.addAttribute("dsVe", dsVe);
            model.addAttribute("ve", new Ve());
            return "ve";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách vé: " + e.getMessage());
            return "ve";
        }
    }
    
    @GetMapping("/ve/form")
    public String veFormPage(Model model) {
        model.addAttribute("ve", new Ve());
        return "form-ve";
    }
    
    @PostMapping("/ve/create")
    public String createVe(@ModelAttribute Ve ve, Model model) {
        try {
            if (veDB.createVe(ve)) {
                model.addAttribute("success", "Tạo vé thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi tạo vé!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/ve";
    }
    
    @PostMapping("/ve/update")
    public String updateVe(@RequestParam String maVe, @ModelAttribute Ve ve, Model model) {
        try {
            if (veDB.updateVe(maVe, ve)) {
                model.addAttribute("success", "Cập nhật vé thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi cập nhật vé!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/ve";
    }
    
    @PostMapping("/ve/delete")
    public String deleteVe(@RequestParam String maVe, Model model) {
        try {
            if (veDB.deleteVe(maVe)) {
                model.addAttribute("success", "Xóa vé thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi xóa vé!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/ve";
    }
    
    @PostMapping("/ve/cancel")
    public String cancelVe(@RequestParam String maVe, Model model) {
        try {
            if (huyVe(maVe)) {
                model.addAttribute("success", "Hủy vé thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi hủy vé!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/ve";
    }
    
    @GetMapping("/ve/search")
    public String searchVe(@RequestParam String keyword, @RequestParam String type, Model model) {
        try {
            List<Ve> results = new ArrayList<>();
            switch (type) {
                case "khachhang":
                    results = timVeTheoKhachHang(keyword);
                    break;
                case "suatchieu":
                    results = timVeTheoSuatChieu(keyword);
                    break;
                default:
                    Ve ve = timVeTheoMa(keyword);
                    if (ve != null) {
                        results.add(ve);
                    }
                    break;
            }
            model.addAttribute("dsVe", results);
            model.addAttribute("searchKeyword", keyword);
            model.addAttribute("searchType", type);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return "ve";
    }
    
    @GetMapping("/ve/statistics")
    public String veStatisticsPage(Model model) {
        try {
            thongKeVe();
            model.addAttribute("success", "Thống kê vé đã được hiển thị trong console!");
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thống kê: " + e.getMessage());
        }
        return "ve";
    }
    
    // Business Logic Methods
    public boolean taoVe(Ve ve) {
        try {
            if (ve == null || ve.getMaVe() == null || ve.getMaVe().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }
            if (ve.getCCCD() == null || ve.getCCCD().trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được để trống!");
            }
            if (ve.getMaSuatChieu() == null || ve.getMaSuatChieu().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }
            if (ve.getMaGhe() == null || ve.getMaGhe().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }
            if (ve.getGiaVe() <= 0) {
                throw new IllegalArgumentException("Giá vé phải lớn hơn 0!");
            }

            return veDB.createVe(ve);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean capNhatVe(String maVe, Ve veMoi) {
        try {
            if (maVe == null || maVe.trim().isEmpty() || veMoi == null) {
                throw new IllegalArgumentException("Mã vé và thông tin vé mới không được để trống!");
            }

            Ve veCu = veDB.getVeById(maVe);
            if (veCu == null) {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
                return false;
            }

            return veDB.updateVe(maVe, veMoi);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xoaVe(String maVe) {
        try {
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }

            Ve ve = veDB.getVeById(maVe);
            if (ve == null) {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
                return false;
            }

            return veDB.deleteVe(maVe);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xemThongTinVe(String maVe) {
        try {
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }

            Ve ve = veDB.getVeById(maVe);
            if (ve != null) {
                ve.hienThiThongTin();
                return true;
            } else {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xemTatCaVe() {
        try {
            List<Ve> danhSachVe = veDB.getAllVe();
            if (danhSachVe.isEmpty()) {
                System.out.println("Danh sách vé trống.");
            } else {
                System.out.println("Tổng số vé: " + danhSachVe.size());
                for (Ve ve : danhSachVe) {
                    ve.hienThiThongTin();
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean huyVe(String maVe) {
        try {
            if (maVe == null || maVe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã vé không được để trống!");
            }

            Ve ve = veDB.getVeById(maVe);
            if (ve == null) {
                System.out.println("Không tìm thấy vé với mã: " + maVe);
                return false;
            }

            if (ve.getTrangThai() != Ve.TrangThaiVe.CHUA_THANH_TOAN) {
                System.out.println("Vé không thể hủy (đã hủy hoặc đã sử dụng)!");
                return false;
            }

            ve.setTrangThai(Ve.TrangThaiVe.DA_HUY);
            return veDB.updateVe(maVe, ve);
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public double tinhTongDoanhThu() {
        try {
            Scanner scanner = new Scanner(System.in);
            LocalDateTime tuNgay = DateTimeUtils.nhapThoiGian(scanner, "Nhập thời gian bắt đầu");
            LocalDateTime denNgay = DateTimeUtils.nhapThoiGian(scanner, "Nhập thời gian kết thúc");

            double tong = 0;
            List<Ve> danhSachVe = veDB.getAllVe();
            for (Ve ve : danhSachVe) {
                if (ve.getTrangThai() == Ve.TrangThaiVe.DA_THANH_TOAN) {
                    tong += ve.getGiaVe(); // Bỏ điều kiện thời gian vì Ve không có trường thời gian đặt
                }
            }

            System.out.println("Tổng doanh thu từ " + DateTimeUtils.formatVietDateTime(tuNgay) +
                               " đến " + DateTimeUtils.formatVietDateTime(denNgay) +
                               " là: " + tong + " VND");
            return tong;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return 0.0;
        }
    }

    public boolean thongKeVe() {
        try {
            int chuaThanhToan = 0, daThanhToan = 0, daHuy = 0;

            List<Ve> danhSachVe = veDB.getAllVe();
            for (Ve ve : danhSachVe) {
                switch (ve.getTrangThai()) {
                    case CHUA_THANH_TOAN: chuaThanhToan++; break;
                    case DA_THANH_TOAN: daThanhToan++; break;
                    case DA_HUY: daHuy++; break;
                }
            }

            System.out.println("=== THỐNG KÊ VÉ ===");
            System.out.println("Chưa thanh toán: " + chuaThanhToan);
            System.out.println("Đã thanh toán: " + daThanhToan);
            System.out.println("Đã hủy: " + daHuy);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public Ve timVeTheoMa(String maVe) {
        try {
            if (maVe == null || maVe.trim().isEmpty()) return null;
            return veDB.getVeById(maVe);
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Ve> timVeTheoKhachHang(String CCCD) {
        ArrayList<Ve> ketQua = new ArrayList<>();
        try {
            if (CCCD == null || CCCD.trim().isEmpty()) return ketQua;

            List<Ve> danhSachVe = veDB.getAllVe();
            for (Ve ve : danhSachVe) {
                if (ve.getCCCD().equalsIgnoreCase(CCCD)) {
                    ketQua.add(ve);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
        }
        return ketQua;
    }

    public ArrayList<Ve> timVeTheoSuatChieu(String maSuatChieu) {
        ArrayList<Ve> ketQua = new ArrayList<>();
        try {
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) return ketQua;

            List<Ve> danhSachVe = veDB.getAllVe();
            for (Ve ve : danhSachVe) {
                if (ve.getMaSuatChieu().equalsIgnoreCase(maSuatChieu)) {
                    ketQua.add(ve);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
        }
        return ketQua;
    }
}
