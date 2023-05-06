import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java KMeans <csv_file> <k_value>");
            return;
        }

        String fileName = args[0];
        int k = Integer.parseInt(args[1]);

        try {
            List<Point> points = DataReader.readCSV(fileName);
            KMeans kMeans = new KMeans(points, k);
            kMeans.initClusters();
            kMeans.cluster();
            kMeans.printClusters();
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid k value or CSV file format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

