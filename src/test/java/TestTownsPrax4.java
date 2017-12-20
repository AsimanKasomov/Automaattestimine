import data.WeatherData;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Asiman on 05.11.2017.
 */
public class TestTownsPrax4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/input.txt"));
        String town;
        while ((town = reader.readLine()) != null) {
            new TestTownsPrax4().testTown(town);
        }
        System.out.println("\nManual input. Type 'exit' to exit.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter a town name: ");
            String input = scanner.nextLine();
            if (input.matches("exit|quit|break|close|e|q|b|c")) break;
            new TestTownsPrax4().testTown(input);
        }
        scanner.close();
    }

    private void testTown(String town) throws IOException {
        WeatherData weatherData = new WeatherData(town, "metric");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/output.txt", true)));
        String output = town + ": " + weatherData.getCurrentTemperature() + " degrees.";
        System.out.println(output);
        out.println(output);
        out.close();
    }
}
