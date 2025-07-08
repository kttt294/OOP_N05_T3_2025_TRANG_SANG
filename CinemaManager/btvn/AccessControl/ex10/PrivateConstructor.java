package btvn.AccessControl.ex10;

public class PrivateConstructor {
    private PrivateConstructor() {
        System.out.println("Secret constructor called!");
    }

    public void reveal() {
        System.out.println("This is a secret object.");
    }

    // Cho phép class khác cùng package khởi tạo (nếu cần)
    static PrivateConstructor create() {
        return new PrivateConstructor();
    }
}
