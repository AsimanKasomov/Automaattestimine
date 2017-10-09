package AutoTests_1_2;

class WeatherRequest {
    String cityName;
    private Country country;
    private MeasurementSystem system;

    WeatherRequest(String city, Country country, MeasurementSystem system) {
        this.cityName = city;
        this.country = country;
        this.system = system;
    }

    String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public MeasurementSystem getSystem() {
        return system;
    }

    public void setSystem(MeasurementSystem system) {
        this.system = system;
    }
}
