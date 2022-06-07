import java.rmi.*;
import java.rmi.server.*;
import java.util.List;

//public class ManyProductsImpl extends UnicastRemoteObject implements ManyProducts
public class ManyProductsImpl implements ManyProducts
{
    List<Product> l;
    ManyProductsImpl(List<Product> l) throws RemoteException
    { 
        this.l = l;
    }

    public double totalprice() throws RemoteException
    {
        double totalprice = 0;
        for(int i = 0; i < l.size(); i++) 
            totalprice += l.get(i).getPrice();
        return totalprice;
    }
}
