public class BreakAndContinue {
    public static void testFor(){
        for(int i=0; i<100; i++){
            if(i == 74) break;
            if(i%9 != 0) continue;
            System.out.println(i);
        }
    }

    public static void testWhile(){
        int i = 0;
        while(i<100){
            if(i == 74) break;
            if(i%9 != 0) continue;
            System.out.println(i);
            i++;
        }
    }

    public static void testDoWhile(){
        int i=0;
        do{
            if(i == 74) break;
            if(i%9 != 0) continue;
            System.out.println(i);
            i++;
        } while(i<100);
    }
}
