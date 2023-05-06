import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public List<Point> points;

    public void readData(String filePath) throws IOException {
        points = new ArrayList<>();

        Files.readAllLines(Paths.get(filePath)).forEach(line -> {
            String[] lineData = line.strip().split(",");
            String label = lineData[lineData.length - 1];
            Point point = new Point(label);
            for (int i = 0; i < lineData.length - 1; i++) {
                point.addVector(Double.parseDouble(lineData[i]));
            }
            points.add(point);
        });
    }
}

