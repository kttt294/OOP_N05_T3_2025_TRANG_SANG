public class NameNumber {
    public String lastName;
    public String telNumber;
    
    public NameNumber(){}
    public NameNumber(String name, String number){
        lastName = name;
        telNumber = number;
    }

    public void setName(String name){
        lastName = name;
    }

    public String getName(){
        return lastName;
    }

    public void setNumber(String number){
        telNumber = number;
    }

    public String getNumber(){
        return telNumber;
    }
}
