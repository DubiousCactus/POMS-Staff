package staff.models;

import java.util.Date;

/**
 * Created by transpalette on 5/28/17.
 */
public class Address {

    private int id;
    private int user_id;
    private String street;
    private String zip;
    private String city;
    private Date created_at;

    public Address(int id, int user_id, String street, String city, String created_at) {
        this.id = id;
        this.user_id = user_id;
        this.street = street;
        this.city = city;
        //this.created_at = new Date(created_at);
    }
}
