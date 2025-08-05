package com.example.servingwebcontent.controller;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.servingwebcontent.model.SuatChieu;
import com.example.servingwebcontent.util.DateTimeUtils;
import com.example.servingwebcontent.database.suatChieuAiven;

@Controller
public class SuatChieuController {

    private suatChieuAiven suatChieuDB = new suatChieuAiven();
    
    // Web Controller Methods
    @GetMapping("/suatchieu")
    public String suatChieuPage(Model model) {
        try {
            List<SuatChieu> dsSC = suatChieuDB.getAllSuatChieu();
            model.addAttribute("dsSC", dsSC);
            model.addAttribute("suatChieu", new SuatChieu());
            return "suatchieu";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách suất chiếu: " + e.getMessage());
            return "suatchieu";
        }
    }
    
    @PostMapping("/suatchieu/create")
    public String createSuatChieu(@ModelAttribute SuatChieu suatChieu, Model model) {
        try {
            if (taoSuatChieu(suatChieu)) {
                model.addAttribute("success", "Tạo suất chiếu thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi tạo suất chiếu!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/suatchieu";
    }
    
    @PostMapping("/suatchieu/update")
    public String updateSuatChieu(@RequestParam String maSuatChieu, @ModelAttribute SuatChieu suatChieu, Model model) {
        try {
            if (capNhatSuatChieu(maSuatChieu, suatChieu)) {
                model.addAttribute("success", "Cập nhật suất chiếu thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi cập nhật suất chiếu!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/suatchieu";
    }
    
    @PostMapping("/suatchieu/delete")
    public String deleteSuatChieu(@RequestParam String maSuatChieu, Model model) {
        try {
            if (xoaSuatChieu(maSuatChieu)) {
                model.addAttribute("success", "Xóa suất chiếu thành công!");
            } else {
                model.addAttribute("error", "Lỗi khi xóa suất chiếu!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/suatchieu";
    }
    
    @GetMapping("/suatchieu/search")
    public String searchSuatChieu(@RequestParam String keyword, @RequestParam String type, Model model) {
        try {
            List<SuatChieu> results = new ArrayList<>();
            switch (type) {
                case "phim":
                    results = timSuatChieuTheoPhim(keyword);
                    break;
                case "phong":
                    results = timSuatChieuTheoPhong(keyword);
                    break;
                default:
                    SuatChieu sc = timSuatChieuTheoMa(keyword);
                    if (sc != null) {
                        results.add(sc);
                    }
                    break;
            }
            model.addAttribute("dsSC", results);
            model.addAttribute("searchKeyword", keyword);
            model.addAttribute("searchType", type);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return "suatchieu";
    }
    
    @GetMapping("/suatchieu/today")
    public String todaySuatChieuPage(Model model) {
        try {
            List<SuatChieu> allSuatChieu = suatChieuDB.getAllSuatChieu();
            List<SuatChieu> todaySuatChieu = new ArrayList<>();
            LocalDate today = LocalDate.now();
            
            for (SuatChieu sc : allSuatChieu) {
                if (sc.getThoiGianBatDau().toLocalDate().equals(today)) {
                    todaySuatChieu.add(sc);
                }
            }
            
            model.addAttribute("dsSC", todaySuatChieu);
            model.addAttribute("title", "Suất chiếu hôm nay");
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải suất chiếu hôm nay: " + e.getMessage());
        }
        return "suatchieu";
    }
    
    // Business Logic Methods
    public boolean hienThiSuatChieuTrongNgay(List<SuatChieu> danhSachSuatChieu) {
        try {
            if (danhSachSuatChieu == null) {
                throw new IllegalArgumentException("Danh sách suất chiếu không được null!");
            }

            Scanner scanner = new Scanner(System.in);
            LocalDateTime dateTime = DateTimeUtils.nhapThoiGian(scanner, "Nhập thời gian (lọc theo ngày)");
            LocalDate ngay = dateTime.toLocalDate();
            boolean tim = false;

            System.out.println("Danh sách suất chiếu trong ngày " + ngay + ":");
            for (SuatChieu sc : danhSachSuatChieu) {
                if (sc.getThoiGianBatDau().toLocalDate().equals(ngay)) {
                    sc.hienThiThongTin();
                    tim = true;
                }
            }

            if (!tim) {
                System.out.println("Không có suất chiếu nào trong ngày " + ngay);
            }

            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean taoSuatChieu(SuatChieu suatChieu) {
        try {
            if (suatChieu == null) {
                throw new IllegalArgumentException("Suất chiếu không được null!");
            }
            if (suatChieu.getMaSuatChieu() == null || suatChieu.getMaSuatChieu().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }
            if (suatChieu.getMaPhim() == null || suatChieu.getMaPhim().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }
            if (suatChieu.getMaPhong() == null || suatChieu.getMaPhong().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }
            if (suatChieu.getThoiGianBatDau() == null) {
                throw new IllegalArgumentException("Thời gian bắt đầu không được null!");
            }
            if (suatChieu.getThoiGianKetThuc() == null) {
                throw new IllegalArgumentException("Thời gian kết thúc không được null!");
            }
            if (suatChieu.getThoiGianBatDau().isAfter(suatChieu.getThoiGianKetThuc())) {
                throw new IllegalArgumentException("Thời gian bắt đầu phải trước thời gian kết thúc!");
            }

            return suatChieuDB.createSuatChieu(suatChieu);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean capNhatSuatChieu(String maSuatChieu, SuatChieu suatChieuMoi) {
        try {
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }
            if (suatChieuMoi == null) {
                throw new IllegalArgumentException("Thông tin suất chiếu mới không được null!");
            }

            SuatChieu suatChieuCu = suatChieuDB.getSuatChieuById(maSuatChieu);
            if (suatChieuCu == null) {
                System.out.println("Không tìm thấy suất chiếu với mã: " + maSuatChieu);
                return false;
            }

            return suatChieuDB.updateSuatChieu(maSuatChieu, suatChieuMoi);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xoaSuatChieu(String maSuatChieu) {
        try {
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }

            SuatChieu suatChieu = suatChieuDB.getSuatChieuById(maSuatChieu);
            if (suatChieu == null) {
                System.out.println("Không tìm thấy suất chiếu với mã: " + maSuatChieu);
                return false;
            }

            return suatChieuDB.deleteSuatChieu(maSuatChieu);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public boolean xemThongTinSuatChieu(String maSuatChieu) {
        try {
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }

            SuatChieu suatChieu = suatChieuDB.getSuatChieuById(maSuatChieu);
            if (suatChieu != null) {
                suatChieu.hienThiThongTin();
                return true;
            } else {
                System.out.println("Không tìm thấy suất chiếu với mã: " + maSuatChieu);
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

    public boolean xemTatCaSuatChieu() {
        try {
            List<SuatChieu> danhSachSuatChieu = suatChieuDB.getAllSuatChieu();
            if (danhSachSuatChieu.isEmpty()) {
                System.out.println("Danh sách suất chiếu trống.");
            } else {
                System.out.println("Tổng số suất chiếu: " + danhSachSuatChieu.size());
                for (SuatChieu sc : danhSachSuatChieu) {
                    sc.hienThiThongTin();
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return false;
        }
    }

    public SuatChieu timSuatChieuTheoMa(String maSuatChieu) {
        try {
            if (maSuatChieu == null || maSuatChieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã suất chiếu không được để trống!");
            }

            return suatChieuDB.getSuatChieuById(maSuatChieu);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<SuatChieu> timSuatChieuTheoPhim(String maPhim) {
        try {
            if (maPhim == null || maPhim.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phim không được để trống!");
            }

            List<SuatChieu> results = suatChieuDB.getSuatChieuByPhim(maPhim);
            return new ArrayList<>(results);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<SuatChieu> timSuatChieuTheoPhong(String maPhong) {
        try {
            if (maPhong == null || maPhong.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phòng không được để trống!");
            }

            List<SuatChieu> results = suatChieuDB.getSuatChieuByPhong(maPhong);
            return new ArrayList<>(results);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi dữ liệu đầu vào: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
