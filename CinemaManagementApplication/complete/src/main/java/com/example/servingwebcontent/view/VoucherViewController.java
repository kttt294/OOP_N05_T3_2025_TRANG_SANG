package com.example.servingwebcontent.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class VoucherViewController {
    @GetMapping("/voucher")
    public String voucherPage(Model model) {
        List<Voucher> dsVoucher = Arrays.asList(
            new Voucher(1, "KM50", 50),
            new Voucher(2, "KM30", 30)
        );
        model.addAttribute("dsVoucher", dsVoucher);
        return "voucher";
    }
    public static class Voucher {
        public int id; public String ma; public int giamGia;
        public Voucher(int id, String ma, int giamGia) { this.id = id; this.ma = ma; this.giamGia = giamGia; }
        public int getId() { return id; } public String getMa() { return ma; } public int getGiamGia() { return giamGia; }
    }
} 