package mock;

import org.json.JSONObject;
import org.junit.Test;
import java.io.IOException;
import java.util.Date;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Asiman. Created on 12.01.2018.
 */
public class ApiTest {
    @Test
    public void testMocAPI() throws IOException {
        RequestMaker requestMaker = mock(RequestMaker.class);
        WeatherForecastService weatherForecastService = new WeatherForecastService();
        weatherForecastService.setMaker(requestMaker);
        DataWriterRepository dataWriter = new DataWriterRepository();
        WeatherForecast response = new WeatherForecast();
        response.setCity("Tallinn");
        response.setCountry("EE");
        response.setCoordinates("0.000000;0.000000");
        response.setDate(new Date());
        when(requestMaker.makeRequest(any(), anyString())).thenReturn(new JSONObject(response));
        dataWriter.writeDataToFile("Tallinn.txt", response.toString());
    }
}