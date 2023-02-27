import java.util.Arrays;

public class Path {
    double[] costs;
    int[] paths;

    Path(double[] costs, int[] paths){
        this.costs = costs;
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "Costs: " + Arrays.toString(costs) + "\n"
                + "Paths: " + Arrays.toString(paths);
    }
}
