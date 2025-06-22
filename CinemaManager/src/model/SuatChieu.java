package model;

import java.time.LocalDateTime;
import java.util.List;

public class SuatChieu{
    String maSuatChieu;
    Phim phim;
    PhongChieu phongChieu;
    LocalDateTime thoiGianBatDau;
    LocalDateTime thoiGianKetThuc;
    List<Ghe> danhSachGheTrong;
}