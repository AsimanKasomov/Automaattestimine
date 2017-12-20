package http;

import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Asiman on 23.10.2017.
 */
public class HttpUtility {
    public static HttpURLConnection makeHttpGetRequest(String requestUrl) throws IOException {
        String sURL = "https://api.openweathermap.org/data/2.5/forecast?q=Tallinn&units=metric&APPID=c2248ce1be70c38c1e89c712f76ef08e";
        URL url = new URL(sURL);
        return (HttpURLConnection) url.openConnection();
    }
}