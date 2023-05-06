import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Cluster {
    public String name;
    public Point centroid;
    public List<Point> points = new ArrayList<>();
    public double E;

    public Cluster(String name, Point centroid) {
        this.name = name;
        this.centroid = centroid;
    }

    public void calcCentroid() {
        String mostCommonLabel = findMostCommonLabel();
        Point newCentroid = new Point(mostCommonLabel);
        boolean end = false;
        int j = 0;
        int numVectors = points.get(0).getVectors().size();
        while (!end) {
            double centroidVector = 0;
            for (Point point : points) {
                List<Double> vectors = point.getVectors();
                if (vectors.size() > j) {
                    centroidVector += vectors.get(j);
                }
            }
            centroidVector /= points.size();
            newCentroid.addVector(centroidVector);
            j++;
            if (j == numVectors) {
                end = true;
            }
        }
        centroid = newCentroid;
    }

    private String findMostCommonLabel() {
        Map<String, Integer> labelCount = new HashMap<>();
        for (Point point : points) {
            labelCount.put(point.label, labelCount.getOrDefault(point.label, 0) + 1);
        }

        String mostCommonLabel = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : labelCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommonLabel = entry.getKey();
            }
        }

        return mostCommonLabel;
    }

    public void addPoint(Point point){
        points.add(point);
    }

    public void clearCluster(){
        points = new LinkedList<>();
    }

    public boolean isClusterChanged(){
        double newE = calcE();
        return E != newE;
    }

    public double calcE(){
        double allDistances = 0;
        for(Point point : points){
            allDistances += calcEucDistance(point, centroid);
        }
        return allDistances / points.size();
    }

    public double calcEucDistance(Point a, Point b){
        double distance = 0;
        for(int i = 0; i < a.getVectors().size(); i++){
            distance += Math.pow((a.getVectors().get(i) - b.getVectors().get(i)),2);
        }
        return Math.sqrt(distance);
    }
    public boolean isCentroidChanged() {
        Point newCentroid = calcNewCentroid();
        boolean changed = !newCentroid.vectors.equals(centroid.vectors);
        centroid = newCentroid;
        E = calcE();
        return changed;
    }

    private Point calcNewCentroid() {
        String mostCommonLabel = findMostCommonLabel();
        Point newCentroid = new Point(mostCommonLabel);
        boolean end = false;
        int j = 0;
        int numVectors = points.get(0).getVectors().size();
        while (!end) {
            double centroidVector = 0;
            for (Point point : points) {
                List<Double> vectors = point.getVectors();
                if (vectors.size() > j) {
                    centroidVector += vectors.get(j);
                }
            }
            centroidVector /= points.size();
            newCentroid.addVector(centroidVector);
            j++;
            if (j == numVectors) {
                end = true;
            }
        }
        return newCentroid;
    }

    @Override
    public String toString() {
        return "Cluster " + name;
    }
}
