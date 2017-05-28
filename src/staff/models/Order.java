package staff.models;

import staff.utils.JSONService;

import java.util.Date;

/**
 * Created by transpalette on 5/25/17.
 */
public class Order {

    private int id;
    private int user_id;
    private boolean processed;
    private int waiting_time;
    private Date created_at;
    private int address_id;
    private boolean confirmed;

    public Order(int id, int user_id, boolean processed, int waiting_time, String created_at, int address_id, boolean confirmed) {
        this.id = id;
        this.user_id = user_id;
        this.processed = processed;
        this.waiting_time = waiting_time;
        //this.created_at = new Date(created_at);
        this.address_id = address_id;
        this.confirmed = confirmed;
    }

    public void setWaitingTime(int waitingTime) {
        this.waiting_time = waitingTime;
    }

    public void confirm() {
        this.confirmed = true;
    }

    public Address getAddress() {
        return JSONService.getAddress(this.address_id);
    }
}
