public class Cycle implements Vehicle {
    @Override
    public boolean StartEngine() {
       throw new UnsupportedOperationException("Cycles do not have engines to start.");
    }

    @Override
    public int Speed() {
        
        return 15; 
    }
    
}
