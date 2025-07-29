package com.example.servingwebcontent.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class DanhGiaViewController {
    @GetMapping("/danhgia")
    public String danhGiaPage(Model model) {
        List<DanhGia> dsDG = Arrays.asList(
            new DanhGia(1, "Nguyễn Văn A", "Hay lắm!", 5),
            new DanhGia(2, "Trần Thị B", "Bình thường", 3)
        );
        model.addAttribute("dsDG", dsDG);
        return "danhgia";
    }
    public static class DanhGia {
        public int id; public String tenKH; public String noiDung; public int diem;
        public DanhGia(int id, String tenKH, String noiDung, int diem) { this.id = id; this.tenKH = tenKH; this.noiDung = noiDung; this.diem = diem; }
        public int getId() { return id; } public String getTenKH() { return tenKH; } public String getNoiDung() { return noiDung; } public int getDiem() { return diem; }
    }
} 