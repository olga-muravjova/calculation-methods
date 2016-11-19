package calculating.methods;

import java.util.function.Function;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


/**
 * Created by Ольга on 19.11.2016.
 */
public class Constants {

    public static final Function<Double, Double> FUNC = Math::sin;
    public static final Function<Double, Double> FUNC_INTEGRAL = (Double x) -> -cos(x);

//    public static final Function<Double, Double> FUNC = (Double x) -> x*x*x;
//    public static final Function<Double, Double> FUNC_INTEGRAL = (Double x) -> x*x*x*x/4;

    //    public static final Function<Double, Double> FUNC = (Double x) -> x+1;
//    public static final Function<Double, Double> FUNC_INTEGRAL = (Double x) -> x*x/2 + x;
}
