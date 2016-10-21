package controllers;

import model.Node;
import model.TypeOfSegment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.nextDown;

/**
 * Created by Ольга on 21.09.2016.
 */
public class NodesCreator {

    private double segmentStart;
    private double segmentEnd;
    private Function<Double, Double> func;
    private int numberOfNodes;
    private Double step;

    public NodesCreator(double segmentStart, double segmentEnd,
                        Function<Double, Double> func, int numberOfNodes) {
        this.segmentStart = segmentStart;
        this.segmentEnd = segmentEnd;
        this.func = func;
        this.numberOfNodes = numberOfNodes;
        step = (segmentEnd - segmentStart) / (numberOfNodes - 1);
    }

    public Double getStep() {
        return step;
    }

    public TypeOfSegment determineTypeOfSegment(Double x, int polynomDegree) {
        if (x >= segmentStart && x <= (segmentStart + step)) {
            return TypeOfSegment.BEGIN;
        }
        if (x >= (segmentEnd - step) && x <= segmentEnd) {
            return TypeOfSegment.END;
        }
        if (x >= (segmentStart + step * nextDown((polynomDegree + 1) / 2))
                && x <= (segmentEnd - step * nextDown((polynomDegree + 1) / 2))) {
            return TypeOfSegment.MIDDLE;
        }
        return TypeOfSegment.INCORRECT;
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
}