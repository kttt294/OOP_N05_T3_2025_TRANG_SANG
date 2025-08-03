public class testCollection {
        public static void test(){
        NameNumber obj1 = new NameNumber("abc", "123");
        NameNumber obj2 = new NameNumber("def", "163");
        NNCollection nn = new NNCollection();
        nn.insert(obj1);
        nn.insert(obj2);

        nn.print();

        //nn.findNumber("fffll");
        

    }
}

