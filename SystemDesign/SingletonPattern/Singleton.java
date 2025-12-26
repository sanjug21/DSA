package SingletonPattern;

public class Singleton {

// eager
//     private static Singleton s=new Singleton();
//    private Singleton(){

//    }
//    public static Singleton getSingleton(){
//     return s;
//    }



// non-synchronized 
//  private static Singleton instance;

//     private Singleton() {
//         }

//     public static Singleton getSingleton() {
//         if (instance == null) {
//             instance = new Singleton();
//         }
//         return instance;
//     }


// synchronized
    private static Singleton s;

    private Singleton() {
    }
    public static synchronized Singleton getSingleton(){
        if(s==null){
            synchronized(Singleton.class){
                if(s==null){
                    s=new Singleton();
                }
            }
        }
        return s;
    
    }
    


    
}
