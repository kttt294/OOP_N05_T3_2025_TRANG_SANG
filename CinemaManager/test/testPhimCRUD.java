public class testPhimCRUD {
    public static void test(){
        PhimCRUD phimCRUD = new PhimCRUD();
        phimCRUD.createPhim();
        phimCRUD.readPhim();
        phimCRUD.filterPhim("Phim 1");
        phimCRUD.updatePhim();
        phimCRUD.deletePhim();
    }
}
