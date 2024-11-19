package OOPS;

class Detail{
    String name;
    int age;
    public void infO()
    {
        System.out.println(this.name+" "+this.age);
    }
}
class Sam extends Detail{

}


public class oop
{

    public static void main(String args[]){
        Detail m1=new Detail();

        m1.name="Pushpendra Singh";
        m1.age=22;
        m1.infO();

}
}


