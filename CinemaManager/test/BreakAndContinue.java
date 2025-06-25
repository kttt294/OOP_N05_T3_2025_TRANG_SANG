public class BreakAndContinue {
    public static void testFor(){
        for(int i=0; i<100; i++){
            if(i == 74) break;
            if(i%9 != 0) continue;
            System.out.println(i);
        }
    }

    public static void testWhile(){
        int j = 0;
        while(j<100){
            if(j == 74) break;
            if(j%9 != 0) {
                j++;
                continue;
            }
            System.out.println(j);
            j++;
        }
    }

    public static void testDoWhile(){
        int k=0;
        do{
            if(k == 74) break;
            if(k%9 != 0) {
                k++;
                continue;
            }
            System.out.println(k);
            k++;
        } while(k<100);
    }
}
