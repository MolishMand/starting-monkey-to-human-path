package RPIS41.Olukhov.wdad.learn.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by molish on 22.10.2016.
 */
public interface XmlDataManager extends Remote{

    public int earningsTotal(Officiant officicant, Calendar date) throws RemoteException;
    public void removeDay(Calendar date) throws RemoteException;
    public void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) throws RemoteException;
    public List<Order> getOrders(Calendar calendar) throws RemoteException;
    public Calendar lastOfficiantWorkDate(Officiant officiant) throws RemoteException;
}
