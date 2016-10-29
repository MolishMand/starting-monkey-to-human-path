package RPIS41.Olukhov.wdad.learn.rmi;

import java.rmi.Remote;
import java.util.Calendar;
import java.util.List;

/**
 * Created by molish on 22.10.2016.
 */
public interface XmlDataManager extends Remote{

    public int earningsTotal(Officiant officicant, Calendar date);
    public void removeDay(Calendar date);
    public void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant);
    public List<Order> getOrders(Calendar calendar);
    public Calendar lastOfficiantWorkDate(Officiant officiant);
}
