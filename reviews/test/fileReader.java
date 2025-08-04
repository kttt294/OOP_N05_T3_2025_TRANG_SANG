import java.io.FileInputStream;
import java.io.File;

public class fileReader {
    public static void test() {
        FIle f = new FIle("test.txt");
        try{
            FileInputStream fis = new FileInputStream(f);
            int c;
            while ((c = fis.read()) != -1) {
                System.out.print((char) c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
