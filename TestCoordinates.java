import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.io.IOException;
import data.WeatherData;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Asiman on 23.10.2017.
 */
public class TestCoordinates {
    private WeatherData weatherData;

    @Before
    public void setup() throws IOException {
        weatherData = new WeatherData("Tallinn", "metric");
    }

    @Test
    public void testLongitudeIsNotBiggerThanMaximum() {
        assertEquals(-1, weatherData.getLongitude().compareTo(new BigDecimal("180")));
    }

    @Test
    public void testLongitudeIsNotSmallerThanMinimum() {
        assertEquals(1, weatherData.getLongitude().compareTo(new BigDecimal("-180")));
    }

    @Test
    public void testLatitudeIsNotBiggerThanMaximum() {
        assertEquals(-1, weatherData.getLatitude().compareTo(new BigDecimal("90")));
    }

    @Test
    public void testLatitudeIsNotSmallerThanMinimum() {
        assertEquals(1, weatherData.getLatitude().compareTo(new BigDecimal("-90")));
    }

    @Test
    public void testLongitudeIsNotNull() {
        assertNotNull(weatherData.getLongitude());
    }

    @Test
    public void testLatitudeIsNotNull() {
        assertNotNull(weatherData.getLatitude());
    }

    @Test
    public void testCoordinatesHaveLatitudeAndLongitude() {
        assertEquals(2, weatherData.getCoordinates().size());
    }

    @Test
    public void testCoordinatesAreNotNull() {
        for (BigDecimal coordinate: weatherData.getCoordinates()) assertNotNull(coordinate);
    }
}