package calculating.methods.composite.formuls;

import java.util.function.Function;

/**
 * Created by Ольга on 19.11.2016.
 */
public class Simpson {

    private Double segmentStart;
    private Double segmentEnd;
    private int numberOfSegments;
    private Function<Double, Double> func;

    public Simpson(Double segmentStart, Double segmentEnd, int numberOfSegments, Function<Double, Double> func) {
        this.segmentStart = segmentStart;
        this.segmentEnd = segmentEnd;
        this.numberOfSegments = numberOfSegments;
        this.func = func;
    }

    public Double calculateIntegral() {
        Double result = 0.0;
        for (int k = 0; k < numberOfSegments; k++) {
            result += getStep() / 6 * (func.apply(getZk(k))
                    + 4 * func.apply(getZk(k) + getStep() / 2)
                    + func.apply(getZk(k + 1)));
        }
        return result;
    }

    private Double getStep() {
        return (segmentEnd - segmentStart) / numberOfSegments;
    }

    private Double getZk(int k) {
        return segmentStart + getStep() * k;
    }
}
