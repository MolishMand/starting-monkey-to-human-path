package RPIS41.Olukhov.wdad.learn.rmi;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by molish on 29.10.2016.
 */
public class Order implements Serializable {
    private Officiant officiant;
    private List<Item> items;

    public Order(Officiant officiant, List<Item> items){
        this.officiant = officiant;
        this.items = items;
    }
    public Order(Officiant officiant){
        this(officiant, new LinkedList<Item>());
    }

    public Officiant getOfficiant(){return officiant;}
    public void addItem(Item item){items.add(item);}
    public Item getItem(int index){return items.get(index);}
    public int getCountItems(){return items.size();}
}
