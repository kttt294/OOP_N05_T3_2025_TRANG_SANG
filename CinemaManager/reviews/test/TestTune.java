public class TestTune {
    public static void test() {
        CellPhone noiseMarker = new CellPhone();
        //ObnoxiousTune ot = new Tune();
        Tune t1 = new Tune();
        Tune t2 = new ObnoxiousTune();
        noiseMarker.ring(t1);
        System.out.println(" ");
        noiseMarker.ring((Tune)t2);
    }
}
