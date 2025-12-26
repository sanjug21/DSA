
public class DBA {
    private Database sq;
    public DBA(Database sq) {
        this.sq = sq;
    }

    public void save() {
        sq.save();
    }

    public static void main(String[] args) {
        DBA dba = new DBA(new Sql());
        DBA dba2= new DBA(new Nosql());
    }
}