public class testOuterInner {
    public static void test() {
        OuterClassTest outer = new OuterClassTest();
        OuterClassTest.InnerClassTest inner = outer.new InnerClassTest();
        System.out.println("hello world");
    }
}
