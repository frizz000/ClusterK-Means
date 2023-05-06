import java.util.ArrayList;
import java.util.List;

public class KMeans {
    private final List<Point> points;
    private final List<Cluster> clusters;
    private final int k;

    public KMeans(List<Point> points, int k) {
        this.points = points;
        this.clusters = new ArrayList<>();
        this.k = k;
    }

    public void initClusters() {
        for (int i = 0; i < k; i++) {
            clusters.add(new Cluster("Cluster " + (i + 1), points.get(i)));
        }
    }

    public void cluster() {
        int iteration = 0;
        boolean changed = true;
        while (changed) {
            System.out.println("Iteration: " + ++iteration);
            for (Cluster cluster : clusters) {
                cluster.clearCluster();
            }

            for (Point point : points) {
                Cluster closestCluster = findClosestCluster(point);
                closestCluster.addPoint(point);
            }

            changed = false;
            for (Cluster cluster : clusters) {
                if (cluster.isCentroidChanged()) {
                    changed = true;
                }
            }
        }
    }


    public Cluster findClosestCluster(Point point) {
        Cluster closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Cluster cluster : clusters) {
            double distance = cluster.calcEucDistance(point, cluster.centroid);
            if (distance < minDistance) {
                minDistance = distance;
                closest = cluster;
            }
        }
        return closest;
    }

    public void printClusters() {
        for (Cluster cluster : clusters) {
            System.out.println(cluster);
            System.out.println("Centroid: " + cluster.centroid);
            System.out.println("Points: ");
            for (Point point : cluster.points) {
                System.out.println(point);
            }
            System.out.println("SSE: " + cluster.calcE());
            System.out.println("-----");
        }
    }
}
