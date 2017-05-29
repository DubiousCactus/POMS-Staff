package staff.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import staff.models.Address;
import staff.models.FoodItem;
import staff.models.Order;


/**
 * Created by transpalette on 5/25/17.
 */
public class JSONService {

    /* Personal access token generated on the server side */
    private static final String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImQyNj"
        + "MyMzUxYmE1YzBmZjdjMzNjNDM1ZGM2ODc4MjZhZmU0ZDUxYmQzMmI2ZWE3ZThjZjUyYTAyMGE4NDQyMjFmNWM5ZT"
        + "QxNjNiMmE1YWRiIn0.eyJhdWQiOiI0IiwianRpIjoiZDI2MzIzNTFiYTVjMGZmN2MzM2M0MzVkYzY4NzgyNmFmZT"
        + "RkNTFiZDMyYjZlYTdlOGNmNTJhMDIwYTg0NDIyMWY1YzllNDE2M2IyYTVhZGIiLCJpYXQiOjE0OTU3MTY5NzgsIm5"
        + "iZiI6MTQ5NTcxNjk3OCwiZXhwIjoxNTI3MjUyOTc4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.iQfjhXIFH8YN9LW6"
        + "HPeqx3qRWyiklEP6nsBw9zj99nVH74Dv-CQ7M2GF1Ful-GFLri-Fb3cTCEjIuV1km_jFRHFkQvMhjbTQZbdaZH4-0"
        + "EnKvwAXiHfNlOi5BJTe15n0lh1dYPs-iKNiriX9re_j7GS-a-5cVGeRuy0U9fvGLHgpTQUwBFHny2WnOMYjhNyznM3"
        + "CeAOCGnJkxEqP638yu9eTl4UIbaujR0L-9byimx1EdWJU2hsZuxEjGOYWE_6yhbYo-ngtYnSr3KUd8hqHGlWY63RGxl"
        + "SAyNOYnBnIE2f7CoiAxQkuzPJWh4_-jqApjVnGDLfVWMnHBTn2L5kasRv3U7IzMw3C9b875Ck1Nb1W8P-hxgJlY8pWi84"
        + "dBqBF2reo8l2sKd8T92M55WrvX6NFVcEQmko4YO9hX6EDSiOWNN5NA3DYWcz6c9PdRM3f7WA-gHGmc_Emn6Rafdhp2UI7V"
        + "QQanRKi4GvpkZ12mw9k6zXR_R2KC7rKR6HUndyEBvgCURo5ZV29I_CEEhocjs8WSQEUNsbhWs2rGGqRLKG7RgLYLeKRE6Wv"
        + "v50ahCpmZ_6UMTpRsTd4HrpirUxR4QPoyLGxkBFF9JlkTLKh0QP0jZ3LjY1Tyqd3ndzGLPZKGiEr_P2jgHhJY6zcQAU0p0tirBrihd58ruAUDxj5JqY";

    private static final String baseURL = "http://localhost:8000/api";

    public static Address getAddress(int id) {
        Address address = null;

        try {

            URL url = new URL(baseURL.concat("/addresses/" + id));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.addRequestProperty("Authorization", token);

            if (conn.getResponseCode() != 200)
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()
            ));

            JSONObject obj = new JSONObject(br.readLine());
            address = new Address(
                    obj.getInt("user_id"),
                    obj.getString("street"),
                    obj.getString("city")
            );

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return address;
    }

    public static ArrayList<Order> getNewOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        try {

            URL url = new URL(baseURL.concat("/orders/new"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.addRequestProperty("Authorization", token);

            if (conn.getResponseCode() != 200)
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()
            ));

            JSONArray obj = new JSONArray(br.readLine());
            for (int i = 0; i < obj.length(); i++) {
                JSONObject orderJson = obj.getJSONObject(i);

                Order order = new Order(
                        orderJson.getInt("id"),
                        orderJson.getInt("user_id"),
                        orderJson.getString("created_at"),
                        (orderJson.isNull("address_id") ? 0 : orderJson.getInt("address_id"))
                );

                JSONArray items = orderJson.getJSONArray("items");
                for (int j = 0; j < items.length(); j++) {
                    JSONObject foodItem = items.getJSONObject(j);
                    JSONArray toppingsArray = foodItem.getJSONArray("toppings");
                    String[] toppings = new String[toppingsArray.length()];

                    for (int k = 0; k < toppingsArray.length(); k++) {
                        toppings[k] = toppingsArray.getString(k);
                    }

                    order.addItem(new FoodItem(
                            foodItem.getString("name"),
                            foodItem.getString("ingredients"),
                            foodItem.getInt("quantity"),
                            foodItem.getString("size"),
                            toppings
                    ));
                }

                orders.add(order);
            }

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return orders;
    }

    public static void confirmOrder(Order order) {
        try {

            URL url = new URL(baseURL.concat("/orders/" + order.getId() + "/confirm"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            conn.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.addRequestProperty("Authorization", token);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject form = new JSONObject();
            form.put("confirmed", true);
            form.put("waiting_time", order.getWaitingTime());

            OutputStream os = conn.getOutputStream();
            os.write(form.toString().getBytes("UTF-8"));
            os.close();

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
