package staff.models;

import staff.utils.JSONService;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by transpalette on 5/25/17.
 */
public class Order {

    private int id;
    private int user_id;
    private Address address;
    private Date created_at;
    private int waiting_time;
    private ArrayList<FoodItem> foodItems;

    public Order(int id, int user_id, String created_at, int address_id) {
        this.id = id;
        this.user_id = user_id;
        //this.created_at = new Date(created_at);
        foodItems = new ArrayList<>();

        if (address_id != 0)
            address = JSONService.getAddress(address_id);
        else
            address = null;
    }

    public void addItem(FoodItem item) {
        foodItems.add(item);
    }

    public void setWaitingTime(int waitingTime) {
        this.waiting_time = waitingTime;
    }

    public int getWaitingTime() {
        return waiting_time;
    }

    public void confirm() {
        //
    }

    public int getId() {
        return id;
    }

    public String getSummary() {
        String summary = "";

        for (FoodItem item : foodItems) {
            summary += item.getQuantity() + "x "
                    + item.getName() + " (" + item.getSize() + ") ";
        }

        return summary;
    }

    @Override
    public boolean equals(Object object) {
        boolean retVal = false;

        if (object instanceof Order){
            Order ptr = (Order) object;
            retVal = ptr.id == this.id;
        }

        return retVal;
    }
}
