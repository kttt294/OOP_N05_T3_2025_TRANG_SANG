public class OuterClassTest {
    int var1 = 1;
    public class InnerClassTest{
        public InnerClassTest(){
            System.out.println("var1" + OuterClassTest.this.var1);
        }
    }
}
