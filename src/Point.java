import java.util.ArrayList;
import java.util.List;

public class Point {
    public final List<Double> vectors = new ArrayList<>();
    public String label;

    public Point(String label){
        this.label = label;
    }

    public void addVector(Double vector){
        vectors.add(vector);
    }

    public List<Double> getVectors(){
        return vectors;
    }

    @Override
    public String toString() {
        return "Point{" +
                "vectors=" + vectors +
                '}';
    }
}
