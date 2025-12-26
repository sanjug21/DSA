
public class BasicPrinter implements MultiFunctionalDevice {

    @Override
    public void print() {
        System.out.println("Printing document...");
    }

    @Override
    public void scan() {
        // Not implemented, as this is a basic printer
        throw new UnsupportedOperationException("Scan functionality not supported by BasicPrinter.");
    }

    @Override
    public void fax() {
        // Not implemented, as this is a basic printer
        throw new UnsupportedOperationException("Fax functionality not supported by BasicPrinter.");
    }
    
}
