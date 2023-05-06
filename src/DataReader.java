import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<Point> readCSV(String fileName) throws IOException {
        List<Point> points = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            Point point = new Point(values[values.length - 1]);
            for (int i = 0; i < values.length - 1; i++) {
                point.addVector(Double.parseDouble(values[i]));
            }
            points.add(point);
        }
        reader.close();
        return points;
    }
}

