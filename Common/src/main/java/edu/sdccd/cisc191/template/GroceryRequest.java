package edu.sdccd.cisc191.template;
import java.io.Serializable;
public class GroceryRequest implements Serializable {

    //Sean Standen - Peer Review
    //Made instance variables private so they can only
    //be accessed by getter and setter methods.
    private String category;
    private String name;
    public GroceryRequest(String category, String name) {
        this.category = category;
        this.name = name;
    }
    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
}