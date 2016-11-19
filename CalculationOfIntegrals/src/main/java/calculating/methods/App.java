package calculating.methods;

import calculating.methods.composite.formuls.Rectangle;
import calculating.methods.composite.formuls.Simpson;
import calculating.methods.composite.formuls.Trapezium;

import java.io.IOException;

import static java.lang.Math.abs;

public class App {
    public static void main(String[] args) throws IOException {
        //get interval
        final String segmentStartString = "Enter segment start:\n";
        final String segmentEndString = "Enter segment end (more than segment start):\n";
        Double segmentStart = ConsoleWriter.getDouble(segmentStartString);
        Double segmentEnd = ConsoleWriter.getLimitedDouble(segmentEndString, segmentStart);

        //get number of nodes
        final String numberOfSegmentsString = "Enter number of nodes(more than 0)\n";
        int numberOfSegments = ConsoleWriter.getInt(numberOfSegmentsString);

        //real integral calculation
        final String realIntegralValue = "real integral value: ";
        Double realValue = Constants.FUNC_INTEGRAL.apply(segmentEnd) - Constants.FUNC_INTEGRAL.apply(segmentStart);
        ConsoleWriter.printDouble(realIntegralValue, realValue);

        //rectangle formula
        Rectangle rectangleFormula = new Rectangle(segmentStart, segmentEnd, numberOfSegments, Constants.FUNC);
        final String rectangleIntegralValue = "rectangle integral value: ";
        Double rectangleValue = rectangleFormula.calculateIntegral();
        ConsoleWriter.printDouble(rectangleIntegralValue, rectangleValue);
        final String accuracyString = "accuracy: ";
        ConsoleWriter.printDouble(accuracyString, abs(realValue - rectangleValue));

        //trapezium formula
        Trapezium trapeziumFormula = new Trapezium(segmentStart, segmentEnd, numberOfSegments, Constants.FUNC);
        final String trapeziumIntegralValue = "trapezium integral value: ";
        Double trapeziumValue = trapeziumFormula.calculateIntegral();
        ConsoleWriter.printDouble(trapeziumIntegralValue, trapeziumValue);
        ConsoleWriter.printDouble(accuracyString, abs(realValue - trapeziumValue));

        //simpson formula
        Simpson simpsonFormula = new Simpson(segmentStart, segmentEnd, numberOfSegments, Constants.FUNC);
        final String simpsonIntegralValue = "simpson integral value: ";
        Double simpsonValue = simpsonFormula.calculateIntegral();
        ConsoleWriter.printDouble(simpsonIntegralValue, simpsonValue);
        ConsoleWriter.printDouble(accuracyString, abs(realValue - simpsonValue));
    }
}
