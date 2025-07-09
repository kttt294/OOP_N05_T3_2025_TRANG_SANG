package btvn.ReusingClasses.ex6;

public class BoardGame extends Game{
    BoardGame(int i){
        super(i);
        //không gọi super(i) sẽ dẫn tới lỗi biên dịch khi chạy ctrinh
        System.out.println("BoardGame Constructor");
        //super(i); //Nếu đặt super(i) ở đây sẽ xảy ra lỗi: must be the first statement
    }
}
