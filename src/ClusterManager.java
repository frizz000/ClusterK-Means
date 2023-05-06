import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClusterManager {
    private final List<Point> points;
    public final List<Cluster> clusters;
    private final int k;

    public ClusterManager(List<Point> points, int k) {
        this.points = points;
        this.k = k;
        clusters = new ArrayList<>();
    }

    public void initializeClusters() {
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            Point centroid = points.get(random.nextInt(points.size()));
            Cluster cluster = new Cluster("Cluster " + (i+1), centroid);
            clusters.add(cluster);
        }
    }

    public void assignPointsToClusters() {
        for (Cluster cluster : clusters) {
            cluster.clearCluster();
        }

        for (Point point : points) {
            Cluster closestCluster = null;
            double closestDistance = Double.MAX_VALUE;
            for (Cluster cluster : clusters) {
                double distance = cluster.calcEucDistance(point, cluster.centroid);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestCluster = cluster;
                }
            }
            assert closestCluster != null;
            closestCluster.addPoint(point);
        }
    }

    public void recalculateCentroids() {
        for (Cluster cluster : clusters) {
            cluster.calcCentroid(points.get(0).label);
        }
    }

    public boolean isConverged() {
        for (Cluster cluster : clusters) {
            if (cluster.isClusterChanged()) {
                return false;
            }
        }
        return true;
    }

    public void run() {
        initializeClusters();
        assignPointsToClusters();

        int maxIterations = 100;
        for (int i = 0; i < maxIterations; i++) {
            recalculateCentroids();
            assignPointsToClusters();
            if (isConverged()) {
                break;
            }
        }

        for (Cluster cluster : clusters) {
            System.out.println(cluster);
        }
    }
}
