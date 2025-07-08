package btvn.AccessControl.ex10;

public class StaticMethod {
    public static PrivateConstructor getSecretInstance() {
        return PrivateConstructor.createInstance();
    }
}
