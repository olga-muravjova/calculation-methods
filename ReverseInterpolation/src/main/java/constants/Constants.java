package constants;

import java.util.function.Function;

import static java.lang.Math.cos;

/**
 * Created by Ольга on 21.09.2016.
 */
public class Constants {

    public static final Function<Double, Double> FUNC = (Double x) -> x * x * x;
    //Math.pow(x,1.0/3);
//    public static final Function<Double, Double> RUNGE_FUNC = (Double x) -> 1 / (1 + 25 * x * x);
////    public static final Function<Double, Double> VARIANT10_FUNC = (Double x) -> sin(x) + x * x;
//    public static final Function<Double, Double> VARIANT10_FUNC = (Double x) -> cos(x) + 2 * x;
}
