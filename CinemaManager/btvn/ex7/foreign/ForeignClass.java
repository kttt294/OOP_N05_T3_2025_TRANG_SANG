package btvn.ex7.foreign;

import btvn.ex7.access.local.*;

public class ForeignClass {
    public static void main(String[] args) {
        // new PackagedClass();  // Lỗi, vì không thẻ truy cập class không phải public từ package khác
        Library.usePackagedClass();  // Gọi được vì Library là public
    }
}