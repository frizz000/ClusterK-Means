import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java Main <data file> <k>");
            return;
        }

        String filePath = args[0];
        int k = Integer.parseInt(args[1]);

        DataReader dataReader = new DataReader();
        dataReader.readData(filePath);
        List<Point> points = dataReader.points;

        ClusterManager clusterManager = new ClusterManager(points, k);
        double prevE = 0;
        int iteration = 0;

        while (true) {
            clusterManager.run();
            double E = clusterManager.clusters.stream().mapToDouble(Cluster::calcE).sum();
            System.out.println("Iteration: " + iteration + ", E: " + E);
            if (E == prevE) {
                break;
            }
            prevE = E;
            iteration++;

            if (iteration == 100) {
                break;
            }

        }
    }
}
