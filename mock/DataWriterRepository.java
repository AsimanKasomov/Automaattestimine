package mock;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Asiman. Crested on 12.01.2018.
 */
class DataWriterRepository {
    private String outputDir = System.getProperty("user.dir") + "/resources/";

    void writeDataToFile(String fileName, String data) throws IOException {
        String outputFile = outputDir + fileName;
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
        bw.write(data);
        bw.close();
    }

    void writeForecastsToFile(List<WeatherForecast> forecasts) throws IOException {
        Map<String, List<WeatherForecast>> groupedList = forecasts.stream().collect(Collectors.groupingBy(WeatherForecast::getCity));
        for (Map.Entry<String, List<WeatherForecast>> group : groupedList.entrySet()){
            String fileName = group.getKey() + ".txt";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < group.getValue().size(); i++) {
                WeatherForecast element = group.getValue().get(i);
                if (i == 0) {
                    sb.append(element.toString()).append(" \n");
                } else {
                    sb.append(element.getDateString()).append(" ");
                    sb.append("min: ").append(String.valueOf(element.getMinTemp())).append(" ");
                    sb.append("max: ").append(String.valueOf(element.getMaxTemp())).append(" \n");
                }
            }
            writeDataToFile(fileName, sb.toString());
        }
    }
}