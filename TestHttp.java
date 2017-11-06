import java.net.HttpURLConnection;
import static org.junit.Assert.*;
import java.io.IOException;
import http.HttpUtility;
import org.junit.Test;

/**
 * Created by Asiman on 23.10.2017.
 */
public class TestHttp {
    @Test
    public void testHttpTest(){
        try {
            String requestUrl = "<API REQUEST URL>";
            HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
            assertEquals(con.getResponseCode(), 200);
        } catch (IOException e) {
            fail("IOException Error");
        }
    }

    @Test
    public void testHttpGetRequestIsNotNull() throws IOException {
        String requestUrl = "<API REQUEST URL>";
        assertNotNull(HttpUtility.makeHttpGetRequest(requestUrl));
    }
}