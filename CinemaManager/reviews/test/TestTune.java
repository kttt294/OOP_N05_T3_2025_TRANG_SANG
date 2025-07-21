public class TestTune {
    public static void test() {
        CellPhone noiseMarker = new CellPhone();
        //ObnoxiousTune ot = new Tune();
        Tune ot = new ObnoxiousTune();
        noiseMarker.ring(ot);
    }
}
