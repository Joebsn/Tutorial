import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Product extends Remote
{
    public double getPrice() throws RemoteException;
    public String getName() throws RemoteException;
}
