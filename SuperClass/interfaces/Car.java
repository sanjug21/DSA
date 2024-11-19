package interfaces;

public class Car {
    int price;
    int speed;
    String color;
    Car( int price,int speed,String color){
        this.price=price;
        this.color=color;
        this.speed=speed;
    }
    @Override 
    public String toString(){
        return "price: "+price+" speed: "+speed+" color: "+color;
    }
   
}
