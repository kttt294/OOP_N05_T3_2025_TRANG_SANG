package com.example.servingwebcontent.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.servingwebcontent.model.PhongChieu;
import com.example.servingwebcontent.database.phongChieuAiven;

@Controller
public class PhongChieuController{
    
    private phongChieuAiven phongChieuDB = new phongChieuAiven();
    
    // Web Controller Methods
    @GetMapping("/phongchieu")
    public String phongChieuPage(Model model) {
        try {
            List<PhongChieu> dsPC = phongChieuDB.getAllPhongChieu();
            model.addAttribute("dsPC", dsPC);
            model.addAttribute("phongChieu", new PhongChieu());
            return "phongchieu";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách phòng chiếu: " + e.getMessage());
            return "phongchieu";
        }
    }
    
    @GetMapping("/phongchieu/form")
    public String phongChieuFormPage(Model model) {
        model.addAttribute("phongChieu", new PhongChieu());
        return "form-phongchieu";
    }
    
    @PostMapping("/phongchieu/create")
    public String createPhongChieu(@ModelAttribute PhongChieu phongChieu, Model model) {
        try {
            if (taoPhongChieu(phongChieu)) {
                model.addAttribute("success", "Tạo phòng chiếu thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi tạo phòng chiếu!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/phongchieu";
    }
    
    @PostMapping("/phongchieu/update")
    public String updatePhongChieu(@RequestParam String maPhong, @ModelAttribute PhongChieu phongChieu, Model model) {
        try {
            if (capNhatPhongChieu(maPhong, phongChieu)) {
                model.addAttribute("success", "Cập nhật phòng chiếu thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi cập nhật phòng chiếu!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/phongchieu";
    }
    
    @PostMapping("/phongchieu/delete")
    public String deletePhongChieu(@RequestParam String maPhong, Model model) {
        try {
            if (xoaPhongChieu(maPhong)) {
                model.addAttribute("success", "Xóa phòng chiếu thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi xóa phòng chiếu!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/phongchieu";
    }
    
    @GetMapping("/phongchieu/search")
    public String searchPhongChieu(@RequestParam String keyword, @RequestParam String type, Model model) {
        try {
            List<PhongChieu> results = new ArrayList<>();
            switch (type) {
                case "ten":
                    results = timPhongChieuTheoTen(keyword);
                    break;
                default:
                    PhongChieu pc = timPhongChieuTheoMa(keyword);
                    if (pc != null) {
                        results.add(pc);
                    }
                    break;
            }
            model.addAttribute("dsPC", results);
            model.addAttribute("searchKeyword", keyword);
            model.addAttribute("searchType", type);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return "phongchieu";
    }
    
    // Business Logic Methods
    public boolean taoPhongChieu(PhongChieu phongChieu) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (phongChieu == null) {
                throw new IllegalArgumentException("Phòng chiếu không được null!");
            }
            if (phongChieu.getMaPhong() == null || phongChieu.getMaPhong().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }
            if (phongChieu.getTenPhong() == null || phongChieu.getTenPhong().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên phòng không được để trống!");
            }
            if (phongChieu.getSoHangGhe() <= 0) {
                throw new IllegalArgumentException("Số hàng phải lớn hơn 0!");
            }
            if (phongChieu.getSoCotGhe() <= 0) {
                throw new IllegalArgumentException("Số cột phải lớn hơn 0!");
            }

            return phongChieuDB.createPhongChieu(phongChieu);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean capNhatPhongChieu(String maPhong, PhongChieu phongChieuMoi) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }
            if (phongChieuMoi == null) {
                throw new IllegalArgumentException("Thông tin phòng chiếu mới không được null!");
            }

            // Kiểm tra phòng chiếu có tồn tại không
            PhongChieu phongChieuCu = phongChieuDB.getPhongChieuById(maPhong);
            if (phongChieuCu == null) {
                System.out.println("Không tìm thấy phòng chiếu với mã: " + maPhong);
                return false;
            }

            return phongChieuDB.updatePhongChieu(maPhong, phongChieuMoi);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xoaPhongChieu(String maPhong) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }

            // Kiểm tra phòng chiếu có tồn tại không
            PhongChieu phongChieu = phongChieuDB.getPhongChieuById(maPhong);
            if (phongChieu == null) {
                System.out.println("Không tìm thấy phòng chiếu với mã: " + maPhong);
                return false;
            }

            return phongChieuDB.deletePhongChieu(maPhong);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xemThongTinPhongChieu(String maPhong) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }

            PhongChieu phongChieu = phongChieuDB.getPhongChieuById(maPhong);
            if (phongChieu != null) {
                phongChieu.hienThiThongTin();
                return true;
            } else {
                System.out.println("Không tìm thấy phòng chiếu với mã: " + maPhong);
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

    public boolean xemTatCaPhongChieu() {
        try {
            List<PhongChieu> danhSachPhongChieu = phongChieuDB.getAllPhongChieu();
            if (danhSachPhongChieu.isEmpty()) {
                System.out.println("Danh sách phòng chiếu trống.");
            } else {
                System.out.println("Tổng số phòng chiếu: " + danhSachPhongChieu.size());
                for (PhongChieu pc : danhSachPhongChieu) {
                    pc.hienThiThongTin();
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public PhongChieu timPhongChieuTheoMa(String maPhong) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }

            return phongChieuDB.getPhongChieuById(maPhong);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<PhongChieu> timPhongChieuTheoTen(String tenPhong) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (tenPhong == null || tenPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên phòng không được để trống!");
            }

            List<PhongChieu> results = phongChieuDB.searchPhongChieuByTen(tenPhong);
            return new ArrayList<>(results);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean thongKePhongChieu() {
        try {
            List<PhongChieu> danhSachPhongChieu = phongChieuDB.getAllPhongChieu();
            System.out.println("=== THỐNG KÊ PHÒNG CHIẾU ===");
            System.out.println("Tổng số phòng chiếu: " + danhSachPhongChieu.size());
            
            int tongSoGhe = 0;
            for (PhongChieu pc : danhSachPhongChieu) {
                tongSoGhe += pc.getSoHangGhe() * pc.getSoCotGhe();
            }
            
            System.out.println("Tổng số ghế: " + tongSoGhe);
            System.out.println("=====================");
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }
} 