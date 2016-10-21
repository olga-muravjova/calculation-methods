package contollers;

import model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Ольга on 22.10.2016.
 */
public class NodeCreator {

    private double segmentStart;
    private double segmentEnd;
    private Function<Double, Double> func;
    private Function<Double, Double> funcDiffFirst;
    private Function<Double, Double> funcDiffSecond;
    private int numberOfNodes;

    public NodeCreator(double segmentStart, double segmentEnd,
                       Function<Double, Double> func,
                       Function<Double, Double> funcDiffFirst,
                       Function<Double, Double> funcDiffSecond, int numberOfNodes) {
        this.segmentStart = segmentStart;
        this.segmentEnd = segmentEnd;
        this.func = func;
        this.funcDiffFirst = funcDiffFirst;
        this.funcDiffSecond = funcDiffSecond;
        this.numberOfNodes = numberOfNodes;
    }

    public Double getStep() {
        return (segmentEnd - segmentStart) / (numberOfNodes - 1);
    }

    public ArrayList<Node> createNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            Double x = getNextValue(i);
            nodes.add(new Node(x, func.apply(x), funcDiffFirst.apply(x), funcDiffSecond.apply(x)));
        }
        return nodes;
    }

    private Double getNextValue(int number) {
        return segmentStart + number *
                (segmentEnd - segmentStart) / (numberOfNodes - 1);
    }

    //private Double getFirsrDerivation(Double x)

//    public List<Double> getListOfValues(int numberOfNodes) {
//        ArrayList<Double> nodes = new ArrayList<>();
//        for (int i = 0; i < numberOfNodes; i++) {
//            Double x = getNextValue(i, numberOfNodes);
//            nodes.add(x);
//        }
//        return nodes;
//    }
//
//    private Double getNextValue(int number, int numberOfNodes) {
//        return segmentStart + number *
//                (segmentEnd - segmentStart) / (numberOfNodes - 1);
//    }
}
