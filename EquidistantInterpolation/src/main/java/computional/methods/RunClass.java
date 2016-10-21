package computional.methods;

import controllers.Interpolator;
import controllers.NodesCreator;
import model.Node;
import model.TypeOfSegment;
import view.ConsoleWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

import static java.lang.Math.nextDown;
import static java.lang.StrictMath.abs;
import static view.Table.createGUI;

/**
 * Created by Ольга on 02.10.2016.
 */
public class RunClass {

    static void runApp(Function<Double, Double> func) {
        try {
            run(func);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void run(Function<Double, Double> func) throws IOException {
        //get interval
        final String segmentStartString = "Enter segment start:\n";
        final String segmentEndString = "Enter segment end (more than segment start):\n";
        Double segmentStart = ConsoleWriter.getDouble(segmentStartString);
        Double segmentEnd = ConsoleWriter.getLimitedDouble(segmentEndString, segmentStart);

        //get number of nodes
        final String numberOfNodesString = "Enter number of nodes(more than 0)\n";
        int numberOfNodes = ConsoleWriter.getInt(numberOfNodesString);

        //print table
        NodesCreator nodesCreator = new NodesCreator(segmentStart,
                segmentEnd, func, numberOfNodes);
        final ArrayList<Node> nodes = nodesCreator.getTableValues();
        final String tableName = "Nodes";
        //create table farme
        javax.swing.SwingUtilities.invokeLater(() -> createGUI(nodes, tableName));

        //get degree of polynom
        final String polynomDegreeString = "Enter polynom degree(more than 0 and less than " + numberOfNodes + ")\n";
        int polynomDegree = ConsoleWriter.getLimitedInt(polynomDegreeString, numberOfNodes);

        //create finite differences table
        Interpolator interpolator = new Interpolator(nodes, polynomDegree);
        interpolator.createFiniteDifferenceTable();

        while (true) {

            Double step =nodesCreator.getStep();
            //get x from diapason
            final String askXString = "\nEnter x in" +
                    //[a, a + h]
                    "\n[" + segmentStart + ", " + (segmentStart + step) + "] or " +
                    //[b - h, b]
                    "\n[" + (segmentEnd - step) + ", " + segmentEnd + "] or " +
                    //[a + h*((n+1)/2), b - h*((n+1)/2)]
                    "\n[" + (segmentStart + step * nextDown((polynomDegree + 1) / 2))
                    + ", " + (segmentEnd - step * nextDown((polynomDegree + 1) / 2)) + "]\n";
            Double x = ConsoleWriter.getDouble(askXString);

            //check x
            TypeOfSegment typeOfSegment = nodesCreator.determineTypeOfSegment(x, polynomDegree);
            if (typeOfSegment == TypeOfSegment.INCORRECT) {
                final String incorrectXString = "Incorrect x";
                ConsoleWriter.printString(incorrectXString);
                continue;
            }
            Double result;
            switch (typeOfSegment) {
                case BEGIN:
                    result = interpolator.interpolateInBeginning(x,step);
                    final String beginSegmentX = "x is from the beginning";
                    ConsoleWriter.printString(beginSegmentX);
                    break;
                case END:
                    result = interpolator.interpolateInEnd(x,step);
                    final String endSegmentX = "x is from the ending";
                    ConsoleWriter.printString(endSegmentX);
                    break;
                case MIDDLE:
                    result = interpolator.interpolateInMiddle(x,step);
                    final String middleSegmentX = "x is from the middle";
                    ConsoleWriter.printString(middleSegmentX);
                    break;
                default:
                    throw new RuntimeException();
            }

            //print result
            final String lagrangeResultString = "Interpolation result: ";
            ConsoleWriter.printDouble(lagrangeResultString, result);
            //print accuracy
            final String accuracyString = "Accuracy:";
            ConsoleWriter.printDouble(accuracyString, abs(func.apply(x) - result));
        }
    }
}
