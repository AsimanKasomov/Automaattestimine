package AutoTests_1_2;

class CurrentWeatherReport {
    String cityName;
    private Country country;
    private MeasurementSystem system;

    CurrentWeatherReport(WeatherRequest request) {
        this.cityName = request.getCityName();
        this.country = request.getCountry();
        this.system = request.getSystem();
    }

    String getReport() {
        return "Reporting urrent weather in " + country + " in the town named " + cityName + " in " + system + ".";
    }
}
