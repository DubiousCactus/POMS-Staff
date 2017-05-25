package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by transpalette on 5/25/17.
 */
public class JSONService {

    public static ArrayList<Order> getNewOrders() {
        try {

            URL url = new URL("http://localhost:8000/api/orders/new");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.addRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImQyNjMyMzUxYmE1YzBmZjdjMzNjNDM1ZGM2ODc4MjZhZmU0ZDUxYmQzMmI2ZWE3ZThjZjUyYTAyMGE4NDQyMjFmNWM5ZTQxNjNiMmE1YWRiIn0.eyJhdWQiOiI0IiwianRpIjoiZDI2MzIzNTFiYTVjMGZmN2MzM2M0MzVkYzY4NzgyNmFmZTRkNTFiZDMyYjZlYTdlOGNmNTJhMDIwYTg0NDIyMWY1YzllNDE2M2IyYTVhZGIiLCJpYXQiOjE0OTU3MTY5NzgsIm5iZiI6MTQ5NTcxNjk3OCwiZXhwIjoxNTI3MjUyOTc4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.iQfjhXIFH8YN9LW6HPeqx3qRWyiklEP6nsBw9zj99nVH74Dv-CQ7M2GF1Ful-GFLri-Fb3cTCEjIuV1km_jFRHFkQvMhjbTQZbdaZH4-0EnKvwAXiHfNlOi5BJTe15n0lh1dYPs-iKNiriX9re_j7GS-a-5cVGeRuy0U9fvGLHgpTQUwBFHny2WnOMYjhNyznM3CeAOCGnJkxEqP638yu9eTl4UIbaujR0L-9byimx1EdWJU2hsZuxEjGOYWE_6yhbYo-ngtYnSr3KUd8hqHGlWY63RGxlSAyNOYnBnIE2f7CoiAxQkuzPJWh4_-jqApjVnGDLfVWMnHBTn2L5kasRv3U7IzMw3C9b875Ck1Nb1W8P-hxgJlY8pWi84dBqBF2reo8l2sKd8T92M55WrvX6NFVcEQmko4YO9hX6EDSiOWNN5NA3DYWcz6c9PdRM3f7WA-gHGmc_Emn6Rafdhp2UI7VQQanRKi4GvpkZ12mw9k6zXR_R2KC7rKR6HUndyEBvgCURo5ZV29I_CEEhocjs8WSQEUNsbhWs2rGGqRLKG7RgLYLeKRE6Wvv50ahCpmZ_6UMTpRsTd4HrpirUxR4QPoyLGxkBFF9JlkTLKh0QP0jZ3LjY1Tyqd3ndzGLPZKGiEr_P2jgHhJY6zcQAU0p0tirBrihd58ruAUDxj5JqY");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()
            ));

            String output;
            System.out.println("Output from Server .... \n");

            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }
}
