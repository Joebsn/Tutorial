import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client
{
    private static final int PORT = 4000;
    public static void main(String[] args) 
    {
        try 
        {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", PORT);
            // lookup the laptop object and assign in laptop variable
            Product laptop = (Product) registry.lookup("laptop");
            Product cellphone = (Product) registry.lookup("cellphone");
            ManyProducts products = (ManyProducts) registry.lookup("list");

            String name = laptop.getName();
            double price = laptop.getPrice();
            System.out.println("Name:" + name);
            System.out.println("Price: " + price);

            name = cellphone.getName();
            price = cellphone.getPrice();
            System.out.println("Name:" + name);
            System.out.println("Price: " + price);

            price = products.totalprice();
            System.out.println("Total Price: " + price);
           
        } catch (Exception e) {
           System.out.println("Exception in client side" + e);
        }
     }
}