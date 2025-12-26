
public class Client {
    public static void main(String[] args) {
        Pen p = new Pen();
        Invoice i = new Invoice(p);
        System.out.println("Invoice Amount: " + i.CreateInvoice());
        // i.PrintInvoice();
        // i.SendMail();
        // SendNotification s= new SendNotification();
        // s.SendMessage(i);

        SendNotification s1 = new SendNotification();
        SendNotification s2 = new sendSMS();
        
    }
}
