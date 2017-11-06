import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.math.BigDecimal;
import java.io.IOException;
import data.WeatherData;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * Created by Asiman on 23.10.2017.
 */
public class TestWeatherData {
    private WeatherData weatherData;

    @Before
    public void setup() throws IOException {
        weatherData = new WeatherData("Tallinn", "metric");
    }

    @Test
    public void testCurrentTemperatureIsNotNull() {
        assertNotNull(weatherData.getCurrentTemperature());
    }

    @Test
    public void testMaxTemperatureIsNotNull() {
        assertNotNull(weatherData.getMaxTemperatures());
    }

    @Test
    public void testMinTemperatureIsNotNull() {
        assertNotNull(weatherData.getMinTemperatures());
    }

    @Test
    public void testTemperatureIsNotSmallerThanZeroKelvin() {
        assertNotEquals(1, new BigDecimal("0").compareTo(weatherData.getCurrentTemperature().add(new BigDecimal("273.15"))));
    }

    @Test
    public void testMaximumTemperatureIsNeverEqualToMinimumTemperature() {
        double[] maximum = weatherData.getMaxTemperatures();
        double[] minimum = weatherData.getMinTemperatures();
        for (int i = 0; i < maximum.length; i++) assertNotEquals(maximum[i], minimum[i]);
    }

    @Test
    public void testMaximumTemperatureIsAlwaysBiggerThanMinimumTemperature() {
        double[] maximum = weatherData.getMaxTemperatures();
        double[] minimum = weatherData.getMinTemperatures();
        for (int i = 0; i < maximum.length; i++) assertTrue(maximum[i] > minimum[i]);
    }

    @Test
    public void testThreeDayForecastHasTwoValues() {
        Map forecast = weatherData.getThreeDayForecast(weatherData.getMaxTemperatures(), weatherData.getMinTemperatures());
        assertThat(forecast.size(), is(2));
    }

    @Test
    public void testThreeDayForecastIsNotNull() {
        Map forecast = weatherData.getThreeDayForecast(weatherData.getMaxTemperatures(), weatherData.getMinTemperatures());
        assertNotNull(forecast);
    }

    @Test
    public void testTimeFormatIsCorrect() {
        String time = weatherData.getTimeOfElement(0);
        assertTrue(time.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]"));
    }

    @Test
    public void testGetJsonResponseIsNotNull() throws IOException {
        assertNotNull(weatherData.getJsonResponse("Tallinn", "metric"));
    }

    @Test
    public void testThreeDayWeatherResponseIsNotEmpty() {
        assertNotEquals(0, weatherData.getThreeDayWeatherResponses().size());
    }

    @Test
    public void testThreeDayWeatherResponseHas24Elements() {
        assertEquals(24, weatherData.getThreeDayWeatherResponses().size());
    }

    @Test
    public void testThreeDayWeatherResponseFirstElementTimeIsMidnight() {
        DateFormat dateformat = DateFormat.getDateTimeInstance();
        dateformat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String datetime = dateformat.format(new Date(weatherData.getThreeDayWeatherResponses().get(0).getAsJsonObject().get("dt").getAsLong() * 1000));
        String time = datetime.substring(datetime.indexOf(" ") + 1);
        assertEquals("0:00:00", time);
    }

    @Test
    public void testThreeDayWeatherResponseLastElementTimeBeforeMidNight() {
        DateFormat dateformat = DateFormat.getDateTimeInstance();
        dateformat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String datetime = dateformat.format(new Date(weatherData.getThreeDayWeatherResponses().get(23).getAsJsonObject().get("dt").getAsLong() * 1000));
        String time = datetime.substring(datetime.indexOf(" ") + 1);
        assertEquals("21:00:00", time);
    }
}
