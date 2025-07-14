package reviews.model;

public class NNCollection {
    private NameNumber[] nnArray = new NameNumber[100];
    private int free;
    public NNCollection() {
        free = 0;
    }
    //Phuong thuc
    public void insert(NameNumber n){
        int index = 0;
        for(int i = free++; i!=0 && nnArray[i-1].getName().compareTo(n.getName()) < 0; i--){
            nnArray[i] = nnArray[i-1];
            index = i;
        }
        nnArray[index] = n;
    }

    public String findNumber(String lName){
        for(int i = 0; i < free; i++){
            if(nnArray[i].getName().equals(lName)){
                return nnArray[i].getNumber();
            }
        }
        return new String("Name not found");
    }
}
