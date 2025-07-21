public class TestTune {
    public static void test() {
        CellPhone noiseMarker = new CellPhone();
        //ObnoxiousTune ot = new Tune();
        Tune t1 = new Tune();
        Tune t2 = new ObnoxiousTune();
        noiseMarker.ring((ObnoxiousTune)t1);
        System.out.println(" ");
        noiseMarker.ring((ObnoxiousTune)t2);
    }
}
