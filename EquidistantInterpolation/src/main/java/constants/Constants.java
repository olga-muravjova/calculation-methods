package constants;

import java.util.function.Function;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Ольга on 21.09.2016.
 */
public class Constants {

//    public static final Function<Double, Double> VARIANT10_FUNC = (Double x) -> x * x * x + 2 * x - 1;

   public static final Function<Double, Double> VARIANT10_FUNC = (Double x) -> sin(x) + x * x / 2;
}
