import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import data.WeatherData;
import java.io.*;
import java.util.Scanner;

import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * Created by Asiman on 02.12.2017.
 */
public class TestTownWriter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/input.txt"));
        String town;
        while ((town = reader.readLine()) != null) {
            new TestTownWriter().testTown(town);
        }
        System.out.println("\nManual input. Type 'exit' to exit.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter a town name: ");
            String input = scanner.nextLine();
            if (input.matches("exit|quit|break|close|e|q|b|c")) break;
            new TestTownWriter().testTown(input);
        }
        scanner.close();
    }

    private void testTown(String town) throws IOException {
        WeatherData weatherData = new WeatherData(town, "metric");
        PrintWriter out = new PrintWriter("src/" + town + ".txt", "UTF-8");
        JsonArray json = weatherData.getThreeDayWeatherResponses();
        double min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, min3 = Integer.MAX_VALUE;
        double max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int i = 0; i < 24; i++) {
            JsonObject obj = json.get(i).getAsJsonObject().get("main").getAsJsonObject();
            if (i < 8) {
                if (obj.get("temp_min").getAsDouble() < min1) min1 = obj.get("temp_min").getAsDouble();
                if (obj.get("temp_max").getAsDouble() > max1) max1 = obj.get("temp_max").getAsDouble();
            } else if (i < 16) {
                if (obj.get("temp_min").getAsDouble() < min2) min2 = obj.get("temp_min").getAsDouble();
                if (obj.get("temp_max").getAsDouble() > max2) max2 = obj.get("temp_max").getAsDouble();
            } else {
                if (obj.get("temp_min").getAsDouble() < min3) min3 = obj.get("temp_min").getAsDouble();
                if (obj.get("temp_max").getAsDouble() > max3) max3 = obj.get("temp_max").getAsDouble();
            }
        }
        String output = town + "\nCoordinates: " + weatherData.getCoordinates() + "\nCurrent: " +
                weatherData.getCurrentTemperature() + "\nDay 1: [" + min1 + ", " + max1 + "]\nDay 2: [" +
                min2 + ", " + max2 + "]\nDay 3: [" + min3 + ", " + max3 + "]\n";
        System.out.println(output);
        out.println(output);
        out.close();
    }

    @Test
    public void testWriteToFile() throws IOException {
        TestTownWriter townWriter;
        townWriter = mock(TestTownWriter.class);
        townWriter.testTown("Tallinn");
        verify(townWriter).testTown("Tallinn");
    }

    @Test
    public void testReadFromFile() throws IOException {
        // TODO: Mock reading from file
    }
}
