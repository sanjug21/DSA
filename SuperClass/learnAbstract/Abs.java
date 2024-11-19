package learnAbstract;



public abstract class Abs {
    int amt=100000;
    Abs(){

    }
    public int viewBal(){
        return amt;
    }

    public abstract boolean payment();
}
