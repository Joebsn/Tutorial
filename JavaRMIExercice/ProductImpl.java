import java.rmi.*;
import java.rmi.server.*;
import java.util.jar.Attributes.Name;

public class ProductImpl implements Product
{
    public String Name;
    public double Price;

    public ProductImpl(String Name, double price)
    {
        this.Name = Name;
        this.Price = price;
    }
    
    public double getPrice() throws RemoteException
    {
        return Price;
    }

    public String getName() throws RemoteException
    {
        return Name;
    }
}
