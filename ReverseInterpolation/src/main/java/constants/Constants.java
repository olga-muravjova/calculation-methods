package constants;

import java.util.function.Function;

import static java.lang.Math.cos;

/**
 * Created by Ольга on 21.09.2016.
 */
public class Constants {

    //public static final Function<Double, Double> FUNC = (Double x) -> x * x * x;
    public static final Function<Double, Double> FUNC = (Double x) -> cos(x) + 2 * x;

}
