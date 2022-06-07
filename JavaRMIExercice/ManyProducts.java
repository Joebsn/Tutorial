import java.rmi.*;
import java.util.List;

public interface ManyProducts extends Remote
{
    public double totalprice() throws RemoteException;
}
