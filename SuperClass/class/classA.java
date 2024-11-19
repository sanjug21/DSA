

public class classA {
    private String name="Sanju";
    int age=23;
    classA(){

    }
    classA(String name,int age){
        this.name=name;
        this.age=age;
    }
    public String get(){
        return this.name;
    }
    public void set(String name){
        this.name=name;
    }
    public  int getAge(){
        return this.age;
    }
    public void setAge(int age){
        try{if(age<0 || age>130){
            throw new Exception("Invalid age");
        }
        this.age=age;
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    public static void main(String[] args) {
        classA s=new classA();
        s.setAge(-10);
        System.out.println(s.age);
       
    }
}
// when code run firstly variable initalized before constuctor

// class is a blueprint 
// object is the product of the class
// this key word is used to diiferntiate b/w local and global variable 

//access modifiers

// public => can be assesed from anywhere
// private => can be assessed in class
// default => can be assessed in any class from a package
// protected => can be assessed in a package

// encapsulation => we hide the data to not allow directly modification of data instead we use get and set function to access data