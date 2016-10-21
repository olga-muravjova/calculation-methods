package controller;

import model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Ольга on 21.09.2016.
 */
public class NodesCreator {

    private double segmentStart;
    private double segmentEnd;
    private Function<Double, Double> func;
    private int numberOfNodes;

    public NodesCreator(double segmentStart, double segmentEnd,
                        Function<Double, Double> func, int numberOfNodes) {
        this.segmentStart = segmentStart;
        this.segmentEnd = segmentEnd;
        this.func = func;
        this.numberOfNodes = numberOfNodes;
    }

    public ArrayList<Node> getTableValues() {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            Double x = getNextValue(i);
            nodes.add(new Node(x, func.apply(x)));
        }
        return nodes;
    }

    private Double getNextValue(int number) {
        return segmentStart + number *
                (segmentEnd - segmentStart) / (numberOfNodes - 1);
    }

    public ArrayList<Node> reverseNodes(ArrayList<Node> nodes) {
        ArrayList<Node> reversedNodes = new ArrayList<>();
         for (Node node : nodes) {
            reversedNodes.add(new Node(node.getY(), node.getX()));
        }
        return reversedNodes;
    }

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
