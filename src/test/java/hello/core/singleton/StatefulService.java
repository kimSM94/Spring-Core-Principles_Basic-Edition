package hello.core.singleton;

public class StatefulService {


//    private int price; //상태를 유지하는 필드 10000 -> 20000

    /*  싱글톤 문제발생
    public void order(String name, int price){
        System.out.println("name = " + name + "price = " + price);
        this.price = price;
    }*/


    // 싱글톤 문제 해결.
    public int order(String name, int price){
        System.out.println("name = " + name + "price = " + price);
//        this.price = price;
        return price;
    }


 /*   public int getPrice(){
        return price;
    }*/
}