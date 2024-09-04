package learnAbstract;


public class finalDemo {
    int x=5;
    final int y=10;
    public finalDemo(int x,int y){
        this.x=x;
        // this.y=y;
    }
     public static void main(String[] args) {
        finalDemo demo=new finalDemo(20, 20);
        System.out.println(demo.x+" "+demo.y);
     }
}