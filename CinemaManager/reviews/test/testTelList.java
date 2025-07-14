public class testTelList {

    public void test(){

        NameNumber obj1 = new NameNumber("abc", "123");
        NameNumber obj2 = new NameNumber("def", "163");
        TelList tl = new TelList();
        tl.ListOfTel(obj1);
        tl.ListOfTel(obj2);

        System.out.println(tl);

    }
    
}
