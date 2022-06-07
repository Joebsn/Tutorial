import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List; 

public class Server 
{
    private static final int PORT = 4000;
    public static void main(String [] args)
    {
        try
        {
            System.out.println("Server is starting on port " + PORT);
            System.setProperty("java.rmi.server.hostname","127.0.0.1"); 

            ProductImpl product1 = new ProductImpl("Laptop", 1000);
            ProductImpl product2 = new ProductImpl("Cellphone", 700);
            Product stub1 = (Product) UnicastRemoteObject.exportObject(product1, 0);
            Product stub2 = (Product) UnicastRemoteObject.exportObject(product2, 0);

            List<Product> l = Arrays.asList(product1, product2);
            
            ManyProductsImpl many = new ManyProductsImpl(l);
            ManyProducts stub3 = (ManyProducts) UnicastRemoteObject.exportObject(many, 0);


            Registry registry = LocateRegistry.getRegistry("127.0.0.1", PORT);

            // Registered the exported object in rmi registry so that client can lookup in this registry and call the object methods.
            registry.rebind("laptop", stub1);
            registry.rebind("cellphone", stub2);
            registry.rebind("list", stub3);

        }
        catch (Exception e) 
		{
			System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
		}

    }
}
