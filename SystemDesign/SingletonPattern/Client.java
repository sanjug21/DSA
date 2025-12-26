package SingletonPattern;

public class Client {
    
     public static void main(String[] args) {
        // we had to limit the multiple object to one
        // Singleton instance1 =new Singleton();
        // Singleton instance2 =new Singleton();
        // Singleton instance3 =new Singleton();

        Singleton s1=Singleton.getSingleton();
        Singleton s2=Singleton.getSingleton();

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());



    }
}
