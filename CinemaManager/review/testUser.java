public class testUser {
    public static void test() {
        User user = new User();

        user.setTen("Nguyen Van A");
        user.setTuoi(25);
        user.setGioiTinh("Nam");
        user.setSDT("0123456789");
        user.setEmail("nguyenvana@example.com");

        System.out.println("Thông tin người dùng:");
        System.out.println("Tên: " + user.getTen());
        System.out.println("Tuổi: " + user.getTuoi());
        System.out.println("Giới tính: " + user.getGioiTinh());
        System.out.println("SĐT: " + user.getSDT());
        System.out.println("Email: " + user.getEmail());
    }
}
