package AutoTests_1_2;

import org.junit.Test;

import static AutoTests_1_2.Country.EE;
import static AutoTests_1_2.MeasurementSystem.metric;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class CurrentWeatherRepositoryTest {

    @Test
    public void testIfWeatherRepositoryRespCityEqualsReqCity() {
        try {
            // given
            WeatherRequest request = new WeatherRequest("Tallinn", EE, metric);
            CurrentWeatherRepository repository = new CurrentWeatherRepository();
            // when
            CurrentWeatherReport report = repository.getCurrentWeather(request);
            // then (no null check!)
            assertEquals(report.cityName, request.cityName);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }
}
