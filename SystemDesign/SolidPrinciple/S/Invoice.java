
public class Invoice {
    Pen p;

    public Invoice(Pen p) {
        this.p = p;
    }
    public double CreateInvoice() {
        return p.price * 1.18+2; 
    }
    // public void PrintInvoice() {
    //     System.out.println("Invoice print kiya");
        
    // }   
    // public void SendMail() {
    //     System.out.println("Mail send kiya");
    // }
}
