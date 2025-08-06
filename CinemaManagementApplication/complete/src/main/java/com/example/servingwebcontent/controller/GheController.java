package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import java.util.List;
import com.example.servingwebcontent.model.Ghe;
import com.example.servingwebcontent.database.gheAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class GheController {

    private gheAiven gheDB = new gheAiven();

    // Web Controller Methods
    @GetMapping("/ghe")
    public String ghePage(Model model) {
        try {
            List<Ghe> dsGhe = gheDB.getAllGhe();
            model.addAttribute("dsGhe", dsGhe);
            model.addAttribute("message", "");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi tải danh sách ghế: " + e.getMessage());
        }
        return "ghe";
    }
    
    @GetMapping("/ghe/form")
    public String gheFormPage(Model model) {
        model.addAttribute("ghe", new Ghe());
        return "form-ghe";
    }

    @PostMapping("/ghe/create")
    public String createGhe(@ModelAttribute Ghe ghe, Model model) {
        try {
            if (taoGhe(ghe)) {
                model.addAttribute("message", "Tạo ghế thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi tạo ghế!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/ghe";
    }

    @PostMapping("/ghe/update")
    public String updateGhe(@RequestParam String maGhe, @ModelAttribute Ghe ghe, Model model) {
        try {
            if (capNhatGhe(maGhe, ghe)) {
                model.addAttribute("message", "Cập nhật ghế thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi cập nhật ghế!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/ghe";
    }

    @PostMapping("/ghe/delete")
    public String deleteGhe(@RequestParam String maGhe, Model model) {
        try {
            if (xoaGhe(maGhe)) {
                model.addAttribute("message", "Xóa ghế thành công!");
            } else {
                model.addAttribute("message", "Lỗi khi xóa ghế!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/ghe";
    }

    @GetMapping("/ghe/search")
    public String searchGhe(@RequestParam String maGhe, Model model) {
        try {
            Ghe ghe = timGheTheoMa(maGhe);
            if (ghe != null) {
                model.addAttribute("dsGhe", List.of(ghe));
                model.addAttribute("message", "Kết quả tìm kiếm cho: " + maGhe);
            } else {
                model.addAttribute("dsGhe", List.of());
                model.addAttribute("message", "Không tìm thấy ghế với mã: " + maGhe);
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return "ghe";
    }

    // Business Logic Methods
    public boolean taoGhe(Ghe ghe) {
        try {
            if (ghe == null) {
                throw new IllegalArgumentException("Ghế không được null!");
            }
            if (ghe.getMaGhe() == null || ghe.getMaGhe().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }

            return gheDB.createGhe(ghe);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean capNhatGhe(String maGhe, Ghe gheMoi) {
        try {
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }
            if (gheMoi == null) {
                throw new IllegalArgumentException("Thông tin ghế mới không được null!");
            }

            Ghe gheCu = gheDB.getGheById(maGhe);
            if (gheCu == null) {
                System.out.println("Không tìm thấy ghế với mã: " + maGhe);
                return false;
            }

            return gheDB.updateGhe(maGhe, gheMoi);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xoaGhe(String maGhe) {
        try {
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }

            Ghe ghe = gheDB.getGheById(maGhe);
            if (ghe == null) {
                System.out.println("Không tìm thấy ghế với mã: " + maGhe);
                return false;
            }

            return gheDB.deleteGhe(maGhe);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xemThongTinGhe(String maGhe) {
        try {
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }

            Ghe ghe = gheDB.getGheById(maGhe);
            if (ghe != null) {
                System.out.println("=== THÔNG TIN GHẾ ===");
                System.out.println("Mã ghế: " + ghe.getMaGhe());
                System.out.println("Hàng: " + ghe.getHang());
                System.out.println("Cột: " + ghe.getCot());
                System.out.println("Mã phòng: " + ghe.getMaPhong());
                System.out.println("Mã suất chiếu: " + ghe.getMaSuatChieu());
                System.out.println("Trạng thái: " + ghe.getTrangThai());
                return true;
            } else {
                System.out.println("Không tìm thấy ghế với mã: " + maGhe);
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

    public boolean xemTatCaGhe() {
        try {
            List<Ghe> danhSach = gheDB.getAllGhe();
            if (danhSach.isEmpty()) {
                System.out.println("Không có ghế nào.");
                return false;
            }

            System.out.println("=== DANH SÁCH TẤT CẢ GHẾ ===");
            for (Ghe ghe : danhSach) {
                ghe.hienThiThongTin();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public Ghe timGheTheoMa(String maGhe) {
        try {
            if (maGhe == null || maGhe.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã ghế không được để trống!");
            }

            return gheDB.getGheById(maGhe);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }
}
