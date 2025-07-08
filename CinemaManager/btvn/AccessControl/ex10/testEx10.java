package btvn.AccessControl.ex10;

public class testEx10 {
    public static void test() {
        PrivateConstructor secret = StaticMethod.getSecretInstance();
        secret.reveal();
    }
}
