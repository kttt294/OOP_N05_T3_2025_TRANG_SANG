import java.util.ArrayList;
public class TelList {

    public ArrayList<NameNumber> tl = new ArrayList<NameNumber>();

    //Create
    public void ListOfTel(NameNumber n){

        tl.add(n);



    }
    public void In(){
        for(int i=0; i<tl.size(); i++)
        {
        System.out.println("ten: " + tl.get(i).lastName);

        }
    }
    
}
