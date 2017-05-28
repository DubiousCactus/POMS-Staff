package staff.models;

import staff.utils.JSONService;

import java.util.Date;

/**
 * Created by transpalette on 5/25/17.
 */
public class Order {

    private int user_id;
    private Address address;
    private Date created_at;
    private int waiting_time;

    public Order(int user_id, String created_at, int address_id) {
        this.user_id = user_id;
        //this.created_at = new Date(created_at);

        if (address_id != 0)
            address = JSONService.getAddress(address_id);
        else
            address = null;
    }

    public void setWaitingTime(int waitingTime) {
        this.waiting_time = waitingTime;
    }

    public void confirm() {
        //
    }
}
