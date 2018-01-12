package mock;

import java.io.*;
import java.util.List;

/**
 * Created by Asiman. Created on 12.01.2018.
 */
public class Main {
    public static void main(String[] args) throws IOException{
        WeatherForecastService weatherForecastService = new WeatherForecastService();
        RequestMaker requestMaker = new RequestMaker();
        DataReaderRepository dataReaderRepository = new DataReaderRepository();
        List<String> places = dataReaderRepository.getDataByUserInput();
        List<WeatherForecast> forecasts = weatherForecastService.getForecastsForPlaces(places);
        DataWriterRepository dataWriterRepository = new DataWriterRepository();
        dataWriterRepository.writeForecastsToFile(forecasts);
    }
}