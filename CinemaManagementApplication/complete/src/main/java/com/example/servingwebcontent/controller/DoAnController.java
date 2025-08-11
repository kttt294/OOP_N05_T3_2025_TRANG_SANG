package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import java.util.List;
import com.example.servingwebcontent.model.DoAn;
import com.example.servingwebcontent.database.doAnAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class DoAnController {
    
    private doAnAiven doAnDB = new doAnAiven();

    // Web Controller Methods
    @GetMapping("/doan")
    public String doAnPage(Model model) {
        try {
            List<DoAn> dsDoAn = doAnDB.getAllDoAn();
            model.addAttribute("dsDoAn", dsDoAn);
            model.addAttribute("message", "");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi tải danh sách đồ ăn: " + e.getMessage());
        }
        return "doan";
    }

    @GetMapping("/doan/form")
    public String hienThiFormTaoDoAn(Model model) {
        model.addAttribute("doAn", new DoAn());
        return "form-doan";
    }

    @PostMapping("/doan/create")
    public String createDoAn(@ModelAttribute DoAn doAn, Model model) {
        try {
            if (taoDoAn(doAn)) {
                model.addAttribute("message", "Tạo đồ ăn thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi tạo đồ ăn!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/doan";
    }

    @PostMapping("/doan/update")
    public String updateDoAn(@RequestParam String maDoAn, @ModelAttribute DoAn doAn, Model model) {
        try {
            if (capNhatDoAn(maDoAn, doAn)) {
                model.addAttribute("message", "Cập nhật đồ ăn thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi cập nhật đồ ăn!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/doan";
    }

    @PostMapping("/doan/delete")
    public String deleteDoAn(@RequestParam String maDoAn, Model model) {
        try {
            if (xoaDoAn(maDoAn)) {
                model.addAttribute("message", "Xóa đồ ăn thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi xóa đồ ăn!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/doan";
    }

    @GetMapping("/doan/search")
    public String searchDoAn(@RequestParam String keyword, Model model) {
        try {
            List<DoAn> results = timDoAnTheoTen(keyword);
            model.addAttribute("dsDoAn", results);
            model.addAttribute("message", "Kết quả tìm kiếm cho: " + keyword);
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return "doan";
    }

    @GetMapping("/doan/statistics")
    public String doAnStatisticsPage(Model model) {
        try {
            thongKeDoAn();
            model.addAttribute("message", "Thống kê đồ ăn đã được hiển thị trong console!");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi thống kê: " + e.getMessage());
        }
        return "doan";
    }

    // Business Logic Methods
    public boolean taoDoAn(DoAn doAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (doAn == null) {
                throw new IllegalArgumentException("Đồ ăn không được null!");
            }
            if (doAn.getMaDoAn() == null || doAn.getMaDoAn().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }
            if (doAn.getTenDoAn() == null || doAn.getTenDoAn().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên đồ ăn không được để trống!");
            }
            if (doAn.getGia() <= 0) {
                throw new IllegalArgumentException("Giá đồ ăn phải lớn hơn 0!");
            }
            if (doAn.getSoLuong() < 0) {
                throw new IllegalArgumentException("Số lượng không được âm!");
            }

            return doAnDB.createDoAn(doAn);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean capNhatDoAn(String maDoAn, DoAn doAnMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDoAn == null || maDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }
            if (doAnMoi == null) {
                throw new IllegalArgumentException("Thông tin đồ ăn mới không được null!");
            }

            // Kiểm tra đồ ăn có tồn tại không
            DoAn doAnCu = doAnDB.getDoAnById(maDoAn);
            if (doAnCu == null) {
                System.out.println("Không tìm thấy đồ ăn với mã: " + maDoAn);
                return false;
            }

            doAnMoi.setMaDoAn(maDoAn);
            return doAnDB.updateDoAn(maDoAn, doAnMoi);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xoaDoAn(String maDoAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDoAn == null || maDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }

            // Kiểm tra đồ ăn có tồn tại không
            DoAn doAn = doAnDB.getDoAnById(maDoAn);
            if (doAn == null) {
                System.out.println("Không tìm thấy đồ ăn với mã: " + maDoAn);
                return false;
            }

            return doAnDB.deleteDoAn(maDoAn);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xemThongTinDoAn(String maDoAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDoAn == null || maDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }

            DoAn doAn = doAnDB.getDoAnById(maDoAn);
            if (doAn != null) {
                System.out.println("=== THÔNG TIN ĐỒ ĂN ===");
                System.out.println("Mã đồ ăn: " + doAn.getMaDoAn());
                System.out.println("Tên đồ ăn: " + doAn.getTenDoAn());
                System.out.println("Giá: " + doAn.getGia() + " VNĐ");
                System.out.println("Số lượng còn lại: " + doAn.getSoLuong());
                return true;
            } else {
                System.out.println("Không tìm thấy đồ ăn với mã: " + maDoAn);
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xemTatCaDoAn() {
        try {
            List<DoAn> danhSach = doAnDB.getAllDoAn();
            if (danhSach.isEmpty()) {
                System.out.println("Không có đồ ăn nào.");
                return false;
            }

            System.out.println("=== DANH SÁCH TẤT CẢ ĐỒ ĂN ===");
            for (DoAn d : danhSach) {
                System.out.println("Mã: " + d.getMaDoAn() + " | Tên: " + d.getTenDoAn() + 
                    " | Giá: " + d.getGia() + " VNĐ | Số lượng: " + d.getSoLuong());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public DoAn timDoAnTheoMa(String maDoAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDoAn == null || maDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }

            return doAnDB.getDoAnById(maDoAn);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<DoAn> timDoAnTheoTen(String tenDoAn) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (tenDoAn == null || tenDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên đồ ăn không được để trống!");
            }

            List<DoAn> results = doAnDB.searchDoAnByTen(tenDoAn);
            return new ArrayList<>(results);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean capNhatSoLuong(String maDoAn, int soLuongMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maDoAn == null || maDoAn.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã đồ ăn không được để trống!");
            }
            if (soLuongMoi < 0) {
                throw new IllegalArgumentException("Số lượng không được âm!");
            }

            DoAn doAn = doAnDB.getDoAnById(maDoAn);
            if (doAn == null) {
                System.out.println("Không tìm thấy đồ ăn với mã: " + maDoAn);
                return false;
            }

            doAn.setSoLuong(soLuongMoi);
            return doAnDB.updateDoAn(maDoAn, doAn);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean thongKeDoAn() {
        try {
            List<DoAn> danhSach = doAnDB.getAllDoAn();
            if (danhSach.isEmpty()) {
                System.out.println("Không có đồ ăn để thống kê.");
                return false;
            }

            int tongSoDoAn = danhSach.size();
            int tongGia = 0;
            int tongSoLuong = 0;

            for (DoAn d : danhSach) {
                tongGia += d.getGia();
                tongSoLuong += d.getSoLuong();
            }

            System.out.println("=== THỐNG KÊ ĐỒ ĂN ===");
            System.out.println("Tổng số loại đồ ăn: " + tongSoDoAn);
            System.out.println("Tổng giá trị: " + tongGia + " VNĐ");
            System.out.println("Tổng số lượng: " + tongSoLuong);
            System.out.println("Giá trung bình: " + (tongGia / tongSoDoAn) + " VNĐ");
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }
} 