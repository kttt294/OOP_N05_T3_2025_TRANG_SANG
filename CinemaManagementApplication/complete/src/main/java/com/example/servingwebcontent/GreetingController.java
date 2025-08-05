package com.example.servingwebcontent;

import com.example.servingwebcontent.model.KhachHang;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/khachhang")
public class GreetingController {

    // Hiển thị form thêm khách hàng
    @GetMapping("/them")
    public String hienThiForm(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "form-khachhang"; // trả về file form-khachhang.html
    }

    // 
    @PostMapping("/luu")
    public String luuKhachHang(@ModelAttribute KhachHang khachHang, Model model) {
        KhachHang.Create(khachHang);
        ArrayList<KhachHang> danhSach = KhachHang.Read();
        model.addAttribute("dsKH", danhSach);
        return "khachhang";
}


    // Hiển thị danh sách khách hàng
    @GetMapping("/danhsach")
    public String hienThiDanhSach(Model model) {
        ArrayList<KhachHang> danhSach = KhachHang.Read();
        model.addAttribute("dsKH", danhSach); // trùng với th:each trong khachhang.html
        return "khachhang";
    }
}
