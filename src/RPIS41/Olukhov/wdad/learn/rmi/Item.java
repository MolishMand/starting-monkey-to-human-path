package RPIS41.Olukhov.wdad.learn.rmi;

import java.io.Serializable;

/**
 * Created by molish on 29.10.2016.
 */
public class Item implements Serializable{

    private String name;
    private int cost;

    public Item(String name, int cost){
        this.name = name;
        this.cost = cost;
    }

    public String getName(){return name;}
    public int getCost(){return cost;}
}
