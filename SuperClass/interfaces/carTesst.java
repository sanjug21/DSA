package interfaces;

import java.util.Arrays;
import java.util.Comparator;

public class carTesst {
    public static void main(String[] args) {
        Car[] cars=new Car[5];
        cars[0]=new Car(1000, 100, "white");
        cars[1]=new Car(1900, 120, "Blue");
        cars[2]=new Car(1700, 130, "Black");
        cars[3]=new Car(2100, 140, "Red");
        cars[4]=new Car(3100, 150, "Grey");
       Arrays.sort(cars,new Comparator<Car>() {

        @Override
        public int compare(Car o1, Car o2) {
           return o1.price-o2.price;
        }

       
       });
     
        Display(cars);
    }
    public static void Display(Car[] cars){
        for(int i=0;i<cars.length;i++){
            System.out.println(cars[i].toString()+" ");
        }
    }
    public static <T extends Comparable<T>> void Bubble(T[] nums){
        int len=nums.length;
        boolean swapped=false;
        for(int i=0;i<len-1;i++){
            for(int j=0;j<len-i-1;j++){
                swapped=false;
                if(nums[j].compareTo(nums[j+1])==0){
                    T temp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=temp;
                    swapped=true;
                }
            }
            if(swapped==false)break;  
        }
   
    }
}
