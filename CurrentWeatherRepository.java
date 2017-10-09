package AutoTests_1_2;

class CurrentWeatherRepository {
    CurrentWeatherReport getCurrentWeather(WeatherRequest request) {
        return new CurrentWeatherReport(request);
    }
}
