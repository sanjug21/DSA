

public class classB {
    private String name;
    private int age;
    public classB(String name,int age){
        this.name=name;
        this.age=age;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name)throws Exception{
        if(name=="")throw new Exception("Invalid Name");
        this.name=name;
    }
    public  int getAge(){
        return this.age;
    }
    public void setAge(int age)throws Exception{
        if(age<0 || age>130){
            throw new Exception("Invalid age");
        }
        this.age=age;
    }
    
    public static void main(String[] args)  {
       classB s=new classB("Sanju", 23);
       try{
        s.setName("");
        s.setAge(10);
        System.out.println(s.name+" "+s.age);
       }catch(Exception e){
        System.out.println(e);
       }
       
    }
}
// exception handelling
// try and catch block
// try{

// }catch(Exception e){
//     System.out.println(e);
// }

