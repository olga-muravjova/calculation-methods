package constants;

import java.util.ArrayList;
import java.util.function.Function;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Ольга on 22.10.2016.
 */
public class Constants {
    public static final Function<Double, Double> FUNC = (Double x) -> cos(x) + 2 * x;
    public static final String FUNC_NAME = "f(x) = cos(x) + 2*x";
    public static final Function<Double, Double> FUNC_DIFF_FIRST = (Double x) -> -sin(x) + 2;
    public static final Function<Double, Double> FUNC_DIFF_SECOND = (Double x) -> -cos(x);
    public static final Double SEGMENT_START = 0.0;
    public static final Double SEGMENT_END = 1.0;
    public static final int NUMBER_OF_NODES = 11;
}
