package staff.models;

import java.util.Date;

/**
 * Created by transpalette on 5/28/17.
 */
public class Address {

    private int user_id;
    private String street;
    private String zip;
    private String city;

    public Address(int user_id, String street, String city) {
        this.user_id = user_id;
        this.street = street;
        this.city = city;
    }
}
