package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.database.hoaDonAiven;
import com.example.servingwebcontent.model.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HoaDonController {
    
    @Autowired
    private hoaDonAiven hoaDonDB;
    
    // === CRUD OPERATIONS ===
    
    @GetMapping("/hoadon")
    public String hienThiDanhSachHoaDon(Model model) {
        try {
            List<HoaDon> danhSachHoaDon = hoaDonDB.getAllHoaDon();
            model.addAttribute("danhSachHoaDon", danhSachHoaDon);
            model.addAttribute("tongSoHoaDon", danhSachHoaDon.size());
            System.out.println("Hiển thị danh sách hóa đơn: " + danhSachHoaDon.size() + " hóa đơn");
            return "hoadon";
        } catch (Exception e) {
            System.out.println("Lỗi hiển thị danh sách hóa đơn: " + e.getMessage());
            model.addAttribute("error", "Lỗi khi tải danh sách hóa đơn");
            return "hoadon";
        }
    }
    
    @GetMapping("/hoadon/create")
    public String hienThiFormTaoHoaDon(Model model) {
        model.addAttribute("hoaDon", new HoaDon());
        model.addAttribute("phuongThuc", HoaDon.phuongThuc.values());
        return "form-hoadon";
    }
    
    @PostMapping("/hoadon/create")
    public String taoHoaDon(@ModelAttribute HoaDon hoaDon, Model model) {
        try {
            // Validation dữ liệu đầu vào
            if (!kiemTraDuLieuHoaDon(hoaDon)) {
                model.addAttribute("error", "Dữ liệu không hợp lệ");
                model.addAttribute("phuongThuc", HoaDon.phuongThuc.values());
                return "form-hoadon";
            }
            
            // Tự động set thời gian thanh toán nếu chưa có
            if (hoaDon.getThoiGianThanhToan() == null) {
                hoaDon.setThoiGianThanhToan(LocalDateTime.now());
            }
            
            // Tạo hóa đơn
            boolean ketQua = hoaDonDB.createHoaDon(hoaDon);
            if (ketQua) {
                System.out.println("Tạo hóa đơn thành công: " + hoaDon.getMaHoaDon());
                return "redirect:/hoadon";
            } else {
                model.addAttribute("error", "Lỗi khi tạo hóa đơn");
                model.addAttribute("phuongThuc", HoaDon.phuongThuc.values());
                return "form-hoadon";
            }
        } catch (Exception e) {
            System.out.println("Lỗi tạo hóa đơn: " + e.getMessage());
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            model.addAttribute("phuongThuc", HoaDon.phuongThuc.values());
            return "form-hoadon";
        }
    }
    
    @GetMapping("/hoadon/edit/{maHoaDon}")
    public String hienThiFormSuaHoaDon(@PathVariable String maHoaDon, Model model) {
        try {
            HoaDon hoaDon = hoaDonDB.getHoaDonById(maHoaDon);
            if (hoaDon != null) {
                model.addAttribute("hoaDon", hoaDon);
                model.addAttribute("phuongThuc", HoaDon.phuongThuc.values());
                return "form-hoadon";
            } else {
                model.addAttribute("error", "Không tìm thấy hóa đơn");
                return "redirect:/hoadon";
            }
        } catch (Exception e) {
            System.out.println("Lỗi hiển thị form sửa hóa đơn: " + e.getMessage());
            model.addAttribute("error", "Lỗi hệ thống");
            return "redirect:/hoadon";
        }
    }
    
    @PostMapping("/hoadon/edit/{maHoaDon}")
    public String capNhatHoaDon(@PathVariable String maHoaDon, @ModelAttribute HoaDon hoaDon, Model model) {
        try {
            // Validation dữ liệu đầu vào
            if (!kiemTraDuLieuHoaDon(hoaDon)) {
                model.addAttribute("error", "Dữ liệu không hợp lệ");
                model.addAttribute("phuongThuc", HoaDon.phuongThuc.values());
                return "form-hoadon";
            }
            
            // Cập nhật hóa đơn
            boolean ketQua = hoaDonDB.updateHoaDon(maHoaDon, hoaDon);
            if (ketQua) {
                System.out.println("Cập nhật hóa đơn thành công: " + maHoaDon);
                return "redirect:/hoadon";
            } else {
                model.addAttribute("error", "Lỗi khi cập nhật hóa đơn");
                model.addAttribute("phuongThuc", HoaDon.phuongThuc.values());
                return "form-hoadon";
            }
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật hóa đơn: " + e.getMessage());
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            model.addAttribute("phuongThuc", HoaDon.phuongThuc.values());
            return "form-hoadon";
        }
    }
    
    @PostMapping("/hoadon/delete/{maHoaDon}")
    public String xoaHoaDon(@PathVariable String maHoaDon, Model model) {
        try {
            boolean ketQua = hoaDonDB.deleteHoaDon(maHoaDon);
            if (ketQua) {
                System.out.println("Xóa hóa đơn thành công: " + maHoaDon);
            } else {
                System.out.println("Lỗi khi xóa hóa đơn: " + maHoaDon);
            }
        } catch (Exception e) {
            System.out.println("Lỗi xóa hóa đơn: " + e.getMessage());
        }
        return "redirect:/hoadon";
    }
    
    // === SEARCH & FILTER OPERATIONS ===
    
    @GetMapping("/hoadon/search")
    public String timKiemHoaDon(@RequestParam(required = false) String CCCD, Model model) {
        try {
            if (CCCD != null && !CCCD.trim().isEmpty()) {
                List<HoaDon> ketQuaTimKiem = hoaDonDB.getHoaDonByCCCD(CCCD.trim());
                model.addAttribute("danhSachHoaDon", ketQuaTimKiem);
                model.addAttribute("ketQuaTimKiem", true);
                model.addAttribute("CCCDTimKiem", CCCD);
                System.out.println("Tìm kiếm hóa đơn theo CCCD: " + CCCD + " - Kết quả: " + ketQuaTimKiem.size());
            } else {
                List<HoaDon> danhSachHoaDon = hoaDonDB.getAllHoaDon();
                model.addAttribute("danhSachHoaDon", danhSachHoaDon);
            }
            model.addAttribute("tongSoHoaDon", model.getAttribute("danhSachHoaDon") != null ? 
                ((List<?>) model.getAttribute("danhSachHoaDon")).size() : 0);
            return "hoadon";
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm hóa đơn: " + e.getMessage());
            model.addAttribute("error", "Lỗi khi tìm kiếm hóa đơn");
            return "hoadon";
        }
    }
    
    // === STATISTICS OPERATIONS ===
    
    @GetMapping("/hoadon/thongke")
    public String hienThiThongKeHoaDon(Model model) {
        try {
            // Thống kê tổng quan
            int tongSoHoaDon = hoaDonDB.getTotalHoaDon();
            
            // Thống kê theo phương thức thanh toán
            List<HoaDon> tatCaHoaDon = hoaDonDB.getAllHoaDon();
            int tienMat = 0, chuyenKhoan = 0;
            int tongDoanhThu = 0;
            
            for (HoaDon hoaDon : tatCaHoaDon) {
                if (hoaDon.getphuongThuc() == HoaDon.phuongThuc.TIEN_MAT) {
                    tienMat++;
                } else if (hoaDon.getphuongThuc() == HoaDon.phuongThuc.CHUYEN_KHOAN) {
                    chuyenKhoan++;
                }
                tongDoanhThu += hoaDon.getTongTien();
            }
            
            // Thống kê theo tháng (3 tháng gần nhất)
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime thang1 = now.minusMonths(1);
            LocalDateTime thang2 = now.minusMonths(2);
            LocalDateTime thang3 = now.minusMonths(3);
            
            List<HoaDon> hoaDonThang1 = hoaDonDB.searchHoaDonByDateRange(thang1, now);
            List<HoaDon> hoaDonThang2 = hoaDonDB.searchHoaDonByDateRange(thang2, thang1);
            List<HoaDon> hoaDonThang3 = hoaDonDB.searchHoaDonByDateRange(thang3, thang2);
            
            model.addAttribute("tongSoHoaDon", tongSoHoaDon);
            model.addAttribute("tienMat", tienMat);
            model.addAttribute("chuyenKhoan", chuyenKhoan);
            model.addAttribute("tongDoanhThu", tongDoanhThu);
            model.addAttribute("hoaDonThang1", hoaDonThang1.size());
            model.addAttribute("hoaDonThang2", hoaDonThang2.size());
            model.addAttribute("hoaDonThang3", hoaDonThang3.size());
            
            System.out.println("Thống kê hóa đơn thành công");
            return "thongke-hoadon";
        } catch (Exception e) {
            System.out.println("Lỗi thống kê hóa đơn: " + e.getMessage());
            model.addAttribute("error", "Lỗi khi thống kê hóa đơn");
            return "thongke-hoadon";
        }
    }
    
    // === BUSINESS LOGIC METHODS ===
    
    private boolean kiemTraDuLieuHoaDon(HoaDon hoaDon) {
        // Kiểm tra mã hóa đơn
        if (hoaDon.getMaHoaDon() == null || hoaDon.getMaHoaDon().trim().isEmpty()) {
            System.out.println("Lỗi: Mã hóa đơn không được để trống");
            return false;
        }
        
        // Kiểm tra tổng tiền
        if (hoaDon.getTongTien() < 0) {
            System.out.println("Lỗi: Tổng tiền không được âm");
            return false;
        }
        
        // Kiểm tra CCCD
        if (hoaDon.getCCCD() == null || hoaDon.getCCCD().trim().isEmpty()) {
            System.out.println("Lỗi: CCCD không được để trống");
            return false;
        }
        
        // Kiểm tra phương thức thanh toán
        if (hoaDon.getphuongThuc() == null) {
            System.out.println("Lỗi: Phương thức thanh toán không được để trống");
            return false;
        }
        
        return true;
    }
    
    public boolean taoHoaDon(HoaDon hoaDon) {
        try {
            if (!kiemTraDuLieuHoaDon(hoaDon)) {
                return false;
            }
            
            // Tự động set thời gian thanh toán nếu chưa có
            if (hoaDon.getThoiGianThanhToan() == null) {
                hoaDon.setThoiGianThanhToan(LocalDateTime.now());
            }
            
            return hoaDonDB.createHoaDon(hoaDon);
        } catch (Exception e) {
            System.out.println("Lỗi tạo hóa đơn: " + e.getMessage());
            return false;
        }
    }
    
    public boolean capNhatHoaDon(String maHoaDon, HoaDon hoaDon) {
        try {
            if (!kiemTraDuLieuHoaDon(hoaDon)) {
                return false;
            }
            
            return hoaDonDB.updateHoaDon(maHoaDon, hoaDon);
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật hóa đơn: " + e.getMessage());
            return false;
        }
    }
    
    public boolean xoaHoaDon(String maHoaDon) {
        try {
            return hoaDonDB.deleteHoaDon(maHoaDon);
        } catch (Exception e) {
            System.out.println("Lỗi xóa hóa đơn: " + e.getMessage());
            return false;
        }
    }
    
    public boolean thongKeHoaDon() {
        try {
            int tongSoHoaDon = hoaDonDB.getTotalHoaDon();
            List<HoaDon> tatCaHoaDon = hoaDonDB.getAllHoaDon();
            
            int tienMat = 0, chuyenKhoan = 0;
            int tongDoanhThu = 0;
            
            for (HoaDon hoaDon : tatCaHoaDon) {
                if (hoaDon.getphuongThuc() == HoaDon.phuongThuc.TIEN_MAT) {
                    tienMat++;
                } else if (hoaDon.getphuongThuc() == HoaDon.phuongThuc.CHUYEN_KHOAN) {
                    chuyenKhoan++;
                }
                tongDoanhThu += hoaDon.getTongTien();
            }
            
            System.out.println("=== THỐNG KÊ HÓA ĐƠN ===");
            System.out.println("Tổng số hóa đơn: " + tongSoHoaDon);
            System.out.println("Tiền mặt: " + tienMat);
            System.out.println("Chuyển khoản: " + chuyenKhoan);
            System.out.println("Tổng doanh thu: " + tongDoanhThu + " VNĐ");
            System.out.println("=========================");
            
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi thống kê hóa đơn: " + e.getMessage());
            return false;
        }
    }
    
    public List<HoaDon> timHoaDonTheoCCCD(String CCCD) {
        try {
            if (CCCD == null || CCCD.trim().isEmpty()) {
                System.out.println("Lỗi: CCCD không được để trống");
                return null;
            }
            
            return hoaDonDB.getHoaDonByCCCD(CCCD.trim());
        } catch (Exception e) {
            System.out.println("Lỗi tìm hóa đơn theo CCCD: " + e.getMessage());
            return null;
        }
    }
    
    public List<HoaDon> timHoaDonTheoKhoangThoiGian(LocalDateTime fromDate, LocalDateTime toDate) {
        try {
            if (fromDate == null || toDate == null) {
                System.out.println("Lỗi: Thời gian không được để trống");
                return null;
            }
            
            if (fromDate.isAfter(toDate)) {
                System.out.println("Lỗi: Thời gian bắt đầu phải trước thời gian kết thúc");
                return null;
            }
            
            return hoaDonDB.searchHoaDonByDateRange(fromDate, toDate);
        } catch (Exception e) {
            System.out.println("Lỗi tìm hóa đơn theo khoảng thời gian: " + e.getMessage());
            return null;
        }
    }
}
