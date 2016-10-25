package start.point;

import constants.Constants;
import controller.NodesCreator;
import controller.NonLinearEquationSolver;
import controller.Sorter;
import controller.interpolators.LagrangeInterpolator;
import model.Node;
import view.ConsoleWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

import static java.lang.StrictMath.abs;
import static view.Table.createGUI;


public class App {
    public static void main(String[] args) throws IOException {
        Function<Double, Double> func = Constants.FUNC;
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

        //get f
        final String askXString = "Enter f:\n";
        Double f = ConsoleWriter.getDouble(askXString);

        //get degree of polynom
        final String polynomDegreeString = "Enter polynom degree(more than 0 and less than " + numberOfNodes + ")\n";
        int polynomDegree = ConsoleWriter.getLimitedInt(polynomDegreeString, numberOfNodes);

        //reverse table
        ArrayList<Node> reversedNodes = nodesCreator.reverseNodes(nodes);

        //print sorted table
        final ArrayList<Node> sortedNodes = Sorter.sortListOfNodes(reversedNodes, f);
        //final String sortedTableName = "Sorted Nodes";
        //create table farme
        //javax.swing.SwingUtilities.invokeLater(() -> createGUI(sortedNodes, sortedTableName));

        //interpolateByLagrange by Lagrange
        //final ArrayList<Node> sortedNodesByF = Sorter.sortListOfNodesByF(reversedNodes, f);
        LagrangeInterpolator reversedLagrangeInterpolator = new LagrangeInterpolator(sortedNodes, polynomDegree);
        Double lagrangeResult = reversedLagrangeInterpolator.interpolate(f);
        //print result
        final String lagrangeResultString = "First method result: ";
        ConsoleWriter.printDouble(lagrangeResultString, lagrangeResult);
        //print accuracy
        final String accuracyString = "Accuracy:";
        ConsoleWriter.printDouble(accuracyString, abs(func.apply(lagrangeResult) - f));

        //second method, solving non-linear equation
        LagrangeInterpolator lagrangeInterpolator = new LagrangeInterpolator(Sorter.sortListOfNodesByF(0nodes, f), polynomDegree);
        NonLinearEquationSolver nonLinearEquationSolver =
                new NonLinearEquationSolver(segmentStart, segmentEnd, lagrangeInterpolator, f);
        Double result = nonLinearEquationSolver.solveEquation();
        //print result
        final String resultString = "Second method result: ";
        ConsoleWriter.printDouble(resultString, result);
        //print accuracy
        ConsoleWriter.printDouble(accuracyString, abs(func.apply(result) - f));
    }
}
