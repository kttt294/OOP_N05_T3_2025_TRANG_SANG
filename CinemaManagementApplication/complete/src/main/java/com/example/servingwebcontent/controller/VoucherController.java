package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.Voucher;
import com.example.servingwebcontent.database.voucherAiven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VoucherController {

    @Autowired
    private voucherAiven voucherDB;

    // Web Controller Methods
    @GetMapping("/voucher")
    public String voucherPage(Model model) {
        try {
            List<Voucher> dsVoucher = voucherDB.getAllVoucher();
            model.addAttribute("dsVoucher", dsVoucher);
            model.addAttribute("message", "");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi tải danh sách voucher: " + e.getMessage());
        }
        return "voucher";
    }

    @GetMapping("/voucher/form")
    public String hienThiFormTaoVoucher(Model model) {
        model.addAttribute("voucher", new Voucher());
        return "form-voucher";
    }

    @PostMapping("/voucher/create")
    public String createVoucher(@ModelAttribute Voucher voucher, Model model) {
        try {
            if (taoVoucher(voucher)) {
                model.addAttribute("message", "Tạo voucher thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi tạo voucher!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/voucher";
    }

    @PostMapping("/voucher/update")
    public String updateVoucher(@RequestParam String maVoucher, @ModelAttribute Voucher voucher, Model model) {
        try {
            if (voucherDB.updateVoucher(maVoucher, voucher)) {
                model.addAttribute("message", "Cập nhật voucher thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi cập nhật voucher!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/voucher";
    }

    @PostMapping("/voucher/delete")
    public String deleteVoucher(@RequestParam String maVoucher, Model model) {
        try {
            if (voucherDB.deleteVoucher(maVoucher)) {
                model.addAttribute("message", "Xóa voucher thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi xóa voucher!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/voucher";
    }

    @GetMapping("/voucher/search")
    public String searchVoucher(@RequestParam String keyword, Model model) {
        try {
            List<Voucher> results = timVoucherTheoTen(keyword);
            model.addAttribute("dsVoucher", results);
            model.addAttribute("message", "Kết quả tìm kiếm cho: " + keyword);
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return "voucher";
    }

    @GetMapping("/voucher/statistics")
    public String voucherStatisticsPage(Model model) {
        try {
            thongKeVoucher();
            model.addAttribute("message", "Thống kê voucher đã được hiển thị trong console!");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi thống kê: " + e.getMessage());
        }
        return "voucher";
    }

    @GetMapping("/voucher/view/{maVoucher}")
    public String viewVoucher(@PathVariable String maVoucher, Model model) {
        try {
            Voucher voucher = voucherDB.getVoucherById(maVoucher);
            if (voucher != null) {
                model.addAttribute("voucher", voucher);
                return "voucher-detail";
            } else {
                model.addAttribute("message", "Không tìm thấy voucher!");
                return "redirect:/voucher";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
            return "redirect:/voucher";
        }
    }

    @GetMapping("/voucher/edit/{maVoucher}")
    public String editVoucherPage(@PathVariable String maVoucher, Model model) {
        try {
            Voucher voucher = voucherDB.getVoucherById(maVoucher);
            if (voucher != null) {
                model.addAttribute("voucher", voucher);
                return "redirect:/voucher";
            } else {
                model.addAttribute("message", "Không tìm thấy voucher!");
                return "redirect:/voucher";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
            return "redirect:/voucher";
        }
    }

    // Business Logic Methods
    public boolean taoVoucher(Voucher voucher) {
        try {
            if (voucher == null || voucher.getMaVoucher() == null || voucher.getMaVoucher().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }
            if (voucher.getMoTa() == null || voucher.getMoTa().trim().isEmpty()) {
                throw new IllegalArgumentException("Mô tả voucher không được để trống!");
            }
            if (voucher.getPhanTramGiamGia() <= 0) {
                throw new IllegalArgumentException("Phần trăm giảm giá phải lớn hơn 0!");
            }
            if (voucher.getSoLuongConLai() == 0) {
                throw new IllegalArgumentException("Số lượng còn lại không được để trống!");
            }

            return voucherDB.createVoucher(voucher);
        } catch (Exception e) {
            System.out.println("Lỗi khi tạo voucher: " + e.getMessage());
            return false;
        }
    }

    public boolean capNhatVoucher(String maVoucher, Voucher voucherMoi) {
        try {
            if (maVoucher == null || maVoucher.trim().isEmpty() || voucherMoi == null) {
                throw new IllegalArgumentException("Dữ liệu đầu vào không hợp lệ!");
            }

            Voucher cu = voucherDB.getVoucherById(maVoucher);
            if (cu == null) {
                System.out.println("Không tìm thấy voucher với mã: " + maVoucher);
                return false;
            }

            return voucherDB.updateVoucher(maVoucher, voucherMoi);
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật voucher: " + e.getMessage());
            return false;
        }
    }

    public boolean xoaVoucher(String maVoucher) {
        try {
            if (maVoucher == null || maVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }

            Voucher v = voucherDB.getVoucherById(maVoucher);
            if (v == null) {
                System.out.println("Không tìm thấy voucher với mã: " + maVoucher);
                return false;
            }

            return voucherDB.deleteVoucher(maVoucher);
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa voucher: " + e.getMessage());
            return false;
        }
    }

    public boolean xemThongTinVoucher(String maVoucher) {
        try {
            if (maVoucher == null || maVoucher.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã voucher không được để trống!");
            }

            Voucher voucher = voucherDB.getVoucherById(maVoucher);
            if (voucher != null) {
                System.out.println("=== THÔNG TIN VOUCHER ===");
                System.out.println("Mã voucher: " + voucher.getMaVoucher());
                System.out.println("Mô tả: " + voucher.getMoTa());
                System.out.println("Phần trăm giảm giá: " + voucher.getPhanTramGiamGia() + "%");
                System.out.println("Ngày bắt đầu: " + voucher.getNgayBatDau());
                System.out.println("Ngày kết thúc: " + voucher.getNgayKetThuc());
                System.out.println("Số lượng còn lại: " + voucher.getSoLuongConLai());
                System.out.println("Trạng thái: " + voucher.getTrangThai());
                return true;
            } else {
                System.out.println("Không tìm thấy voucher với mã: " + maVoucher);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xem thông tin voucher: " + e.getMessage());
            return false;
        }
    }

    public boolean xemTatCaVoucher() {
        try {
            List<Voucher> danhSach = voucherDB.getAllVoucher();
            if (danhSach.isEmpty()) {
                System.out.println("Không có voucher nào.");
                return false;
            }

            System.out.println("=== DANH SÁCH TẤT CẢ VOUCHER ===");
            for (Voucher v : danhSach) {
                System.out.println("Mã: " + v.getMaVoucher() + " | Mô tả: " + v.getMoTa() + 
                    " | Giảm giá: " + v.getPhanTramGiamGia() + "% | Trạng thái: " + v.getTrangThai());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi xem danh sách voucher: " + e.getMessage());
            return false;
        }
    }

    public Voucher timVoucherTheoMa(String maVoucher) {
        try {
            if (maVoucher == null || maVoucher.trim().isEmpty()) return null;
            return voucherDB.getVoucherById(maVoucher);
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm voucher: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Voucher> timVoucherTheoTen(String keyword) {
        ArrayList<Voucher> ketQua = new ArrayList<>();
        try {
            if (keyword == null || keyword.trim().isEmpty()) return ketQua;

            List<Voucher> results = voucherDB.searchVoucherByMoTa(keyword);
            return new ArrayList<>(results);
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm kiếm voucher theo tên: " + e.getMessage());
            return ketQua;
        }
    }

    public boolean kiemTraVoucherHopLe(String maVoucher) {
        try {
            Voucher v = voucherDB.getVoucherById(maVoucher);
            if (v == null) return false;

            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(v.getNgayBatDau()) || now.isAfter(v.getNgayKetThuc())) return false;

            int sl = v.getSoLuongConLai();
            if (sl <= 0) return false;

            return "HoatDong".equalsIgnoreCase(v.getTrangThai());
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra voucher hợp lệ: " + e.getMessage());
            return false;
        }
    }

    public boolean suDungVoucher(String maVoucher) {
        try {
            Voucher v = voucherDB.getVoucherById(maVoucher);
            if (v == null || !kiemTraVoucherHopLe(maVoucher)) return false;

            int sl = v.getSoLuongConLai();
            sl--;
            v.setSoLuongConLai(sl);
            if (sl <= 0) v.setTrangThai("HetHang");

            return voucherDB.updateVoucher(maVoucher, v);
        } catch (Exception e) {
            System.out.println("Lỗi khi sử dụng voucher: " + e.getMessage());
            return false;
        }
    }

    public boolean thongKeVoucher() {
        try {
            List<Voucher> danhSach = voucherDB.getAllVoucher();
            if (danhSach.isEmpty()) {
                System.out.println("Không có voucher để thống kê.");
                return false;
            }

            int hoatDong = 0, hetHang = 0, hetHan = 0;
            int tongGiamGia = 0;
            LocalDateTime now = LocalDateTime.now();

            for (Voucher v : danhSach) {
                if ("HoatDong".equalsIgnoreCase(v.getTrangThai())) hoatDong++;
                else if ("HetHang".equalsIgnoreCase(v.getTrangThai())) hetHang++;
                if (now.isAfter(v.getNgayKetThuc())) hetHan++;
                tongGiamGia += v.getPhanTramGiamGia();
            }

            System.out.println("=== THỐNG KÊ VOUCHER ===");
            System.out.println("Voucher hoạt động: " + hoatDong);
            System.out.println("Voucher hết hàng: " + hetHang);
            System.out.println("Voucher hết hạn: " + hetHan);
            System.out.println("Tổng phần trăm giảm giá: " + tongGiamGia + "%");
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi thống kê voucher: " + e.getMessage());
            return false;
        }
    }
}
